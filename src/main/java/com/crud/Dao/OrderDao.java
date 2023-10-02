package com.crud.Dao;

import com.crud.domain.OrderBook;
import com.crud.domain.Orders;
import com.crud.domain.Produce;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  OrderDao {
    @Insert("insert into orders (username,totalAmont,statusid) values (#{username},#{totalAmont},#{statusid})")
    @Options(useGeneratedKeys = true, keyColumn = "orderid", keyProperty = "orderid")
    boolean addOrder(Orders orders);
    @Insert("insert into orderdetails (orderid,produceid,quantity,unitprice,totalPrice) values(#{orderid},#{produceid},#{quantity},#{unitprice},#{totalprice}) ")
    boolean addBooks(Produce produce);
    @Update("Update orders set totalAmont=#{total} where orderid=#{id}")
    boolean addAmont(double total,int id);
    @Update("Update orders set statusid=#{status} where orderid=#{id}")
    boolean putStatus(int status,int id);
    @Select("Select produceid,quantity,unitprice,totalPrice from orderdetails where orderid=#{id}")
    List<Produce> getBook(int id);
    @Select("select a.orderid,a.orderdate,a.totalAmont,b.statusname from orders a,orderstatus b where a.statusid=b.id and a.username=#{username}")
    List<Orders> getOrder(String username);

    @Select("select a.id,a.isbn,a.book,a.author,a.publisher,a.price,a.theme,a.subtitle,a.volume,a.VolumeName,a.series,a.version,a.page," +
            "a.size,a.annex,a.annotations,a.outline,a.textbook,a.classification,a.readers,a.layout,a.PublicationTime,a.languages,b.quantity,b.unitprice,b.totalPrice " +
            "from orderdetails b,book a where a.id=b.produceid and b.orderid=#{orderid}")
    List<OrderBook> getOrderBook(int orderid);
}
