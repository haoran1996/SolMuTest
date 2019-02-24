package blockchain.Jnotify;

import blockchain.CMDRedirect.CMDUtils;
import blockchain.CMDRedirect.Main;
import blockchain.ExtractUtils.GetTotalGasUsed;
import blockchain.ExtractUtils.ReadSolcovInfo;
import blockchain.JDBC.MysqlUtil;
import net.contentobjects.jnotify.JNotifyListener;

import java.sql.SQLException;

import static blockchain.CMDRedirect.Main.*;

public class Listener implements JNotifyListener{
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        System.out.println(wd);
        System.out.println(rootPath);
        print("renamed " + rootPath + " : " + oldName + " -> " + newName);
    }

    public void fileModified(int wd, String rootPath, String name) {
        print("modified " + rootPath + " : " + name);
    }

    public void fileDeleted(int wd, String rootPath, String name) {
        print("deleted " + rootPath + " : " + name);
        if(name.equals("scTopics")){
            try {
                Thread.sleep(2000);
                System.out.println("**********************");
                gain_result();
                FileNotify.Completed = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                CMDUtils.KillNodeProcess();
            }
        }
    }

    public void fileCreated(int wd, String rootPath, String name) {
        print("created " + rootPath + " : " + name);
    }

    void print(String msg) {
        System.err.println(msg);
    }

    public static void gain_result(){
        try {
            BranchCoverage = ReadSolcovInfo.GetTotalCoverage(MutationProjectPath);
            Coveredline = ReadSolcovInfo.getAllCoveredLines(MutationProjectPath);
            TestCondition = ReadSolcovInfo.GetTestCondition(MutationProjectPath);
            Thread.sleep(1000);
            System.out.println("运行完成,正在将本次测试结果写入数据库。。。");
            MysqlUtil.InsertTestFile(MutationInfo,TestFileName,
                 BranchCoverage,Coveredline,TestCondition,sql_statement);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
