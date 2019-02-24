package blockchain.ExtractUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadSolcovInfo {

    public static void main(String[] args){
        String s = "E:\\blockchain\\MutationTest\\TC\\SkinCoin-token";
    }

    /**
     * 判断变异体是否杀死，如果测试用例全部通过即未杀死
     * @param projectpath
     * @return
     */
    public static String GetTestCondition(String projectpath){
        StringBuffer sb = new StringBuffer();
        sb.append(projectpath);
        sb.append("\\SolcovInfo.txt");
        String CovInfoPath = sb.toString();
        BufferedReader br=null;
        String testcondition = null;
        int temp = 0;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(CovInfoPath));
            br=new BufferedReader(isr);
            String line = null;
            while((line=br.readLine())!= null){
                if(line.contains("Some truffle tests failed while running coverage")){
                    System.out.println("Test failed (mutant is killed).");
                    testcondition = "Test failed (mutant is killed).";
                    temp = 1;
                    break;
                }else if(line.contains("Compilation failed.")){
                    System.out.println("Compilation failed.");
                    testcondition = "Compilation failed.";
                    temp = 1;
                }else if(line.contains("Exiting without generating coverage")){
                    System.out.println("Exiting without generating coverage");
                    testcondition = "Exiting without generating coverage";
                    temp = 1;
                }
            }
            br.close();
            isr.close();
            if(temp == 0){
                System.out.println("All tests passed.(survive)");
                testcondition = "All tests passed.(survive)";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testcondition;
    }

    /**
     * 获取总覆盖率信息
     * @param projectpath
     * @return
     */
    public static double GetTotalCoverage(String projectpath) {
        StringBuffer sb = new StringBuffer();
        sb.append(projectpath);
        sb.append("\\SolcovInfo.txt");
        String CovInfoPath = sb.toString();
        BufferedReader br=null;
        double TotalStmtsCoverage = 0;
        double TotalBranchCoverage = 0;
        double TotalFuncsCoverage = 0;
        double TotalLinesCoverage = 0;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(CovInfoPath));
            br=new BufferedReader(isr);
            String line = null;
            while((line=br.readLine())!= null){
                if(line.contains("All files")){
                    String[] temp = line.split("\\|");
                    //				   for(int i=0; i<temp.length; i++){
                    //					   System.out.println(i + temp[i]);
                    //				   }
                    //				   System.out.println(temp[2].trim());
                    String StmtsCoverage = temp[1].trim().toString();
                    String BranchCoverage = temp[2].trim().toString();
                    String FuncsCoverage = temp[3].trim().toString();
                    String LinesCoverage = temp[2].trim().toString();
                    try {
                        TotalStmtsCoverage = Double.parseDouble(StmtsCoverage);
                        TotalBranchCoverage = Double.parseDouble(BranchCoverage);
                        TotalFuncsCoverage = Double.parseDouble(FuncsCoverage);
                        TotalLinesCoverage = Double.parseDouble(LinesCoverage);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    System.out.println("TotalStmtsCoverage = " + TotalStmtsCoverage);
                    System.out.println("TotalBranchCoverage = " + TotalBranchCoverage);
                    System.out.println("TotalFuncsCoverage = " + TotalFuncsCoverage);
                    System.out.println("TotalLinesCoverage = " + TotalLinesCoverage);
                }
            }
            br.close();
            isr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TotalBranchCoverage;
    }

    /**
     * 返回一个html的覆盖行信息
     * @param file
     * @return
     */
    public static List<String> getCoveredlines(File file){
        String htmlname = file.getName();
        String solname = htmlname.substring(0,htmlname.length()-5);
        BufferedReader br=null;
        List<String> CoveredInfo = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(isr);
            String line = null;
            int linenum = 0;
            while ((line = br.readLine()) != null) {
                if (line.contains("<span class=\"cline-any")) {
                    linenum++;
                    if(line.contains("cline-yes")){
                        StringBuffer sb = new StringBuffer();
                        sb.append(solname);
                        sb.append("文件的第");
                        sb.append(linenum);
                        sb.append("行覆盖到了");
                        CoveredInfo.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
            br.close();
            isr.close();
            return CoveredInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return CoveredInfo;
        }
    }

    /**
     * 输入project路径
     * 返回该次测试覆盖的所有行信息
     * @param projectpath
     * @return
     */
    public static String getAllCoveredLines(String projectpath){
        List<String> allcoveredlines = new ArrayList<>();
        StringBuffer sb1 = new StringBuffer();
        sb1.append(projectpath);
        sb1.append("\\coverage\\contracts");
        File file = new File(sb1.toString());
        File[] fs = file.listFiles();
        for(File f:fs){
            if(!f.isDirectory()){
                List<String> coveredlines = getCoveredlines(f);
                allcoveredlines.addAll(coveredlines);
            }
        }

        if(allcoveredlines==null){
            return null;
        }
        //list转String
        StringBuffer result = new StringBuffer();
        boolean first = true;
        //第一个前面不拼接","
        for(String string :allcoveredlines) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }
}
