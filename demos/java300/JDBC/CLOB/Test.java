package JDBC.CLOB;

import MyUtils.SqlUtil;

import java.io.*;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 说明：测试文本大对象的使用
 *
 * @Auther: 11432_000
 * @Date: 2019/2/20 16:43
 * @Description:
 */
public class Test {

    public static void main(String[] args)throws Exception {
        test02();
    }

    public static void test01()throws Exception{
        Connection connection = SqlUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("insert into user_login(user_account,user_password,user_info) values (?,?,?)");
        ps.setObject(1,"麻尧");
        ps.setObject(2,"123654");
        //将文件以流的方式写入数据库
        ps.setClob(3,new FileReader("C:\\Users\\11432_000.000.000\\Desktop\\编程\\笔记及资料\\数据库.txt"));
//        ps.setClob(3,new BufferedReader(new InputStreamReader(new ByteArrayInputStream("我是马哟啊".getBytes()))));
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
        while(resultSet.next()){
            //获取大文本对象
            Clob c = resultSet.getClob("user_info");
            //获取字符流
            BufferedReader stream = new BufferedReader(c.getCharacterStream());
            String len;
            while ((len = stream.readLine())!= null){
                System.out.println(len);
            }
            stream.close();
        }
        ps.close();
        connection.close();
    }
}
