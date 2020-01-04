package JavaIO.CharacterStream;

import java.io.Reader;
import java.io.Writer;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 16:56
 * @Description:
 */
public class WriterAndReader {
    public static void main(String[] args) throws Exception{
        Writer writer = null;
        Reader reader = null;

        //1、读取一个字符 2、读取一个字符串（可以没有起始位置）
        reader.read();
        reader.read(new char[1024] ,0 ,100);
        //关闭流
        reader.close();
        //1、输出一个字符  2、输出一个字符数组(可以没有起始位置)。
        writer.write('d');
        writer.write(new char[1024] ,0 ,100);
        writer.write("" ,0 ,12);
        //在文件尾部添加一个字符
        writer.append("");
    }
}
