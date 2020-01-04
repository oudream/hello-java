package ManyThreads;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/25 10:47
 * @Description:
 */
public class FirstThread {
    public static void main(String[] args) {
        Runnable runnable1 =new MyFirstThread("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg" ,"D:\\idea-file\\javaDemo02\\src\\ManyThreads\\Files/1.jpg");
        Runnable runnable2 =new MyFirstThread("http://b-ssl.duitang.com/uploads/item/201503/14/20150314212812_kCLmy.jpeg" ,"D:\\idea-file\\javaDemo02\\src\\ManyThreads\\Files/2.jpg");
        Runnable runnable3 =new MyFirstThread("http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg" ,"D:\\idea-file\\javaDemo02\\src\\ManyThreads\\Files/3.jpg");
        new Thread(runnable1 ,"001").start();
        new Thread(runnable2 ,"002").start();
        new Thread(runnable3 ,"003").start();
    }
}
class MyFirstThread implements Runnable{

    private String url;
    private String name;

    public MyFirstThread(String url ,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        download(this.url ,this.name);
    }

    public void download(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url) ,new File(name));
            System.out.println(name);
        } catch (IOException e) {
            System.out.println("下载失败");
        }
    }
}
