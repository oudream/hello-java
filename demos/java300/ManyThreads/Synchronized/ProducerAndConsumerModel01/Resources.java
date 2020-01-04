package ManyThreads.Synchronized.ProducerAndConsumerModel01;

/**
 * 说明：临界资源（缓存区）
 *
 * @Auther: 11432_000
 * @Date: 2019/1/8 15:58
 * @Description:
 */
public class Resources<T> {

    private int maxSize;
    private int length;
    private Object[] list;

    public Resources(int maxSize) {
        this.maxSize = maxSize;
        this.length = 0;
        this.list = new Object[maxSize];
    }

    public synchronized void addFood(T food){
        try {
            if (this.length >= maxSize){
                this.wait();
            }
            this.list[this.length ++] = food;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized T getFood(){
        if (this.length <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = (T) this.list[(this.length --) - 1];
        notifyAll();
        return t;
    }

    public int getLength() {
        return length;
    }
}
class Food{
    int id;
}
