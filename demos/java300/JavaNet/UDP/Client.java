package JavaNet.UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * 说明：接收端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 16:10
 * @Description:
 */
public class Client {

    public static void main(String[] args) throws Exception {
        receiveFile();
    }

    public static void receiveFile()throws IOException{
        System.out.println("接受文件中。。。");
        DatagramSocket datagramSocket = new DatagramSocket(9876);
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024 * 10] ,0 ,1024 * 10);
        datagramSocket.receive(datagramPacket);
        byte[] data = datagramPacket.getData();
        BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(new File("D:\\idea-file\\javaDemo02\\src\\JavaNet\\Files\\copy.jpg")));
        fileOutputStream.write(data , 0 ,data.length);
        fileOutputStream.flush();
        fileOutputStream.close();
        datagramSocket.close();
    }

    public static void receiveObject()throws IOException,ClassNotFoundException{
        DatagramSocket datagramSocket = new DatagramSocket(9876);
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024] ,0 ,1024);
        datagramSocket.receive(datagramPacket);
        byte[] data = datagramPacket.getData();
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(data)));
        Object o = objectInputStream.readObject();
        if (o instanceof Man){
            System.out.println(o);
        }
    }

    public static void receiveData()throws IOException{
        System.out.println("接收方启动....");
        DatagramSocket datagramSocket =new DatagramSocket(9876);
        byte[] datas = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(datas , 0 ,datas.length);
        datagramSocket.receive(datagramPacket);
        byte[] data = datagramPacket.getData();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data ,0 ,data.length);
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(byteArrayInputStream));
        int i = dataInputStream.readInt();
        boolean b = dataInputStream.readBoolean();
        char c = dataInputStream.readChar();
        System.out.println(i);
        System.out.println(b);
        System.out.println(c);
        datagramSocket.close();
    }


    public static void getMsg()throws IOException {
        System.out.println("接收方启动....");
        //使用DatagramSocket，指定端口、地址，创建接收端。
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //准备容器接收DatagramPacket包。
        byte[] data = new byte[1024 * 60];
        DatagramPacket datagramPacket = new DatagramPacket(data ,0 ,data.length);
        //阻塞式接收数据包receive。
        datagramSocket.receive(datagramPacket);
        //获取数据。
        byte[] data1 = datagramPacket.getData();
        //获取数据长度
        int length = datagramPacket.getLength();
        //获取地址
        InetAddress address = datagramPacket.getAddress();
        //获取端口
        int port = datagramPacket.getPort();
        //获取
        SocketAddress socketAddress = datagramPacket.getSocketAddress();
        System.out.println(new String(data1,"UTF-8"));
        //释放资源。
        datagramSocket.close();
    }
}
