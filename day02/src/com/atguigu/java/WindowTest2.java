package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 存在线程的安全问题，待解决。
 *
 * 说明:在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器
 *
 * @author kasio
 * @create 2020-04-2020/04/21 16:03
 */

class Window2 extends Thread{
    private static int ticket = 100;

//  private Object obj = new Object();  不满足共用同一把所的条件，三个对象都有自己的锁，所以把obj静态化
    private static Object obj = new Object();
    @Override
    public void run() {

        while(true){
//            ↓错误方式：this代表t1，t2,t3三个对象
//            synchronized (this){
//            ↓正确的
//            synchronized(obj){
//            正确的：类也是对象，只会加载一次，是唯一的
            synchronized (Window2.class){   //Class clazz = Window2.class 可行

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }

    }
}


public class WindowTest2 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}

