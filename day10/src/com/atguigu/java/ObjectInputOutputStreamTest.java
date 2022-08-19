package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 对象流得使用
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取 基本数据类型数据或 对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 * 4.序列化机制：对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，
 *   从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
 *   //当其它程序获取了这种二进制流，就可以恢复成原来的Java对象
 *
 * @author kasio
 * @create 2021-03-02 23:51
 */
public class ObjectInputOutputStreamTest {
    /*
    序列化过程：将内存中得java对象保存到磁盘中或通过网络传播出去
    使用ObjectOutputStream来实现
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try {
            //1.造流造对象
            oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
            //2.写出的过程
            oos.writeObject(new String("我爱北京天安门"));
            oos.flush(); //刷新操作

            oos.writeObject(new Person("王铭", 23));
            oos.flush();

            oos.writeObject(new Person("张学良", 23, 1001, new Account(5000)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                //3.关闭资源
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    反序列化：将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream来实现
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.txt"));

            //反序列化要按造序列化的顺序来做
            Object obj = ois.readObject();
            String str = (String)obj;
            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p1);
            System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
