package com.atguigu.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输出流，也可以作为一个输入流(造对象时，仍需要两个对象，一个用来输入，一个用来输出)
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件不存在，则在执行过程中自动创建
 *   如果写出到的文件存在，则会对原文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4.可以通过相关的操作，实现RandomAccessFile"插入"数据的效果(插入效率差，一般用来追加)
 *
 * @author kasio
 * @create 2021-03-03 15:53
 */
public class RandomAccessFileTest {
    @Test
    public void test1(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.
            raf1 = new RandomAccessFile(new File("1.png"),"r");
            raf2 = new RandomAccessFile(new File("2.png"),"rw");

            //2.
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){

                raf2.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile(new File("hello.txt"), "rw");

        //把指针调到角标为3的位置
        raf1.seek(3);
        //write(byte[])
        raf1.write("xyz".getBytes());

        raf1.close();
    }

    /*
    使用RandomAccessFile实现数据插入的效果
     */
    @Test
    public void test3() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile(new File("hello.txt"), "rw");

        //把指针调到角标为3的位置
        //seek(int pos)方法不同于其他流(要么在文件末尾添加，要么整个文件覆盖掉)，此方法可以深入到文件内部内部进行添加操作
        //像b站的流媒体视频用的就是这个技术
        raf1.seek(3);
        //保存指针3后面的所有数据
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer, 0, len));
        }

        //此时指针在最末尾，再调回角标4的位置,写入"xyz"
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //将StringBuilder中的数据写入文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：将StringBuilder替换为ByteArrayOutputStream。参考ByteArrayOutputStreamTest.java
    }
}
