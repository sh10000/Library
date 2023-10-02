package com.crud.controller;

import com.crud.Dao.OrderDao;
import com.crud.controller.utils.R;
import com.crud.domain.Orders;
import com.crud.service.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class OrderController {
    @Autowired
    private OrderServer orderDao;
    @PostMapping("/give")
    public R saveOrder(@RequestBody List<Integer> bookids ){
        System.out.println(bookids);
        int save=orderDao.saveOrder((Map<Integer, Integer>) bookids);
        return new R<>(true,save,"添加成功");
    }
}
