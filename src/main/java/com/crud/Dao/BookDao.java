package com.crud.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crud.domain.Book;
import com.crud.domain.Order;
import com.crud.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDao  extends BaseMapper<Book>{
    @Insert("INSERT INTO `order` (uid, bookid, buy, orderid) VALUES (#{uid}, #{bookid}, #{buy}, #{orderid})")
    Boolean saveOrder(Order order);
}
