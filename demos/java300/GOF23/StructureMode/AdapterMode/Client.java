package GOF23.StructureMode.AdapterMode;

/**
 * 说明：客户端
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 13:42
 * @Description:
 */
public class Client {

    public void run(TargetInterface target){
        target.say();
    }
}
