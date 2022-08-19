package com.atguigu.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kasio
 * @create 2021-01-31 14:12
 */
public class PropertiesTest {
    //Properties：常用来处理配置文件。key和value都是String类型
    public static void main(String[] args) {
        FileInputStream fis = null; //默认在工程目录下
        try {
            Properties pros = new Properties();
            fis = new FileInputStream("jdbc.properties");

            pros.load(fis); //加载对应的文件
            String name = pros.getProperty("name");
            String password = pros.getProperty("password");

            //当汉字出现乱码时，Settings→Editor→File Encodings→☑Transparent native-to-ascii coversion
            //然后删除重新创建.properties配置文件
            System.out.println("name = " + name + ", password = " + password);
        } catch (IOException e) { //选中的代码后Alt+Shift+z
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
