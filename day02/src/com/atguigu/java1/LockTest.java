package com.atguigu.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * （前两个是同步代码块和同步方法）
 * 解决线程安全问题方式三：Lock  ----》 JDK5.9新增
 *
 * 1.面试题：synchronized 和 Lock 的异同？
 *  相同：二者都可以解决线程安全问题
 *  不同：synchronized机制在执行完相应的同步代码块以后，自动释放同步监视器
 *       Lock需要手动的启动同步（lock()），同时结束同步时也需要手动实现（unlock()）
 *
 * 2.优先使用顺序：
 *  Lock  同步代码块（已经进入了方法体，分配了相应资源）  同步方法（在方法体之外）
 *
 * 面试题：如何解决线程安全问题？有几种方式
 *
 * @author kasio
 * @create 2020-04-2020/04/21 18:19
 */

class Window implements Runnable{
    private int ticket = 100;
//    1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock(true);
    //true↑保证线程公平替换，不会出现线程1运行一次后再次运行。先进先出

    @Override
    public void  run(){
        while(true){

            try {

                //调用锁定方法lock(),类似获取同步监视器，保证此大括号里时单线程运行
                lock.lock();

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：售票，票号为：" + ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                //调用解锁方法：unlock()
                lock.unlock();

            }


        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

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
