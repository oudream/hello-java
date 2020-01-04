package JavaBasics.StudyJava.Collection;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/14 10:19
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
//        MyArrayList<String> myCollection = new MyArrayList<>();
//        for (int i = 0; i < 400; i++) {
//            myCollection.add("" + i);
//        }
//        System.out.println(myCollection.getLength()  + "   " + myCollection.getSize());
//        myCollection.set(52,"wobushi");
////        myCollection.add(0 ,"45");
//        myCollection.remove(399);
//        System.out.println(myCollection);


//        MyLinkList<String> stringMyLinkList = new MyLinkList<>();
//        stringMyLinkList.add("a");
//        stringMyLinkList.add("b");
//        stringMyLinkList.add("c");
//        stringMyLinkList.insert(1 ,"lalalalal");
//        System.out.println(stringMyLinkList);

        MyHashSet<String> hashSet = new MyHashSet();
        hashSet.add("aa");
        hashSet.add("bb");
        hashSet.add("aa");
        System.out.println(hashSet);
    }
}
