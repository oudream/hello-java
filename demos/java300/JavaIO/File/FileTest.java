package JavaIO.File;

import java.io.File;
import java.io.IOException;

/**
 * 说明：File类测试
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 14:18
 * @Description:
 */
public class FileTest {

    public static void main(String[] args) throws Exception{
        File file = new File("D:\\idea-file\\javaDemo02");
//        showAllFile(file ,"");
        System.out.println(getFileLength(file));
    }

    public void method(File file)throws IOException {
        //获取文件名
        file.getName();
        //获取文件路径（参照构造函数的路径）
        file.getPath();
        //返回绝对路径
        file.getAbsolutePath();
        //返回 1、父目录路径  2、父目录。
        String parent = file.getParent();
        File parentFile = file.getParentFile();
        //判断是否为: 1、不存在 2、是否为目录 3、是否为文件。
        file.exists();
        file.isDirectory();
        file.isFile();
        //获取文件长度（为0时：该File为文件夹或不存在时）
        file.length();
        //创建新文件,返回成功与否。以存在返回false
        boolean newFile = file.createNewFile();
        //删除已存在的文件,返回成功与否。
        boolean delete = file.delete();
        //移动 + 重命名 文件/目录
        file.renameTo(new File("newName"));
        //1、获取 2、修改   最后修改时间
        file.lastModified();
        file.setLastModified(System.currentTimeMillis());
        //1、返回目录下的所有文件或目录的File对象。  2、返回目录下所有文件或目录名称(不是文件路径)。
        File[] files = file.listFiles();
        String[] list = file.list();
        //1、创建目录，若父目录不存在，则失败  2、创建目录，若父目录不存在，则一同创建。
        file.mkdir();
        file.mkdirs();
    }


    /** 打印文件夹下所有文件 */
    public static void showAllFile(File file, String str){
        System.out.println(str + file.getName());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files){
                showAllFile(f ,"---" + str);
            }
        }
        if (file.isFile()){
            return;
        }
    }


    /** 获取文件夹大小 */
    public static long getFileLength(File file){
        long conunt = 0L;
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files){
                conunt += getFileLength(f);
            }
        }
        if (file.isFile()){
            return file.length();
        }
        return conunt;
    }
}
