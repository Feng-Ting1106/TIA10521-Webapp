package com.schedule.model;

import java.util.List;

public interface ScheduleDAO_interface {
		public void add (ScheduleVO schedule);
		public void update (ScheduleVO schedule);
		public void delete (Integer scheduleId);
		
//		單筆查詢
		public ScheduleVO findByPK(Integer scheduleId);
//		多筆查詢
		public List<ScheduleVO> getAll();
}
