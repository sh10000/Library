package com.crud.service.impl;

import com.crud.Dao.BookDao;
import com.crud.Dao.OrderDao;
import com.crud.domain.Book;
import com.crud.domain.OrderBook;
import com.crud.domain.Orders;
import com.crud.domain.Produce;
import com.crud.service.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OrderServerImpl implements OrderServer {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public Integer saveOrder(Map<Integer, Integer> bookQuantityMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=new String();
        Produce produce =new Produce();
        Orders orders =new Orders();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
            // 其他用户信息也可以在 userDetails 中获取
            orders.setUsername(username);
        }
        int id=0,i = 0;
        double total=0;
        for (Map.Entry<Integer, Integer> entry : bookQuantityMap.entrySet()) {

            Integer bookId = entry.getKey();
            Integer quantity = entry.getValue();
            produce.setUnitprice(bookDao.bookPrice(bookId));
            produce.setQuantity(quantity);
            produce.setTotalprice(produce.getQuantity()*produce.getUnitprice());
            produce.setProduceid(bookId);
            if(i==0){
                orders.setStatusid(1);
                orderDao.addOrder(orders);
                int generatedId = orders.getOrderid();
                id=generatedId;
                i+=1;
            }
            total+=produce.getQuantity()*produce.getUnitprice();
            produce.setOrderid(id);
            orderDao.addBooks(produce);

        }
        orderDao.addAmont(total,id);
        return id;


    }

    @Override
    public boolean putOrder(int id) {
        orderDao.putStatus(2,id);
        List<Produce> books=askBooks(id);
        orderDao.putStatus(3,id);
        for (Produce book:books) {
            if(book.getQuantity()>=2){
                orderDao.putStatus(4,id);
            }

            Book check= bookDao.selectById(book.getProduceid());
            int size= Integer.parseInt(check.getPage().replaceAll("[^0-9]", ""));
            if(size<50){
                orderDao.putStatus(4,id);
            }
            int page= Integer.parseInt(check.getSize().replaceAll("[^0-9]", ""));
            if(page>27||page<18){
                orderDao.putStatus(4,id);
            }
            String readers = check.getReaders();
            if(readers.contains("中学生") || readers.contains("小学生") || readers.contains("幼儿") ||
                    readers.contains("高职") || readers.contains("高专") || readers.contains("儿童")){
                orderDao.putStatus(4,id);
            }

        }
        return true;
    }

    @Override
    public List<Produce> askBooks(int id) {
        return orderDao.getBook(id);
    }

    @Override
    public List<Orders> askOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=new String();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
            // 其他用户信息也可以在 userDetails 中获取
        }
        return orderDao.getOrder(username);
    }

    @Override
    public List<OrderBook> askOrderBook(int id) {

        return orderDao.getOrderBook(id);
    }

    @Override
    public boolean delete(int id) {
        orderDao.delete(id);
        return true;
    }
}
