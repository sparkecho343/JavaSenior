package com.atguigu.exer;

import org.junit.Test;

/**
 * @author kasio
 * @create 2020-08-10 12:59
 */
public class StringDemo {

    /*
    将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”

    方法一：转换成char[]数组，
     */
    public String reverse(String str,int startIndex,int endIndex){

        //方式一：

        if(str != null){

            char[] arr = str.toCharArray();
            for(int x = startIndex, y =endIndex; x < y; x++, y--){
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
                return new String(arr);
            }

        }
        return null;
    }

    //方式二：使用String的拼接操作,思路：把字符串分成三部分
    public String reverse1(String str,int startIndex,int endIndex){

        if(str != null){
            //第一部分：把前部不需要反转的字符串单独拎出来
            String reverseStr = str.substring(0,startIndex);
            //第二部分：把需要反转的字符串，一个一个反转后拼接起来
            for(int i = endIndex; i >= startIndex; i--){
                reverseStr += str.charAt(i);
            }
            //第三部分
            reverseStr += str.substring(endIndex + 1);
            return reverseStr;
        }
        return null;
    }

    //方式三：使用StringBuffer/StringBuilder替换String,效率最高

    public String reverse2(String str,int startIndex,int endIndex){

        if(str != null){
            StringBuilder builder = new StringBuilder(str.length());
            //第一部分
            builder.append(str.substring(0,startIndex));
            //第二部分
            for(int i = endIndex; i >= startIndex; i--){
                builder.append(str.charAt(i));
            }
            //第三部分
            builder.append(str.substring(endIndex + 1));
            return builder.toString();
        }
        return null;
    }

    @Test
    public void testReverse(){
        String str = "abcdefg";
        String reverse = reverse2(str, 2,5);
        System.out.println(reverse);
    }


}
