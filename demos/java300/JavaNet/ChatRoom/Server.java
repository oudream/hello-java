package JavaNet.ChatRoom;

import MyUtils.DateUtils;
import MyUtils.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 说明：聊天室服务器
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 16:16
 * @Description:
 */
public class Server{
    public static void main(String[] args)throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("-----服务器启动------");
        while (true){
            Socket accept = serverSocket.accept();
            System.out.println(accept.getInetAddress().getHostAddress() + "连接了服务器");
            ServerThread ServerThread = new ServerThread(accept);
            new Thread(ServerThread).start();
        }
    }
}
/** 服务器线程 */
class ServerThread implements Runnable{

    private static final String SYSTEM_MESSAGE = "系统消息:";

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean isRun;
    private String name;

    private static CopyOnWriteArrayList<ServerThread> all = new CopyOnWriteArrayList<>();

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            this.isRun = true;
            joinChat();
            name = receiveMsg();
            sendMsgToOne("欢迎来到聊天室");
            sendMsgToAll(SYSTEM_MESSAGE + "\"" + name +"\"" + "来到了聊天室");
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
            if (newMsg.equals("")){continue;}
            if (newMsg.equals("#bye")){
                isRun = false;
                break;
            }
            sendMsg(newMsg);
        }
        release();
    }

    /** 群聊 */
    private void sendMsgToAll(String msg){
        for (ServerThread serverThread : all){
            if (serverThread == this){
                continue;
            }
            serverThread.sendMsgToOne(msg);
        }
    }
    private void sendMsgToOne(String msg){
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("发送消息异常");
            this.isRun = false;
        }
    }
    private void sendMsg(String msg){
        if (msg.startsWith("@")){
            int i = msg.indexOf(":");
            String targetName = msg.substring(1,i);
            String youMsg = msg.substring(i + 1 ,msg.length());
            for (ServerThread serverThread : all){
                if (serverThread.getName().equals(targetName)){
                    msg = name + "@你   " + DateUtils.getTime(new Date()) +":\r\n" + youMsg;
                    serverThread.sendMsgToOne(msg);
                    break;
                }
            }
        }else {
            msg = name +"   " + DateUtils.getTime(new Date()) +":\r\n" + msg;
            sendMsgToAll(msg);
        }
    }

    private String receiveMsg(){
        String msg = "";
        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            this.isRun = false;
            if (!socket.isClosed()){
                System.out.println("接收错误");
            }
        }
        return msg;
    }

    //关闭资源
    private void release(){
        IOUtils.coles(dataInputStream,dataOutputStream,socket);
        this.isRun = false;
        all.remove(this);
        sendMsgToAll(SYSTEM_MESSAGE + "\"" + name +"\"" + "离开了聊天室");
    }

    public String getName() {
        return name;
    }
}
