package JavaBasics.Objective.DanMuGame;

import java.awt.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 16:24
 * @Description:
 */
public class ShellList {

    private int number;
    private Shell[] shells;

    public ShellList(int number) {
        this.number = number;
        this.shells = new Shell[number];
        for (int i = 0; i < this.number; i++) {
            this.shells[i] = new Shell();
        }
    }

    public void draws(Graphics g ,Plane plane){
        if (plane.isTwinkle()){
            if ((System.currentTimeMillis() - plane.getInvincibleStart()) >= 1500){
                plane.setTwinkle(false);
            }
        }
        for (int i = 0; i < this.number; i++) {
            this.shells[i].draw(g);
            if (shells[i].getRect().intersects(plane.getRect())){
                if (!plane.isTwinkle() && !plane.isInvincible()){
                    plane.setLife(plane.getLife()-1);
                    plane.setTwinkle(true);
                    plane.setInvincibleStart(System.currentTimeMillis());
                }
                if (plane.isInvincible()){
                    if ((System.currentTimeMillis() - plane.getInvincibleStart()) >= 5000){
                        plane.setInvincible(false);
                    }
                }
                if (plane.getLife() <= 0){
                    plane.setSurvive(false);
                }
            }
        }
    }


    public void setShellsSpeed(int speed){
        for (int i = 0; i < this.number; i++) {
            this.shells[i].setSpeed(shells[i].getSpeed() + speed);
        }
    }

    public int getSpeed(){
        return shells[0].getSpeed();
    }



}
