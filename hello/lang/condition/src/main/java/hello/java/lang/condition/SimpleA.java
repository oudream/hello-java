package hello.java.lang.condition;

class SimpleA {
    public static int toInt1(String s) {
        int r = -1;
        try {
            r = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return r;
    }

    public static void helloTry1() {
        System.out.println(toInt1("工"));
    }

    public static void main(String args[]) {
        System.out.println("Begin - " + SimpleA.class.getPackage().getName() + ":");
        helloTry1();
        System.out.println("End - " + SimpleA.class.getPackage().getName() + ".");
    }
}  