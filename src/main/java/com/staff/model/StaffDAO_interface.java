package com.staff.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface StaffDAO_interface {
	public void add (StaffVO staff);
	public void update (StaffVO staff);
	public void delete (Integer staffId);
	
//	單筆查詢
	public StaffVO findByPK(Integer staffId);

//	多筆查詢
	public List<StaffVO> getAll();
	
	
	public List<Map<String, Object>> getWorkableStaff(int startTime, int total , Date date);
}
