package GOF23.StructureMode.BridgeMode;

/**
 * 说明：电脑
 *
 * @Auther: 11432_000
 * @Date: 2019/1/25 15:35
 * @Description:
 */
public abstract class Computer {
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void say(){
        System.out.println(brand.getClass().getSimpleName() + "牌电脑");
    }
}
