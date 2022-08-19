package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author kasio
 * @create 2021-01-24 16:45
 */
public class CollectionTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(1);
        coll.add(198);
        coll.add(1000);

        coll.forEach(System.out::println);
    }

    //练习：在List 内去除重复数字值，要求尽量简单
    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object integer : list2) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        p1.name = "CC"; //此时在new Person(1001,"AA")的哈希值地址上把AA修改了CC
        System.out.println(set.remove(p1)); //根据此时p1对象内1001，CC的值生成哈希值，匹配的地址是null，没删成功
        //↑因为原来p1的哈希值地址是1001，AA的值来生成的
        System.out.println(set);

        set.add(new Person(1001,"CC"));//根据1001，CC生成的哈希值地址，能添加成功
        System.out.println(set);
        set.add(new Person(1001,"AA"));//根据1001，AA生成哈希值地址，发现有对象存在(p1)，再根据equals方法对比发现AA和CC值不一样，所以纵向添加成功
        System.out.println(set);

        //题外话：在路上认错人，其实时进行哈希值判断相等，但其实equals方法判断后对象内的值不相等。
    }

}
