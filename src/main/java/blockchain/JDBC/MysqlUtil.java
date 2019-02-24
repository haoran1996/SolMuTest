package blockchain.JDBC;




import blockchain.CMDRedirect.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUtil {

    public static String getSql_statement(String TableName){
        StringBuffer sb = new StringBuffer();
        sb.append("insert into `");
        sb.append(TableName);
        sb.append("`(MutationInfo,TestFileName,BranchCoverage,Coveredline,TestCondition) values (?,?,?,?,?)");
        return sb.toString();
    }

    public static void InsertTestFile(String MutationInfo, String TestFileName, double BranchCoverage, String CoveredLine, String TestCondition, String sql_statement) throws SQLException {
        try {
            Connection connection = JDBCutil.gen_connct("test", "root", "root");
            //JDBCutil.close(connection);
            // 不自动提交
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql_statement);
//            DecimalFormat df = new DecimalFormat("#.00");
            //插入
            statement.setString(1,MutationInfo);
            statement.setString(2,TestFileName);
            statement.setDouble(3,BranchCoverage);
            statement.setString(4,CoveredLine);
            statement.setString(5,TestCondition);
            statement.execute();
            connection.commit();
            System.out.println("写入成功！");
//            JDBCutil.close(connection);
        } catch (SQLException e) {
            System.out.println("写入失败！");
            e.printStackTrace();
        }
    }

    public static List<String> getTestInfoFromTable(String tablename){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<String> testinfolist = new ArrayList<>();
        try {
            //1.获取Connection
            connection = JDBCutil.gen_connct("test", "root", "root");
            //2.获取Statement
            statement = connection.createStatement();
            //3.准备Sql
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM ");
            sb.append(tablename);
            sb.append(";");
            String sql = sb.toString();
            //4.执行查询，得到ResultSet
            rs = statement.executeQuery(sql);
            //5.处理ResultSet
            while(rs.next()){
                    sb.setLength(0);
                    sb.append(rs.getString("MutationInfo"));
                    sb.append("##");
                    sb.append(rs.getString("TestFileName"));
                    testinfolist.add(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return testinfolist;
        }
    }

    public static BCdata GetBCdataByID(int id){
        //获取id=2的customers数据表的记录，并打印
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        BCdata bcdata = null;
        try {
            //1.获取Connection
            connection = JDBCutil.gen_connct("blockchain", "root", "12345");
            //2.获取Statement
            statement = connection.createStatement();
            //3.准备Sql
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM ");
            sb.append(Main.TableName);
            sb.append(" where id = ");
            sb.append(id);
            String sql = sb.toString();
            sb.setLength(0);
            //4.执行查询，得到ResultSet
            rs = statement.executeQuery(sql);
            //5.处理ResultSet
            while(rs.next()){
//            bcdata = new BCdata(rs.getInt("id"),rs.getString("data"),
//                    rs.getInt("tcnum"),rs.getLong("gas"),
//                    rs.getDouble("uncov"),rs.getLong("time"));
             }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return bcdata;
        }
    }

}
