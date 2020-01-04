package JDBC.BatchProcessing;

import MyUtils.SqlUtil;

import java.sql.Connection;
import java.sql.Statement;

/**
 * 说明：批处理测试
 *
 * @Auther: 11432_000
 * @Date: 2019/2/14 14:54
 * @Description:
 */
public class Batch {

    public static void main(String[] args) throws Exception{
        Connection connection = SqlUtil.getConnection();
        //关闭自动提交
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        for (int i = 0; i < 10000; i++) {
            //添加sql到批处理
            statement.addBatch("INSERT INTO user_login(user_account,user_password) values('TestData"+ i +
                    "','123456')");
        }
        //执行批处理
        statement.executeBatch();
        //提交
        connection.commit();
        statement.close();
        connection.close();
    }
}
