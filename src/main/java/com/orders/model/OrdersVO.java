package com.orders.model;
import java.sql.Date;
import java.time.*;

public class OrdersVO implements java.io.Serializable{
	
	private Integer order_id;
	private Integer member_id;
	private Integer staff_id;
	private Integer schedule_id;
	private String geton_location;
	private String getoff_location;
	private Integer redeem_point;
	private Integer payment;
	private Integer pay_method;
	private String notes;
	private Integer status;
	private Integer star;
	private String rating;
	private byte[] picture;
	private LocalDateTime create_time;
	private LocalDateTime last_update_time;
	
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	public Integer getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Integer schedule_id) {
		this.schedule_id = schedule_id;
	}
	public String getGeton_location() {
		return geton_location;
	}
	public void setGeton_location(String geton_location) {
		this.geton_location = geton_location;
	}
	public String getGetoff_location() {
		return getoff_location;
	}
	public void setGetoff_location(String getoff_location) {
		this.getoff_location = getoff_location;
	}
	public Integer getRedeem_point() {
		return redeem_point;
	}
	public void setRedeem_point(Integer redeem_point) {
		this.redeem_point = redeem_point;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Integer getPay_method() {
		return pay_method;
	}
	public void setPay_method(Integer pay_method) {
		this.pay_method = pay_method;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public LocalDateTime getCreate_time() {
		return create_time;
	}
	public void setCreate_time(LocalDateTime create_time) {
		this.create_time = create_time;
	}
	public LocalDateTime getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(LocalDateTime last_update_time) {
		this.last_update_time = last_update_time;
	}
	
}
