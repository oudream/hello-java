package JavaBasics.StudyJava.Exception;

import java.io.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/13 13:49
 * @Description:
 */
public class Exception {
    public static void main(String[] args){
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile()throws IOException{
        char[] bytes;
        Reader reader = null;
        try {
            File file = new File("D:\\高质量本子\\Tony游戏.txt");
            reader = new FileReader(file);
            bytes = new char[(int)(file.length() * 2)];
            reader.read(bytes);
            for (char c : bytes){
                System.out.print(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
