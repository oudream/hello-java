package JavaNet.InetSocketAddress;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 14:32
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        //是InetAddress子类，包含端口
        InetSocketAddress localhost = new InetSocketAddress("localhost", 8080);
        //获取端口和地址
        int port = localhost.getPort();
        InetAddress address = localhost.getAddress();
        System.out.println(localhost);
    }
}
