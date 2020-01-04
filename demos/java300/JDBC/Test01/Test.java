package JDBC.Test01;

import java.sql.*;

/**
 * 说明：连接数据库
 *
 * @Auther: 11432_000
 * @Date: 2019/2/3 14:50
 * @Description:
 */
public class Test {

    public static void main(String[] args)throws Exception{

        String username = "root";
        String password = "123456";
        //加载驱动类
        Class.forName("com.mysql.cj.jdbc.Driver");
        long start = System.currentTimeMillis();
        //连接数据库，内含.Socket对象，jdbc:mysql://localhost(主机地址):3306(端口)/test(数据库名称)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?" +
                "useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", username, password);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        /*//执行SQL语句(不安全)
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO user_login(user_account,user_password) values('56','kkkkkkkkk')");
        //预编译SQL（安全）,?为占位符，索引从1开始
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_login(user_account,user_password) values(?,?)");
        //设置占位符的具体值（？的），参数：占位符索引，值
        //包含所有基本数据类型和引用（Object）
        preparedStatement.setString(1,"张三");
        preparedStatement.setString(2,"123456789");
        preparedStatement.execute();
        //执行增删改
        int i = preparedStatement.executeUpdate();*/
        PreparedStatement ps = connection.prepareStatement("select * from user_login where user_account = ?");
        ps.setString(1,"张三");
        //执行返回结果集的sql
        ResultSet resultSet = ps.executeQuery();
        //遍历结果集
        while (resultSet.next()){
            //取出该行的某一列数据
            System.out.println("ID:" + resultSet.getObject(1) );
        }
        resultSet.close();
        ps.close();
        connection.close();
    }
}
