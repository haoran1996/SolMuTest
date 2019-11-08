package blockchain.CMDRedirect;



import blockchain.JDBC.BCdata;
import blockchain.JDBC.MysqlUtil;
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
    public static String MutationProjectPath;//临时项目路径
    public static String TableName;
    public static String sql_statement;
    public static long begintime = 0;
    //contracts文件夹路径
    public static String OriginalContractsPath;
    public static String MutationContractsPath;
    //变异体路径
    public static String MutationDir;
    //本地链创建命令
    public static String TestrpcRunCommand = null;
    //运行truffletest还是solcov
    public static String TestCommand;
    //mysql数据库名
    public static String DBName;
    //mysql用户名
    public static String username = "root";
    //mysql密码
    public static String pwd = "root";
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
    public static long TimeCost = 0;
    public static List<String> TestInfo;
    //记录所有变异信息，用于二阶变异
    public static List<String> AllMutationInfo = new ArrayList<>();
    public static List<BCdata> listbcdata = new ArrayList<>();
    public static long endtime = 0;
    public static long time = 0;
    public static String test25dir;

    /**
     * 程序主入口
     * @param args
     */
    public static void main(String[] args){
        String ASpath = "E:\\blockchain\\MutationTest\\TC\\201910TSE\\allObjectMutation\\all_object\\AirSwap";
        String SCpath = "E:\\blockchain\\MutationTest\\TC\\201910TSE\\allObjectMutation\\all_object\\SkinCoin-token";
        String SIpath = "E:\\blockchain\\MutationTest\\TC\\201910TSE\\allObjectMutation\\all_object\\SmartIdentity";
        String CFpath = "E:\\blockchain\\MutationTest\\TC\\201910TSE\\allObjectMutation\\all_object\\cryptofin-solidity";
        String DEpath = "E:\\blockchain\\MutationTest\\TC\\201910TSE\\allObjectMutation\\all_object\\DemocracyEarth";
        String SBCIpath = "E:\\blockchain\\MutationTest\\TC\\201910TSE\\allObjectMutation\\all_object\\sbci-token-master";

        TestCommand = "solidity-coverage";
//        generateMutant(SIpath);
//        generateMutant(ASpath);
//        generateMutant(SCpath);
//        generateMutant(CFpath);
//        generateMutant(DEpath);
//        generateMutant(SBCIpath);

//        singlerun(ASpath,"tse_split","airswap_mut_split");
        singlerun(CFpath,"tse_split","crytofin_mut_split");
//        singlerun(DEpath,"tse_split","democracy_mut_split");
        singlerun(SIpath,"tse_split","smartid_mut_split");
        TestrpcRunCommand = "testrpc-sc --gasLimit 0xfffffffffff --port 8555 --account=\"0xa39f809bd1966856b1a0f1e7868ca5b3c052329db0e6d3fb50e7fc135c8a051f,100000000000000000000000000\" --account=\"0xefc73898fc9bbc6c315c94cd5c57e5f67ad05145fd24713f5a533dfa163470c3,100000000000000000000000000\" --account=\"0xa5119ff109d2886e358cf7712feb98fb8b16db5029273a435b7e2b131b9fd8fe,100000000000000000000000000\" --account=\"0x38712cee7ace9c96181cc6a31f55fac50b3ff005d7c1bfa0c3a0121c8fc4b1c2,100000000000000000000000000\" --account=\"0x59983eb3692f525def24c283b775973112d67d30589fa63798dfd70cea82467d,100000000000000000000000000\" --account=\"0xf09a07170897bec0ce50e45ac28f70288e4dd7e9f687cf9d04556554db59a07e,100000000000000000000000000\" --account=\"0xd7ad15ff5b4b8f50bbf6472e15bca78e4d00ead8a2c74da12d18c51c7b2d3d93,100000000000000000000000000\" --account=\"0x21637e41ea5ec2c0a08c734ddcf4fd3fc01eaa7618b412e09b76f86808cb4dd8,100000000000000000000000000\" --account=\"0x75986d9e701e574e888ed76bf084e7a15bcae8c203a84136e64e3ce18ab62b85,100000000000000000000000000\" --account=\"0xb3547fac81fa0d4b553d3c6637f60eb143159d434d3a0ea9ba44a5c5df192c4c,100000000000000000000000000\"";
        singlerun(SBCIpath,"tse_split","sbci_mut_split");
        TestrpcRunCommand = "testrpc-sc -u 0 -u 1 -u 2 --account=\"0x6a936b41ff2044aa470c693b1cba7af1f7ea10139d9d65dc403518df037515d5,1000000000000000000000000\" --account=\"0x5ebd6d2fa870e35d48ee3f803dbbab34403aa42c67a3036dee721235a87cdd69,0\" --account=\"0x56383562a52a451683c2cae3c09b2c1b2a173ab0cdfb8526006fb0b8654d54c3,1000000000000000000000000\" --account=\"0x56383562a52a451683c2cae3c09b2c1f2a173ab0cdfb8526006fb0b8654d54c3,0\" --gasLimit 0xfffffffffff --port 8555";
        singlerun(SCpath,"tse_split","skincoin_mut_split");
    }

    //9月实验命令，运行25测试集
    public static void run25experiment(String projectPath, String testrpcRunCommand, String testpath, String dbname, String tablename){
        //运行命令
        DBName = dbname;
        ProjectPath = projectPath;
        TestrpcRunCommand = testrpcRunCommand;
        File testdir = new File(testpath);
        File[] files = testdir.listFiles();
        StringBuilder sb = new StringBuilder();
        String newtablename = null;
        for(File file:files){
            String fatherdir = file.getName(); //first
            File[] files1 = file.listFiles();
            for(File file1:files1){
                String childdir = file1.getName(); //1
                test25dir = file1.getAbsolutePath();
                sb.setLength(0);
                sb.append(fatherdir + "_" + childdir);
                newtablename = sb.toString();
//                MutationInfo = newtablename;
                MysqlUtil.CreateTable(DBName,newtablename);
                singlerun(ProjectPath,DBName,newtablename);
            }
        }
    }

    public static void runMutants(){
        //运行命令
        TestCommand = "solidity-coverage";
        //SmartId
//        singlerun("C:\\Users\\Administrator\\Desktop\\20190801\\xjh_tc\\SmartIdentity","equalmutant","smartid_gen");
//        generateMutant("C:\\Users\\Administrator\\Desktop\\20190801\\xjh_tc\\SmartIdentity");
//        singlerun("C:\\Users\\Administrator\\Desktop\\20190801\\xjh_tc\\SmartIdentity","equalmutant","smartid_spec");
        //SkinCoin
//        TestrpcRunCommand = "testrpc-sc -u 0 -u 1 -u 2 --account=\"0x6a936b41ff2044aa470c693b1cba7af1f7ea10139d9d65dc403518df037515d5,1000000000000000000000000\" --account=\"0x5ebd6d2fa870e35d48ee3f803dbbab34403aa42c67a3036dee721235a87cdd69,0\" --account=\"0x56383562a52a451683c2cae3c09b2c1b2a173ab0cdfb8526006fb0b8654d54c3,1000000000000000000000000\" --account=\"0x56383562a52a451683c2cae3c09b2c1f2a173ab0cdfb8526006fb0b8654d54c3,0\" --gasLimit 0xfffffffffff --port 8555";
//        singlerun("C:\\Users\\Administrator\\Desktop\\20190801\\xjh_tc\\SkinCoin-token1\\SkinCoin-token","equalmutant","skincoin_gen");
//        generateMutant("C:\\Users\\Administrator\\Desktop\\20190801\\xjh_tc\\SkinCoin-token1\\SkinCoin-token");
//        singlerun("C:\\Users\\Administrator\\Desktop\\20190801\\xjh_tc\\SkinCoin-token1\\SkinCoin-token","equalmutant","skincoin_spec");

        //Cryptofin
//        generateMutant("E:\\blockchain\\MutationTest\\TC\\TC\\cryptofin-solidity");
//        singlerun("E:\\blockchain\\MutationTest\\TC\\TC\\cryptofin-solidity","equalmutant","cryptofin_total");

        //4.AirSwap
//        ProjectPath = "E:\\blockchain\\MutationTest\\TC\\20190801\\xjh_tc\\AirSwap";
//        generateMutant(ProjectPath);
//        singlerun(ProjectPath,"equalmutant","airswap_total");
    }
    /**
     * 生成变异体
     * @param projectPath
     */
    public static void generateMutant(String projectPath){
        ProjectPath = projectPath;
//        List<String> mutatorlist = SetMutators.selectallmutators();//选择全部变异算子
//        List<String> mutatorlist = SetMutators.selectnomalmutators();//选择一般变异算子
//        List<String> mutatorlist = SetMutators.selecspecificmutators();//选择特殊变异算子
        List<String> mutatorlist =setmutators();//选择自定义变异算子
        generateMutation(projectPath,mutatorlist);
    }


    /**
     * 运行变异测试，mysql设置
     * @param projectPath
     * @param tableName
     */
    public static void singlerun(String projectPath, String dbName, String tableName){
        ProjectPath = projectPath;
        DBName = dbName;
        TableName = tableName;//表名
        //mysql用户名
        username = "root";
        //mysql密码
        pwd = "root";
        //若未自定义测试链，则使用默认
        if(TestrpcRunCommand == null){
            TestrpcRunCommand = "testrpc-sc --gasLimit 0xfffffffffff --port 8555";
        }
        MysqlUtil.CreateTable(DBName,TableName);
        runMutationTest(ProjectPath);//完整变异测试，暂时修改为不生成变异体
    }


    /**
     * 选择变异算子,未使用，具体见MutationUtil.SetMutators
     * @return
     */
    public static List<String> setmutators(){
        List<String> mutatorlist = new ArrayList<>();
        mutatorlist.add("ChangeToInequality");
        return mutatorlist;
    }
    /**
     * 运行变异测试
     * @param
     */
    public static void runMutationTest(String projectpath){
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
            TestInfo = getTestInfoFromTable(TableName,DBName);
            replacesourcefile(); //每次用一个变异体替换原码，测试变异体
//            RunSourceCode();//直接运行源码
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

    //运行truffle test还是solidity-coverage
    public static void getResult(){
        try {
            //运行testrpc
            init();

            CMDRedirect.RunTestrpc(MutationProjectPath,TestrpcRunCommand);
            //运行truffle test
            if(TestCommand.equals("truffle test")){
                CMDRedirect.RunTruffleTest(MutationProjectPath);
            }
            //运行solidity-coverage
            if(TestCommand.equals("solidity-coverage")){
                CMDRedirect.RunSolcov(MutationProjectPath);
            }
//            FileNotify.notify(MutationProjectPath);
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
                String[] strings = mutationname.split("_");
                sb1.setLength(0);
                sb1.append(strings[0]);
                sb1.append(".sol");
                String solfilename = sb1.toString();//原始solfile名
                String solfilepath = FileUtil.findFile(MutationContractsPath,solfilename);//待替换文件路径
                deleteFile(solfilepath);
                CopyDir.fileCopy(mutation.getAbsolutePath(),solfilepath);
//                System.out.println(mutationname+"已经被复制到"+sb1.toString());
                Thread.sleep(00);
                MutationInfo = mutationname;
                //每次放入一个testcase
                runTestcases();//每次运行一个测试用例
//                runAllTest();//直接运行测试
//                run25Test(test25dir);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 直接在源码上运行5组实验测试代码
     */
    public static void RunSourceCode(){
        //初始化MutationContractsPath
        StringBuffer sb1 = new StringBuffer();
        sb1.append(ProjectPath);
        sb1.append("\\contracts");
        OriginalContractsPath = sb1.toString();
//        MutationInfo = "SourceCode";
        run25Test(test25dir);
    }

    /**
     * 运行测试用例
     */
    public static void runTestcases(){
        StringBuffer sb = new StringBuffer();
        String testinfo;
        //清空test文件夹，然后复制testcases文件夹到test里保证目录格式
        sb.append(MutationProjectPath);
        sb.append("\\test");
        String mutationtestdir = sb.toString();
        deleteDir(new File(mutationtestdir));
        sb.setLength(0);
        sb.append(MutationProjectPath);
        sb.append("\\testcases");
        String mutationtestcasesdir = sb.toString();
        CopyDir.copy(mutationtestcasesdir,mutationtestdir);
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
            if(TestInfo.contains(testinfo)){
                System.out.println("跳过：" + testinfo);
            }
            else{
                System.out.println("TestInfo：" + testinfo);
                TestInfo.add(testinfo);
                //先删除test文件夹下的文件，再放入新testcase
                FileUtil.delAllFile(mutationtestdir);
                String testfilepath = testcaselist.get(i).getAbsolutePath();
                String newfilepath = testfilepath.replace("\\testcases\\","\\test\\");
                CopyDir.fileCopy(testfilepath,newfilepath);
                getResult();
            }
        }

    }

    public static void runAllTest(){
        StringBuffer sb = new StringBuffer();
        String testinfo;
        sb.append(MutationInfo);
        sb.append("##");
        sb.append("All");
        TestFileName = "All";
        testinfo = sb.toString();
        if(TestInfo.contains(testinfo)){
            System.out.println("跳过 " + testinfo);
        }
        else{
            System.out.println("TestInfo：" + testinfo);
            TestInfo.add(testinfo);
            getResult();
        }

    }

    public static void run25Test(String copydirpath){
        StringBuffer sb = new StringBuffer();
        String testinfo;
        //清空test文件夹，然后复制testcases文件夹到test里保证目录格式
        sb.append(MutationProjectPath);
        sb.append("\\test");
        String mutationtestdir = sb.toString();
        deleteDir(new File(mutationtestdir));
        CopyDir.copy(copydirpath,mutationtestdir);
        sb.setLength(0);
        sb.append(MutationInfo);
        sb.append("##");
        sb.append("All");
        TestFileName = "All";
        testinfo = sb.toString();
        if(TestInfo.contains(testinfo)){
            System.out.println("跳过 " + testinfo);
        }
        else{
            System.out.println("TestInfo：" + testinfo);
            TestInfo.add(testinfo);
            getResult();
        }
    }

}

