package JavaIO.ByteStream.BufferedStream;

import java.io.*;

/**
 * 说明：字节缓冲流测试 （缓冲区默认为8KB）
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 10:16
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        File file = new File("C:\\Users\\11432_000.000.000\\Desktop\\碧蓝\\BS_PIC_2018-09-15-22-08-06.png");
        File newFile = new File("C:\\Users\\11432_000.000.000\\Desktop\\BS_PIC_2018-09-15-22-08-06.png");
        long start ,end;
        start = System.currentTimeMillis();
        copy(file ,"C:\\Users\\11432_000.000.000\\Desktop\\BS.png");
        end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        JavaIO.ByteStream.FileStream.TestMain.copy(file ,newFile);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void copy(File file ,String newFileName) {
        try(InputStream inputStream = new FileInputStream(file);
            BufferedInputStream buffer = new BufferedInputStream(inputStream)
            ;BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFileName))){
            byte[] bytes = new byte[1024];
            int len;
            while ((len = buffer.read(bytes)) > -1){
                bufferedOutputStream.write(bytes ,0 ,len);
            }
            bufferedOutputStream.close();
            buffer.close();
        }catch (Exception e){

        }
    }
}
