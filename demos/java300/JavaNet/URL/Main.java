package JavaNet.URL;

import java.net.URL;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 14:46
 * @Description:
 */
public class Main {

    public static void main(String[] args) throws Exception{
        //构造器
        URL url = new URL("https://localhost:80/question/208944843.html?name=mayao#a");
        //获取协议
        String protocol = url.getProtocol();
        //获取域名
        String host = url.getHost();
        //获取端口
        int port = url.getPort();
        //获取/question/208944843.html?name=mayao
        String file = url.getFile();
        //获取资源路径
        String path = url.getPath();
        //获取参数
        String query = url.getQuery();
        //获取锚点
        String ref = url.getRef();
        System.out.println(protocol);
        System.out.println(host);
        System.out.println(port);
        System.out.println(file);
        System.out.println(path);
        System.out.println(query);
        System.out.println(ref);
    }
}
