package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张.使用实现Runnable接口的方式
 * 存在线程的安全问题，待解决。
 *
 * 1.问题：买票过程中出现重票，错票 —》出现了线程的安全问题
 * 2.问题出现原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
 * 3.如何解决：当一个线程在操作ticket的时候，其他线程不能参与进来。直到线程a操作完ticket时，
 *              线程才可以开始操作ticket。这种情况即使线程a出现了阻塞,也不能改变
 *
 * 4，在Java中，我们通过同步机制，来解决线程安全问题
 *
 * 方式一：同步代码块
 *
 *  synchronized（同步监视器）{
 *      //需要被同步的代码
 *  }
 *  说明：1.操作共享数据的代码，即为需要被同步的diamagnetic  --》不能包含多了，或包含太少了。包多了效率低，还可能，与错误解决线程安全
 *       2.共享数据：多个线程共同操作的变量。比如：ticket就是共享数据。
 *       3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *          要求：多个线程必须要共用同一把锁。
 *
 *       补充：在实现Runnble接口创建多线程de方式中，我们可以考虑使用this充当同步监视器
 *
 * 方式二:同步方法
 *      如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步
 *
 *
 * 5.同步的方式,解决了线程安全的问题. --好处
 *   操作同步代码时,只能又一个线程参与,其他线程等待.相当于是一个单线程的过程,效率低.--坏处
 *
 * @author kasio
 * @create 2020-04-2020/04/21 15:00
 */

class Window1 implements Runnable{
    private int ticket = 100;
//    Object obj = new Object(); 可行，但是太繁琐，每次都要new一个同步锁

    @Override
    public void run(){
//        Object obj = new Object();    此时不满足同一个锁的要求
        while(true){
//            synchronized(obj){
            synchronized(this){   //此时的this：唯一的Window1对象   方式二：synchronized
                if(ticket > 0){
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "买票，票号为:" + ticket);
                    ticket--;

                }else{
                    break;
                }
            }

        }
    }
}

public class WindowTest1 {
    public static void main (String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
