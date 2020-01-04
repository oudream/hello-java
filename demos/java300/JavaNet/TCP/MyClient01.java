package JavaNet.TCP;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 说明：单向客户端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 13:06
 * @Description:
 */
public class MyClient01 {

    public static void main(String[] args)throws Exception {
        //建立连接
        Socket socket = new Socket("localhost" ,8888);
        //获取输入输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
        dataOutputStream.writeUTF("你们吼啊");
        //释放资源
        dataOutputStream.close();
        socket.close();
    }
}
