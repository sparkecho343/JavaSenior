package com.atguigu.exer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 练习2. 实现图片加密操作。
 *
 * @author kasio
 * @create 2021-02-23 22:51
 */
public class PicTest {
    //图片的加密
    @Test
    public void test1(){
        FileInputStream fio = null;
        FileOutputStream fos = null;
        try {
            fio = new FileInputStream("1.png");
            fos = new FileOutputStream("1_secret.png");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fio.read(buffer)) != -1){
                //对字节数据进行修改
                //错误的：并未对buffer里的元素进行修改
    //            for(byte b : buffer){
    //                b = (byte)(b ^ 5);
    //            }
                for(int i =0; i < len; i++){
                    buffer[i] = (byte)(buffer[i] ^ 5);
                }
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fio != null){
                try {
                    fio.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //图片的解密
    @Test
    public void test2(){
        FileInputStream fio = null;
        FileOutputStream fos = null;
        try {
            fio = new FileInputStream("1_secret.png");
            fos = new FileOutputStream("1_nosecret.png");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fio.read(buffer)) != -1){
                //对字节数据进行修改
                //错误的：并未对buffer里的元素进行修改
                //            for(byte b : buffer){
                //                b = (byte)(b ^ 5);
                //            }
                //公式：m^n^n = m  (m异或n再异或n等于m)
                for(int i =0; i < len; i++){
                    buffer[i] = (byte)(buffer[i] ^ 5);
                }
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fio != null){
                try {
                    fio.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
