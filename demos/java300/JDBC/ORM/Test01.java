package JDBC.ORM;

import MyUtils.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/2/21 15:51
 * @Description:
 */
public class Test01 {

    public static void main(String[] args) throws Exception{
       test01();
    }

    public static void test01()throws SQLException,ClassNotFoundException {
        Map<String,Object> map;
        List<Map<String,Object>> list = new ArrayList<>();
        Connection con = SqlUtil.getConnection();
        PreparedStatement ps = con.prepareStatement("select Sname,Sage,Ssex from Student");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            map = new HashMap<>();
            map.put("姓名",resultSet.getObject(1));
            map.put("年龄",resultSet.getObject(2));
            map.put("性别",resultSet.getObject(3));
            list.add(map);
        }
        Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()){
            map = iterator.next();
            Set<String> set = map.keySet();
            for (String s : set){
                System.out.print(s + "-->" +map.get(s) + "\t");
            }
            System.out.println();
        }
        SqlUtil.close(resultSet,con,ps);
    }
}
