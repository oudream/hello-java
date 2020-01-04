package JDBC.Affair;

import MyUtils.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 说明：事务测试
 *
 * @Auther: 11432_000
 * @Date: 2019/2/14 15:38
 * @Description:
 */
public class Test {
    public static void main(String[] args)throws ClassNotFoundException,InterruptedException {
        Connection connection = null;
        //事务处理
        try{
            connection = SqlUtil.getConnection();
            //设置手动提交，不提交不修改数据库
            connection.setAutoCommit(false);
            //执行第一条SQL
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO user_login(user_account,user_password) values(?,?)");
            ps1.setObject(1,"CNM");
            ps1.setObject(2,"WOCAO");
            ps1.executeUpdate();
            //模拟延迟
            Thread.sleep(1000);
            //执行第2条SQL（抛出异常）
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO user_login(user_account,user_password) values(?,?,?)");
            ps2.setObject(1,"MD");
            ps2.setObject(2,"WOCAO");
            ps2.executeUpdate();
            //提交执行结果
            connection.commit();
        }catch (SQLException e){
            try {
                //回滚事务,还原数据库到第一条SQL执行前。
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
