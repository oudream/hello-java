package JavaNet.ChatRoom;

import MyUtils.DateUtils;
import MyUtils.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 说明：聊天室服务端测试
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 16:47
 * @Description:
 */
public class ServerTest {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("-----服务器启动------");
        while (true){
            Socket accept = serverSocket.accept();
            ServerTest03 serverTest03 = new ServerTest03(accept);
            new Thread(serverTest03).start();
            System.out.println(accept.getInetAddress().getHostAddress() + "连接了服务器");
        }
    }
}
/** 一对一 封装版 */
class ServerTest02 implements Runnable{

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean isRun;

    public ServerTest02(Socket socket) {
        this.socket = socket;
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            this.isRun = true;
        } catch (IOException e) {
            this.isRun = false;
            System.out.println("获取IO流错误");
        }
    }

    @Override
    public void run() {
        while (isRun){
            String newMsg = null;
            newMsg = receiveMsg();
            sendMsg(newMsg);
            release();
        }
    }

    private void sendMsg(String msg){
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            this.isRun = false;
            System.out.println("发送错误");
        }
    }

    private String receiveMsg(){
        String msg = "";
        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            this.isRun = false;
            System.out.println("接收错误");
        }
        return msg;
    }

    private void release(){
        IOUtils.coles(dataInputStream,dataOutputStream);
        this.isRun = false;
    }
}
/** 群聊版 */
class ServerTest03 implements Runnable{

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean isRun;
    private String name;

    private static CopyOnWriteArrayList<ServerTest03> all = new CopyOnWriteArrayList<>();

    public ServerTest03(Socket socket) {
        this.socket = socket;
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            this.isRun = true;
            joinChat();
            name = receiveMsg();
        } catch (IOException e) {
            this.isRun = false;
            System.out.println("获取IO流错误");
        }
    }

    public void joinChat(){
        all.add(this);
    }

    @Override
    public void run() {
        while (isRun){
            String newMsg = receiveMsg();
            System.out.println("转发消息-->" + newMsg);
            sendMsgToAll(newMsg);
        }
        release();
    }

    /** 群聊 */
    private void sendMsgToAll(String msg){
        for (ServerTest03 serverTest03 : all){
            if (serverTest03 == this){
                continue;
            }
            serverTest03.sendMsg(msg);
        }
    }

    private void sendMsg(String msg){
        try {
            msg = name +"   " + DateUtils.getTime(new Date()) +":\r\n" + msg;
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            this.isRun = false;
            System.out.println("发送消息异常");
        }
    }

    private String receiveMsg(){
        String msg = "";
        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            this.isRun = false;
            System.out.println("接收错误");
        }
        return msg;
    }

    private void release(){
        IOUtils.coles(dataInputStream,dataOutputStream);
        this.isRun = false;
    }
}

