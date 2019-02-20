package blockchain.CMDRedirect;



import blockchain.FileUtils.*;

import java.io.*;

import static blockchain.CMDRedirect.Main.MutationContractsPath;
import static blockchain.ExtractUtils.getAST.getAST;

public class CMDRedirect {

    private static String FilePath; //F:\\blockchain\\metacoin
    private static String TestrpcRunCommand;

    public static void main(String[] args){
//        String TestrpcRunCommand = "testrpc-sc -u 0 -u 1 -u 2 --account=\"0x6a936b41ff2044aa470c693b1cba7af1f7ea10139d9d65dc403518df037515d5,1000000000000000000000000\" --account=\"0x5ebd6d2fa870e35d48ee3f803dbbab34403aa42c67a3036dee721235a87cdd69,0\" --account=\"0x56383562a52a451683c2cae3c09b2c1b2a173ab0cdfb8526006fb0b8654d54c3,1000000000000000000000000\" --account=\"0x56383562a52a451683c2cae3c09b2c1f2a173ab0cdfb8526006fb0b8654d54c3,0\" --gasLimit 0xfffffffffff --port 8555";
        try {
            MutationContractsPath = "E:\\blockchain\\MutationTest\\TC";
            String SolfilePath = "E:\\blockchain\\MutationTest\\TC\\SkinCoin.sol";
            String cmdstr = RunSolidityParser(SolfilePath);

            String ast = getAST(cmdstr);
            System.out.println(ast);
//            Thread.sleep(1000);
//            String ast = getAST("E:\\blockchain\\MutationTest\\TC\\test_ast.txt");
//            System.out.println(ast);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建测试链
     * 默认命令：testrpc-sc --gasLimit 0xfffffffffff --port 8555
     */
    public static void RunTestrpc(String FilePath,String TestrpcRunCommand){
        StringBuffer sc = new StringBuffer();
        sc.append(Main.ProjectPath);
        sc.append("\\scTopics");
        WriteUtil.deleteFile(sc.toString());
        Runtime rt = Runtime.getRuntime();
        StringBuffer sb = new StringBuffer();
        StringBuffer fp = new StringBuffer();
        fp.append(FilePath);
        fp.append("\\ChainInfo.txt");
        String filepath = fp.toString();
        fp = null;
//        CMDUtils.clearInfoForFile(filepath);
        WriteUtil.deleteFile(filepath);
        sb.append("cmd /c pushd ");
        sb.append(FilePath);
        sb.append(" && ");
        sb.append(TestrpcRunCommand);
        sb.append(" > ");
        sb.append(filepath);
        String cmd = sb.toString();
        sb = null;
        System.out.println("启动本地测试链。。。");
        try {
            Process pr1 = rt.exec(cmd);
            System.out.println("Testrpc is running.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行solidity-coverage
     */
    public static void RunSolcov(String FilePath){
        Runtime rt = Runtime.getRuntime();
        StringBuffer sb = new StringBuffer();
        sb.append("cmd /c pushd ");
        sb.append(FilePath);
        sb.append(" && solidity-coverage > ");
        sb.append(FilePath);
        sb.append("\\SolcovInfo.txt");
        String cmd = sb.toString();
        System.out.println("启动solidity-coverage。。。");
        try {
            Process pr2 = rt.exec(cmd);
            Main.begintime = System.currentTimeMillis();
            System.out.println("Solcov is running.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从源码转化为AST
     * solidity-parser test.sol > 111.txt
     */
    public static String RunSolidityParser(String SolfilePath){
        String temp = SolfilePath.substring(0,SolfilePath.length()-4);
        StringBuffer sb1 = new StringBuffer();
        sb1.append(temp);
        sb1.append("_ast.txt");
        String astPath = sb1.toString();
        //创建ast文件
        try {
            File file = new File(astPath);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //运行sol
        Runtime rt = Runtime.getRuntime();
        StringBuffer sb = new StringBuffer();
        sb.append("cmd /c pushd ");
        sb.append(MutationContractsPath);
        sb.append(" && node ");
        sb.append("testparser.js");
        String cmd = sb.toString();
        System.out.println("启动solidity-parser构建ast");
        try {
            Process pr2 = rt.exec(cmd);
            InputStream in = pr2.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer bf = new StringBuffer();
            String message;
            while((message = br.readLine()) != null) {
                bf.append(message);
                bf.append("\n");
            }
            String cmdstr = bf.toString();
            return cmdstr;
        } catch (IOException e) {
            e.printStackTrace();
            return "parse failed.";
        }
    }


}
