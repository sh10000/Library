package com.crud;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crud.Dao.BookDao;
import com.crud.Dao.UserDao;
import com.crud.domain.Book;
import com.crud.domain.Func;
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
        PasswordEncoder pe=new BCryptPasswordEncoder();
        String encode = pe.encode("123");
        System.out.println(encode);
        boolean matches = pe.matches("1234", encode);
        System.out.println(matches);
    }
    @Test
    void selecrByids() {
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void save() {
        Book book=new Book();
        book.setName("测试");
        book.setType("测试");
        book.setDescription("测试");
        System.out.println(bookDao.insert(book));
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
    void testGETBy(){
        QueryWrapper<Book> qw=new QueryWrapper<>();
        qw.like("name","Spring");
        System.out.println(bookDao.selectList(qw));
    }
    @Test
    void testGETBy2(){
        String name=null;
        LambdaQueryWrapper<Book> lqw=new LambdaQueryWrapper<>();
        lqw.like(name!=null,Book::getName,"Spring");
        System.out.println(bookDao.selectList(lqw));
    }

}
