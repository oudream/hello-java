package JavaBasics.StudyJava.Date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：SimpleDateFormat测试
 *
 * @Auther: 11432_000
 * @Date: 2018/12/11 15:20
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss  MM月第WW周 本年第ww周 本年第DDD天kk时mm分ss秒SSS毫秒");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        System.out.println((int)Math.toDegrees(Math.asin(0.5)));

    }
}
