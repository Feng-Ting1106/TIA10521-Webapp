package com.schedule.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ScheduleVO implements Serializable {
	private Integer schedule_id;
	private Integer staff_id;
	private String timeslot;	
	private Date date;
	private Timestamp create_time;
	
	public ScheduleVO(Integer staff_id, String timeslot, Date date) {
		super();
		this.staff_id = staff_id;
		this.timeslot = timeslot;
		this.date = date;
	}
	public ScheduleVO() {
	}
	
	public ScheduleVO(Integer schedule_id, Integer staff_id, String timeslot, Date date, Timestamp create_time) {
		super();
		this.schedule_id = schedule_id;
		this.staff_id = staff_id;
		this.timeslot = timeslot;
		this.date = date;
		this.create_time = create_time;
	}

	public String toString() {
		return "Schedule [schedule_id=" + schedule_id + ", staff_id=" + staff_id + ", timeslot=" + timeslot + ", date="
				+ date + ", create_time=" + create_time + "]";
	}

	public Integer getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(Integer schedule_id) {
		this.schedule_id = schedule_id;
	}

	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
}

