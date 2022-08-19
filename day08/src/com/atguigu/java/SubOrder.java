package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kasio
 * @create 2021-02-02 0:09
 */
public class SubOrder extends Order<Integer>{ //SubOrder:不是泛型类

    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<E>();
        for (E e: list){
            list.add(e);
        }
        return list;
    }
}
