package com.atguigu.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 注解的使用
 * 1.理解Annotation
 * ① jdk5.0 新增的功能
 *
 * ②Annotation 其实就是代码里的 特殊标记, 这些标记可以在编译, 类加载, 运行时被读取, 并执行相应的处理。
 * 通过使用Annotation, 程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③在JavaEE/Android中注解占据了更重要的角色，例如
 * 用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。
 *
 * 2.Anotation使用示例
 * 示例一：生成文档相关注解
 * 示例二：在编译时进行格式查 检查(JDK 内置的三个基本注解)
 *   @Override: 限定重写父类方法, 该注解只能用于方法
 *   @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 *   @SuppressWarnings: 抑制编译器警告
 * 示例三： 跟踪代码依赖性，实现替代配置文件功能
 *
 * 3.如何自定义注解，参照@SuppressWarnings定义注解
 * ①注解声明为：@interface
 * ②内部定义成员，通常使用value来表示
 * ③可以指定成员的默认值，使用default来表示
 * ④如果自定义注解没有成员，表明是一个标识作用。如同Serializable是一个标识接口
 *
 * 如果注解有成员，在使用注解时，需要指定成员的值。
 * 自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
 * 自定义注解通常都会指明两个元注解：Retention，Target
 *
 * 4，jdk提供的四种元注解
 * 元注解:对现有的注解进行解释说明的注解
 * Retention：指定所修饰的Annotation 的生命周期：SOURCE\CLASS(默认行为)\RUNTIME，
 *             只用声明为RUNTIME生命周期的注释，才能通过反射获取。
 * Target：用于修饰Annotation定义,用于指定被修饰的Annotation能用于修饰哪些程序元素。
 ***********↓出现频率较低*********************
 * Documented：表示所修饰的注解在被javadoc解析时，保留下来
 * Inherited：被它修饰的Annotation将具有继承性
 *
 * 5,通过反射来获取注解信息 ---到反射内容时系统讲解
 *
 * 6.jsk8 中注解的新特性：可重复注解，类型注解
 * 6.1 可重复注解：①再MyAnnotation上声明@Repeatable,成员值为MyAnnotation.class
 *               ②MyAnnotation的Target和Repeatable等元注解和MyAnnotations相同
 * 6.2 类型注解：
 * ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
 * ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
 *
 *
 * @author kasio
 * @create 2021-01-07 17:28
 */
public class AnnotationTest {
    public static void main(String[] args){
        Person p = new Student();
        p.walk();

        Date date = new Date(2021, 01, 07);
        System.out.println(date);

        @SuppressWarnings("unused")     //抑制了unused未使用的警告，在Eclipse有更好的体验
        int num = 10;

        @SuppressWarnings({"unused","rawtypes"})    //抑制了rawtypes泛型的警告
        ArrayList list = new ArrayList();

//        System.out.println(num);
    }

    //Inherited：被它修饰的Annotation将具有继承性
    @Test
    public void testGetAnnotation(){
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (int i = 0;i < annotations.length;i++){
            System.out.println(annotations[i]);
        }
    }

}

//jdk8之前实现可重复注解→使用数组
//@MyAnnotations(@MyAnnotation(value = "hi"),@MyAnnotation(value = "abc"))
//jdk8之后实现可重复注解→@Repeatable(MyAnnotations.class)
@MyAnnotation(value = "hi")
@MyAnnotation(value = "abc")
class Person{
    private String name;
    private int age;

    @MyAnnotation()
    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("人走路");
    }

    public void eat(){
        System.out.println("人吃饭");
    }
}

interface Info{
    void show();
}

class Student extends Person implements Info{

    //不加Override，但是下面的方法也是重写，但是不会校验
    public void walk() {
        System.out.println("学生走路");
    }

    @Override  //编译的时候会校验没下面的方法必须时重写的,只有方法才能加Override
    public void show() {

    }
}

class Generic<@MyAnnotation T>{

    public void show() throws @MyAnnotation RuntimeException{

        ArrayList<@MyAnnotation String> list= new ArrayList<>();

        int num = (@MyAnnotation int) 10L;

    }
}
