package blockchain.CMDRedirect;



import blockchain.JDBC.BCdata;
import blockchain.Jnotify.FileNotify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static blockchain.CMDRedirect.CMDUtils.KillNodeProcess;
import static blockchain.FileUtils.FileUtil.deleteDir;
import static blockchain.FileUtils.FileUtil.deleteFile;
import static blockchain.JDBC.MysqlUtil.getSql_statement;
import static blockchain.JDBC.MysqlUtil.getTestInfoFromTable;


import blockchain.FileUtils.*;
import blockchain.MutationUtils.Generation;
import blockchain.MutationUtils.SetMutators;

public class Main {
    public static String ProjectPath;//待测项目路径
    public static String MutationProjectPath;//零时项目路径
    public static String TableName;
    public static String sql_statement;
    public static long begintime = 0;
    //contracts文件夹路径
    public static String OriginalContractsPath;
    public static String MutationContractsPath;
    //变异体路径
    public static String MutationDir;
    //本地链创建命令
    public static String TestrpcRunCommand;

    //临时
    public static String MutationInfo;
    public static String MutationOperator;
    public static int MutatedLine;
    public static String MutatedFileContent;
    public static String TestFileName;
    public static String TestFileContent;
    public static double BranchCoverage = 0;
    public static String Coveredline;
    public static String TestCondition;

    public static List<String> TestInfo;

    public static List<BCdata> listbcdata = new ArrayList<>();
    public static long endtime = 0;
    public static long time = 0;




    public static void main(String[] args){
        ProjectPath = "E:\\blockchain\\MutationTest\\tempTC\\SkinCoin-token";
        TableName = "test2";
        List<String> mutatorlist = setmutators();
        TestrpcRunCommand = "testrpc-sc -u 0 -u 1 -u 2 --account=\"0x6a936b41ff2044aa470c693b1cba7af1f7ea10139d9d65dc403518df037515d5,1000000000000000000000000\" --account=\"0x5ebd6d2fa870e35d48ee3f803dbbab34403aa42c67a3036dee721235a87cdd69,0\" --account=\"0x56383562a52a451683c2cae3c09b2c1b2a173ab0cdfb8526006fb0b8654d54c3,1000000000000000000000000\" --account=\"0x56383562a52a451683c2cae3c09b2c1f2a173ab0cdfb8526006fb0b8654d54c3,0\" --gasLimit 0xfffffffffff --port 8555";
//        generateMutation(ProjectPath,mutatorlist);//仅生成变异体
        runMutationTest(ProjectPath,mutatorlist);     //完整变异测试
    }

    /**
     * 选择变异算子
     * @return
     */
    public static List<String> setmutators(){
        List<String> mutatorlist = new ArrayList<>();
        mutatorlist.add("ChangeToEquality");
        mutatorlist.add("Replaceif(!Toif(");

        return mutatorlist;
    }
    /**
     * 运行变异测试
     * @param mutatorlist
     */
    public static void runMutationTest(String projectpath,List<String> mutatorlist){
        sql_statement = getSql_statement(TableName);
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(ProjectPath);
            sb.append("\\SolMuTest");
            MutationProjectPath = sb.toString();
            sb.setLength(0);
            sb.append(MutationProjectPath);
            sb.append("\\contracts");
            MutationContractsPath = sb.toString();

//            generateMutation(projectpath,mutatorlist);
            //每次用一个变异体替换原码
            replacesourcefile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 仅生成变异体
     * @param ProjectPath
     * @param mutatorlist
     */
    public static void generateMutation(String ProjectPath, List<String> mutatorlist){
        //创建零时文件夹
        CopyDir.makeMutationDir(ProjectPath);
        //生成变异体
        Generation.generate(MutationProjectPath,mutatorlist);
    }

    /**
     * 运行solidity-coverage前初始化
     */
    public static void init(){
        //关闭node进程
        KillNodeProcess();
        //删除coverage文件夹
        StringBuffer sb = new StringBuffer();
        sb.append(MutationProjectPath);
        sb.append("\\coverage");
        File dirfile = new File(sb.toString());
        deleteDir(dirfile);
    }

    public static void getResult(){
        try {
            //运行testrpc
            init();
            CMDRedirect.RunTestrpc(MutationProjectPath,TestrpcRunCommand);
            CMDRedirect.RunSolcov(MutationProjectPath);
            //判断solcov运行完毕
            FileNotify.notify(MutationProjectPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void replacesourcefile(){
        //初始化MutationContractsPath
        StringBuffer sb1 = new StringBuffer();
        sb1.append(ProjectPath);
        sb1.append("\\contracts");
        OriginalContractsPath = sb1.toString();
        //找到mutation文件夹
        sb1.setLength(0);
        sb1.append(MutationProjectPath);
        sb1.append("\\mutation");
        MutationDir = sb1.toString();
        //用mutationfile替换原solfile
        try {
            File mutationdir = new File(MutationDir);
            File[] mutations = mutationdir.listFiles();
            for(File mutation:mutations){
                File mutaioncontractsfile = new File(MutationContractsPath);
                FileUtil.deleteDir(mutaioncontractsfile);
                CopyDir.copy(OriginalContractsPath,MutationContractsPath);
                String mutationname = mutation.getName();
                String[] strings = mutationname.split("#");
                sb1.setLength(0);
                sb1.append(MutationContractsPath);
                sb1.append("\\");
                sb1.append(strings[0]);
                sb1.append(".sol");
                String solfilepath = sb1.toString();//原始solfile名
                deleteFile(solfilepath);
                CopyDir.fileCopy(mutation.getAbsolutePath(),solfilepath);
//                System.out.println(mutationname+"已经被复制到"+sb1.toString());
                Thread.sleep(2000);
                MutationInfo = mutationname;
                //每次放入一个testcase
                TestInfo = getTestInfoFromTable(TableName);
                runTestcases();//每次运行一个测试用例
                runAllTest();//直接运行测试
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 运行测试用例
     */
    public static void runTestcases(){
        StringBuffer sb = new StringBuffer();
        String testinfo;
        sb.append(MutationProjectPath);
        sb.append("\\testcases");
        try {
            List<File> testcaselist = FileUtil.getFileList(sb.toString());
            for(int i=0; i<testcaselist.size(); i++){
                TestFileName = testcaselist.get(i).getName();
                //生成testinfo
                sb.setLength(0);
                sb.append(MutationInfo);
                sb.append("##");
                sb.append(TestFileName);
                testinfo = sb.toString();
                //判断testinfo是否已存在,即是否重复
                if(!TestInfo.contains(testinfo)){
                    Thread.sleep(1000);
                    TestInfo.add(testinfo);
                    sb.setLength(0);
                    sb.append(MutationProjectPath);
                    sb.append("\\test");
                    //先删除test文件夹下的文件，再放入新testcase
                    FileUtil.delAllFile(sb.toString());
                    sb.append("\\");
                    sb.append(TestFileName);
                    CopyDir.fileCopy(testcaselist.get(i).getAbsolutePath(),sb.toString());
                    getResult();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runAllTest(){
        StringBuffer sb = new StringBuffer();
        String testinfo;
        sb.append(MutationInfo);
        sb.append("##");
        sb.append("All");
        testinfo = sb.toString();
        try {
            if(!TestInfo.contains(testinfo)){
                Thread.sleep(1000);
                TestInfo.add(testinfo);
                getResult();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

