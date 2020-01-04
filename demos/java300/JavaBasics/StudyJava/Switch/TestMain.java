package JavaBasics.StudyJava.Switch;

import java.util.Random;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/28 09:27
 * @Description:
 */
public class TestMain {
    public static void main(String[] args){
        Random random = new Random();
        int anInt = random.nextInt(26);
        char ch = 'a';
        switch ((char)(ch + anInt)){
            case 'm':
            case 'a':
            case 'y':
            case 'o':
                System.out.println("我需要");
                break;
                default:
                    System.out.println("不需要");
        }
    }
}
