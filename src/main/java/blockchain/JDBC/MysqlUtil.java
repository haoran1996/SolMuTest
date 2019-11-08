package blockchain.JDBC;




import blockchain.CMDRedirect.Main;
import blockchain.MutationUtils.AllInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static blockchain.CMDRedirect.Main.pwd;
import static blockchain.CMDRedirect.Main.username;

public class MysqlUtil {


    public static void CreateTable(String dbname, String tablename){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE `");
        sb.append(tablename);
        sb.append("` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `MutationInfo` varchar(200) DEFAULT NULL,\n" +
                "  `TestFileName` varchar(200) DEFAULT NULL,\n" +
                "  `BranchCoverage` double DEFAULT NULL,\n" +
                "  `CoveredLine` mediumtext,\n" +
                "  `TestCondition` varchar(200) DEFAULT NULL,\n" +
                "  `TimeCost` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n");
        String creatsql = sb.toString();
        try {
            Connection conn = null;
            Statement stmt = null;
            conn = JDBCutil.gen_connct(dbname, username, pwd);
            //执行创建表
            stmt = conn.createStatement();
            if(0 == stmt.executeLargeUpdate(creatsql))
            {
                System.out.println("成功创建表"+tablename);
            }
            else
            {
                System.out.println("创建表失败！");
            }
        } catch (SQLException e) {
            System.out.println("表已存在。");
        }
    }

    public static String getSql_statement(String TableName){
        StringBuffer sb = new StringBuffer();
        sb.append("insert into `");
        sb.append(TableName);
        sb.append("`(MutationInfo,TestFileName,BranchCoverage,Coveredline,TestCondition,TimeCost) values (?,?,?,?,?,?)"); // sql写入命令
        return sb.toString();
    }

    public static void InsertTestFile(String MutationInfo, String TestFileName, double BranchCoverage, String CoveredLine, String TestCondition, long TimeCost, String sql_statement, String dbname) throws SQLException {
        try {
            Connection connection = JDBCutil.gen_connct(dbname, username, pwd);
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
            statement.setLong(6,TimeCost);
            statement.execute();
            connection.commit();
            System.out.println("写入成功！");
//            JDBCutil.close(connection);
        } catch (SQLException e) {
            System.out.println("写入失败！");
            e.printStackTrace();
        }
    }

    public static List<String> getTestInfoFromTable(String tablename, String dbname){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<String> testinfolist = new ArrayList<>();
        try {
            //1.获取Connection
            connection = JDBCutil.gen_connct(dbname, username, pwd);
            //2.获取Statement
            statement = connection.createStatement();
            //3.准备Sql
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM ");
            sb.append(tablename);
            sb.append(";");
            String sql = sb.toString();
            //4.‘执行查询，得到ResultSet
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

    public static List<AllInfo> GetAllInfoByMutationInfo(String mutationinfo, String dbname) {
        //获取id=2的customers数据表的记录，并打印
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        AllInfo allinfo = null;
        List<AllInfo> allinfolist = new ArrayList<>();
        try {
            //1.获取Connection
            connection = JDBCutil.gen_connct(dbname, username, pwd);
            //2.获取Statement
            statement = connection.createStatement();
            //3.准备Sql
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM ");
            sb.append(Main.TableName);
            sb.append(" where MutationInfo = \'");
            sb.append(mutationinfo);
            sb.append("\'");
            String sql = sb.toString();
            sb.setLength(0);
            //4.执行查询，得到ResultSet
            rs = statement.executeQuery(sql);
            //5.处理ResultSet
            while (rs.next()) {
                allinfo = new AllInfo(rs.getInt("id"), rs.getString("MutationInfo"), rs.getString("TestFileName"),
                        rs.getDouble("BranchCoverage"), rs.getString("CoveredLine"), rs.getString("TestCondition"), rs.getInt("TimeCost"));
                allinfolist.add(allinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return allinfolist;
        }
    }
}
