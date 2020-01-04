package MyUtils;

import JavaIO.RandomAccessFile.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/17 14:50
 * @Description:
 */
public class DateUtils {

    public static String getTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static long getLongByDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(str).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}
