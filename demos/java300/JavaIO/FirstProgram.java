package JavaIO;

import java.io.*;

/**
 * 说明：IO操作步骤：1、选择源。2、选择流。3、选择操作。4、关闭流。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 14:23
 * @Description:
 */
public class FirstProgram {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\abc.txt");
        File file02 = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\a.txt");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream(file02);
        int s;
        byte[] bytes = new byte[1024];
        while ((s = inputStream.read()) > -1){
            System.out.print((char) s);
        }
        outputStream.close();
        inputStream.close();
//        byte[] bytes = new byte[(int) file.length()];
//        int read = inputStream.read(bytes);
//        System.out.println(new String(bytes));
    }
}
