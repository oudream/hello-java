package JavaBasics.Objective.DanMuGame;

import javax.swing.*;
import java.awt.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 13:15
 * @Description:
 */
public class GameObject extends JFrame {

    private double thisX;
    private double thisY;
    private Image images;
    private int speed;
    private int thisWide;
    private int thisHeight;

    public GameObject(){
    }


    public GameObject(int thisX, int thisY, Image images){
        this.thisX = thisX;
        this.thisY = thisY;
        this.images = images;
    }

    public void drawSelf(Graphics g){
        g.drawImage(this.images ,(int)this.thisX ,(int)this.thisY,null);
    }

    public Rectangle getRect(){
        return new Rectangle((int)this.thisX,(int)this.thisY,thisWide,thisHeight);
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

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getThisWide() {
        return thisWide;
    }

    public void setThisWide(int thisWide) {
        this.thisWide = thisWide;
    }

    public int getThisHeight() {
        return thisHeight;
    }

    public void setThisHeight(int thisHeight) {
        this.thisHeight = thisHeight;
    }
}
