package ManyThreads.Synchronized.ProducerAndConsumerModel01;

/**
 * 说明：生产者
 *
 * @Auther: 11432_000
 * @Date: 2019/1/8 15:56
 * @Description:
 */
public class Producer implements Runnable{

    private Resources<Food> resources;

    public Producer(Resources<Food> resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        //生产
        for (int i = 0; i < 100; i++) {
            Food food = new Food();
            food.id = i;
            resources.addFood(food);
            System.out.println("生产了食物-->" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
