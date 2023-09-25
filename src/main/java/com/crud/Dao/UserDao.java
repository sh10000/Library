package com.crud.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.crud.domain.Func;
import com.crud.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao  extends BaseMapper<User> {
    @Select("select * from user where username=#{username}")
    User findByUsernameUser(String username);

@Select("select * from func where id IN (select fid from role_func where  " +
        " rid in(select rid from user_role where uid=#{username}))")
    List<Func> findPermissionByUserId(String username);

}
