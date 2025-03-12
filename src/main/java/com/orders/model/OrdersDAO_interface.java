package com.orders.model;

import java.util.*;

public interface OrdersDAO_interface {
          public void insert(OrdersVO ordersVO);
          public void update(OrdersVO ordersVO);
          public void delete(OrdersVO ordersVO);
          public OrdersVO findByPrimaryKey(Integer order_id);
          public List<OrdersVO> getAll();
          public List<OrdersVO> getOrderlist(Integer member_id);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
