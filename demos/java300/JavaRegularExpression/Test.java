package JavaRegularExpression;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/3/3 09:54
 * @Description:
 */
@SuppressWarnings("all")
public class Test {


    public static void main(String[] args) {
        test03();
    }


    public static void test01(){
        //构建正则表达式
        Pattern pattern = Pattern.compile("((0\\d{2,3}-)?\\b\\d{7,8}\\s)|(1[358]\\d{9}\\b)");
        //创建Matcher对象，需要目标字符串
        Matcher matcher = pattern.matcher("7055051 022-7846350 15222355009");
        //尝试匹配整个字符串
//        boolean matches = matcher.matches();
        boolean b;
        //查找下一个匹配的字符串
        b = matcher.find();
        while (b = matcher.find()){
            System.out.print(b);
            System.out.println(matcher.group());
        }
    }

    public static void test02(){
        Pattern pattern = Pattern.compile("([a-z]+)([0-9]*)");

        Matcher matcher = pattern.matcher("asds215*adfff&a454545415");

        matcher.find();
        //获取匹配到的整个字符串
        System.out.println(matcher.group());
        //获取第一个捕获组（括号）匹配到的字符串
        System.out.println(matcher.group(1));
        //获取第二个捕获组（括号）匹配到的字符串
        System.out.println(matcher.group(2));
    }

    public static void test03(){
        String str = "asds215*adfff&a454545415llkk";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        //替换匹配到的字符串
        String newStr = matcher.replaceAll("#");
        //分割字符串
        String[] split = str.split("\\d+");
        System.out.println(Arrays.toString(split));
        
    }
}
