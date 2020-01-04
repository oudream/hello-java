package JavaIO.ByteStream.FileStream;

import java.io.*;

/**
 * 说明：文件字节流测试。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 17:21
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\abc.txt");
        in(file);
        out(file ,"6666666666666\n\r");
    }

    public static void in(File file)throws Exception{
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) > -1){
            String str = new String(bytes ,0 ,len);
            System.out.print(str + "\t");
        }
        inputStream.close();
    }

    public static void out(File file, String value)throws IOException{
        OutputStream outputStream = new FileOutputStream(file,true);
        byte[] bytes = value.getBytes();
        outputStream.write(bytes ,0 ,bytes.length - 1);
        outputStream.close();
    }

    public static void copy(File file ,File newFile)throws IOException{
        InputStream in = new FileInputStream(file);
        OutputStream out = new FileOutputStream(newFile);
        byte[] flush = new byte[1024];
        int len;
        while ((len = in.read(flush)) > -1) {
            out.write(flush, 0, len);
            out.flush();
        }
        out.close();
        in.close();
    }
}
