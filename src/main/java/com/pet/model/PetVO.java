package com.pet.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PetVO implements java.io.Serializable{
	
	@Override
	public String toString() {
		return "PetVO [pet_id=" + pet_id + ", member_id=" + member_id + ", pet_type=" + pet_type + ", pet_name="
				+ pet_name + ", pet_gender=" + pet_gender + ", pet_weight=" + pet_weight + ", status=" + status
				+ ", create_time=" + create_time + ", last_update_time=" + last_update_time + "]";
	}
	private Integer pet_id;
	private Integer member_id;
	private String pet_type;
	private String pet_name;
	private Integer pet_gender;
	private Integer pet_weight;
	private Integer status;
	private LocalDateTime create_time;
	private LocalDateTime last_update_time;
	
	public Integer getPet_id() {
		return pet_id;
	}
	public void setPet_id(Integer pet_id) {
		this.pet_id = pet_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getPet_type() {
		return pet_type;
	}
	public void setPet_type(String pet_type) {
		this.pet_type = pet_type;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public Integer getPet_gender() {
		return pet_gender;
	}
	public void setPet_gender(Integer pet_gender) {
		this.pet_gender = pet_gender;
	}
	public Integer getPet_weight() {
		return pet_weight;
	}
	public void setPet_weight(Integer pet_weight) {
		this.pet_weight = pet_weight;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
