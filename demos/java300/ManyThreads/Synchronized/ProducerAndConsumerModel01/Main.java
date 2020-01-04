package ManyThreads.Synchronized.ProducerAndConsumerModel01;

/**
 * 说明：管程法
 *
 * @Auther: 11432_000
 * @Date: 2019/1/8 15:57
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Resources<Food> resources = new Resources<>(10);
        new Thread(new Producer(resources)).start();
        new Thread(new Consumer(resources)).start();
    }
}
