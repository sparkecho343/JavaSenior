package com.atguigu.java;

import java.io.Serializable;

/**
 * Person类需要满足如下的要求，方可序列化
 * 1.需要实现接口：Serializable
 * 2.当前类提供一个全局常量：serialVersionUID
 *      如果类没有显示定义这个静态常量，它的值是Java运行时环境根据类的内部细节自动生成的。
 *      若类的实例变量做了修改，serialVersionUID 可能发生变化。故建议，显式声明。
 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的。
 *   (默认情况下，基本数据类型可序列化)
 *
 * 补充: ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
 *
 * @author kasio
 * @create 2021-03-03 0:06
 */
//凡是像Serializable接口一样，没有任何属性和方法的，我们都称为：标识接口
public class Person implements Serializable {
    //序列号番号，正数负数都可以
    public static final long serialVersionUID = 472445234532L;

    private static String name;
    private transient int age;
    private int id;
    private Account acct;

    public Person() {
    }

    public Person(String name, int age, int id, Account acct) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.acct = acct;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", acct=" + acct +
                '}';
    }
}

class Account implements Serializable{
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
