package com.atguigu.java;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * JDK8中的日期时间测试
 *
 * @author kasio
 * @create 2020-09-25 14:23
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate(){
        //偏移量
        Date date1 = new Date(2020, 9, 8);
        System.out.println(date1); //Fri Oct 08 00:00:00 JST 3920 偏移性

        Date date2 = new Date(2020-1900, 9-1, 8);
        System.out.println(date2);
    }

    /*
    LocalDate、LocalTime、LocalDateTime的使用
    说明：
            1.LocalDateTime相较于LocalDate、LocalTime，使用频率要高一些
            2.类似于Calendar
     */

    @Test
    public void test1(){
        //now()获取当前的日期，时间，日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of()设置指定的年，月，日，时，分，秒，没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
        System.out.println(localDateTime1);

        //getXxx()
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //withXxx():设置相关的属性
        //体现不可变性
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate1);
        System.out.println(localDate);

        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime2);
        System.out.println(localDateTime);

        //plusXxx()
        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime3);
        System.out.println(localDateTime);

        //minusXxx()
        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime4);
        System.out.println(localDateTime);
    }

    /*
    Instant的使用
     */
    @Test
    public void test2(){
        //now()获取本初子午线的标准时间
        Instant instant = Instant.now();
        System.out.println(instant); //2020-09-25T06:35:20.882Z，本初子午线的标准时间

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2020-09-25T14:35:20.882+08:00

        //toEpochMilli():获取自1970-01-01 00:00:00(UTC)开始的毫秒数  --> Date类的getTime()方法
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        //ofEpochMilli():通过给定的毫秒数，获取Instant实例  --> Date构造器：new Date(Long millis)
        Instant ofEpochMilli = Instant.ofEpochMilli(1601015720882L);
        System.out.println(ofEpochMilli);

    }


    /*
    DateTimeFormatter:格式化或解析日期和时间
    类似于SimpleDateFormat
     */

    @Test
    public void test3(){
//   方式一：预定义的标准格式。如： ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化: 日期-->字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str1);// 2020-10-20T21:56:58.677

        //解析：字符串 -->日期
        TemporalAccessor parse = formatter.parse("2020-10-20T21:56:58.677");//多态形式，用接口接收
        System.out.println(parse);// {},ISO resolved to 2020-10-20T21:56:58.677
//  方式二：
//  本地化相关的格式。如：ofLocalizedDateTime()
//  FormatStyle.LONG（不能用？） / FormatStyle.MEDIUM / FormatStyle.SHORT ：使用与LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        //格式化
        String str2 = formatter1.format(localDateTime);
        String str3 = formatter2.format(localDateTime);
        System.out.println(str2);// 20/10/20 22:16
        System.out.println(str3);// 2020/10/20 22:34:36

//  本地化相关格式。如：ofLocalizedDate()
//  FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT : 适用于LocalDate
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        DateTimeFormatter formatter4 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        DateTimeFormatter formatter5 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter formatter6 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        //格式化
        String str4 = formatter3.format(localDateTime);
        String str5 = formatter4.format(localDateTime);
        String str6 = formatter5.format(localDateTime);
        String str7 = formatter6.format(localDateTime);
        System.out.println(str4);// 20/10/20
        System.out.println(str5);// 2020/10/20
        System.out.println(str6);// 2020/10/20
        System.out.println(str7);// 日语：2020年10月20日  中文；2020年10月20日 星期二

//  重点（开发时常用）：方式三：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
        DateTimeFormatter formatter7 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String str8 = formatter7.format(LocalDateTime.now());
        System.out.println(str8);// 2020-10-20 10:51:12

        //解析
        TemporalAccessor accessor = formatter7.parse("2020-10-20 10:51:12");
        System.out.println(accessor);// {MilliOfSecond=0, MinuteOfHour=51, HourOfAmPm=10, SecondOfMinute=12, NanoOfSecond=0, MicroOfSecond=0},ISO resolved to 2020-10-20

    }
}
