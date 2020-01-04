package JavaIO.ByteStream.ByteArrayStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * 说明：字节数组流
 *
 * @Auther: 11432_000
 * @Date: 2018/12/20 14:59
 * @Description:
 */
public class TestMain {

    public static void main(String[] args)throws Exception {
        byte[] bytes = "wo shi ma yao".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        int read;
        while((read = inputStream.read()) > -1){
            System.out.print((char) read);
        }
        System.out.println();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytes , 0 , bytes.length - 1);
        byte[] bytes1 = outputStream.toByteArray();
        System.out.println(new String(bytes1));
    }
}
