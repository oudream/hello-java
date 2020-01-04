package JavaBasics.StudyJava.arrays;

import java.util.Arrays;

/**
 * 说明：测试Arrays类
 *
 * @Auther: 11432_000
 * @Date: 2018/12/11 09:59
 * @Description:
 */
public class TestArrays {

    public static void main(String[] args){
        int[] ints = {1,2,3,4,5};
        //字符串化数组
        System.out.println(Arrays.toString(ints));
        //根据comparable的实现进行排序
        Arrays.sort(ints);
        //查找指定元素, 查找失败返回-1
        Arrays.binarySearch(ints ,2);
    }
}
