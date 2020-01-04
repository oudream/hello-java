package JavaNet.TCP;

import MyUtils.FileUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：文件上传-服务端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 13:52
 * @Description:
 */
public class FileServer {

    public static void main(String[] args)throws Exception {
        boolean isYes = false;
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket accept = serverSocket.accept();
        byte[] data = new byte[1024 * 1];
        InputStream inputStream = new BufferedInputStream(accept.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        while ((len = inputStream.read(data ,0 ,data.length)) > 0){
            byteArrayOutputStream.write(data ,0 ,len);
        }
        byteArrayOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        FileUtils.copyFileByBytesAndNewFileName(bytes ,"D:\\idea-file\\javaDemo02\\src\\JavaNet\\Files\\新.png");
        byteArrayOutputStream.close();
        inputStream.close();
    }
}
