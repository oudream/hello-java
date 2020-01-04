package ManyThreads.Synchronized.ProducerAndConsumerModel02;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/9 14:02
 * @Description:
 */
public class Resources {
    private String value;
    /** T代表演员表演。观众等待 F相反 */
    private boolean flag = true;

    public synchronized void play(String v){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value = v;
        System.out.println("表演了-->" + v);
        flag = !flag;
        notifyAll();
    }

    public synchronized void look(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了-->" + value);
        flag = !flag;
        notifyAll();
    }
}

