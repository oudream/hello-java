package JavaIO.ByteStream.PrintStream;

import java.io.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/22 13:42
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        test();
    }


    public static void test(){
        File file = new File("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\abc.txt");
        PrintStream ps;
        try (PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)) ,true);){
            printStream.println("打印流");
            printStream.print(true);
            ps = System.out;
            ps.println("打印流");
            ps.print(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
