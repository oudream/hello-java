package JavaBasics.Objective.DanMuGame;

import java.awt.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 15:25
 * @Description:
 */
public class Shell extends GameObject{

    private double direction ;

    public Shell() {
        this.setThisX(200);
        this.setThisY(200);
        this.setThisWide(10);
        this.setThisHeight(10);
        this.direction = Math.random() * Math.PI * 2;
    }

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval((int) this.getThisX(), (int)this.getThisY(),this.getThisWide() ,this.getThisHeight());
        g.setColor(color);
        this.setThisX(this.getThisX() + this.getSpeed() * Math.cos(this.direction));
        this.setThisY(this.getThisY() + this.getSpeed() * Math.sin(this.direction));
        if(this.getThisX() >= 700 - 10 || this.getThisX() <= 10){
            this.direction = Math.PI - this.direction;
        }
        if (this.getThisY() >= 700 - 10 || this.getThisY() <= 40){
            this.direction = -this.direction;
        }
    }


}
