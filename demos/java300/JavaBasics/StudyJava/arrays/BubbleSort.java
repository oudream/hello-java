package JavaBasics.StudyJava.arrays;

import java.util.Random;

/**
 * 说明：冒泡排序及基本优化
 *
 * @Auther: 11432_000
 * @Date: 2018/12/11 10:20
 * @Description:
 */
public class BubbleSort {

    public static void main(String[] args) {
        bubbleSort();
    }

    public static int[] bubbleSort(){
        Random random = new Random();
        boolean flag = false;
        int[] ints = new int[20];
        int max;
        for (int i = 0; i < 20; i++) {
            ints[i] = random.nextInt(100);
        }
        for (int i = 0; i < ints.length; i++) {
            flag = false;
            for (int j = 0; j < ints.length - 1 - i; j++) {
                if (ints[j] > ints[j + 1]){
                    max = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = max;
                    flag = true;
                }
            }
            if (!flag){ break; }
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(ints[i] + "  ");
        }
        return ints;
    }
}
