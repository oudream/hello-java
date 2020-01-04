package JavaBasics.StudyJava.File;

import java.io.File;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/12 15:26
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\11432_000.000.000\\Desktop\\编程");
        showAllFile(file ,"");
    }
    public static void showAllFile(File file ,String str){
        if (file.isDirectory()){
            System.out.println(str + file.getName());
            str = str + "      ";
            File[] files = file.listFiles();
            for (File f : files){
                showAllFile(f ,str);
            }
        }
        if (file.isFile()){
            System.out.println(str + file.getName());
        }

    }

}
