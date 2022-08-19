package com.atguigu.exer;

/**
 * 一道面试题
 *
 * @author kasio
 * @create 2020-05-13 15:14
 */
public class StringTest {

    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'b';
    }

    //引用类型传递地址值，基本数据类型传递数值
    //1，ex对象的str变量的堆引用地址传给chang方法的局部变量str，
    //2，局部变量str被重新赋值"test ok"字符串常量在常量池的地址值。
    //3，所以此时ex对象的成员变量str还是指向原来堆的引用地址。change方法的局部变量重新指向常量池的地址。
    //4，之所以这样是因为，成员变量str是不可变更对象String类型
    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }
}
