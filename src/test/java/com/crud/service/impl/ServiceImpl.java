package com.crud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crud.domain.Book;
import com.crud.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceImpl {
    @Autowired
    private IBookService bookDao;
@Test
    void selecrByids() {
        System.out.println(bookDao.getById(1));
    }
    @Test
    void save() {
    }
    @Test
    void update() {
        List<Integer> order=new ArrayList<>();
        order.add(1);
        order.add(2);
        order.add(3);
        System.out.println(bookDao.saveOrder(order));}
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
//    @Test
//    void testGETBy(){
//        QueryWrapper<Book> qw=new QueryWrapper<>();
//        qw.like("name","Spring");
//        System.out.println(bookDao.selectList(qw));
//    }
//    @Test
//    void testGETBy2(){
//        String name=null;
//        LambdaQueryWrapper<Book> lqw=new LambdaQueryWrapper<>();
//        lqw.like(name!=null,Book::getName,"Spring");
//        System.out.println(bookDao.selectList(lqw));
//    }

}
