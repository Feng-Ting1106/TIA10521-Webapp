package com.staff.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StaffVO implements Serializable {
	
	private Integer staff_id;
	private Integer apply_id;
	private String email;
	private String password;
	private String name;
	private String phone;
	private Integer gender;
	private String plate_number;
	private String introduction;
	private String captcha_code;
	private Integer status;
	private Timestamp create_time;
	private Timestamp last_update_time;
	
	public StaffVO() {
		super();
	}

	public StaffVO(Integer staff_id, Integer apply_id, String email, String password, String name, String phone,
			Integer gender, String plate_number, String introduction, String captcha_code,
			Integer status, Timestamp create_time, Timestamp last_update_time) {
		super();
		this.staff_id = staff_id;
		this.apply_id = apply_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.plate_number = plate_number;
		this.introduction = introduction;
		this.captcha_code = captcha_code;
		this.status = status;
		this.create_time = create_time;
		this.last_update_time = last_update_time;
	}

	public StaffVO(Integer apply_id, String email, String password, String name, String phone, Integer gender,
			String plate_number, String introduction) {
		super();
		this.apply_id = apply_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.plate_number = plate_number;
		this.introduction = introduction;
	}

	public String toString() {
		return "Staff [staff_id=" + staff_id + ", apply_id=" + apply_id + ", email=" + email + ", password=" + password
				+ ", name=" + name + ", phone=" + phone + ", gender=" + gender + ", plate_number=" + plate_number
				+ ", introduction=" + introduction +", captcha_code=" + captcha_code
				+ ", status=" + status + ", create_time=" + create_time + ", last_update_time=" + last_update_time + "]";
	}
	
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	public Integer getApply_id() {
		return apply_id;
	}
	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPlate_number() {
		return plate_number;
	}
	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getCaptcha_code() {
		return captcha_code;
	}
	public void setCaptcha_code(String captcha_code) {
		this.captcha_code = captcha_code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(Timestamp last_update_tim) {
		this.last_update_time = last_update_tim;
	}

}

