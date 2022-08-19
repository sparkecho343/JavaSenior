package com.atguigu.java2;

/**
 * 线程通信的例子：使用两个线程打印 1-100。线程1, 线程2 交替打印(线程通信)
 *
 *涉及到得三个方法：
 * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器
 * notify():一旦执行此方法，就会唤醒被wait得一个线程。如果有多个线程被wait，就唤醒优先级高的
 * notifyAll():一旦执行此方法，就会唤醒所有被wait得线程。
 *
 * 说明：
 * 1.wait(),notifjy(),notifyAll()三个方法必须使用在同步代码块或同步方法中。
 * 2.wait(),notifjy(),notifyAll()三个方法得调用者必须是同步代码块或同步方法中的同步监视器。
 *      否则会出现IllegalMonitorStateException异常
 * 3.wait(),notifjy(),notifyAll()三个方法是定义在java.lang.Object类中。
 *      因为同步监视器可以是任何类，要保证任何一个类中都可以用以上三个方法的话，则放入Object类中。
 *
 * 面试题：sleep()和wait()的异同？
 * 1.相同点：一旦执行方法，都可以是的当前线程进入阻塞状态。
 * 2.不同点：1）两个方法声明的位置不同：Thread类中声明sleep(),Object类中声明wait()
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用。wait()使用在同步代码块或同步方法中
 *          3）关于是否释放同步监视器：如果两个方法都是同再同步代码块或同步方法中，sleep()方法不会释放锁，
 *              wait()会释放锁。
 *
 * @author kasio
 * @create 2020-04-2020/04/23 17:22
 */

class Number implements Runnable{

    private int number = 1;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true){

            synchronized (obj) {

//                notify();     IllegalMonitorStateException,因为this和obj不同，所有异常
                obj.notify();

                if(number <= 100){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用如下wait()方法得线程进入阻塞状态
                        //一旦wait就释放锁，而sleep不会释放锁
//                        wait();
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {

    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }

}
