package com.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crud.Dao.BookDao;
import com.crud.domain.Book;
import com.crud.domain.Order;
import com.crud.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService  {
    @Autowired
    private BookDao bookDao;
    @Override
    public boolean saveBook(Book book) {
        return bookDao.insert(book)>0;
    }

    @Override
    public boolean modify(Book book) {
        return bookDao.updateById(book)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.deleteById(id)>0;
    }

    @Override
    public IPage<Book> getPage(int currentPage, int PageSize) {
        IPage<Book> page=new Page<>(currentPage,PageSize);
         bookDao.selectPage(page,null);
         return page;
    }
    @Override
    public IPage<Book> getPage(int currentPage, int PageSize,Book book) {
        LambdaQueryWrapper<Book> lqw=new LambdaQueryWrapper<Book>();
        lqw.like(Strings.isNotEmpty(book.getIsbn()),Book::getIsbn,book.getIsbn());
        lqw.like(Strings.isNotEmpty(book.getBook()),Book::getBook,book.getBook());
        lqw.like(Strings.isNotEmpty(book.getClassification()),Book::getClassification,book.getClassification());
        IPage<Book> page=new Page<>(currentPage,PageSize);
        bookDao.selectPage(page,lqw);
        return page;
    }

    @Override
    public boolean saveOrder(List<Integer> bookid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=new String();
        Date now = new Date();

        // 定义日期时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        // 格式化日期时间为字符串
        String uniqueString = dateFormat.format(now);

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
            // 其他用户信息也可以在 userDetails 中获取
        }
        Order order1=new Order();
        for (Integer book: bookid) {
            order1.setBookid(book);
            order1.setUid(username);
            order1.setBuy(0);
            order1.setOrderid(uniqueString);
            bookDao.saveOrder(order1);
        }

        return true;
    }

}
