package GOF23.StructureMode.FlyWeightMode;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 16:39
 * @Description:
 */
public interface ChessFlyWeight {
    void display(Coordinate coordinate);
    void setColor(String color);
    String getColor();
}
class ConcreteChess implements ChessFlyWeight{

    private String color;

    public ConcreteChess(String color) {
        this.color = color;
    }

    @Override
    public void display(Coordinate coordinate) {
        System.out.println("棋子颜色：" + this.color + "位置：x=" + coordinate.getX()
                + "y=" + coordinate.getY());
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }
}