package hello.os.thread;

import java.net.Inet4Address;

class ClassA {
    public String name = "123";
}

class Common{
    public ClassA a = new ClassA(); // ref

    public void show(){
        System.out.println(a.name);
    }
}


class Table{
    void printTable(int n, Common c){//method not synchronized
        for(int i=1;i<=500;i++){
            System.out.println(n*i);
            try{
                c.show();
//                System.out.println(Common.a.name);
//                Thread.sleep(0);
            }catch(Exception e){System.out.println(e);}
        }

    }
}

class MyThread1 extends Thread{
    Table t;
    Common c;
    MyThread1(Table t, Common c){
        this.t=t;
        this.c = c;
    }
    public void run(){
        t.printTable(5, c);
    }

}
class MyThread2 extends Thread{
    Table t;
    Common c;
    MyThread2(Table t, Common c){
        this.t=t;
        this.c = c;
    }
    public void run(){
        t.printTable(100, c);
    }
}

class SimpleA{
    public static void main(String args[]){
//        Table obj = new Table();//only one object
//        Common c = new Common();
//        MyThread1 t1=new MyThread1(obj, c);
//        MyThread2 t2=new MyThread2(obj, c);
//        t1.start();
//        t2.start();

        Integer i1 = 128;
        Integer j1 = 128;
        Integer i2 = -129;
        Integer j2 = -129;
        System.out.println(i1==j1);
        System.out.println(i2==j2);
    }
}  