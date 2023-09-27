package com.crud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crud.domain.Book;

import java.util.List;

public interface IBookService extends IService<Book> {
boolean saveBook(Book book);
boolean modify(Book book);
boolean delete(Integer id);
IPage<Book>  getPage(int currentPage, int PageSize);
IPage<Book>  getPage(int currentPage, int PageSize, Book book);
boolean saveOrder(List<Integer> bookid);
}
