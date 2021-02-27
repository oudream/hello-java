package hello.os.thread;

class MyThread implements Runnable {
    String message;
    String name; // name of thread

    Thread t;

    MyThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
        System.out.println(name + " exiting.");
        message = "finish"+name;
    }
}

public class SimpleA {
    public static void main(String args[]) {
        MyThread ob1 = new MyThread("One");
        MyThread ob2 = new MyThread("Two");
        MyThread ob3 = new MyThread("Three");

        System.out.println("Thread One is alive: " + ob1.t.isAlive());
        System.out.println("Thread Two is alive: " + ob2.t.isAlive());
        System.out.println("Thread Three is alive: " + ob3.t.isAlive());

        String message1 = "";
        String message2 = "";
        String message3 = "";
        try {
            System.out.println("Waiting for threads to finish.");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
            message1 = ob1.message;
            message2 = ob2.message;
            message3 = ob3.message;
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Thread One is alive: " + ob1.t.isAlive());
        System.out.println("Thread Two is alive: " + ob2.t.isAlive());
        System.out.println("Thread Three is alive: " + ob3.t.isAlive());

        System.out.println(message1);
        System.out.println(message2);
        System.out.println(message3);

        System.out.println("Main thread exiting.");
    }
}

