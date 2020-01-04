package ManyThreads.Synchronized.ProducerAndConsumerModel02;

/**
 * 说明：信号灯法
 *
 * @Auther: 11432_000
 * @Date: 2019/1/9 14:09
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Resources resources = new Resources();
        new Thread(new Player(resources)).start();
        new Thread(new Watcher(resources)).start();
    }
}
class Player implements Runnable{
    Resources resources;

    public Player(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.resources.play("节目" + (i + 1));
        }
    }
}class Watcher implements Runnable{
    Resources resources;

    public Watcher(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.resources.look();
        }
    }
}