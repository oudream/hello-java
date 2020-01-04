package JavaBasics.StudyJava.Date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 说明：CalendarTest
 *
 * @Auther: 11432_000
 * @Date: 2018/12/12 09:27
 * @Description:
 */
public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2018,1,12);
        int year = calendar.get(Calendar.YEAR);
        //0-11月,对应1-12月
        int month = calendar.get(Calendar.MONTH);
        //星期1-7
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(date);

        calendar.set(Calendar.YEAR,2000);
        //计算
        calendar.add(Calendar.DAY_OF_MONTH,100);
        System.out.println(calendar);
        //转换
        Date time = calendar.getTime();
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(new Date());
    }
}
