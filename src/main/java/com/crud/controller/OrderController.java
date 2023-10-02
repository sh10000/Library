package com.crud.controller;

import com.crud.Dao.OrderDao;
import com.crud.controller.utils.R;
import com.crud.domain.OrderBook;
import com.crud.domain.Orders;
import com.crud.service.OrderServer;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServer orderDao;
    @PostMapping("/give")
    public R saveOrder(@RequestBody Map<Integer, Integer> bookids ){
        System.out.println(bookids);
        int save=orderDao.saveOrder(bookids);
        return  new R(true,save,"添加成功");
    }
    @PostMapping("/put/{id}")
    public R putOrder(@PathVariable int id){
      orderDao.putOrder(id);
        return  new R(true,"提交成功");
    }
    @GetMapping("/getOrder")
    public R putOrder(){
        List<Orders> orders = orderDao.askOrders();
        return  new R(true,orders,"提交成功");
    }
    @GetMapping("/getOrderBook/{id}")
    public R OrderBook(@PathVariable int id){
        List<OrderBook> orderBooks = orderDao.askOrderBook(id);
        return  new R(true,orderBooks,"查询成功");
    }
    @DeleteMapping("/deleteOrder/{id}")
    public R DeleteOrder(@PathVariable int id){
        orderDao.delete(id);
        return  new R(true,"删除成功");
    }
}
