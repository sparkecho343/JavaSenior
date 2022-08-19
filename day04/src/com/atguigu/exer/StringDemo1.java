package com.atguigu.exer;

import org.junit.Test;

/**
 * @author kasio
 * @create 2020-08-17 15:23
 */
public class StringDemo1 {
    /*
    获取一个字符串在另一个字符串出现的次数
        比如：获取ab在abkkcadkabkebfkabkskab中出现的次数
     */

    /**
     * 获取subStr在mainStr中出现的次数
     * @param mainStr
     * @param subStr
     * @return
     */

    public int getCount(String mainStr, String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;

        if (mainLength >= subLength){
            //方式一：
//            while((index = mainStr.indexOf(subStr)) != -1){
//                count++;
//                mainStr = mainStr.substring(index+subStr.length());
//            }

            //方式二：对方式一的改进
            while((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
                index += subLength;
            }

            return count;
        }else{
            return 0;
        }
    }

    @Test
    public void testGetCount(){
        String mainStr = "abkkcadkabkebfkabkskab";
        String subStr = "ab";

        System.out.println(getCount(mainStr, subStr));
    }
}
