package com.atguigu.java;

import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 涉及到String类和其他结构之间的转换
 *
 * @author kasio
 * @create 2020-05-24 15:09
 */
public class StringTest1 {

    /*
    String 与 byte[]之间的转换

    编码：String --> byte[]:调用String的getBytes()
    解码：byte[] --> String:调用String的构造器

    编码：字符串 --> 字节 （看得懂 --> 看不懂的二进制数据）
    解码：编码的逆过程，字节 --> 字符串 （看不懂的二进制数据 --> 看得懂）

    说明：解码时，要求解码使用的字符集必须与编码是使用的字符集一致，否则会出现乱码。
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        String str1 = "abc123中国";
        byte[] bytes = str1.getBytes(); //使用默认的字符集进行转换,进行编码
        System.out.println(Arrays.toString(bytes));

        byte[] gbks = str1.getBytes("gbk");//使用gbk字符集进行编码
        System.out.println(Arrays.toString(bytes));

        System.out.println("*****************");

        String str2 = new String(bytes); //使用默认的字符集，进行编码
        System.out.println(str2);

        String str3 = new String(gbks);
        System.out.println(str3);   //出现乱码，原因：编码集和解码集不一致！

        String str4 = new String(gbks, "GBK");
        System.out.println(str4);   //没有出现乱码，原因：编码集和解码集是一致的！
    }

    /*
    String 与char[]数组之间的转换

    String --> char[]:调用String的toCharArray()
    char[] --> String:调用String的构造器
     */
    @Test
    public void test2() {
        String str1 = "abc123"; //题目：反转成； a21cb3

        char[] charArray = str1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }

        char[] arr = new char[]{'h', 'e', 'l', 'l', 'o'};
        String str2 = new String(arr);
        System.out.println(str2);
    }


    /*
    复习:
    String 与基本数据类型，包装类之间的转换
    String --> 基本数据类型，包装类:调用包装类的静态方法：parseXxx(str)
    基本数据类型，包装类 --> String:调用String重载的valueOf(xxx)
     */
    @Test
    public void test1() {
        String str1 = "123";
//        int num = (int)str1;  //错误的
        int num = Integer.parseInt(str1);

        String str2 = String.valueOf(num);  //"123"
        String str3 = num + "";

        System.out.println(str1 == str3);   //str3指向new的堆空间，str1指向常量池空间，false
        System.out.println(str1 == str2);   //str2指向new的堆空间，str1指向常量池空间，false
        System.out.println(str3 == str2);   //false
    }

}
