package JavaNet.ChatRoom;

import MyUtils.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * 说明：聊天室客户端测试
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 16:36
 * @Description:
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8888);
        System.out.println("客户端启动");
        new Thread(new SayThread(socket ,"麻尧")).start();
        new Thread(new LookThread(socket)).start();
    }
}
/** 输入线程 */
/*class SayThread implements Runnable{

    private DataOutputStream dataOutputStream;
    private Socket socket;
    private BufferedReader reader;
    private boolean isRun;
    private String name;

    public SayThread(Socket socket ,String name) {
        try {
            this.name = name;
            this.socket = socket;
            this.dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(System.in));
            this.isRun = true;
            sayString(name);
        } catch (IOException e) { }
    }

    @Override
    public void run() {
        String msg = "";
        while (isRun){
            msg = readLine();
            sayString(msg);
        }
        release();
    }

    private void sayString(String msg){
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            isRun = false;
            System.out.println("输出异常");
        }
    }

    private String readLine(){
        String msg = "";
        try {
            msg = reader.readLine();
        } catch (IOException e) {
            isRun = false;
            System.out.println("读取控制台输入异常");
        }
        return msg;
    }

    private void release(){
        IOUtils.coles(dataOutputStream,reader);
        this.isRun = false;
    }
}
*//** 输出线程 *//*
class LookThread implements Runnable{

    private DataInputStream dataInputStream;
    private Socket socket;
    private  boolean isRun;
    public LookThread(Socket socket) {
        try {
            this.socket = socket;
            this.dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            this.isRun = true;
        } catch (IOException e) { }
    }

    @Override
    public void run() {
        while (isRun){
            String msg = null;
            try {
                msg = dataInputStream.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                isRun = false;
            }
        }
        release();
    }

    private void release(){
        IOUtils.coles(dataInputStream);
        this.isRun = false;
    }
}*/
