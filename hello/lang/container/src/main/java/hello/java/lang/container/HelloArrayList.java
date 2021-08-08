package hello.java.lang.container;

import java.util.ArrayList;
import java.util.Collections;

class HelloArrayList {
    public static void helloArrayList1(int k) {
        String str1 = "Lance";
        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        myNumbers.add(33);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(34);
        myNumbers.add(8);
        myNumbers.add(12);

        Collections.sort(myNumbers);

        for (int i : myNumbers) {
            System.out.println(i);
        }
    }
}
