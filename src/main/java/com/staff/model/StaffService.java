package com.staff.model;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffService {
	private StaffDAO_interface dao;

	public StaffService() {
		dao = new StaffJDBCDAO();
	}

	public void addStaff(Integer apply_id, String email, String password, String name, String phone, Integer gender,
			String plate_number, String introduction) {

		StaffVO staff = new StaffVO();
		staff.setApply_id(apply_id);
		staff.setEmail(email);
		staff.setPassword(password);
		staff.setName(name);
		staff.setPhone(phone);
		staff.setGender(gender);
		staff.setPlate_number(plate_number);
		staff.setIntroduction(introduction);

		dao.add(staff);
	}

	public StaffVO updateStaff(String email, String password, String name, String phone, String plate_number,
			String introduction, Integer staff_id) {

		StaffVO staff = new StaffVO();
		staff.setEmail(email);
		staff.setPassword(password);
		staff.setName(name);
		staff.setPhone(phone);
		staff.setPlate_number(plate_number);
		staff.setIntroduction(introduction);
		staff.setStaff_id(staff_id);
		
		dao.update(staff);
		return staff;

	}

//
	public void deleteStaff(Integer staff_id) {
		dao.delete(staff_id);
	}

//
	public StaffVO getOneStaff(Integer staff_id) {
		return dao.findByPK(staff_id);
	}

//
	public List<StaffVO> getAll() {
		return dao.getAll();
	}
	public  List<Map<String, Object>> getWorkableStaff(int time , Date  date ) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		try {
//			date = sdf.parse(dateStr);
//			System.out.println("8");
//		} catch (Exception e) {
//			System.out.println("9");
//
//		}
		 java.sql.Date sqlDate = new  java.sql.Date(date.getTime());
		 
//		 StringBuilder sb = new StringBuilder("000000000000000000000000");
//		 for(int i = 0; i <= sb.length()-3; i++) {
//			 if(i == time) {
//				 sb.setCharAt(i, '1');
//				 sb.setCharAt(i+1, '1');
//				 sb.setCharAt(i+2, '1');
//			 }
//		 }
//		 String timeslot = sb.toString();
		 System.out.println("準備連接資料庫");
		 List<Map<String, Object>> staffList =  new ArrayList<>( dao.getWorkableStaff(time, 3 , sqlDate ));
		 System.out.println("連接資料庫查詢完畢");
//		 Map<String, Object> staffMap = new HashMap();
//		 if( staff.is)
//		 staffMap.put("id", staff.getStaff_id());
//		 staffMap.put("name", staff.getName());
//		 staffMap.put("gender", staff.getGender());
//		 staffMap.put("introduction", staff.getIntroduction());
		return staffList;
	}
	
}
