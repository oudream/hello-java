package JavaBasics.Objective.DanMuGame;

import JavaBasics.Objective.MyUtils.GameUtils;

import java.awt.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/1 14:21
 * @Description:
 */
public class Bomb {

    private static Image[] images;
    private double thisX;
    private double thisY;
    private int count = 0;

    public Bomb(int sum){
        images = new Image[sum];
        for (int i = 0; i < sum; i++) {
            images[i] = GameUtils.getImage("images/e" + (i +1) + ".gif");
        }
    }

    public void die(Graphics graphics){
        if (this.count <= 15){
            graphics.drawImage(images[count] ,(int)this.thisX,(int)this.thisY,null);
            this.count++;
        }else {
            this.count = 0;
        }
    }

    public void setXAndY(double x,double y){
        this.thisX = x;
        this.thisY = y;
    }

    public double getThisX() {
        return thisX;
    }

    public void setThisX(double thisX) {
        this.thisX = thisX;
    }

    public double getThisY() {
        return thisY;
    }

    public void setThisY(double thisY) {
        this.thisY = thisY;
    }
}
