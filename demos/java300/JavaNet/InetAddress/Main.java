package JavaNet.InetAddress;

import java.net.InetAddress;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 13:57
 * @Description:
 */
public class Main {

    public static void main(String[] args)throws Exception {
        //获取本机InetAddress（主机名 + IP）
        InetAddress localHost = InetAddress.getLocalHost();
        //根据域名获取InetAddress（主机名 + IP）
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        //根据IP获取InetAddress（主机名 + IP）,需要权限。
        InetAddress byName1 = InetAddress.getByName("192.168.56.1");
        System.out.println(byName1);
    }
}
