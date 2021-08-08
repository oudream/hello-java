package hello.java.lang.container;

import java.util.Iterator;
import java.util.TreeSet;

class HelloTreeSet {
    public static void helloTreeSet1(int k) {
        TreeSet<String> al=new TreeSet<String>();
        al.add("Ravi");
        al.add("Vijay");
        al.add("Ravi");
        al.add("Ajay");
        //Traversing elements
        Iterator<String> itr=al.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
