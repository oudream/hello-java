package JavaBasics.StudyJava.arrays;

import java.util.Scanner;

/**
 * 说明：折半查找(数组有序)
 *
 * @Auther: 11432_000
 * @Date: 2018/12/11 10:49
 * @Description:
 */
public class BinaryChop {

    public static void main(String[] args) {
        int[] ints = BubbleSort.bubbleSort();
        int mid = 0;
        int find ,oldMid = 0;
        Scanner scanner = new Scanner(System.in);
        find = scanner.nextInt();
        int head = 0;
        int tail = ints.length;
        while(ints[mid] != find){
            mid = (head + tail) / 2;
            if (ints[mid] > find){
                tail = mid;
            }
            if (ints[mid] < find){
                head = mid;
            }
            if (tail - head <= 1){
                mid = -1;
                break;
            }
        }
        System.out.println(find + "位置:" + mid);
    }
}
