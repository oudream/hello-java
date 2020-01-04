package JavaIO.ByteStream.DataStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * 说明：基本数据流
 *
 * @Auther: 11432_000
 * @Date: 2018/12/20 14:37
 * @Description:
 */
public class TestMain {
    public static void main(String[] args)throws Exception {
        test();
    }

    public static void test()throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        outputStream.writeUTF("窝窝头");
        outputStream.writeInt(10);
        outputStream.writeBoolean(true);
        //
        outputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(byteArrayInputStream));
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readInt());
        System.out.println(dataInputStream.readBoolean());
        dataInputStream.close();
        outputStream.close();
    }
}
