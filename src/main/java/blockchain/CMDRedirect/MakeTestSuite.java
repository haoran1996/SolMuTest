package blockchain.CMDRedirect;

import blockchain.FileUtils.FileUtil;
import blockchain.MutationUtils.AllInfo;
import blockchain.JDBC.MysqlUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MakeTestSuite {
    public static void main(String[] args){
        String mutationpath = "E:\\blockchain\\MutationTest\\tempTC\\SkinCoin-token\\SolMuTest\\mutation";
        Main.TableName = "skincointoken";
        String mutationinfo = null;
        List<AllInfo> allinfolist = new ArrayList<>();
        try {
            File mutationdir = new File(mutationpath);
            File[] files = mutationdir.listFiles();
            for(File f:files){
                mutationinfo = f.getName();
                System.out.println("**********"+ mutationinfo + "***********");
                allinfolist = MysqlUtil.GetAllInfoByMutationInfo(mutationinfo,Main.DBName);
                if(allinfolist.size() == 0){
                    System.out.println("空");
                }else{
                    for(int i=0; i<allinfolist.size(); i++){
                        System.out.println(allinfolist.get(i).Write());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
