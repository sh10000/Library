package com.crud.service;

import com.crud.domain.OrderBook;
import com.crud.domain.Orders;
import com.crud.domain.Produce;

import java.util.List;
import java.util.Map;

public interface OrderServer {
    Integer saveOrder(Map<Integer, Integer> bookQuantityMap);
    boolean putOrder(int id);
    List<Produce> askBooks(int id);
    List<Orders> askOrders();
    List<OrderBook> askOrderBook(int id);
}
