package JavaNet.TCP;

import org.apache.log4j.net.SocketServer;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/**
 * 说明：登录-客户端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 13:41
 * @Description:
 */
public class LoginClient {

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket("localhost" ,8888);
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        System.out.println("请输入用户名");
        String username = reader.readLine();
        System.out.println("请输入密码");
        String password = reader.readLine();
        outputStream.writeUTF("username=" + username + "&" + "password=" + password);
        outputStream.flush();
        reader.close();
        //获取结果
        boolean b = dataInputStream.readBoolean();
        if (b){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
        dataInputStream.close();
        outputStream.close();
        socket.close();
    }
}
