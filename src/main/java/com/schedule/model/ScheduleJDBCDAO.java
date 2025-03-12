package com.schedule.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScheduleJDBCDAO implements ScheduleDAO_interface {
//	========連接資料庫資訊========
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/pet_db?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
//	========連接資料庫資訊========

//	========SQL指令========
	public static final String INSERT_SCHEDULE = "insert into schedule(staff_id, timeslot, date) values (?, ?, ?)";
	public static final String GET_ONE = "select * from schedule where schedule_id = ?";
	public static final String UPDATA_SCHEDULE = "update schedule set timeslot = ?, date = ? where schedule_id = ?" ;
	public static final String GET_ALL = "select * from schedule order by schedule_id ";
	public static final String DELETE_SCHEDULE = "DELETE FROM schedule WHERE schedule_id = ?";

//	======================
	public void add(ScheduleVO schedule) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SCHEDULE);
//			System.out.println("連線成功");
			pstmt.setInt(1, schedule.getStaff_id());
			pstmt.setString(2, schedule.getTimeslot());
			pstmt.setDate(3, schedule.getDate());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void update(ScheduleVO schedule) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATA_SCHEDULE);
				pstmt.setString(1, schedule.getTimeslot());
				pstmt.setDate(2, schedule.getDate());
				pstmt.setInt(3, schedule.getSchedule_id());
				
				
				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
		}

	public void delete(Integer scheduleId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SCHEDULE);
//			System.out.println("連線成功");
			pstmt.setInt(1, scheduleId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public ScheduleVO findByPK(Integer scheduleId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScheduleVO schedule = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, scheduleId);
			rs = pstmt.executeQuery();
//			System.out.println("連線成功");
			while (rs.next()) {
//				查出來的資料存成變數
				Integer schedule_id = rs.getInt("schedule_id");
				Integer staff_id = rs.getInt("staff_id");
				String timeslot = rs.getString("timeslot");
				Date date = rs.getDate("date");
				Timestamp create_time = rs.getTimestamp("create_time");

				 schedule = new ScheduleVO(schedule_id, staff_id, timeslot, date, create_time);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return schedule;
	}

	public List<ScheduleVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ScheduleVO> scheduleList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
//			System.out.println("連線成功");
			while (rs.next()) {
//				把查詢出來的資料儲存成變數
				Integer schedule_id = rs.getInt("schedule_id");
				Integer staff_id = rs.getInt("staff_id");
				String timeslot = rs.getString("timeslot");
				Date date = rs.getDate("date");
				Timestamp create_time = rs.getTimestamp("create_time");
//				包裝查詢出來的資料
				ScheduleVO schedule = new ScheduleVO(schedule_id, staff_id, timeslot, date, create_time);
//				將包裝好的物件，用集合收集起來
				scheduleList.add(schedule);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return scheduleList;
	}

}
