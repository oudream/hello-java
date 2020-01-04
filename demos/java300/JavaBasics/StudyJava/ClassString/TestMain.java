package JavaBasics.StudyJava.ClassString;

/**
 * 说明：String 常用函数
 *
 * @Auther: 11432_000
 * @Date: 2018/12/10 15:41
 * @Description:
 */
public class TestMain {

    public static void main(String[] args){
        String str1 = "mayao";
        String str2 = "liushe";
        //返回索引处的字符。
        System.out.println(str1.charAt(2));
        //替换
        System.out.println(str1.replace("ma" ,""));
        //头尾检查是否有字符串
        System.out.println(str1.endsWith("o") + "" + str1.startsWith("s"));
        //去除首尾空格
        System.out.println(str1.trim());
    }
}
