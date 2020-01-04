package JavaBasics.StudyJava.Map;

/**
 * 说明：桶排序
 *
 * @Auther: 11432_000
 * @Date: 2018/12/18 11:10
 * @Description:
 */
public class BucketSorting {

    public static void main(String[] args) {
//        int a[] = {8,2,3,4,3,6,6,3,9};
//        bucketSort(a ,10);
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i] + " ");
//        }
    }

    public static void bucketSort(int[] a, int max) {
        int[] buckets;
        if (a==null || max<1){ return ;}
        // 创建一个容量为max的数组buckets，并且将buckets中的所有数据都初始化为0。
        buckets = new int[max];
        for(int i = 0; i < a.length; i++){
            buckets[a[i]]++;
        }
        // 2. 排序
        for (int i = 0, j = 0; i < max; i++) {
            while( (buckets[i]--) >0 ) {
                a[j++] = i;
            }
        }
        buckets = null;
    }

}
