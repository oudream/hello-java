package MyUtils;

import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/2/14 14:56
 * @Description:
 */
public class SqlUtil {

    //读取和处理资源文件
    static Properties properties = null;

    static {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("JDBC/db.propreties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("mysqlDriver"));
        Connection connection = DriverManager.getConnection(properties.getProperty("mysqlURL"),
                properties.getProperty("mysqlUser"), properties.getProperty("mysqlPwd"));
        return connection;
    }

    public static void close(AutoCloseable... closeables){
        for (AutoCloseable autoCloseable : closeables){
            if (autoCloseable != null){
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
