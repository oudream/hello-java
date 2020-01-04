package ManyThreads.Synchronized;

import java.util.*;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2019/1/8 13:18
 * @Description:
 */
public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list = new ArrayList();
        list.add(1);
        list.add(4);
        list.add(23);
        list.add(9);
        list.add(7);
        list.add(5);
        HappyCinema happyCinema = new HappyCinema(list ,"");
        new Customer(happyCinema ,"王老五",1,4).start();
        new Customer(happyCinema ,"王老6",1,7).start();
    }
}
/** 通过继承Tread 代理线程 */
class Customer extends Thread {
    List<Integer> seats;
    public Customer(Runnable target,String name ,Integer... seats) {
        super(target,name);
        this.seats = Arrays.asList(seats);
    }
}

class HappyCinema implements Runnable{

    List<Integer> available;
    String name;

    public HappyCinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    public synchronized boolean buy(List number){
        if (!available.containsAll(number)){
            return false;
        }
        available.removeAll(number);
        return true;
    }

    public void show(){
        Iterator<Integer> iterator = available.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "\t");
        }
    }

    @Override
    public void run() {
        Customer thread =(Customer) Thread.currentThread();
        boolean buy = this.buy(thread.seats);
        this.show();
        if (buy){
            System.out.println(Thread.currentThread().getName() + "购买成功");
        }else {
            System.out.println(Thread.currentThread().getName() + "购买失败");
        }
    }

}
