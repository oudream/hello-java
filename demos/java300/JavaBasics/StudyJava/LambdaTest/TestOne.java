package JavaBasics.StudyJava.LambdaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/10 10:02
 * @Description:
 */
public class TestOne {

    public static void main(String[] args){
        List list = new ArrayList<String>();
        for (int i = 0 ;i < 100; i++){
            list.add("tester" + i);
        }
//        list.forEach(s -> System.out.println(s));
        TestInterface anInterface = (String s, boolean is) -> {if (is){ System.out.println("我是:" + s); }};
        anInterface.show("mayao" ,true);
        Predicate<String> filter01 = (s) -> (s.indexOf("tester1") != -1);
        list.stream().filter(filter01).forEach(x -> System.out.println(x));
    }
}
