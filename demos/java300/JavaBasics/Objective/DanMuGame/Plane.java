package JavaBasics.Objective.DanMuGame;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 13:26
 * @Description:
 */
public class Plane extends GameObject {

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean survive = true;
    private int life = 3;
    private boolean twinkle = false;
    private long invincibleStart;
    private long invincibleStop;
    private boolean invincible = false;


    public Plane(){

    }

    public Plane(int thisX, int thisY, Image images){
        super(thisX ,thisY ,images);
    }

    public void pressDirection(KeyEvent keyEvent){
        int keyCode = keyEvent.getKeyCode();
        //上下右左
        switch (keyCode){
            case KeyEvent.VK_UP:
                this.up = true;
                break;
            case KeyEvent.VK_DOWN:
                this.down = true;
                break;
            case KeyEvent.VK_RIGHT:
                this.right = true;
                break;
            case KeyEvent.VK_LEFT:
                this.left = true;
                break;
            default:
                break;
        }
    }

    public void looseDirection(KeyEvent keyEvent){
        int keyCode = keyEvent.getKeyCode();
        //上下右左
        switch (keyCode){
            case KeyEvent.VK_UP:
                this.up = false;
                break;
            case KeyEvent.VK_DOWN:
                this.down = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.right = false;
                break;
            case KeyEvent.VK_LEFT:
                this.left = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void drawSelf(Graphics g) {
        double newX = this.getThisX();
        double newY = this.getThisY();
        if (this.left){
            newX = this.getThisX() - this.getSpeed();
        }if (this.right){
            newX = this.getThisX() + this.getSpeed();
        }if (this.up){
            newY = this.getThisY() - this.getSpeed();
        }if (this.down){
            newY = this.getThisY() + this.getSpeed();
        }
        if(newX > 20 && newX < 680 && newY > 20 && newY < 680){
            this.setThisX(newX);
            this.setThisY(newY);
        }
        g.drawImage(this.getImages() ,(int)this.getThisX() ,(int)this.getThisY(),null);
    }

    public boolean isSurvive() {
        return survive;
    }

    public void setSurvive(boolean survive) {
        this.survive = survive;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isTwinkle() {
        return twinkle;
    }

    public void setTwinkle(boolean twinkle) {
        this.twinkle = twinkle;
    }

    public long getInvincibleStart() {
        return invincibleStart;
    }

    public void setInvincibleStart(long invincibleStart) {
        this.invincibleStart = invincibleStart;
    }

    public long getInvincibleStop() {
        return invincibleStop;
    }

    public void setInvincibleStop(long invincibleStop) {
        this.invincibleStop = invincibleStop;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
}
