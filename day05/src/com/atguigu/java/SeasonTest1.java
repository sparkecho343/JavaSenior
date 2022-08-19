package com.atguigu.java;

/**
 * 使用enum关键字定义枚举类
 *
 * @author kasio
 * @create 2021-01-01 18:38
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        //toSting()方法
        System.out.println(Season1.SPRING); //打印的不是地址值，说明Season1的符类不是Object
        System.out.println(Season1.class.getSuperclass());

        System.out.println("*******************");
        //values()方法
        Season1[] values = Season1.values();
        for(int i = 0;i < values.length;i++){
            System.out.println(values[i]);
        }

        System.out.println("*******************");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        System.out.println("*******************");
        //valueOf(String objName):返回枚举类中对象名时objName的对象
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
//        Season1 winter = Season1.valueOf("WINTER1");
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
        winter.show();
    }
}

interface Info{
    void show();
}

//使用enum关键字枚举类
enum Season1 implements Info{
    //1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象用";"结束，且删除public static final Season1 和 new Season1
    SPRING("春天", "春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("春天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("春天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来？");
        }
    },
    WINTER("春天", "冰天雪地"){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };


    //2.声明Season对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //3.私有化类的构造器
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }


    //4.其他诉求1；获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

//    //5.其他诉求2：提供toString方法
//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }

    //情况一
//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}