package com.crud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crud.domain.Book;
import com.crud.domain.User;
import com.crud.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ServiceImpl {
    @Autowired
    private IBookService bookDao;
    @Autowired
    private OrderServerImpl orderServer;
    @Autowired
    private UserServiceImpl userService;
@Test
    void selecrByids() {
        System.out.println(bookDao.getById(1));
    }
    @Test
    void save() {
        Map<Integer, Integer> bookQuantityMap = new HashMap<>();
        bookQuantityMap.put(977, 5); // 书籍ID为1，数量为5
        bookQuantityMap.put(988, 3);
        orderServer.saveOrder(bookQuantityMap);
    }
    @Test
    void update() {
        System.out.println(orderServer.askBooks(10));
    }
    @Test
    void delete() {
        bookDao.removeById(22);
    }
    @Test
    void selectAll(){
        System.out.println(bookDao.list());
    }
    @Test
    void testGetPage(){
    IPage<Book> page=new Page<>(2,5);
        IPage page1 = bookDao.page(page);
        System.out.println(page1.getRecords());

    }
    @Test
    void testGETBy(){
        User user=new User();
        user.setUsername("abc123");
        user.setPassword("123");
        System.out.println(user);
        System.out.println(userService.register(user));
    }
    @Test
    void testGETBy2(){
       orderServer.putOrder(10);
    }
    @Test
    void testOrder(){
        System.out.println(orderServer.askOrders());
    }
}
