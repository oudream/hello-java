package JavaBasics.StudyJava.MathTest;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/27 14:13
 * @Description:
 */
public class TestMain {

    public static void main(String[] args){
//        BigDecimalTest();
//        BitOperation();
        System.out.println(Math.sqrt(4));
    }

    private static void BigDecimalTest(){
        float f1 = 1.0235f;
        double v = 1.0235;
        BigDecimal bigDecimal = BigDecimal.valueOf(f1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(v);
        System.out.println(bigDecimal);
        System.out.println(bigDecimal1);
    }

    private static void BitOperation(){
        int a = 3;
        int b = 5;
        System.out.println(a&b);
        System.out.println(a|b);
        System.out.println(a^b);
        System.out.println(a>>1);
        System.out.println(a<<2);
        System.out.println(~b);
    }

    private static void ScannerTest(){
       Scanner sc = new Scanner(System.in);
    }
}
