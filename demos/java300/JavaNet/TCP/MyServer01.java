package JavaNet.TCP;

import JavaIO.RandomAccessFile.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 说明：单向服务端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 13:00
 * @Description:
 */
public class MyServer01 {

    public static void main(String[] args)throws IOException {
        //使用ServerSocket创建服务器，并指定端口。
        ServerSocket serverSocket = new ServerSocket(8888);
        //阻塞式的等待客户端的请求。
        Socket accept = serverSocket.accept();
        //获取输入输出流
        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
        String s = dataInputStream.readUTF();
        System.out.println(s);
        //释放
        dataInputStream.close();
    }
}
