package JavaNet.TCP;

import org.apache.log4j.net.SocketServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：登录-服务端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 13:52
 * @Description:
 */
public class LoginServer{
    private static final String USER_NAME = "mayao";
    private static final String PASSWORD = "19970510951";

    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            Socket accept = serverSocket.accept();
            new Thread(new TcpServerThread(accept)).start();
        }
    }

    private static void test()throws Exception{
        boolean isYes = false;
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket accept = serverSocket.accept();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(accept.getInputStream()));
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(accept.getOutputStream()));
        //获取参数列表
        String para = dataInputStream.readUTF();
        Map<String, String> parameter = getParameter(para);
        //验证
        if (parameter.get("username") != null && parameter.get("username").equals(USER_NAME)){
            if (parameter.get("password") != null && parameter.get("password").equals(PASSWORD)){
                isYes = true;
            }
        }
        System.out.println(isYes);
        //返回结果
        dataOutputStream.writeBoolean(isYes);
        dataOutputStream.flush();
        dataInputStream.close();
        dataOutputStream.close();
    }

    //提取参数
    private static Map<String,String> getParameter(String s){
        Map<String,String> map = new HashMap<>();
        String[] strings = s.split("&");
        for (String a : strings){
            String[] split = a.split("=");
            map.put(split[0] ,split[1]);
        }
        return map;
    }
}
