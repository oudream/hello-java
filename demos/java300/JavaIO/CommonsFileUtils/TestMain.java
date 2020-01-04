package JavaIO.CommonsFileUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.*;
import sun.invoke.empty.Empty;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/24 10:41
 * @Description:
 */
public class TestMain {

    public static void main(String[] args)throws Exception {
        File file = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T");
        //获取文件或目录的大小
        FileUtils.sizeOf(file);
        //返回目录的子孙集，参数二为文件过滤器 ，参数三为目录过滤。
        FileUtils.listFiles(file , EmptyFileFilter.NOT_EMPTY ,null);
        //文件过滤器
        FileFilterUtils.or(new SuffixFileFilter("java") ,new SuffixFileFilter("class"));
        FileFilterUtils.and(new SuffixFileFilter("java") ,EmptyFileFilter.NOT_EMPTY);
        //目录过滤器(默认只看下一层 )
        IOFileFilter instance = DirectoryFileFilter.INSTANCE;
        //读取文件
        String msg = FileUtils.readFileToString(file ,"UTF-8");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        //逐行读取
        List<String> list = FileUtils.readLines(file, "UTF-8");
        LineIterator lineIterator = FileUtils.lineIterator(file);
        //写入文件 1、参数：文件 ，写入的内容 ，编码 ，是否续写。
        //2、参数：文件、写入的内容的集合、分隔符、是否续写
        FileUtils.write(file ,"hello world" ,"UTF-8" ,true);
        FileUtils.writeLines(file ,new ArrayList<>() ,"," ,true);
        //文件拷贝
        FileUtils.copyFile(file ,new File(""));
        //拷贝文件到目录
        FileUtils.copyFileToDirectory(file ,new File(""));
        //拷贝目录
        FileUtils.copyDirectory(file ,new File(""));
        //拷贝目录到目录
        FileUtils.copyDirectoryToDirectory(file ,new File(""));
        //拷贝URL内容
        FileUtils.copyURLToFile(new URL("http://www.baidu.com") ,new File(""));

    }

}
