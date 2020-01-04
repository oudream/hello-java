package GOF23.StructureMode.DecoratorMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 15:38
 * @Description:
 */
public interface MyCar {
    void move();
}

class Car implements MyCar{
    @Override
    public void move() {
        System.out.println("在地上跑");
    }
}

class FlyCar implements MyCar{

    private MyCar myCar;

    public FlyCar(MyCar myCar) {
        this.myCar = myCar;
    }

    @Override
    public void move() {
        System.out.println("可以飞");
        this.myCar.move();
    }
}

class WaterCar implements MyCar{

    private MyCar myCar;

    public WaterCar(MyCar myCar) {
        this.myCar = myCar;
    }

    @Override
    public void move() {
        System.out.println("可以水里游");
        this.myCar.move();
    }
}
