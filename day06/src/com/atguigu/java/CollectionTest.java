package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Collection接口中声明的方法的测试
 *
 * 结论：
 * 向Collection接口的实现类的对象中添加obj数据时，要求obj所在类要重写equals(),
 * 否则有可能调用父类Object中的equals()方法,对比空间地址值。
 * @author kasio
 * @create 2021-01-18 22:59
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
//        Person p = new Person("Jerry", 20);
//        coll.add(p);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //1.boolean contains(Object obj):判断当前集合是否包含obj
        //我们在判断时会调用obj所在类的方法equals()
        System.out.println("*****************contains(Object obj)*****************");
        boolean contains = coll.contains(123);
        System.out.println(contains);
        System.out.println(coll.contains(new String("Tom"))); //ture
//        System.out.println(coll.contains(p)); //ture
        System.out.println(coll.contains(new Person("Jerry",20))); //false -->ture


        //2.boolean containsAll(Collection coll1):判断形参coll1中的所有元素是否都存在于当前集合中。
        System.out.println("*****************containsAll(Collection coll1)*****************");
        Collection coll1 = Arrays.asList(123,456);
        System.out.println(coll.containsAll(coll1));
    }

    @Test
    public void test2(){
        //3.boolean remove(Object obj):从当前集合中删除obj元素
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        System.out.println("*****************remove(Object obj)*****************");
        boolean remove = coll.remove(1234);
        System.out.println(remove); //false
        System.out.println(coll);

        boolean remove1 = coll.remove(new Person("Jerry", 20));  //equals()判断为true后可移除
        System.out.println(remove1);
        System.out.println(coll);

        //4.boolean removeAll(Collection coll1)删除交集保留差集:从当前集合中移除coll1中coll也包含的所有的元素
        //并集：对于给定的两个集合，返回一个包含两个集合中所有元素的新集合。
        //交集：对于给定的两个集合，返回一个包含两个集合中共有元素的新集合。
        // 差集：对于给定的两个集合，返回一个包含所有存在于第一个集合且不存在于第二个集合的元素的新集合。
        System.out.println("*****************removeAll(Collection coll1)*****************");
        Collection coll1 = Arrays.asList(123,4567);
        boolean removeAll = coll.removeAll(coll1); //调用coll1对象中equals()方法
        System.out.println(removeAll);
        System.out.println(coll);
    }

    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

//        //5.boolean retainAll(Collection coll1):交集：获取当前集合和coll1集合的交集，并返回给当前集合coll
//        System.out.println("*****************retainAll(Collection coll1)*****************");
//        Collection coll1 = Arrays.asList(123, 456, 789);
//        boolean retainAll = coll.retainAll(coll1);
//        System.out.println(retainAll);
//        System.out.println(coll);

        //6.boolean equals(Object obj):要想返回ture，需要当前集合和形参集合的元素都相同（是否有序也需要考虑）。
        System.out.println("*****************equals(Object obj)*****************");
        Collection coll2 = new ArrayList();
        coll2.add(456);
        coll2.add(123);
        coll2.add(new Person("Jerry", 20));
        coll2.add(new String("Tom"));
        coll2.add(false);

        System.out.println(coll.equals(coll2)); //false，元素相同但是顺序不同，ArrayList是有序集合，如果是hashSet无序集合则不需要考虑
    }

    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //7.int hashCode():返回当前对象的哈希值
        System.out.println("*****************hashCode()*****************");
        System.out.println(coll.hashCode());

        //8.集合 --> 数组:Object[] toArray()
        System.out.println("*****************toArray()*****************");
        Object[] arr = coll.toArray();
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }

        //拓展：数组 --> 集合:调用Arrays类的静态方法asList()
        System.out.println("*****************Arrays.asList()*****************");
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);

        //Arrays.asList(T... a)可变形参的注意事项
        System.out.println("*****************Arrays.asList()的可变形参*****************");
        //情况1：
        int[] i = new int[]{123, 456};
        System.out.println(i);
        List<int[]> arr1 = Arrays.asList(i); //Arrays.asList(T... a)可变形参，把123，456两个元素当成int[]数组一个元素了
        System.out.println(arr1);
        System.out.println(arr1.size()); //1

        //情况2：自动装包
        List<Integer> arr2 = Arrays.asList(123, 456); //这种情况就不会把123，456两个元素当成int[]数组一个元素了
        System.out.println(arr2);
        System.out.println(arr2.size()); //2

        //情况3：
        List<Integer> arr3 = Arrays.asList(new Integer[]{123, 456});
//        List<Integer[]> arr6 = Arrays.asList(new Integer[]{123, 456}); //编译报错：「不適合な型: 推論変数Tには、不適合な境界があります」
        System.out.println(arr3);
        System.out.println(arr3.size()); //2

        //情况4：
        List<Integer[]> arr4 = Arrays.asList(new Integer[]{123, 456},new Integer[]{123, 456});
        System.out.println(arr4); //输出两个空间地址值
        System.out.println(arr4.size()); //2

        //iterato():返回Iterator接口的实例，用于遍历集合元素。放在IteratorTest.java中测试
    }
}
