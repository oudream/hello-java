package ManyThreads.Synchronized;

/**
 * 说明：死锁
 *
 * @Auther: 11432_000
 * @Date: 2019/1/8 15:03
 * @Description:
 */
public class Deadlock {
    public static void main(String[] args) {
        new Thread(new Eat(1),"001").start();
        new Thread(new Eat(2),"002").start();
    }
}
/** 刀 */
class Knife{

    private volatile static Knife knife = null;

    private Knife(){}

    public static Knife getknife(){
        if (knife == null){
            synchronized (Knife.class){
                if (knife == null){
                    knife = new Knife();
                }
            }
        }
        return knife;
    }
}
/** 叉 */
class Fork{
    private volatile static Fork fork = null;

    public static Fork getFork(){
        if (fork == null){
            synchronized(Fork.class){
                if (fork == null){
                    fork = new Fork();
                }
            }
        }
        return fork;
    }
}
class Eat implements Runnable{
    static Knife knife = Knife.getknife();
    static Fork fork = Fork.getFork();
    private int i;

    public Eat(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            if (i % 2 == 0){
                synchronized(knife){
                    Thread.sleep(1000);
                    synchronized (fork){
                        System.out.println("我没死锁");
                    }
                }
            }else {
                synchronized(fork){
                    Thread.sleep(1000);
                    synchronized (knife){
                        System.out.println("我没死锁");
                    }
                }
            }
        }catch (Exception e){}

    }
}

