package JavaIO.CharacterStream.FileStream;

import java.io.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/20 10:34
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        File file = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\abc.txt");
        try {
            fileRead(file);
            fileWrite(file ,"麻尧" ,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileRead(File file)throws IOException {
        Reader reader = new FileReader(file);
        char[] flush = new char[10];
        int len;
        while ((len = reader.read(flush)) > -1){
            System.out.println(new String(flush ,0 ,len));
        }
        reader.close();
    }


    public static void fileWrite(File file ,String value ,boolean append)throws IOException {
        Writer writer = new FileWriter(file);
        if (append){
            char[] chars = value.toCharArray();
           for (char c : chars){
               writer.append(c);
           }
        }else {
            writer.write(value);
        }
        writer.close();
    }
}
