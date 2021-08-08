package hello.java.lang.container;

import java.util.HashSet;

class HelloHashSet {
    public static void helloHashSet1(int k) {
        HashSet<String> sites = new HashSet<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Zhihu");
        sites.add("Runoob");     // ÖØ¸´µÄÔªËØ²»»á±»Ìí¼Ó
        for (String i : sites) {
            System.out.println(i);
        }
    }
}
