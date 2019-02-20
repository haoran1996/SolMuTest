package blockchain.FileUtils;

import blockchain.CMDRedirect.Main;
import blockchain.JDBC.MysqlUtil;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WriteUtil {

    /**
     * 写文件
     * @param filepath
     * @param content
     */
    public static void WriteToFile(String filepath, String content){
        try {
            File file = new File(filepath);
            String parentPath = file.getParent();
            File dir = new File(parentPath);
            if(!dir.exists()){
                dir.mkdir();
            }
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
//            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取写文件的路径
     * @param JsonFile
     * @return
     */
    public  static String GetWritePath(File JsonFile){
        //JsonFile = "F:\\blockchain\\SmartContrat\\metacoin\\build\\contracts\\**.json"
        StringBuffer sb = new StringBuffer();
        String JsonName = JsonFile.getName();
        String file_name = JsonName.substring(0, JsonName.lastIndexOf("."));
        String ContractPath = JsonFile.getParentFile().getParentFile().getParentFile().getPath();
        sb.append(ContractPath);
        sb.append("\\test");
        Main.DirPath = sb.toString();
        sb.append("\\");
        sb.append(file_name);
        sb.append(".js");
        String WritePath = sb.toString();
        sb = null;
        return WritePath;
    }

    /**
     * 清空文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String pathname){
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println("文件已经被成功删除");
        }
        return result;
    }

    /**
     * 先根遍历序递归删除文件夹
     * @param dirFile 要被删除的文件或者目录
     * @return 删除成功返回true, 否则返回false
     */
    public static boolean deleteDir(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                deleteDir(file);
            }
        }
        return dirFile.delete();
    }
}
