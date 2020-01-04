package JavaIO.ByteStream;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 16:45
 * @Description:
 */
public class InputAndOutput {

    public static void main(String[] args)throws Exception{
        InputStream in = null;
        OutputStream out = null;
        //1、读一个字节，返回该字节。 2、对一个字节数组，返回读取的字节数(可以有起始位置)。
        in.read();
        in.read(new byte[1024],0 ,100);
        //关闭流,释放系统资源
        in.close();
        //标记当传入参数的位置
        in.mark(10);
        //将指针跳转到mark方法标记的位置
        in.reset();
        //判断该流是否支持reset和mark方法
        in.markSupported();


        //1、输出一个字节  2、输出一个字节数组(可以有起始位置)。
        out.write((byte) 201);
        out.write(new byte[1024] ,0 ,100);
        //刷新内存，将内存上的数据真正的写入文件。
        out.flush();
        //关闭流,释放系统资源
        out.close();
    }
}
