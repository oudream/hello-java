package GOF23.CreateMode.CopyMode;

import java.util.Date;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/24 15:57
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws Exception{
        Sheep03 sheep03 = new Sheep03();
        sheep03.setDate(new Date());
        sheep03.setName("001");
        Sheep03 clone =(Sheep03) sheep03.clone();
        System.out.println(clone);
        System.out.println(sheep03);
        System.out.println(clone == sheep03);
        System.out.println(clone.getDate() == sheep03.getDate());
    }
}
