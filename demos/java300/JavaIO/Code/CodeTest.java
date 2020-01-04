package JavaIO.Code;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/19 16:04
 * @Description:
 */
public class CodeTest {
    public static void main(String[] args) throws Exception{
        String str = "Mayao麻尧";
        byte[] bytes = str.getBytes("GBK");
        System.out.println(bytes.length);
        String s = new String(bytes ,"GBK");
        System.out.println(s);
    }
}
