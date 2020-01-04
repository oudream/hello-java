package JavaIO.CharacterStream.BufferedStream;

import java.io.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 10:46
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {
        File file = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\abc.txt");
        File newFile = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\a.txt");
        String read = read(file);
        System.out.println(read);
        write(newFile ,read);
    }

    public static String read(File file){
        StringBuilder str = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));){
            String line;
            //读取一行，自动识别换行符
            while ((line = reader.readLine()) != null){
                str.append(line + "\r\n");
            }
        }catch (Exception e){

        }
        return str.toString();
    }

    private static void write(File file ,String value){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
            int startIndex ,endIndex;
            startIndex = 0;
            while ((endIndex = value.indexOf("\r\n" ,startIndex)) != -1){
                writer.write(value ,startIndex + 1 ,(endIndex - startIndex - 1));
                startIndex = endIndex + 1;
                //在文件中输入换行
                writer.newLine();
            }
        }catch (Exception e){

        }
    }
}
