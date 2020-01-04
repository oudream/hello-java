package JavaIO.RandomAccessFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

/**
 * 说明：随机读写流
 *
 * @Auther: 11432_000
 * @Date: 2018/12/22 14:49
 * @Description:
 */
public class TsetMain {
    public static void main(String[] args)throws Exception {
        SplitFile splitFile = new SplitFile();
        splitFile.init(10 ,"D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\Files" , "D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\BS_PIC_2018-09-15-22-08-42.png");
        splitFile.split();
        splitFile.merge("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\2.png");
    }
}


