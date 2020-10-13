package hello.java.lang.oo;

import com.sun.java.swing.plaf.motif.MotifPasswordFieldUI;
import hello.java.lang.oo.overridea.*;

import java.io.IOException;

class SimpleA{
    private final int helloi1;
    private final static int helloi2 = 0;

    SimpleA(int helloi1) {
        this.helloi1 = helloi1;
    }

    public static void helloTry1() {
        try {
            SimpleA a = new SimpleA(5);
            System.out.println(a.helloi1);
            //商品详情XSS过滤
            if (1==1){
                throw new Exception("請使用YouTube視頻連接");
            }

        } catch (Exception e) {
            return;
        }
    }

    public static void helloThrow1() throws ExceptionA {
        int i = 10;
        if (i > 0) {
            throw new ExceptionA("i > 0");
        }
    }

    public static void helloThrow2() {
        try {
            helloThrow1();
        } catch (ExceptionA e) {
            return;
        }
    }

    public static void helloOo1(){
        Employee empOne = new Employee("dev1");
        Employee empTwo = new Employee("cus1");

        Employee empThree;

        empOne.empAge(26);
        empOne.empDesignation("Dev Coder");
        empOne.empSalary(1000);
        empOne.printEmployee();

        empTwo.empAge(21);
        empTwo.empDesignation("Custom Coder");
        empTwo.empSalary(500);
        empTwo.printEmployee();
        empTwo.printEmployee("xx");
    }

    public static void helloResource1() {
        System.out.println("class.getResource begin:");

        System.out.println(SimpleA.class.getResource(""));
        System.out.println(SimpleA.class.getResource("x1.xml"));
        System.out.println(SimpleA.class.getResource("/"));

        System.out.println("getClassLoader begin:");

        System.out.println(SimpleA.class.getClassLoader().getResource("x1.xml"));
        System.out.println(SimpleA.class.getClassLoader().getResource("/"));
        System.out.println(ClassLoader.getSystemResource("x1.xml"));
    }

    public static void helloOverride1() {
        IShape aShape = new Square();
        System.out.println("IShape say: " + aShape.introduceYourself());
    }

    public static void main(String args[]) throws IOException {
        System.out.println("Hello OO begin:");

//        helloOo1();
//        helloTry1();

//        helloThrow2();

//        helloResource1();

//        helloStringBuilder1();

//        System.out.println(String.valueOf(System.in.read()));
//        helloOverride1();

        ModifyA.helloModify1();

        System.out.println("Hello OO end.");
    }

    private static void helloStringBuilder1() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 1000 * 1000 * 2; i++) {
            s.append("abcdefghijklabcdefghijklabcdefghijklabcdefghijklabcdefghijkl");
        }
        System.out.println(s.length());
        System.out.println(s.length());
        System.out.println(s.length());
    }
}