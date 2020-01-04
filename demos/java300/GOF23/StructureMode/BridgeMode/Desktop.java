package GOF23.StructureMode.BridgeMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 15:59
 * @Description:
 */
public class Desktop extends Computer{

    public Desktop(Brand brand) {
        super(brand);
    }
}
class Pad extends Computer{

    public Pad(Brand brand) {
        super(brand);
    }
}