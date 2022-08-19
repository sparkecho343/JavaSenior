package com.atguigu.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * jak 8之前的日期时间的API测试
 * 1. System类中CurrentTimeMillis()
 * 2. java.util.Date和子类java.sql.Date
 * 3. SimpleDateFromat
 * 4. Calendar
 *
 * @author kasio
 * @create 2020-08-17 17:19
 */
public class DateTimeTest {
    /*
    SimpleDateFromat的使用：SimpleDateFromat对日期Date类的格式化和解析

    1.两个操作：
    1.1格式化： 日期 --》 字符串
    1.2解析： 格式化的逆过程，字符串 --》 日期

    2.SimpleDateFromat的实例化
     */

    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化： 日期 --》 字符串
        Date date = new Date();
        System.out.println(date);  //date.toString

        String format = sdf.format(date); //格式化成本地格式的字符串
        System.out.println(format);

        //解析：格式化的逆过程，字符串 --》 日期
        //String str = "2020-08-17"; //抛异常
        String str = "20/08/17 17:31";
        Date date1 = sdf.parse(str); //使用默认格式解析
        System.out.println(date1);

        //************指定方式进行格式化和解析：调用带参数的构造器*************
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1); //2020-08-17 05:44:02

        //解析:要求字符串必须是符合SimpleDateFormat识别的格式（通过构造器形参来体现），
        //否则就会抛异常
        Date date2 = sdf1.parse("2021-08-17 05:44:02");
        System.out.println(date2); //Tue Aug 17 05:44:02 JST 2021
    }

    /*
    练习一：字符串"2020-09-20"转换成java.sql.Date

    练习二：：“三天打鱼两天晒网”  1991-01-01  xxxx-xx-xx 打渔？晒网？
    举例：2020-09-08 ？ 总天数
    总天数 % 5 ==1,2,3 :打渔
    总天数 % 5 ==4，9 ：晒网

    总天数的计算？
    方式一：(date2.getTime() - date1.getTime()) / (1000 *60*60*24) + 1
    方式二：(1990-01-01 --> 2019-12-31) + (2020-01-01 --> 2020-09-08)
     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-20";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        //字符串 → java.util.Date
        Date date = sdf1.parse(birth);
        System.out.println(date);
        //java.util.Date → java.sql.Date
        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);
    }


    /*
    Calendar日历类(抽象类)的使用
     */
    @Test
    public void testCalendar(){
        //1.实例化
        //方式一：创建其子类（GregorianCalendar）的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());

        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        //set()
        calendar.set(Calendar.DAY_OF_MONTH,22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //add()
        calendar.add(Calendar.DAY_OF_MONTH,10);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        calendar.add(Calendar.DAY_OF_MONTH,-5);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //getTime():日历类 --> Date
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime():Date--> 日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
    }
}
