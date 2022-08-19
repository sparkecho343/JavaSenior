package com.atguigu.exer2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author kasio
 * @create 2021-02-17 0:10
 */
public class FileDemo {
    @Test
    public void test1() throws IOException {
        File file = new File("c:\\io\\io1\\hello.txt");
        //创建一个与File同目录下的另外一个文件，文件名为：haha.txt
        File destFile = new File(file.getParent(),"haha.txt");
        boolean newFile = destFile.createNewFile();
        if(newFile){
            System.out.println("创建成功!");
        }
    }
}
