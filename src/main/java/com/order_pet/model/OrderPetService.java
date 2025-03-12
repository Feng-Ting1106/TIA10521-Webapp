package com.order_pet.model;

import java.util.List;

public class OrderPetService {

    private OrderPetDAO_interface dao;

    
    public OrderPetService() {
        dao = new OrderPetJDBCDAO(); // 預設使用 JDBC 實現的 DAO
    }

    // 提供 set 方法以便動態設置 DAO
    public void setOrderPetDAO(OrderPetDAO_interface dao) {
        this.dao = dao;
    }

    // 新增一筆預約寵物記錄
    public OrderPetVO addOrderPet(Integer orderId, Integer petId) {
        OrderPetVO orderPetVO = new OrderPetVO();

        orderPetVO.setOrderId(orderId);
        orderPetVO.setPetId(petId);
        dao.insert(orderPetVO);

        return orderPetVO;
    }

    // 更新一筆預約寵物記錄
    public OrderPetVO updateOrderPet(Integer opetId, Integer orderId, Integer petId) {
        OrderPetVO orderPetVO = new OrderPetVO();

        orderPetVO.setOpetId(opetId);
        orderPetVO.setOrderId(orderId);
        orderPetVO.setPetId(petId);
        dao.update(orderPetVO);

        return orderPetVO;
    }

    // 刪除一筆預約寵物記錄
    public void deleteOrderPet(Integer opetId) {
        dao.delete(opetId);
    }

    // 根據預約寵物編號查詢一筆記錄
    public OrderPetVO getOneOrderPet(Integer opetId) {
        return dao.findByPrimaryKey(opetId);
    }

    // 查詢所有預約寵物記錄
    public List<OrderPetVO> getAll() {
        return dao.getAll();
    }
}