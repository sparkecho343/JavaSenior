package com.atguigu.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author kasio
 * @create 2021-03-03 17:26
 */
public class FileUtilsTest {
    public static void main(String[] args) throws IOException {
        File srcFile = new File("day10\\1.png");
        File destFile = new File("day10\\3.png");
        FileUtils.copyFile(srcFile,destFile);
    }
}
