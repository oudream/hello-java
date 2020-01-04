package JavaBasics.Objective.DanMuGame;

import JavaBasics.Objective.MyUtils.GameUtils;

import java.awt.*;
import java.util.Random;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/2 12:42
 * @Description:
 */
public class Gain extends GameObject{
    /** 增益类型 1、加一滴血 2、加2速度 3、难度减1 4、无敌五秒  */
    private int gainType;
    private boolean isGain = false;
    private double direction;
    private static Image[] images = new Image[4];
    static {
        for (int i = 0; i < 4; i++) {
            Image image = GameUtils.getImage("images/timg" + i + ".jpg");
            images[i] = image;
        }
    }

    public Gain(){
        Random random = new Random();
        this.gainType = random.nextInt(4);
        this.isGain = true;
        this.setImages(images[this.gainType]);
        this.direction = Math.random() * Math.PI * 2;
        this.setThisX(250);
        this.setThisY(70);
        this.setThisWide(15);
        this.setThisHeight(15);
        this.setSpeed(7);
    }

    public void getGainForPlane(Plane plane ,ShellList shells){
        switch (this.gainType + 1){
            case 1:
                plane.setLife(plane.getLife() + 1);
                break;
            case 2:
                plane.setSpeed(plane.getSpeed() + 2);
                break;
            case 3:
                shells.setShellsSpeed(-1);
                break;
            case 4:
                plane.setInvincible(true);
                plane.setInvincibleStart(System.currentTimeMillis());
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g ,Plane plane, ShellList shells) {
        g.drawImage(this.getImages() ,(int) this.getThisX(),(int)this.getThisY(),null);
        this.setThisX(this.getThisX() + this.getSpeed() * Math.cos(this.direction));
        this.setThisY(this.getThisY() + this.getSpeed() * Math.sin(this.direction));
        if(this.getThisX() >= 700 - 10 || this.getThisX() <= 10){
            this.direction = Math.PI - this.direction;
        }
        if (this.getThisY() >= 700 - 10 || this.getThisY() <= 40){
            this.direction = -this.direction;
        }
        if (plane.getRect().intersects(this.getRect())){
            this.getGainForPlane(plane ,shells);
            this.setGain(false);
        }
    }

    public boolean isGain() {
        return isGain;
    }

    public void setGain(boolean gain) {
        isGain = gain;
    }
}
