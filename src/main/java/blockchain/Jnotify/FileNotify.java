package blockchain.Jnotify;

import blockchain.CMDRedirect.Main;
import blockchain.ExtractUtils.ReadSolcovInfo;
import net.contentobjects.jnotify.JNotify;

import java.io.File;

import static blockchain.CMDRedirect.Main.MutationProjectPath;
//该文件暂时作废
public class FileNotify {
    public static boolean Completed;
    public static void notify(String filepath) throws Exception {
        Completed = false;
        if (!new File(filepath).exists()) {
            System.out.println("文件目录不存在");
            return;
        }

        // 定义你所需要检测的事件类型，或者是全部FILE_ANY
        int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED | JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;
        int mask1 =JNotify.FILE_ANY;
//        System.out.println(JNotify.FILE_CREATED);//1
//        System.out.println(JNotify.FILE_DELETED);//2
//        System.out.println( JNotify.FILE_MODIFIED);//4
//        System.out.println( JNotify.FILE_RENAMED);//8
//        System.out.println(mask);//15
//        System.out.println(mask1);//15

        // 是否检测子目录
        boolean watchSubtree = false;

        // 添加监听
        int watchID = JNotify.addWatch(filepath, 2, watchSubtree, new Listener());
        System.out.println("开始监听scTopics文件");
        long BeginTime = System.currentTimeMillis();
        long CurrentTime = 0;
        // 定义监听持续时间，120s
        while(!Completed) {
            //运行truffle test时定义等待编译时间，若项目编译很快可以缩短该时间，如15s
            //若运行solidity-coverage可以去掉，有另外判断运行成功的方法
            Thread.sleep(1000*2);
            Boolean CompileFailed = ReadSolcovInfo.isCompileFailed(MutationProjectPath);
            //编译失败直接退出
            if(CompileFailed == true){
                //System.out.println("中途检查发现Compilation failed.");
                Listener.gain_compilefailed_result();
                break;
            }
            // 超时时间为120s
            CurrentTime = System.currentTimeMillis();
            if(CurrentTime - BeginTime > 1000*100){
                System.out.println("solcov超时，退出");
                break;
            }
        }

        //移除监听
        boolean res = JNotify.removeWatch(watchID);
        if (!res) {
            System.out.println("已退出监听。");
        }

    }

    public static void main(String[] args) {
        String path = "F:\\blockchain\\test1";
        System.out.println(path);
        try {
            notify(path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
