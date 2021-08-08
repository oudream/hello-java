package hello.java.lang.container;

import java.util.HashMap;
import java.util.Map;

class HelloHashMap {
    public static void helloHashMap1(int k) {
        HashMap<Integer,String> map=new HashMap<Integer,String>();//Creating HashMap
        map.put(1,"Mango");  //Put elements in Map
        map.put(2,"Apple");
        map.put(3,"Banana");
        map.put(4,"Grapes");

        System.out.println("Iterating Hashmap...");
        for(Map.Entry m : map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        System.out.println(map.get(5));
        System.out.println(map.get(2));
    }

}
