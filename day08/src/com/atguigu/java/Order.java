package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类
 * @author kasio
 * @create 2021-02-01 23:54
 */
public class Order<T> {
    String orderName;
    int orderIn;

    //类的内部结构就可以使用类的泛型
    T orderT;

    public Order(){
        //编译不通过，此时T不是一个类，还是一个变量
//        T[] arr = new T[10];

        //编译通过了，先new Object数组类，再强转为T指定的类的类型的数组
        T[] arr1 = (T[]) new Object[10];
    }

    public Order(String orderName,int orderIn,T orderT){
        this.orderName = orderName;
        this.orderIn = orderIn;
        this.orderT = orderT;
    }

    //如下的三个方法都不是泛型方法
    public T getOrderT(){
        return orderT;
    }

    public void setOrderT(T orderT){
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderIn=" + orderIn +
                ", orderT=" + orderT +
                '}';
    }

    //静态方法中不能使用类的泛型。
//    public static void show(T orderT){
//        System.out.println(orderT);
//    }

    public void show(){
        //不能保证T实例化时，一定是异常类
//        try{
//
//        }catch(T t){
//
//        }
    }

    //泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
    //换句话说，泛型方法所属的类是不是泛型类都没有关系。
    //泛型方法可以声明为静态的，原因：泛型参数实在调用方法时确定的，并非在实例化类时确定。
    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<E>();
        for (E e: list){
            list.add(e);
        }
        return list;
    }
}
