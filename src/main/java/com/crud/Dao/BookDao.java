package com.crud.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crud.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookDao  extends BaseMapper<Book>{
    @Select("Select price from book where id=#{id}")
    Double bookPrice(int id);
}
