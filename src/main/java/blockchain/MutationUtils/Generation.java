package blockchain.MutationUtils;

import blockchain.Mutator.BooleanFalseReturnValsMutator;
import blockchain.Mutator.BooleanTrueReturnValsMutator;
import blockchain.Mutator.Mutator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static blockchain.MutationUtils.Mutation.generateMutation;

public class Generation {

    public static void main(String[] args){
        String filepath = "E:\\blockchain\\MutationTest\\TC\\SkinCoin-token\\contracts";
        String mutationOperator = "BOOLEAN_TRUE_RETURN";
        applyMutatorToAllFile(filepath,mutationOperator);
    }

    /**
     * 主入口
     * @param contractsDir
     * @param mutatorlist
     */
    public static void generate(String contractsDir,List<String> mutatorlist){
        for(int i=0;i<mutatorlist.size()-1;i++){
            applyMutatorToAllFile(contractsDir,mutatorlist.get(i));
        }
    }


    /**
     * 对contracts文件夹下所有文件运用某个变异算子
     * @param contractsDir
     * @param mutationOperator
     */
    public static void applyMutatorToAllFile(String contractsDir,String mutationOperator){
        try {
            File dir = new File(contractsDir);
            File[] fs = dir.listFiles();
            for(File f:fs){
                if(!f.isDirectory()){
                    String filepath = f.getAbsolutePath();
                    generateMutation(filepath,mutationOperator);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择变异算子
     * @return
     */
    public List<String> selectmutators(){
        List<String> mutatorlist = new ArrayList<>();
        mutatorlist.add("BOOLEAN_FALSE_RETURN");
        mutatorlist.add("BOOLEAN_TRUE_RETURN");


        return mutatorlist;
    }

}
