package com.crud;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crud.Dao.BookDao;
import com.crud.Dao.OrderDao;
import com.crud.Dao.UserDao;
import com.crud.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CrudApplicationTests {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
@Test
    void selectByName(){
        List<String> funces=new ArrayList<>();
        List<Func> s = userDao.findPermissionByUserId("123");
        s.forEach(c->funces.add(c.getCode()));
        String func = "";
        for (int i = 0; i < funces.size() ;i++) {
            if(i!=funces.size()-1) {
                func += funces.get(i) + ",";
            }else {
                func+=funces.get(i);
            }
        };

        System.out.println(func);
    }
    @Test
    void contextLoads() {
        System.out.println(orderDao.getOrderBook(10));
    }
    @Test
    void selecrByids() {
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void save() {
        Orders order =new Orders();
        order.setStatusid(1);
        order.setTotalAmont(100.0);
        order.setUsername("123");
        orderDao.addOrder(order);
        int generatedId = order.getOrderid(); // 获取自动生成的ID
        System.out.println("生成的订单ID：" + generatedId);
    }

    @Test
    void delete() {
       bookDao.deleteById(22);
    }
    @Test
    void selectAll(){
        QueryWrapper qw=new QueryWrapper();
        System.out.println(bookDao.selectList(null));
    }
    @Test
    void testGetPage(){
        IPage page=new Page(1,5);
        IPage page1 = bookDao.selectPage(page, null);
        System.out.println(page1.getRecords());

    }
    @Test
    void changepassword() {
        User user=new User();
        user.setUsername("abc123");
        user.setPassword("123456");
        userDao.register(user);
    }
    @Test
    void testGETBy(){
      Produce produce=new Produce();
      produce.setProduceid(1);
      produce.setQuantity(1);
      produce.setOrderid(1);
      produce.setUnitprice(1.1);
      produce.setTotalprice(1.2);
      orderDao.addBooks(produce);
    }

}
