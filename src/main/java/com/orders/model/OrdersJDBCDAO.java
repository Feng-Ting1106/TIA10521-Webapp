package com.orders.model;

import java.util.*;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class OrdersJDBCDAO implements OrdersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_db?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO orders (member_id,staff_id,schedule_id,geton_location,getoff_location,redeem_point,payment,pay_method,notes,status,star,rating,picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT order_id,member_id,staff_id,schedule_id,geton_location,getoff_location,redeem_point,payment,pay_method,notes,status,star,rating,picture,create_time FROM orders order by order_id";
	private static final String GET_ONE_STMT = 
		"SELECT order_id,member_id,staff_id,schedule_id,geton_location,getoff_location,redeem_point,payment,pay_method,notes,status,star,rating,picture,create_time FROM orders where order_id = ?";
	private static final String DELETE = 
	    "UPDATE orders set status=? where order_id = ?";
	private static final String UPDATE = 
		"UPDATE orders set member_id=?,staff_id=?,schedule_id=?,geton_location=?,getoff_location=?,redeem_point=?,payment=?,pay_method=?,notes=?,status=?,star=?,rating=?,picture=? where order_id = ?";
	private static final String GET_ORDERLIST_BY_MEMBERID =
		"SELECT * from orders where member_id = ?";
	
	
	@Override
	public void insert(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ordersVO.getMember_id());
			pstmt.setInt(2, ordersVO.getStaff_id());
			pstmt.setInt(3, ordersVO.getSchedule_id());
			pstmt.setString(4, ordersVO.getGeton_location());
			pstmt.setString(5, ordersVO.getGetoff_location());
			pstmt.setInt(6, ordersVO.getRedeem_point());
			pstmt.setInt(7, ordersVO.getPayment());
			pstmt.setInt(8, ordersVO.getPay_method());
			pstmt.setString(9, ordersVO.getNotes());
			pstmt.setInt(10, ordersVO.getStatus());
			pstmt.setInt(11, ordersVO.getStar());
			pstmt.setString(12, ordersVO.getRating());
			pstmt.setBytes(13, ordersVO.getPicture());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ordersVO.getMember_id());
			pstmt.setInt(2, ordersVO.getStaff_id());
			pstmt.setInt(3, ordersVO.getSchedule_id());
			pstmt.setString(4, ordersVO.getGeton_location());
			pstmt.setString(5, ordersVO.getGetoff_location());
			pstmt.setInt(6, ordersVO.getRedeem_point());
			pstmt.setInt(7, ordersVO.getPayment());
			pstmt.setInt(8, ordersVO.getPay_method());
			pstmt.setString(9, ordersVO.getNotes());
			pstmt.setInt(10, ordersVO.getStatus());
			pstmt.setInt(11, ordersVO.getStar());
			pstmt.setString(12, ordersVO.getRating());
			pstmt.setBytes(13, ordersVO.getPicture());
			pstmt.setInt(14, ordersVO.getOrder_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ordersVO.getStatus());
			pstmt.setInt(2, ordersVO.getOrder_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public OrdersVO findByPrimaryKey(Integer order_id) {

		OrdersVO ordersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				ordersVO = new OrdersVO();
				ordersVO.setOrder_id(rs.getInt("order_id"));
				ordersVO.setMember_id(rs.getInt("member_id"));
				ordersVO.setStaff_id(rs.getInt("staff_id"));
				ordersVO.setSchedule_id(rs.getInt("schedule_id"));
				ordersVO.setGeton_location(rs.getString("geton_location"));
				ordersVO.setGetoff_location(rs.getString("getoff_location"));
				ordersVO.setRedeem_point(rs.getInt("redeem_point"));
				ordersVO.setPayment(rs.getInt("payment"));
				ordersVO.setPay_method(rs.getInt("pay_method"));
				ordersVO.setNotes(rs.getString("notes"));
				ordersVO.setStatus(rs.getInt("status"));
				ordersVO.setStar(rs.getInt("star"));
				ordersVO.setRating(rs.getString("rating"));
				ordersVO.setPicture(rs.getBytes("picture"));
				ordersVO.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return ordersVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				ordersVO = new OrdersVO();
				ordersVO.setOrder_id(rs.getInt("order_id"));
				ordersVO.setMember_id(rs.getInt("member_id"));
				ordersVO.setStaff_id(rs.getInt("staff_id"));
				ordersVO.setSchedule_id(rs.getInt("schedule_id"));
				ordersVO.setGeton_location(rs.getString("geton_location"));
				ordersVO.setGetoff_location(rs.getString("getoff_location"));
				ordersVO.setRedeem_point(rs.getInt("redeem_point"));
				ordersVO.setPayment(rs.getInt("payment"));
				ordersVO.setPay_method(rs.getInt("pay_method"));
				ordersVO.setNotes(rs.getString("notes"));
				ordersVO.setStatus(rs.getInt("status"));
				ordersVO.setStar(rs.getInt("star"));
				ordersVO.setRating(rs.getString("rating"));
				ordersVO.setPicture(rs.getBytes("picture"));
				ordersVO.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
				ordersVO.setLast_update_time(rs.getTimestamp("create_time").toLocalDateTime());
				list.add(ordersVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<OrdersVO> getOrderlist(Integer member_id) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ORDERLIST_BY_MEMBERID);
			pstmt.setInt(1,member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrder_id(rs.getInt("order_id"));
				ordersVO.setMember_id(rs.getInt("member_id"));
				ordersVO.setStaff_id(rs.getInt("staff_id"));
				ordersVO.setSchedule_id(rs.getInt("schedule_id"));
				ordersVO.setGeton_location(rs.getString("geton_location"));
				ordersVO.setGetoff_location(rs.getString("getoff_location"));
				ordersVO.setRedeem_point(rs.getInt("redeem_point"));
				ordersVO.setPayment(rs.getInt("payment"));
				ordersVO.setPay_method(rs.getInt("pay_method"));
				ordersVO.setNotes(rs.getString("notes"));
				ordersVO.setStatus(rs.getInt("status"));
				ordersVO.setStar(rs.getInt("star"));
				ordersVO.setRating(rs.getString("rating"));
				ordersVO.setPicture(rs.getBytes("picture"));
				ordersVO.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
				ordersVO.setLast_update_time(rs.getTimestamp("create_time").toLocalDateTime());
				list.add(ordersVO); // Store the row in the list
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return list;
	}

	public static void main(String[] args) {

		OrdersJDBCDAO dao = new OrdersJDBCDAO();

		// 新增
//		OrdersVO ordersVO1 = new OrdersVO();
//		ordersVO1.setMember_id(1);
//		ordersVO1.setStaff_id(1);
//		ordersVO1.setSchedule_id(1);
//		ordersVO1.setGeton_location("台北市");
//		ordersVO1.setGetoff_location("新北市");
//		ordersVO1.setRedeem_point(0);
//		ordersVO1.setPayment(100);
//		ordersVO1.setPay_method(0);
//		ordersVO1.setNotes("狗狗很會掉毛");
//		ordersVO1.setStatus(1);
//		ordersVO1.setStar(5);
//		ordersVO1.setRating("服務很好");
//		ordersVO1.setPicture(null);
//		dao.insert(ordersVO1);

		// 修改
//		OrdersVO ordersVO2 = new OrdersVO();
//		ordersVO2.setMember_id(1);
//		ordersVO2.setStaff_id(1);
//		ordersVO2.setSchedule_id(1);
//		ordersVO2.setGeton_location("新北市");
//		ordersVO2.setGetoff_location("台北市");
//		ordersVO2.setRedeem_point(100);
//		ordersVO2.setPayment(0);
//		ordersVO2.setPay_method(0);
//		ordersVO2.setNotes("貓咪很會掉毛");
//		ordersVO2.setStatus(1);
//		ordersVO2.setStar(5);
//		ordersVO2.setRating("服務很好");
//		ordersVO2.setPicture(null);
//		ordersVO2.setOrder_id(1);
//		dao.update(ordersVO2);

		// 刪除
//		OrdersVO ordersVO4 = new OrdersVO();
//		ordersVO4.setOrder_id(1);
//		ordersVO4.setStatus(0);
//		dao.delete(ordersVO4);

		// 查詢
//		OrdersVO ordersVO3 = dao.findByPrimaryKey(1);
//		System.out.print(ordersVO3.getOrder_id() + ",");
//		System.out.print(ordersVO3.getMember_id() + ",");
//		System.out.print(ordersVO3.getStaff_id() + ",");
//		System.out.print(ordersVO3.getSchedule_id() + ",");
//		System.out.print(ordersVO3.getGeton_location() + ",");
//		System.out.print(ordersVO3.getGetoff_location() + ",");
//		System.out.print(ordersVO3.getRedeem_point() + ",");
//		System.out.print(ordersVO3.getPayment() + ",");
//		System.out.print(ordersVO3.getPay_method() + ",");
//		System.out.print(ordersVO3.getNotes() + ",");
//		System.out.print(ordersVO3.getStatus() + ",");
//		System.out.print(ordersVO3.getStar() + ",");
//		System.out.print(ordersVO3.getRating() + ",");
//		System.out.print(ordersVO3.getPicture() + ",");
//		System.out.print(ordersVO3.getCreate_time() + ",");
//		System.out.println(ordersVO3.getLast_update_time());
//		System.out.println("---------------------");

		// 查詢
//		List<OrdersVO> list = dao.getAll();
//		for (OrdersVO aOrders : list) {
//			System.out.print(aOrders.getOrder_id() + ",");
//			System.out.print(aOrders.getMember_id() + ",");
//			System.out.print(aOrders.getStaff_id() + ",");
//			System.out.print(aOrders.getSchedule_id() + ",");
//			System.out.print(aOrders.getGeton_location() + ",");
//			System.out.print(aOrders.getGetoff_location() + ",");
//			System.out.print(aOrders.getRedeem_point() + ",");
//			System.out.print(aOrders.getPayment() + ",");
//			System.out.print(aOrders.getPay_method() + ",");
//			System.out.print(aOrders.getNotes() + ",");
//			System.out.print(aOrders.getStatus() + ",");
//			System.out.print(aOrders.getStar() + ",");
//			System.out.print(aOrders.getRating() + ",");
//			System.out.print(aOrders.getPicture() + ",");
//			System.out.print(aOrders.getCreate_time() + ",");
//			System.out.print(aOrders.getLast_update_time());		
//			System.out.println();
//
//		}
		
		List<OrdersVO> list = dao.getOrderlist(1);
		for (OrdersVO aOrders : list) {
			System.out.print(aOrders.getOrder_id() + ",");
			System.out.print(aOrders.getMember_id() + ",");
			System.out.print(aOrders.getStaff_id() + ",");
			System.out.print(aOrders.getSchedule_id() + ",");
			System.out.print(aOrders.getGeton_location() + ",");
			System.out.print(aOrders.getGetoff_location() + ",");
			System.out.print(aOrders.getRedeem_point() + ",");
			System.out.print(aOrders.getPayment() + ",");
			System.out.print(aOrders.getPay_method() + ",");
			System.out.print(aOrders.getNotes() + ",");
			System.out.print(aOrders.getStatus() + ",");
			System.out.print(aOrders.getStar() + ",");
			System.out.print(aOrders.getRating() + ",");
			System.out.print(aOrders.getPicture() + ",");
			System.out.print(aOrders.getCreate_time() + ",");
			System.out.print(aOrders.getLast_update_time());		
			System.out.println();

		}
	}


}