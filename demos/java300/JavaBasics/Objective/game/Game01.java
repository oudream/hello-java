package JavaBasics.Objective.game;
import java.awt.*;
import javax.swing.*;
/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/27 09:44
 * @Description:
 */
public class Game01 extends JFrame{

     public static final Image image01 = Toolkit.getDefaultToolkit().getImage("images/ball.png");
     public static final Image image02 = Toolkit.getDefaultToolkit().getImage("images/background.jpg");
     private double x = 100.0;
     private double y = 100.0;
     double degree = 3.14/3;

    /** 打开窗口 */
    void launchFrame(){
        boolean flag = true;
        setSize(856,500);
        setLocation(50,50);
        setVisible(true);
        int spik = 100;
        while(true){
            /** 自动调用paint方法 */
            repaint();
            try {
                Thread.sleep(50);
                if (y > 500 - 40 -30 || y < 40 + 30){
                    degree = -degree;
                }if (x > 856 - 40 - 30 || x < 40){
                    degree = 3.14 - degree;
                }
                if (spik > 10){
                    x += spik * Math.cos(degree);
                    y += spik * Math.sin(degree);
                }else { }

                spik -= 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
       public void paint(Graphics g){
        g.drawImage(image02,0,0,null);
        g.drawImage(image01,(int)x,(int)y,null);
    }

    public static void main(String[] args){
        Game01 game01 = new Game01();
        game01.launchFrame();
    }
}
