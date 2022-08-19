package com.atguigu.java2;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 1.泛型在继承反面的体先
 *
 * 2.通配符的使用
 *
 *
 * @author kasio
 * @create 2021-02-03 0:08
 */
public class GenericTest {

    /*
    1.泛型在继承反面的体先
      虽然类A是类B的父类，但是G<A>和G<B>二者不具备子父类关系，二者是并列关系
      补充：类A是类B的父类，则A<G>是B<G>的父类。
     */
    @Test
    public void test1(){

        Object obj = null;
        String str = null;
        //子类的对象赋给父类的引用，多态的引用
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //编译不通过
//        Date date = new Date();
//        str = date;
        List<Object> list1 = null;
        List<String> list2 = null;
        //此时的list1和list2类型不具有子父类关系
        //编译不通过
//        list1 = list2;
        /*
        反证法：
        假设可以list1 = list2
            list1.add(123);导致混入非String的数据。出错。
         */

        show(list1);
        show1(list2);
    }

    public void show1(List<String> list){

    }
    public void show(List<Object> list){

    }

    @Test
    public void test2(){
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;
        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();
    }

    /*
    2.通配符的使用
      通配符：?
      类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list =list1;
        list =list2;

        //编译通过
//        print(list1);
//        print(list2);

        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入):List<?>就不能向其内部添加数据
        //除了添加null之外
//        list.add("DD");
//        list.add('?');
        list.add(null);

        //获取(读取)：允许读取数据，读取数据类型为Object，不管?是什么，总能赋给根父类
        Object o = list.get(0);
        System.out.println(o);

    }

    //不管?是啥，总能被Object的引用装下
    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
        }
    }

    /*
    3.有限制条件的通配符的使用
        ? extends A:
                G<? extends A>可以作为G<A>和G<B>的父类的，其中B是A的子类
        ? super A:
                G<? super A>可以作为G<A>和G<B>的父类的，其中B是A的父类
     */
    @Test
    public void test4(){
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();
        List<Object> list5 = new ArrayList<>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据:
        list1 = list4;
        Person p = list1.get(0);
        //编译不通过
//        Student s = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);
        //编译不通过
//        Person p1 = list2.get(0);

        //写入数据
        //编译不通过：<? extends Person> 代表(负无穷,Person)这个范围内的某个类，
        // 考虑到如果这个值比Student类还要小(是Student的子类)，则无法多态赋值进去。
//        list1.add(new Student());

        //编译通过：<? super Person> 代表(Person,正无穷)这个范围内的某个类，
        //无论是这个范围里的那个类(这个类一定是Person的父类或就是Person类)，都可以实现多态赋值
        list2.add(new Student());
        list2.add(new Person());
    }
}
