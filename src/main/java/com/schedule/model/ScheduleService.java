package com.schedule.model;

import java.sql.Date;
import java.util.List;


public class ScheduleService {
	private ScheduleDAO_interface dao;

	public ScheduleService() {
		dao = new ScheduleJDBCDAO();
	}

	public void addSchedule(Integer staff_id, String timeslot, java.sql.Date date) {

		ScheduleVO schedule = new ScheduleVO();
		schedule.setStaff_id(staff_id);
		schedule.setTimeslot(timeslot);
		schedule.setDate(date);
		
		dao.add(schedule);
	}

	public ScheduleVO updateSchedule( String timeslot, Date date, Integer schedule_id) {

		ScheduleVO schedule = new ScheduleVO();
		schedule.setTimeslot(timeslot);
		schedule.setDate(date);
		schedule.setSchedule_id(schedule_id);

		dao.update(schedule);
		return schedule;

	}
//
	public void deleteSchedule(Integer schedule_id) {
		dao.delete(schedule_id);
	}
//
	public ScheduleVO getOneSchedule(Integer schedule_id) {
		return dao.findByPK(schedule_id);
	}
//
	public List<ScheduleVO> getAll() {
		return dao.getAll();
	}
}
