package GOF23.StructureMode.FlyWeightMode;

/**
 * 说明：外部状态
 *
 * @Auther: 11432_000
 * @Date: 2019/1/26 16:43
 * @Description:
 */
public class Coordinate {

    private int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
