package blockchain.MutationUtils;

import blockchain.CMDRedirect.Main;
import blockchain.FileUtils.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static blockchain.CMDRedirect.Main.AllMutationInfo;

public class Mutation {
    private File file;
    private String projectpath;
    private String filepath;
    private String mutationdir;
    private String filename;
    private String MutationOperator;
    private List<String> allMutationInfo;
    private boolean hasmutated = false;
    private int mutationNum = 0;


    public Mutation(File file, String projectpath, String filepath, String filename, String mutationOperator, List<String> allmutationinfo) {
        this.file = file;
        this.projectpath = Main.MutationProjectPath;
        this.filepath = filepath;
        this.filename = filename;
        this.allMutationInfo = allmutationinfo;
        MutationOperator = mutationOperator;
    }

    public Mutation(String filepath, String mutationOperator) {
        this.file = new File(filepath);
        String tempstr = file.getName();
        this.filename = tempstr.substring(0,tempstr.length()-4);
        File tempfile = new File(file.getParent());
        this.projectpath =  Main.MutationProjectPath;
        StringBuffer sb = new StringBuffer();
        sb.append(projectpath);
        sb.append("\\mutation");
        this.mutationdir = sb.toString();
        MutationOperator = mutationOperator;
    }

    /**
     * 对一个文件重复运用一个变异算子生成变异体
     * @param filepath
     * @param mutationOperator
     */
    public static int generateMutation(String filepath, String mutationOperator){
        Mutation mutation = new Mutation(filepath, mutationOperator);
        String[] mutatorinfo = blockchain.MutationUtils.MutationOperator.getMutatorInfo(mutationOperator);
        do{
            mutation.mutate(mutatorinfo[1],mutatorinfo[2]);
        }while(mutation.hasmutated == true);
        return mutation.getMutationNum();
    }

    /**
     * 基本变异操作
     * @param originalStr
     * @param mutatedStr
     */
    public boolean mutate(String originalStr, String mutatedStr){
        hasmutated = false;
        String mutationinfo = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(isr);
            String line = null;
            String mutationName = null;
            int linenum = 0;
            while ((line = br.readLine()) != null) {
                linenum++;
                if(line.contains(originalStr) && hasmutated == false){
                    mutationName = generateMutationName(filename,linenum,MutationOperator);
//                    mutationinfo = generateMutationInfo(filename,linenum,MutationOperator,line);
//                    System.out.println(mutationinfo);
                    if(cangenerate(mutationName,projectpath)){
                        String newline = line.replace(originalStr,mutatedStr);
                        sb.append(newline);
                        sb.append("\n");
                        hasmutated = true;
                        mutationinfo = generateMutationInfo(filename,linenum,MutationOperator,newline);
                        System.out.println(mutationinfo);
//                        mutationName = generateMutationName(filename,linenum,MutationOperator);
                    }else{
                        sb.append(line);
                        sb.append("\n");
                    }
                }else{
                    sb.append(line);
                    sb.append("\n");
                }
            }
            br.close();
            isr.close();
            if(hasmutated == true){
                String MutatedFileContent = sb.toString();
                makeMutationFile(mutationName, MutatedFileContent);
                AllMutationInfo.add(mutationinfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hasmutated;
    }

    public static String generateMutationInfo(String filename,int MutatedLine,String MutationOperator,String line){
        StringBuffer sb = new StringBuffer();
        sb.append(filename);
        sb.append("#");
        sb.append(MutatedLine);
        sb.append("#");
        sb.append(MutationOperator);
        sb.append("#");
        sb.append(line.trim());
        return sb.toString();
    }

    public static String generateMutationName(String filename,int MutatedLine,String MutationOperator){
        StringBuffer sb = new StringBuffer();
        sb.append(filename);
        sb.append("#");
        sb.append(MutatedLine);
        sb.append("#");
        sb.append(MutationOperator);
        return sb.toString();
    }

    public static boolean cangenerate(String mutationinfo,List<String> allmutationinfo){
        if(allmutationinfo.contains(mutationinfo)){
            return false;
        }else
            return true;
    }

    private boolean cangenerate(String mutationname,String projectpath){
        List<String> allmutationname = new ArrayList<>();
        File file = new File(mutationdir);
        if(!file.exists()){
            return true;
        }
        else{
            File[] fs = file.listFiles();
            for(File f:fs){
                if(!f.isDirectory()){
                    allmutationname.add(f.getName());
                }
            }
            return cangenerate(mutationname,allmutationname);
        }
    }

    private boolean makeMutationFile(String mutationfilename,String content){
        StringBuffer sb = new StringBuffer();
        sb.append(mutationdir);
        sb.append("\\");
        sb.append(mutationfilename);
        FileUtil.WriteToFile(sb.toString(),content);
        System.out.println("成功生成"+mutationfilename);
        mutationNum++;
        return true;
    }

    public int getMutationNum() {
        return mutationNum;
    }
}
