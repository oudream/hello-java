package hello.db.mysql;

import java.sql.*;
import java.util.Date;

class SimpleA {
    public static void hellobatch1() {
        try {
            Connection conn;
            PreparedStatement stmt;
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://192.168.5.19:3306/db1";
            String user = "root";
            String password = "Aa.123456";
            String sql = "insert into test (id,name,address) values (?,?,?)";
            //String sql = "insert into test values (?,?,?),(?,?,?),(?,?,?)";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            stmt = (PreparedStatement) conn.prepareStatement(sql);
            // 关闭事务自动提交 ,这一行必须加上
            conn.setAutoCommit(false);
            System.out.println(new Date());
            /* —————————————————————————— */
            // ------- 1000,000条 ---------
            //Wed Jul 29 20:22:49 CST 2020
            //Wed Jul 29 20:25:06 CST 2020
            // 耗时;137秒  平均7299条/秒
            for (int i = 1; i <= 1000000; i++) {
                stmt.setString(1, i + "_t");
                stmt.setString(2, "new_num_" + i);
                stmt.setString(3, "西城区" + i + "号路");
                stmt.addBatch(); // 加入批量处理
                if(i % 5000==0){
                    stmt.executeBatch(); // 执行批量处理
                    conn.commit(); // 提交
                }
            }
            System.out.println(new Date());
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void helloSelect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.5.19:3306/db1", "root", "Aa.123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t1");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
//        helloSelect();
        hellobatch1();
    }

}  