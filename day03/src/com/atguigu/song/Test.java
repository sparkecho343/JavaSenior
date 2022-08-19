package com.atguigu.song;

/**
 * @author kasio
 * @create 2020-08-01 15:34
 */
public class Test {

   public static String trim(String str){

       return str.replaceAll("(^\\s*)|(\\s*$)","");
   }
    public static void main(String args[]) {
        System.out.println(Test.trim("       323fdas 324 vf    "));
    }


}
