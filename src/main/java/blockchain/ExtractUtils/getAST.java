package blockchain.ExtractUtils;

import java.io.*;

public class getAST {

    /**
     * 处理solidity-parser生成的ast，返回正确ast
     * @param aststr
     * @return
     */
    public static String getAST(String aststr){
        StringBuffer sb = new StringBuffer();
        BufferedReader br=null;
        boolean isTestPass = true;
        try {
            br=new BufferedReader(new StringReader(aststr));
            String line =null;
            String formerline = null;
            String templine = null;
            if((templine = br.readLine())!= null){
                line = templine;
                while((templine = br.readLine())!= null){
                    formerline = line;
                    line = templine;
                    if(templine.contains("\"start\":")) {
                        formerline = formerline.substring(0,formerline.length()-1);
                        sb.append(formerline);
                        sb.append("\n");
                        continue;
                    }
                    else if(templine.contains("\"end\":")){
                        line = br.readLine();
                        continue;
                    }
                    else{
                        sb.append(formerline);
                        sb.append("\n");
                        continue;
                    }
                }
                br.close();
                sb.append("}");
                String ast = sb.toString();
                return ast;
            }else{
                return "文件为空";
            }


        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
