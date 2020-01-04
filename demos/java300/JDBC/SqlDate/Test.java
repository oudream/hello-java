package JDBC.SqlDate;

import MyUtils.SqlUtil;
import MyUtils.DateUtils;

import java.sql.*;
import java.util.Random;

/**
 * 说明：测试时间
 *
 * @Auther: 11432_000
 * @Date: 2019/2/20 13:57
 * @Description:
 */
public class Test {
    public static void main(String[] args)throws Exception {
        System.out.println(DateUtils.getTime(new java.util.Date(DateUtils.getLongByDate("2019-2-19 21:00:00"))));
        test03();
    }


    /**添加一条数据*/
    public static void test01()throws Exception{
        Connection connection = SqlUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("INSERT INTO user_login(user_account,user_password," +
                "user_date,user_time,user_timestamp) values(?,?,?,?,?)");
        ps.setString(1,"张三");
        ps.setString(2,"123456789");
        ps.setObject(3,new java.sql.Date(System.currentTimeMillis()));
        ps.setObject(4,new Time(System.currentTimeMillis()));
        ps.setObject(5,new Timestamp(System.currentTimeMillis()));
        ps.executeUpdate();
        connection.commit();
    }

    /** 添加100条随机时间的数据*/
    public static void test02()throws Exception{
        Connection connection = SqlUtil.getConnection();
        Random random = new Random();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        for (int i = 0; i < 100; i++) {
            int rand = 10000000 + random.nextInt(100000000);
            statement.addBatch("INSERT INTO user_login(user_account,user_password," +
                    "user_date,user_time,user_timestamp) values('张三','123456',"
                    + "'" + new Date(System.currentTimeMillis() - rand) + "',"
                    + "'" + new Time(System.currentTimeMillis()) + "',"
                    + "'" + new Timestamp(System.currentTimeMillis() - rand) + "')");
        }
        statement.executeBatch();
        connection.commit();
        statement.close();
        connection.close();
    }

    /**取出指定时间段的数据*/
    public static void test03()throws Exception{
        Connection connection = SqlUtil.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement("select * from user_login where user_timestamp > ? and user_timestamp < ? order by user_timestamp");
        ps.setObject(1,new Timestamp(DateUtils.getLongByDate("2019-2-19 12:00:00")));
        ps.setObject(2,new Timestamp(DateUtils.getLongByDate("2019-2-19 21:00:00")));
        ResultSet resultSet = ps.executeQuery();
        int i = 0;
        while (resultSet.next()){
            System.out.println("姓名：" + resultSet.getObject(2) +"  " + "日期：" + resultSet.getObject(4) + "   " +
                    "时间：" + resultSet.getObject(5) +
                    "     Timestemp：" + resultSet.getObject(6));
            i ++;
        }
        System.out.println(i);
        ps.close();
        connection.close();
    }

}
