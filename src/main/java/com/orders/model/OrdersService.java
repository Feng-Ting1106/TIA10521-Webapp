package com.orders.model;

import java.util.List;

public class OrdersService {

	private OrdersDAO_interface dao;

	public OrdersService() {
//		dao = new OrdersDAO();
		dao = new OrdersJDBCDAO(); //不想用連線池就這樣
	}

	public OrdersVO addOrders(
			Integer member_id, Integer staff_id, Integer schedule_id, String geton_location,
			String getoff_location, Integer redeem_point, Integer payment, Integer pay_method,
			String notes, Integer status, Integer star, String rating,
			byte[] picture) {

		OrdersVO ordersVO = new OrdersVO();

		ordersVO.setMember_id(member_id);
		ordersVO.setStaff_id(staff_id);
		ordersVO.setSchedule_id(schedule_id);
		ordersVO.setGeton_location(geton_location);
		ordersVO.setGetoff_location(getoff_location);
		ordersVO.setRedeem_point(redeem_point);
		ordersVO.setPayment(payment);
		ordersVO.setPay_method(pay_method);
		ordersVO.setNotes(notes);
		ordersVO.setStatus(status);
		ordersVO.setStar(star);
		ordersVO.setRating(rating);
		ordersVO.setPicture(picture);
		dao.insert(ordersVO);

		return ordersVO;
	}

	public OrdersVO updateOrders(
			Integer member_id, Integer staff_id, Integer schedule_id, String geton_location,
			String getoff_location, Integer redeem_point, Integer payment, Integer pay_method,
			String notes, Integer status, Integer star, String rating,
			byte[] picture, Integer order_id )  {

		OrdersVO ordersVO = new OrdersVO();
		
		ordersVO.setMember_id(member_id);
		ordersVO.setStaff_id(staff_id);
		ordersVO.setSchedule_id(schedule_id);
		ordersVO.setGeton_location(geton_location);
		ordersVO.setGetoff_location(getoff_location);
		ordersVO.setRedeem_point(redeem_point);
		ordersVO.setPayment(payment);
		ordersVO.setPay_method(pay_method);
		ordersVO.setNotes(notes);
		ordersVO.setStatus(status);
		ordersVO.setStar(star);
		ordersVO.setRating(rating);
		ordersVO.setPicture(picture);
		ordersVO.setOrder_id(order_id);
		dao.update(ordersVO);

		return ordersVO;
	}

	public OrdersVO deleteOrders(Integer order_id) {
		
		OrdersVO ordersVO = new OrdersVO();
		
		ordersVO.setStatus(0);
		ordersVO.setOrder_id(order_id);
		dao.delete(ordersVO);
		
		return ordersVO;
	}

	public OrdersVO getOneOrders(Integer order_id) {
		return dao.findByPrimaryKey(order_id);
	}

	public List<OrdersVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrdersVO> getOrderlist(Integer member_id) {
		return dao.getOrderlist(member_id);
	} 
}
