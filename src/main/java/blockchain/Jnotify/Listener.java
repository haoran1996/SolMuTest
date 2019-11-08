package blockchain.Jnotify;

import blockchain.CMDRedirect.CMDUtils;
import blockchain.CMDRedirect.Main;
import blockchain.ExtractUtils.GetTotalGasUsed;
import blockchain.ExtractUtils.ReadSolcovInfo;
import blockchain.JDBC.MysqlUtil;
import net.contentobjects.jnotify.JNotifyListener;

import java.sql.SQLException;

import static blockchain.CMDRedirect.Main.*;
//该文件暂时作废
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
                Thread.sleep(0);
                System.out.println("**********************");
                FileNotify.Completed = true;
                gain_result();
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
            endtime = System.currentTimeMillis();
            TestCondition = ReadSolcovInfo.GetTestCondition(MutationProjectPath);
            if(Main.TestCondition.equals("Compilation failed.")){
                BranchCoverage = 0;
                Coveredline = null;
                TimeCost = endtime - begintime;
            }
            else{
                BranchCoverage = ReadSolcovInfo.GetTotalCoverage(MutationProjectPath);
                Coveredline = ReadSolcovInfo.getAllCoveredLines(MutationProjectPath);
                TimeCost = endtime - begintime;
            }
            Thread.sleep(0);
            System.out.println("运行完成,正在将本次测试结果写入数据库。。。");
            MysqlUtil.InsertTestFile(MutationInfo,TestFileName,
                 BranchCoverage,Coveredline,TestCondition, TimeCost, sql_statement,DBName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void gain_compilefailed_result(){
        try {
            BranchCoverage = 0;
            Coveredline = null;
            TestCondition = "Compilation failed.";
            Thread.sleep(0);
            System.out.println("运行完成,正在将本次测试结果写入数据库。。。");
            TimeCost = System.currentTimeMillis() - begintime;
            MysqlUtil.InsertTestFile(MutationInfo,TestFileName,
                    0,null, TestCondition, TimeCost, sql_statement,DBName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
