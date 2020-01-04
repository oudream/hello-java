package JavaBasics.StudyJava.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/12 10:02
 * @Description:
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("请输入日期（格式1997-05-10）:");
        Scanner sc = new Scanner(System.in);
        try {
            String s = sc.nextLine();
            calendarShow(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void calendarShow(String input)throws Exception{
        Calendar calendar = new GregorianCalendar();
        Calendar c1 = new GregorianCalendar();
        System.out.println("你刚刚输入的是：" + input);
        System.out.println("日\t一\t二\t三\t四\t五\t六\t");
        //将字符串转换为Date和Calendar
        Date inputDate = getDateByString(input);
        calendar.setTime(inputDate);
        show(calendar);
    }


    public static Date getDateByString(String input)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(input);
    }

    public static void show(Calendar calendar){
        int month ,dayOfWeek, dayOfMonth;
        final int NOW_DAY = calendar.get(Calendar.DAY_OF_MONTH);
        final int NOW_MIONTH =calendar.get(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH ,1);
        do{
            //获取当前是本周的第几天和当前是本月的第几天
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            //将1号对应到正确的星期数
            if (dayOfMonth == 1){
                for (int i = 0; i < dayOfWeek; i++) {
                    System.out.print("\t");
                }
            }
            //日期加1天，并获取当前日期的月份
            calendar.add(Calendar.DAY_OF_MONTH ,1);
            month = calendar.get(Calendar.MONTH);
            if (dayOfMonth == NOW_DAY){
                System.out.print(dayOfMonth + "*" + "\t");
            }else {
                System.out.print(dayOfMonth + "\t");
            }
            //周日是每周的第一天
            if (dayOfWeek == 6){
                System.out.println();
            }
        }while (month == NOW_MIONTH);
    }
}
