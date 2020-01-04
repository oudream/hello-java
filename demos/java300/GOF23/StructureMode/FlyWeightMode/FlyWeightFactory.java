package GOF23.StructureMode.FlyWeightMode;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：享元工厂
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 16:53
 * @Description:
 */
public class FlyWeightFactory {
    //享元池
    private static Map<String,ChessFlyWeight> map = new HashMap<>();

    public static ChessFlyWeight getChessFlyWeight(String color){
        if (map.get(color) != null){
            return map.get(color);
        }
        else {
            ConcreteChess concreteChess = new ConcreteChess(color);
            map.put(color,concreteChess);
            return concreteChess;
        }
    }
}
