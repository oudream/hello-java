package JavaIO.CharacterStream.InputStream;

import java.io.*;
import java.net.URL;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/21 13:40
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {
        download();
    }

    public static void read(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))){
            String msg = "";
            while (!"exit".equals(msg)){
                msg = reader.readLine();
                writer.write(msg);
                writer.newLine();
                writer.flush();
            }
        }catch (Exception e){

        }
    }

    public static void download(){
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(new URL("http://www.baidu.com").openStream() ,"UTF-8"));
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\baidu.html"), "UTF-8"))){
            String len;
            while ((len = reader.readLine()) != null){
                writer.write(len ,0 , len.length());
                writer.newLine();
            }
            writer.flush();
        }catch (Exception e){

        }
    }
}
