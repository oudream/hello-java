package JDBC.BLOB;

import MyUtils.SqlUtil;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/2/21 13:58
 * @Description:
 */
public class Test {
    public static void main(String[] args)throws Exception {
        test02();
    }

    public static void test01()throws Exception{
        Connection connection = SqlUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("insert into user_login(user_account,user_password,user_file) values (?,?,?)");
        ps.setObject(1,"麻尧");
        ps.setObject(2,"123456");
        ps.setBlob(3,new FileInputStream("D:\\idea-file\\javaDemo02\\src\\JavaIO\\T\\BS_PIC_2018-09-15-22-08-42.png"));
        ps.executeUpdate();
        connection.commit();
        ps.close();
        connection.close();
    }

    public static void test02()throws Exception{
        Connection connection = SqlUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("select * from user_login where user_account = ?");
        ps.setObject(1,"麻尧");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            Blob user_file = resultSet.getBlob("user_file");
            InputStream stream = user_file.getBinaryStream();
            File file = new File("D:\\idea-file\\javaDemo02\\src\\JDBC\\BLOB\\2.png");
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[1024];
            int len;
            while((len = stream.read(bytes)) > 0){
                outputStream.write(bytes,0,len);
            }
            stream.close();
            outputStream.close();
        }
        connection.commit();
        ps.close();
        connection.close();
    }
}
