package com.atguigu.java1;

/**
 * 使用同步机制将单例模式中的懒汉式改写成线程安全的
 * @author kasio
 * @create 2020-04-2020/04/21 17:09
 */
public class BankTest {
}

class Bank{
    private static Bank ins = null;
    private Bank(){}


//    public static synchronized Bank getIns(){ //可行，同步锁：当前类本身Bank·class，类可以造对象，它本身也是对象
    public static Bank getIns(){
//        同步方法块方式一：效率稍差
//        就当线程1获取同步锁时运行下面代码时，
//        线程2进入getIns方法后遇到收到阻塞，不会等待，而直接跳过synchronized方法块继续运行，结束方法。
//        synchronized (Bank.class){
//            if(ins == null){
//                ins = new Bank();
//            }
//            return ins;
//        }

//        同步方法块方式二：效率稍高
        if(ins == null){
//            前几个刚进来的线程会在这里排队，当有一个线程进入同步方法快后new了Bank后，
//            在排队的进去后无法满足条件后出来，
//            后面再来的线程则直接本阻挡在↑上面的if判断前
//            这就是优于方式一的地方
            synchronized (Bank.class){
                if(ins == null){
                    ins = new Bank();
                }
            }
        }
        return ins;
    }
}
