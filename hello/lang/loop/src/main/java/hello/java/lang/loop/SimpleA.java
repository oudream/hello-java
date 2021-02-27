package hello.java.lang.loop;

import java.util.Random;

class SimpleA {
    public static void main(String args[]) {
        System.out.println("Hello Java");
//        helloLoop1();
        helloRandom1();
    }

    private static void helloRandom1() {
        Random random = new Random();
        System.out.println(random.nextInt(100));
    }

    private static void helloLoop1() {
        for (int i = 0; i < 100; i++) {
            String s = Integer.toString(i);
            System.out.println(s);
        }
    }
}  