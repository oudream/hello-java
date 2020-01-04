package ManyThreads.Synchronized.ProducerAndConsumerModel01;

/**
 * 说明：消费者
 *
 * @Auther: 11432_000
 * @Date: 2019/1/8 15:56
 * @Description:
 */
public class Consumer implements Runnable{

    private Resources<Food> resources;

    public Consumer(Resources<Food> resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        //消费
        for (int i = 0; i < 100; i++) {
            Food food = resources.getFood();
            System.out.println("消费了食物-->" + food.id);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
