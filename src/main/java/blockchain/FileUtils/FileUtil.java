package blockchain.FileUtils;

import blockchain.CMDRedirect.Main;
import blockchain.JDBC.MysqlUtil;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class FileUtil {
    private static LinkedList filelist = new LinkedList();

    public static void main(String[] args){
        String dir = "E:\\blockchain\\MutationTest\\TC\\cryptofin-solidity\\SolMuTest\\test";
        String dir2 = "E:\\blockchain\\MutationTest\\TC\\cryptofin-solidity\\SolMuTest\\testcases";
        CopyDir.copy(dir2,dir);
    }

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
//            System.out.println(pathname+"文件已经被成功删除");
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

    /**
     * 删除指定文件夹下所有文件
     * @param path 文件夹完整绝对路径
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile() && !temp.getName().equals("assertRevert.js") && !temp.getName().equals("util.js")) {//删除test文件夹时有的文件不能删
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 遍历test文件夹下的文件
     */
    public static List<File> getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (!fileName.equals("assertRevert.js") && !fileName.equals("util.js")) { // 判断文件名是否以.js结尾
//                    String strFileName = files[i].getAbsolutePath();
//                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }
        }
        return filelist;
    }

    //获取constracts文件夹下待测文件
    public static List<File> getFileUnderTest(String strPath) {
        filelist = new LinkedList();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (!fileName.contains("Migrations.sol")) { // 排除Migrations.sol
//                    String strFileName = files[i].getAbsolutePath();
//                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }
        }
        return filelist;
    }

    /**
     * 递归查找文件
     * @param baseDirName  查找的文件夹路径
     * @param targetFileName  需要查找的文件名
     */
    public static String findFile(String baseDirName, String targetFileName){
        List<File> filelist = new ArrayList<>();
        findFiles_1(baseDirName,targetFileName,filelist);
        return filelist.get(0).getAbsolutePath();
    }


    public static void findFiles_1(String baseDirName, String targetFileName, List fileList) {
        String tempName = null;
        //判断目录是否存在
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || !baseDir.isDirectory()){
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
        } else {
            String[] filelist = baseDir.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(baseDirName + "\\" + filelist[i]);
                //System.out.println(readfile.getName());
                if(!readfile.isDirectory()) {
                    tempName =  readfile.getName();
                    if (tempName.equals(targetFileName)) {
                        //匹配成功，将文件名添加到结果集
                        fileList.add(readfile.getAbsoluteFile());
                    }
                } else if(readfile.isDirectory()){
                    findFiles_1(baseDirName + "\\" + filelist[i],targetFileName,fileList);
                }
            }
        }
    }

    /**
     * 查找solidity-coverage生成的文件
     */
    public static List<File> getHtmlFiles(String baseDirName,String Suffix) {
        List<File> filelist = new ArrayList<>();
        findFiles_2(baseDirName,Suffix,filelist);
        return filelist;
    }

    public static void findFiles_2(String baseDirName, String Suffix, List fileList) {
        String tempName = null;
        //判断目录是否存在
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || !baseDir.isDirectory()){
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
        } else {
            String[] filelist = baseDir.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(baseDirName + "\\" + filelist[i]);
                //System.out.println(readfile.getName());
                if(!readfile.isDirectory()) {
                    tempName =  readfile.getName();
                    if (tempName.endsWith(Suffix) && !tempName.equals("index.html")) {
                        //匹配成功，将文件名添加到结果集
                        fileList.add(readfile.getAbsoluteFile());
                    }
                } else if(readfile.isDirectory()){
                    findFiles_2(baseDirName + "\\" + filelist[i],Suffix,fileList);
                }
            }
        }
    }

}
