package JavaBasics;

import java.util.stream.Stream;

/**
 * 说明：for循环练习
 *
 * @Auther: 11432_000
 * @Date: 2018/11/28 09:44
 * @Description:
 */
public class ForLoop {
    public static void main(String[] args){
//        for (int i = 0,j = 0;i < 10; i++,j = (int) Math.pow(i,2)){
//            System.out.println(i + "  " + j);
//        }
//        for (int i = 1;i <= 9;i++){
//            for (int j = 1;j <= i;j++){
//                System.out.print(i + "*" + j + "=" +(i*j) + "\t");
//           X }
//            System.out.println();
//        }
//        Image[] images = new Image[4];
//        for (int i = 0; i < 4; i++) {
//            Image image = GameUtils.getImage("images/timg" + i + ".jpg");
//            images[i] = image;
//        }
        System.out.println(Stream.of("green","yellow","blue").max((s1,s2) -> s1.compareTo(s2))
        .filter(s -> s.endsWith("n")).orElse("yellow"));

    }
}
