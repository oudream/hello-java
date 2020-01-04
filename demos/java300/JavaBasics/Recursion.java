package JavaBasics;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/28 13:52
 * @Description:
 */
public class Recursion {
    public static void main(String[] args){
        Recursion recursion = new Recursion();
        recursion.recursion(10);
        int sum = 1;
        for (int i = 1;i <= 10;i++){
            sum *= i;
        }
        System.out.println(sum + "<----");
    }

    public int recursion(int x){
        if (x == 0){ return 1;}
        int sum = x * recursion(x-1);
        System.out.println(sum);
        return sum;
    }
}
