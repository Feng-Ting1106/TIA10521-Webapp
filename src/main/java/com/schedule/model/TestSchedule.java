
package com.schedule.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TestSchedule {
	public static void main(String[] args) {
		ScheduleJDBCDAO scheduleTest = new ScheduleJDBCDAO();
//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateU = null;
//
		String dateStr = "2024-01-01"; // 輸入要測的加入日期
//
//		文字轉java.date
		try {
			dateU = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		java.date轉sql.date
		Date date = new Date(dateU.getTime());
//
//		ScheduleService ss = new ScheduleService();
//		
//
//		ss.addSchedule(1, "000000000011100000000000", date);
//		// 查詢可被預約員工測試
//		StaffService ss = new StaffService();
//		StaffJDBCDAO s2 = new StaffJDBCDAO();
//		List<Map<String, Object>>  staffMap1 = new ArrayList<>(s2.getWorkableStaff(12, 3, date));
////		List<Map<String, Object>> staffList = new ArrayList<>(ss.getWorkableStaff(12, "2024/1/1"));
//		for (Map<String, Object> a : staffMap1) {
//			Set<Map.Entry<String, Object>> entrySet = a.entrySet();
//			for (Map.Entry<String, Object> entry : entrySet) {
//				System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//			}
//
//		}
//		借放一下測試更改班表時間的code
		System.out.println( setSch(12,15) );
	}

	public static String setSch(int startTime, int endTime) {

		StringBuilder sb = new StringBuilder("000000000000000000000000");
		for (int i = 0; i < sb.length(); i++) {
			if (i == startTime) {
				while (i <= endTime) {
					sb.setCharAt(i, '1');
					i++;
				}
			}
		}
		String timeslot = sb.toString();
		return timeslot;
	}
}
