package JavaIO.ByteStream.ByteArrayStream;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

/**
 * 说明：使用字节数组将文件存入内存，再从内存读取到文件。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/20 16:20
 * @Description:
 */
public class Copy {

    public static void main(String[] args) {
        try {
            byte[] bytes = fileToByteArray("C:\\Users\\11432_000.000.000\\Desktop\\图片\\115图片20180414115942.jpg");
            System.out.println(bytes.length);
            byteArrayToFile(bytes ,"C:\\Users\\11432_000.000.000\\Desktop\\115图片.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] fileToByteArray(String path)throws IOException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] flush = new byte[1024];
        int len;
        while ((len = inputStream.read(flush)) > -1){
            outputStream.write(flush, 0 ,len);
        }
        outputStream.flush();
        inputStream.close();
        return outputStream.toByteArray();
    }

    public static void byteArrayToFile(byte[] bytes ,String path)throws IOException{
        File file = new File(path);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        OutputStream outputStream = new FileOutputStream(file ,false);
        byte[] flush = new byte[1024];
        int len;
        while ((len = inputStream.read(flush)) > -1){
            outputStream.write(flush ,0 ,len);
        }
        outputStream.flush();
        outputStream.close();
    }


}
