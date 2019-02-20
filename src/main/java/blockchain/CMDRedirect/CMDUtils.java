package blockchain.CMDRedirect;

import java.io.*;




public class CMDUtils {

    public static void main(String[] args){
        KillNodeProcess();
    }

    /**
     * 每次运行solidity-coverage前要重新启动node.exe
     */
    public static void KillNodeProcess()
    {
        try
        {
            String[] cmd = {"tasklist"};
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String string_Temp = in.readLine();
            System.out.println("结束node.exe进程");
            while (string_Temp != null)
            {
                if(string_Temp.indexOf("node.exe")!=-1){
                    Runtime.getRuntime().exec("taskkill /f /im node.exe");
                    System.out.println("进程已杀死！");
                    break;
                }
                string_Temp = in.readLine();
            }
        }
        catch (Exception e)
        {
        }
    }


}
