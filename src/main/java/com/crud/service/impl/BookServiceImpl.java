package com.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crud.Dao.BookDao;
import com.crud.Dao.OrderDao;
import com.crud.domain.Book;
import com.crud.domain.Orders;
import com.crud.domain.Produce;
import com.crud.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


}
