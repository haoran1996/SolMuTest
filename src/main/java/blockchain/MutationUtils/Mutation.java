package blockchain.MutationUtils;

import blockchain.FileUtils.WriteUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Mutation {
    private File file;
    private String projectpath;
    private String filepath;
    private String mutationdir;
    private String filename;
    private String MutationOperator;
    private List<String> allMutationInfo;
    private boolean hasmutated = false;


    public Mutation(File file, String projectpath, String filepath, String filename, String mutationOperator, List<String> allmutationinfo) {
        this.file = file;
        this.projectpath = projectpath;
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
        this.projectpath = tempfile.getParent();
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
    public static void generateMutation(String filepath, String mutationOperator){
        Mutation mutation = new Mutation(filepath, mutationOperator);
        String[] mutatorinfo = blockchain.MutationUtils.MutationOperator.getMutatorInfo(mutationOperator);
        do{
            mutation.mutate(mutatorinfo[1],mutatorinfo[2]);
        }while(mutation.hasmutated == true);
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
            int linenum = 0;
            while ((line = br.readLine()) != null) {
                linenum++;
                if(line.contains(originalStr) && hasmutated == false){
                    mutationinfo = generateMutationInfo(filename,MutationOperator,linenum);
//                    System.out.println(mutationinfo);
                    if(cangenerate(mutationinfo,projectpath)){
                        String newline = line.replace(originalStr,mutatedStr);
                        sb.append(newline);
                        sb.append("\n");
                        hasmutated = true;
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
                makeMutationFile(mutationinfo, MutatedFileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hasmutated;
    }

    public static String generateMutationInfo(String filename,String MutationOperator,int MutatedLine){
        StringBuffer sb = new StringBuffer();
        sb.append(filename);
        sb.append("-");
        sb.append(MutationOperator);
        sb.append("-");
        sb.append(MutatedLine);
        return sb.toString();
    }

    public static boolean cangenerate(String mutationinfo,List<String> allmutationinfo){
        if(allmutationinfo.contains(mutationinfo)){
            return false;
        }else
            return true;
    }

    private boolean cangenerate(String mutationinfo,String projectpath){
        List<String> allmutationinfo = new ArrayList<>();
        File file = new File(mutationdir);
        if(!file.exists()){
            return true;
        }
        else{
            File[] fs = file.listFiles();
            for(File f:fs){
                if(!f.isDirectory()){
                    allmutationinfo.add(f.getName());
                }
            }
            return cangenerate(mutationinfo,allmutationinfo);
        }
    }

    private boolean makeMutationFile(String mutationfilename,String content){
        StringBuffer sb = new StringBuffer();
        sb.append(mutationdir);
        sb.append("\\");
        sb.append(mutationfilename);
        WriteUtil.WriteToFile(sb.toString(),content);
        System.out.println("成功生成"+mutationfilename);
        return true;
    }
    


}
