package com.order_pet.model;

import java.util.List;

public interface OrderPetDAO_interface {
    // 新增一筆預約寵物記錄
    public void insert(OrderPetVO orderPetVO);
    // 更新一筆預約寵物記錄
    public void update(OrderPetVO orderPetVO);
    // 根據預約寵物編號刪除一筆記錄
    public void delete(Integer opetId);
    // 根據預約寵物編號查詢一筆記錄
    public OrderPetVO findByPrimaryKey(Integer opetId);
    // 查詢所有預約寵物記錄
    public List<OrderPetVO> getAll();
}
