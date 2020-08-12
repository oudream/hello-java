package hello.java.lang.oo;

class SimpleB {
    public static String showName() {
        return "SimpleB";
    }

    public static void helloString1() {
        String str1 = "Lance";
        String str2 = new String("Lance");
        String str3 = str2;
        String str4 = "Lance";
        /**
         *  ==:
         */
        System.out.println(str1 == str2);//false
        System.out.println(str1 == str3);//false
        System.out.println(str3 == str2);//true
        System.out.println(str1 == str4);//true
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));
        System.out.println(str1.equals(str4));
    }


}
