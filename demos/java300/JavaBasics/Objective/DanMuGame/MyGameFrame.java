package JavaBasics.Objective.DanMuGame;

import JavaBasics.Objective.MyUtils.GameUtils;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：窗口展示
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 10:12
 * @Description:
 */
public class MyGameFrame extends Frame {

    public static final int FRAME_FATE = 40;

    private Image image01 = GameUtils.getImage("images/bg.jpg");
    private Image image02 = GameUtils.getImage("images/plane.png");
    private Plane plane = new Plane(250,500,image02);
    private ShellList shells = new ShellList(20);
    private Bomb bomb;
    private Date start = new Date();
    private Date end;
    private String dieMessage;
    private Gain gain;

    /** 双缓冲 */
    private Image imageBuffer = null;
    private Graphics graphicsBuffer = null;

    public MyGameFrame(){
        this.setWideAndHeight();
    }

    public Plane getPlane() {
        return plane;
    }

    public ShellList getShells() {
        return shells;
    }

    public void setWideAndHeight(){
        this.setWideAndHeight(image02.getWidth(null),
                image02.getHeight(null));
    }

    public void setWideAndHeight(int wide,int height){
        this.plane.setThisWide(wide);
        this.plane.setThisHeight(height);
    }

    @Override
    /** 双缓冲解决闪烁问题
     *  原理：在后台先画好一帧图，在刷新时直接展示这张图，然后准备好下一帧。*/
    public void update(Graphics g) {
        if(this.imageBuffer == null){
            //这是游戏窗口的宽度和高度
            this.imageBuffer = this.createImage(Constant.GAME_WIDH,Constant.GAME_HEIGHT);
            this.graphicsBuffer = imageBuffer.getGraphics();
        }
        this.graphicsBuffer.fillRect(0,0,Constant.GAME_WIDH,Constant.GAME_HEIGHT);
        this.paint(this.graphicsBuffer);
        g.drawImage(this.imageBuffer ,0 , 0 ,null);
    }

    @Override
    public void paint(Graphics g){
        /**
         * 功能描述: 窗口画笔，用于在窗口显示图像，以窗口左上角为原点。
         */
        g.drawImage(image01 ,0 , 0, null);
        shells.draws(g , plane);
        this.showMessage(g,"生命数："+ plane.getLife() ,40,55,18);
        this.showMessage(g,"难度：" + (shells.getSpeed() - 5)  ,40,75,18);
        this.showMessage(g,"飞机速度：" + (this.plane.getSpeed())  ,40,95,18);
        if (this.gain != null && this.gain.isGain()){
            this.gain.draw(g,this.plane ,this.shells);
            if (!this.gain.isGain()){
                this.gain = null;
            }
        }
        if (plane.isSurvive()){
            plane.drawSelf(g);
            long gameTime = this.getGameTime();
            if (gameTime % (10*1000) <= FRAME_FATE && gameTime > 0){
                this.shells.setShellsSpeed(1);
            }
            if (gameTime % (10 * 1000) <= FRAME_FATE && gameTime > 0 && this.gain == null){
                this.gain = new Gain();
            }
        }else {
            if (bomb == null){
                this.bomb = new Bomb(16);
                this.bomb.setXAndY(plane.getThisX(),plane.getThisY());
            }
            bomb.die(g);
            this.showLiveTime(g);
        }
    }

    private long getGameTime(){
        this.end = new Date();
        long liveTime = this.end.getTime() - this.start.getTime();
        return liveTime;
    }

    public void showLiveTime(Graphics g){
        if (this.dieMessage == null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            this.dieMessage ="存活时间：" + simpleDateFormat.format(new Date(this.getGameTime()));
        }
        this.showMessage(g, this.dieMessage ,200,200,40);
    }

    public void showMessage(Graphics g,String message,int x,int y,int fontSize){
        Color color = g.getColor();
        g.setColor(Color.RED);
        Font font = new Font("宋体",Font.BOLD,fontSize);
        g.setFont(font);
        g.drawString(message,x ,y);
        g.setColor(color);
    }

    public void launchFrame(){
        /**
         *
         * 功能描述: 初始化窗口
         *
         * @param: []
         * @return: void
         * @auther: 11432_000
         * @date: 2018/11/30 10:14
         */
        //设置标题
        this.setTitle("弹幕小游戏");
        //设置窗口大小
        this.setSize(Constant.GAME_WIDH,Constant.GAME_HEIGHT);
        //设置窗口位置,以屏幕左上角为原点。
        setLocation(700,300);
        //设置窗口监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            /** 点击关闭按钮时 */
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }

        });
        //设置键盘监听器
        this.addKeyListener(new KeyListener());
        //设置显示窗口与否
        this.setVisible(true);
        //设置飞机线程
        Runnable runnable = new PaintThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /** 刷新画面的线程 */
    class PaintThread implements Runnable{
        @Override
        public void run() {
            while (true){
                //重画窗口
                repaint();
                try {
                    Thread.sleep(FRAME_FATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /** 键盘监听器 */
    class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            plane.pressDirection(keyEvent);
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            plane.looseDirection(keyEvent);
        }
    }

    /** 游戏入口 */
    public static void main(String[] args){
        MyGameFrame myGameFrame = new MyGameFrame();
        myGameFrame.getPlane().setSpeed(10);
        myGameFrame.getShells().setShellsSpeed(5);
        myGameFrame.launchFrame();
    }
}
