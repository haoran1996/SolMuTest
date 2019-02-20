package blockchain.CMDRedirect;



import blockchain.JDBC.BCdata;
import blockchain.Jnotify.FileNotify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static blockchain.CMDRedirect.CMDUtils.KillNodeProcess;
import static blockchain.FileUtils.WriteUtil.deleteDir;
import static blockchain.JDBC.MysqlUtil.getSql_statement;

public class Main {
    public static String ProjectPath;
    public static String DirPath;
    public static String TableName;
    public static String sql_statement;
    public static long begintime = 0;
    public static String MutationContractsPath;
    public static String TestrpcRunCommand;

    //临时
    public static String MutationProjectPath;
    public static String MutatedFileName;
    public static String MutationOperator;
    public static int MutatedLine;
    public static String MutatedFileContent;
    public static String TestFileName;
    public static String TestFileContent;
    public static double BranchCoverage = 0;
    public static String Coveredline;
    public static int isMutantKilled;

    public static List<BCdata> listbcdata = new ArrayList<>();
    public static long endtime = 0;
    public static long time = 0;




    public static void main(String[] args){
        ProjectPath = "E:\\blockchain\\MutationTest\\tempTC\\SkinCoin-token";
        TableName = "test";
        TestrpcRunCommand = "testrpc-sc -u 0 -u 1 -u 2 --account=\"0x6a936b41ff2044aa470c693b1cba7af1f7ea10139d9d65dc403518df037515d5,1000000000000000000000000\" --account=\"0x5ebd6d2fa870e35d48ee3f803dbbab34403aa42c67a3036dee721235a87cdd69,0\" --account=\"0x56383562a52a451683c2cae3c09b2c1b2a173ab0cdfb8526006fb0b8654d54c3,1000000000000000000000000\" --account=\"0x56383562a52a451683c2cae3c09b2c1f2a173ab0cdfb8526006fb0b8654d54c3,0\" --gasLimit 0xfffffffffff --port 8555";
        runMutationTest();
    }

    public static void runMutationTest(){
        sql_statement = getSql_statement(TableName);
        //运行testrpc
        try {
            init();
            CMDRedirect.RunTestrpc(ProjectPath,TestrpcRunCommand);
            CMDRedirect.RunSolcov(ProjectPath);
            //判断solcov运行完毕
            FileNotify.notify(ProjectPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init(){
        //关闭node进程
        KillNodeProcess();
        //删除coverage文件夹
        StringBuffer sb = new StringBuffer();
        sb.append(ProjectPath);
        sb.append("\\coverage");
        File dirfile = new File(sb.toString());
        deleteDir(dirfile);
    }
//    public static void GenerateSingleTestfile(String ProjectPath, int TimeLimit, int Numlimit, String sql_statement){
//        try {
//            //确保Node进程关闭
//            KillNodeProcess();
//            //生成测试用例
//            TestCaseGeneration.run(ProjectPath , TimeLimit, Numlimit);
//            //运行solidity-coverage
//            CMDRedirect.RunTestrpc(ProjectPath);
//            CMDRedirect.RunSolcov(ProjectPath);
//            //TCNUM
//            Main.tcnum =  TestCaseGeneration.getNum_ti();
//            //获得gas及coverage,监听文件的删除来判断solidity-coverage是否运行完毕
//            FileNotify.notify(ProjectPath);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}

