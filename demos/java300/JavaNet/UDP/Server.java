package JavaNet.UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 说明：发送端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 16:10
 * @Description:
 */
public class Server {
    public static void main(String[] args)throws Exception {
        sendFile("D:\\idea-file\\javaDemo02\\src\\JavaNet\\Files\\115截图20180328225528.png");
    }

    /** 发送文件 */
    public static void sendFile(String filePath)throws IOException{
        DatagramSocket datagramSocket = new DatagramSocket(22222);
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 10];
        int len;
        while ((len = fileInputStream.read(buffer)) > 0){
            outputStream.write(buffer , 0 ,len);
        }
        outputStream.flush();
        byte[] bytes = outputStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(bytes ,0,bytes.length ,new InetSocketAddress("localhost" ,9876));
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
        outputStream.close();
        fileInputStream.close();
    }
    /** 发送对象 */
    public static void sendObject()throws IOException{
        System.out.println("发送方启动。。。。");
        Man man = new Man("mayao", "男", 21);
        DatagramSocket datagramSocket = new DatagramSocket(22222);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        outputStream.writeObject(man);
        outputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(bytes ,0 ,bytes.length ,new InetSocketAddress("localhost",9876));
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
    /** 发送基本数据 */
    public static void sendData()throws IOException{
        System.out.println("发送方启动。。。。");
        DatagramSocket datagramSocket = new DatagramSocket(22222);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream(1024);
        DataOutputStream dataStream = new DataOutputStream(new BufferedOutputStream(byteArray));
        dataStream.writeInt(102344);
        dataStream.writeBoolean(true);
        dataStream.writeChar('s');
        dataStream.flush();
        byte[] datas = byteArray.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(datas , 0 ,datas.length ,new InetSocketAddress("localhost" ,9876));
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }

    /** 发送文字 */
    public static void msgSend()throws IOException{
        System.out.println("发送方启动。。。。");
        InetAddress inetAddress = InetAddress.getByName("192.168.56.1");
        //使用DatagramSocket，指定端口、地址，创建接收端。
        DatagramSocket datagramSocket = new DatagramSocket(12222);
        //准备数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("" +
                "D:\\idea-file\\javaDemo02\\src\\JavaNet\\Files\\a.txt"))));
        String info = null;
        byte[] data = new byte[(int) (new File("D:\\idea-file\\javaDemo02\\src\\JavaNet\\Files\\a.txt").length() * 1.5)];
        int a = 0;
        while ((info = reader.readLine()) != null){
            byte[] bytes = info.getBytes("UTF-8");
            System.arraycopy(bytes ,0 ,data,a + 1,bytes.length );
            a += bytes.length;
        }
        //封装到DatagramPacket
        DatagramPacket datagramPacket = new DatagramPacket(data ,0 ,data.length ,
                new InetSocketAddress("localhost" ,9999));
        //发送
        datagramSocket.send(datagramPacket);
        //释放资源。
        datagramSocket.close();
    }
}

