package com.crud.Dao;

import com.crud.domain.Orders;
import com.crud.domain.Produce;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface  OrderDao {
    @Insert("insert into orders (username,totalAmont,statusid) values (#{username},#{totalAmont},#{statusid})")
    @Options(useGeneratedKeys = true, keyColumn = "orderid", keyProperty = "orderid")
    boolean addOrder(Orders orders);
    @Insert("insert into orderdetails (orderid,produceid,quantity,unitprice,totalPrice) values(#{orderid},#{bookid},#{quantity},#{unitprice},#{totalprice}) ")
    boolean addBooks(Produce produce);

}
