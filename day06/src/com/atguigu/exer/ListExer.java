package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kasio
 * @create 2021-01-21 22:39
 */
public class ListExer {
    /*
    区分List中的remove(int index)和remove(Object obj)
     */

    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);
    }
    private void updateList(List list) {
//        list.remove(2); //删的时索引2对应的元素
        list.remove(new Integer(2)); //删的对应的元素2
    }
}
