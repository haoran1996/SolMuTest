package blockchain.FileUtils;
import java.io.*;

import static blockchain.CMDRedirect.Main.MutationProjectPath;

public class CopyDir {

    public static void main(String[] args) throws Exception {
        makeMutationDir("E:\\blockchain\\MutationTest\\tempTC\\Smart Identity");
    }

    /**
     * 创建变异测试文件夹
     * @param filePath
     */
    public static void makeMutationDir(String filePath){
        StringBuffer sb = new StringBuffer();
        sb.append(filePath);
        sb.append("\\SolMuTest");
        MutationProjectPath = sb.toString();
        try {
            File dir = new File(MutationProjectPath);
            FileUtil.deleteDir(dir);
            copy(filePath,MutationProjectPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件夹
     * @param src
     * @param des
     * @throws Exception
     */
    public static void copy(String src, String des){
        try {
            //初始化文件复制
            File file1=new File(src);
            //把文件里面内容放进数组
            File[] fs=file1.listFiles();
            //初始化文件粘贴
            File file2=new File(des);
            //判断是否有这个文件
            if(!file2.exists()){
                file2.mkdirs();
            }
            //遍历文件及文件夹
            for (File f : fs) {
                if(f.isFile()){
                    //文件
                    fileCopy(f.getPath(),des+"\\"+f.getName()); //调用文件拷贝的方法
                }else if(f.isDirectory()){
                    //文件夹，跳过SolMuTest文件夹！
                    if(f.getName().equals("SolMuTest")) continue;
                    else  copy(f.getPath(),des+"\\"+f.getName());//递归调用复制方法
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件复制的具体方法
     */
    public static void fileCopy(String src, String des) {
        File oldfile = new File(src);
        if(!oldfile.exists()){
            System.out.println("原文件不存在，无法复制！");
        }
        else{
//            File newfile = new File(des);
//            if(!newfile.exists()){
//                newfile.mkdirs();
//            }
            try {
                //io流固定格式
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));
                int i = -1;//记录获取长度
                byte[] bt = new byte[2014];//缓冲区
                while ((i = bis.read(bt))!=-1) {
                    bos.write(bt, 0, i);
                }
                bis.close();
                bos.close();
                //关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}