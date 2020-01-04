package MyUtils;

import java.io.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/15 14:30
 * @Description:
 */
public class FileUtils {

    public static byte[] getFileDataByFileName(String fileName)throws IOException {
        File file = new File(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] flush = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(flush)) > 0){
            byteArrayOutputStream.write(flush ,0 ,len);
        }
        byteArrayOutputStream.flush();
        bufferedInputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean copyFileByBytesAndNewFileName(byte[] data ,String newFileName)throws IOException{
        File newFile = new File(newFileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFile));
        bufferedOutputStream.write(data ,0 ,data.length);
        bufferedOutputStream.close();
        if (newFile==null || newFile.exists() || !newFile.isFile()){
            return false;
        }
        return true;
    }
}
