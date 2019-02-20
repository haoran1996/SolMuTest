package blockchain.ExtractABI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ExtractContract {
    private String ContractName;
    private String Bytecode;
    public String getContractName() {
        return ContractName;
    }

    public static void main(String[] args){

    }

    /*
        解析abi获取方法序列
     */
    public  List<Function> GetFunctionList(String JPath){
        List<Function> listfunction = new ArrayList<Function>();
//        String path = "F:\\blockchain\\SmartContrat\\TestAdd\\build\\contracts\\Add.json";
        String Jsonstr = RDJSON.ReadFile(JPath);
        JSONObject jobj = JSON.parseObject(Jsonstr);
        ContractName = jobj.getString("contractName");
        JSONArray arr = jobj.getJSONArray("abi");
        for (int i = 0; i < arr.size(); i++) {
            JSONObject object = arr.getJSONObject(i);
            String temp = object.getString("type");
            if (temp.equals("function")) {
//                System.out.println(object);
                Function func = JSONObject.parseObject(object.toJSONString(), Function.class);
                listfunction.add(func);
            }
        }
        return listfunction;
    }

    /**
     * 从abi获取bytecode
     * @param JPath
     * @return
     */
    public  String GetBytecode(String JPath){
        String Jsonstr = RDJSON.ReadFile(JPath);
        JSONObject jobj = JSON.parseObject(Jsonstr);
        ContractName = jobj.getString("contractName");
        String bytecodejs = jobj.getString("bytecode");
        Bytecode = bytecodejs.replace("0x","");
        return Bytecode;
    }


}


