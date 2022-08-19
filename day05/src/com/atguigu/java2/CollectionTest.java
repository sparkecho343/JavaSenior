package com.atguigu.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 一，集合框架的概述
 *
 * 1，集合和数组都是对多个数据进行存储操作的结构，简称java容器。
 *   说明：此时的存储是 ，主要指的是内存层面的存储，不涉及持久化的存储(.txt,.jpg,.avi,数据库中)
 *
 * 2.1，数组再存储多个数据方面的特点：
 *      >一旦初始画以后，长度就确定了。
 *      >数据一旦定义好，其元素的类型也就确立了。我们也就只能操作指定类型的数组了。
 *       比如：String[] arr;int[] arr1;Object[] arr2;
 * 2.2,数组再存储多个数据时的缺点；
 *      >一旦初始画时候，其长度就不可修改。
 *      >数组中提供的方法非常有限，对于添加，删除，插入数据等操作，非常不便，同时效率不高。
 *      >获取数组中实际元素的个数的需求，数组没有线程的属性和方法可用。
 *      >数组存储数据的特点：有序，可重复。对于无序，不可重复的需求，不能满足。
 *
 * 二，集合框架
 *      |----Collection接口：单列集和，用来存储一个一个的对象
 *          |----List接口：存储有序的，可重复的数据  -->"动态"数组
 *              |----ArrayList，LinkedList，Vector
 *
 *          |----Set接口：存储无序的，可重复的数据  -->高中讲的"集合"
 *              |----HashSet，LinkedHashSet，TreeSet
 *
 *      |----Map接口：双列集和，用来存储一对(key - value)一对的数据  -->高中函数 y = f(x)
 *              |----HashMap，LinkedHash Map，TreeMap，Hashtable，Properties
 *
 * 三，Collection接口中的方法的使用
 *
 *
 *
 *
 *
 *
 * @author kasio
 * @create 2021-01-12 16:54
 */
public class CollectionTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();

        //boolean add(Object e):将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123); //自动装箱
        coll.add(new Date());

        //int size():获取添加的元素的个数
        System.out.println(coll.size()); //4

        //boolean addAll(Collection coll1):将coll1集合中的元素添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);
        System.out.println(coll.size()); //6
        System.out.println(coll);

        //void clear():清空集合元素
        coll.clear();

        //boolean isEmpty():判断当前集合是否有元素
        System.out.println(coll.isEmpty());
    }

}
