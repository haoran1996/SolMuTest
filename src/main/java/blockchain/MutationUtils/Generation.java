package blockchain.MutationUtils;

import blockchain.Mutator.BooleanFalseReturnValsMutator;
import blockchain.Mutator.BooleanTrueReturnValsMutator;
import blockchain.Mutator.Mutator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static blockchain.CMDRedirect.Main.MutationContractsPath;
import static blockchain.CMDRedirect.Main.setmutators;
import static blockchain.MutationUtils.Mutation.generateMutation;

public class Generation {
    public static int MutationNum;

    public static void main(String[] args){
        String filepath = "E:\\blockchain\\MutationTest\\tempTC\\Smart Identity\\SolMuTest";
        List<String> mutatorlist = setmutators();
        generate(filepath,mutatorlist);
    }


    /**
     * 生成变异体主入口
     * @param MutationProjectPath
     * @param mutatorlist
     */
    public static void generate(String MutationProjectPath,List<String> mutatorlist){
        StringBuffer sb = new StringBuffer();
        sb.append(MutationProjectPath);
        sb.append("\\contracts");
        MutationContractsPath = sb.toString();
        MutationNum = 0;
        for(int i=0;i<=mutatorlist.size()-1;i++){
            applyMutatorToAllFile(sb.toString(),mutatorlist.get(i));
        }
        System.out.println("——————————————");
        System.out.println("本次变异测试共生成" + MutationNum + "个变异体");
    }


    /**
     * 对contracts文件夹下所有文件运用单个变异算子
     * @param contractsDir
     * @param mutationOperator
     */
    public static void applyMutatorToAllFile(String contractsDir,String mutationOperator){
        try {
            File dir = new File(contractsDir);
            File[] fs = dir.listFiles();
            int num = 0;
            for(File f:fs){
                if(!f.isDirectory()){
                    String filepath = f.getAbsolutePath();
                    num = num + generateMutation(filepath,mutationOperator);
                }
            }
            MutationNum = MutationNum + num;
            System.out.println("—" + mutationOperator + "共生成" + num + "个变异体");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
