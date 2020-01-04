package ManyThreads.Runnable;

/**
 * 说明：实现Runnable接口
 *
 * @Auther: 11432_000
 * @Date: 2018/12/25 15:59
 * @Description:
 */
public class GuiTuSaiPao {
    public static void main(String[] args) {
        new Thread(new Runner(),"R").start();
        new Thread(new Runner(),"W").start();
    }
}

class Runner implements Runnable{

    private static String winner;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.print(Thread.currentThread().getName() + i + "\t");
            if (gameOver(i)) {
                return;
            }
        }
    }

    private boolean gameOver(int steps){
        if (winner != null){
            return true;
        }else {
            if (steps >= 99){
                winner = Thread.currentThread().getName();
                System.out.println("Winner --" + winner);
            }
        }
        return false;
    }
}
