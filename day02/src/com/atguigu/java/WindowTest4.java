package com.atguigu.java;

/**
 * 使用同步方法处理继承Thread类的方式中线程安全问题
 * @author kasio
 * @create 2020-04-2020/04/21 16:54
 */
class Window4 extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {
            show();
        }

    }

//    private synchronized void show(){ 依然不安全，同步监视器时this，有三个对象，所以不是唯一
    private static synchronized void show(){  //保证show方法静态，同步监视器：Window4.class
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }
    }


}


public class WindowTest4 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
