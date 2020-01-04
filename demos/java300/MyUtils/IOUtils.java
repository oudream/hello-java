package MyUtils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/17 13:22
 * @Description:
 */
public class IOUtils {

    public static void coles(Closeable... closeables){
        for (Closeable closeable : closeables){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
