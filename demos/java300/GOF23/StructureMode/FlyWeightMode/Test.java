package GOF23.StructureMode.FlyWeightMode;

/**
 * 说明：享元模式
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 17:00
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        ChessFlyWeight 黑 = FlyWeightFactory.getChessFlyWeight("黑");
        ChessFlyWeight bai = FlyWeightFactory.getChessFlyWeight("黑");
        System.out.println(bai);
        System.out.println(黑);
    }
}
