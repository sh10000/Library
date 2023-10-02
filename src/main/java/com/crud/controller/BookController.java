package com.crud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crud.controller.utils.R;
import com.crud.domain.Book;
import com.crud.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public R getAll(){
        return  new R(true,bookService.list());
    }
    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));

    }
    @PutMapping
    public R update(@RequestBody Book book){
        return new R(bookService.modify(book));
    }
    @DeleteMapping("{id}")
    //@Secured("ROLE_Abc")
    @PreAuthorize("hasAuthority('p1')")
    public R delete(@PathVariable Integer id){
        return new R(bookService.delete(id));
    }
    @GetMapping("{id}")
    public R getById(@PathVariable    Integer id){
        if(bookService.getById(id)!=null){
        return  new R(true,bookService.getById(id));
        }
        return  new R(false,bookService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable  int currentPage, @PathVariable  int pageSize, Book book){

        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        if(currentPage>page.getPages()){
            page = bookService.getPage((int)page.getPages(), pageSize,book);
        }
        return  new R(true,page);

    }




}
