package JavaNet.TCP;

import JavaIO.RandomAccessFile.Data;
import MyUtils.FileUtils;

import java.io.*;
import java.net.Socket;

/**
 * 说明：文件上传-客户端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 13:41
 * @Description:
 */
public class FileClient {

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket("localhost" ,8888);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        byte[] data = FileUtils.getFileDataByFileName("D:\\idea-file\\javaDemo02\\src\\JavaNet\\Files\\115截图20180328225528.png");
        bufferedOutputStream.write(data ,0 ,data.length);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        socket.close();
    }


}
