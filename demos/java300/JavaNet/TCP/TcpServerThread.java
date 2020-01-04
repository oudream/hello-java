package JavaNet.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：服务器线程
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 14:28
 * @Description:
 */
public class TcpServerThread implements Runnable{

    public Socket accept;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private static final String USER_NAME = "mayao";
    private static final String PASSWORD = "19970510951";

    public TcpServerThread(Socket accept) {
        this.accept = accept;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(accept.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(accept.getOutputStream()));
        }catch (Exception e){}
    }

    @Override
    public void run() {
        try {
            boolean isYes = false;
            //获取参数列表
            Map<String, String> parameter = getParameter();
            //验证
            String username = parameter.get("username");
            String password = parameter.get("password");
            if (username != null && USER_NAME.equals(username)){
                if (password != null && PASSWORD.equals(password)){
                    isYes = true;
                    System.out.println("登录cg");
                }
            }
            System.out.println("用户名:" + username + "--" + "密码:" + password);
            //返回结果
            sendMsg(isYes);
            Thread.sleep(3000);
            close();
        }catch (Exception e){}
    }

    private Map<String,String> getParameter()throws IOException{
        String para = dataInputStream.readUTF();
        Map<String,String> map = new HashMap<>();
        String[] strings = para.split("&");
        for (String a : strings){
            String[] split = a.split("=");
            map.put(split[0] ,split[1]);
        }
        return map;
    }

    public void sendMsg(String string)throws IOException {
        dataOutputStream.writeUTF(string);
        dataOutputStream.flush();
    }

    public void sendMsg(Boolean string)throws IOException {
        dataOutputStream.writeBoolean(string);
        dataOutputStream.flush();
    }

    public void close()throws IOException{
        dataInputStream.close();
        dataOutputStream.close();
    }
}
