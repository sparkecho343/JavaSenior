package com.atguigu.exer1;

import java.util.List;

/**
 * 定义一个测试类：
 * 创建DAO类的对象，分别调用其save、get、update、list、delete方法来操作User对象，使用Junit单元测试类进行测试。
 * @author kasio
 * @create 2021-02-03 16:47
 */
public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();

        dao.save("1001",new User(1001,34,"周杰伦"));
        dao.save("1002",new User(1002,20,"昆凌"));
        dao.save("1003",new User(1003,25,"蔡依林"));

        dao.update("1003",new User(1003,30,"方文山"));
        dao.delete("1002");

        List<User> list = dao.list();
        System.out.println(list);
        list.forEach(System.out::println);
    }
}
