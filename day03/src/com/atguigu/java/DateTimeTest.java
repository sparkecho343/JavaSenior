package com.atguigu.java;

import org.junit.Test;

import java.util.Date;

/**
 * JDK 8之前的日期和时间的API测试
 * @author kasio
 * @create 2020-07-23 15:06
 */
public class DateTimeTest {
    /*
    java.util.Date类
        /---java.sql.Date类 继承关系

    1.两个构造器的使用
        构造器一：Date():创建一个对应的当前时间的Date对象
        造器二：创建指定毫秒数的Date对象
    2.两个方法的使用
        >toString():显示当前的年，月，日，时，分，秒
        >getTime():获取当前Date对象对应的毫秒数。（时间戳）

    3.java.sql.Date对应着数据库中的日期类型的变量
        >如何实例化
        >sql.Date ---> util.Date对象 (多态)
        >如何将java.util.Date对象转换成java.sql.Date对象 （父类转子类）
     */
    @Test
    public void test2(){
        //构造器一：Date():创建一个对应的当前时间的Date对象
        Date date1 = new Date();
        System.out.println(date1);
        //相当于System.out.println(date1.toString()); Thu Jul 23 15:20:42 JST 2020
        System.out.println(date1.getTime());//1595485352631

        //构造器二：创建指定毫秒数的Date对象
        Date date2 = new Date(1595485352631L);
        System.out.println(date2); //Thu Jul 23 15:22:32 JST 2020

        java.sql.Date date3 = new java.sql.Date(3534523453231L);
        System.out.println(date3);//2082-01-02

        //如何将java.util.Date对象转换成java.sql.Date对象
        //情况一：
//        Date date4 = new java.sql.Date(3412341234L); //多态
//        java.sql.Date date5 = (java.sql.Date) date4;

        //情况一：
        Date date6 = new Date();
//        java.sql.Date date7 = (java.sql.Date) date6; //编译不报错，运行报错
        java.sql.Date date8 = new java.sql.Date(date6.getTime());
    }


    //1.System类中的currentTimeMillis()
    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        System.out.println(time);
        //返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
        //称为时间戳
    }
}
