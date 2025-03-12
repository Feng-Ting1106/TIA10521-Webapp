package com.pet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetJDBCDAO implements PetDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_db?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO pet (member_id,pet_type,pet_name,pet_gender,pet_weight,status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pet_id,member_id,pet_type,pet_name,pet_gender,pet_weight,status,create_time,last_update_time FROM pet order by pet_id";
	private static final String GET_ONE_STMT = 
		"SELECT pet_id,member_id,pet_type,pet_name,pet_gender,pet_weight,status,create_time,last_update_time FROM pet where pet_id = ?";
	private static final String DELETE = 
		"UPDATE pet set status=? where pet_id = ?";
	private static final String UPDATE = 
		"UPDATE pet set member_id=?,pet_type=?,pet_name=?,pet_gender=?,pet_weight=?,status=? where pet_id = ?";
	private static final String GET_PET_FINDBYMEMBERID = 
			"SELECT pet_id,member_id,pet_type,pet_name,pet_gender,pet_weight,status,create_time,last_update_time FROM pet where member_id = ?";
	
	public List<PetVO> findeByMemberId(Integer member_id) {
		PetVO petVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PetVO> petList = new ArrayList<PetVO>();
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PET_FINDBYMEMBERID);
			
			pstmt.setInt(1, member_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				petVO = new PetVO();
				
				petVO.setPet_id(rs.getInt("pet_id"));
				petVO.setMember_id(rs.getInt("member_id"));
				petVO.setPet_type(rs.getString("pet_type"));
				petVO.setPet_name(rs.getString("pet_name"));	
				petVO.setPet_gender(rs.getInt("pet_gender"));
				petVO.setPet_weight(rs.getInt("pet_weight"));
				petVO.setStatus(rs.getInt("status"));
				petVO.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
				petVO.setLast_update_time(rs.getTimestamp("last_update_time").toLocalDateTime());
				petList.add(petVO);
				
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
		return petList;
	}
	
	
	@Override
	public void insert(PetVO petVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, petVO.getMember_id());
			pstmt.setString(2, petVO.getPet_type());
			pstmt.setString(3, petVO.getPet_name());		
			pstmt.setInt(4, petVO.getPet_gender());
			pstmt.setInt(5, petVO.getPet_weight());
			pstmt.setInt(6, petVO.getStatus());
			
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
	public void update(PetVO petVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, petVO.getMember_id());
			pstmt.setString(2, petVO.getPet_type());
			pstmt.setString(3, petVO.getPet_name());		
			pstmt.setInt(4, petVO.getPet_gender());
			pstmt.setInt(5, petVO.getPet_weight());
			pstmt.setInt(6, petVO.getStatus());	
			pstmt.setInt(7, petVO.getPet_id());

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
	public void delete(PetVO petVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, petVO.getStatus());
			pstmt.setInt(2, petVO.getPet_id());

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
	public PetVO findByPrimaryKey(Integer pet_id) {

		PetVO petVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pet_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				petVO = new PetVO();
				petVO.setPet_id(rs.getInt("pet_id"));
				petVO.setMember_id(rs.getInt("member_id"));
				petVO.setPet_type(rs.getString("pet_type"));
				petVO.setPet_name(rs.getString("pet_name"));	
				petVO.setPet_gender(rs.getInt("pet_gender"));
				petVO.setPet_weight(rs.getInt("pet_weight"));
				petVO.setStatus(rs.getInt("status"));
				petVO.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
				petVO.setLast_update_time(rs.getTimestamp("last_update_time").toLocalDateTime());

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
		return petVO;
	}

	@Override
	public List<PetVO> getAll() {
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO petVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				petVO = new PetVO();
				petVO.setPet_id(rs.getInt("pet_id"));	
				petVO.setMember_id(rs.getInt("member_id"));
				petVO.setPet_type(rs.getString("pet_type"));
				petVO.setPet_name(rs.getString("pet_name"));	
				petVO.setPet_gender(rs.getInt("pet_gender"));
				petVO.setPet_weight(rs.getInt("pet_weight"));
				petVO.setStatus(rs.getInt("status"));
				petVO.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
				petVO.setLast_update_time(rs.getTimestamp("last_update_time").toLocalDateTime());
				list.add(petVO);
				
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

	public static void main(String[] args) {

		PetJDBCDAO dao = new PetJDBCDAO();

		// 新增
//		PetVO petVO1 = new PetVO();
//		petVO1.setMember_id(1);
//		petVO1.setPet_type("1");
//		petVO1.setPet_name("旺旺");	
//		petVO1.setPet_gender(1);
//		petVO1.setPet_weight(10);
//		petVO1.setStatus(1);
//		dao.insert(petVO1);

		
		// 修改
//		PetVO petVO2 = new PetVO();
//		petVO2.setPet_id(1);
//		petVO2.setMember_id(2);
//		petVO2.setPet_type("1");
//		petVO2.setPet_name("旺旺2");	
//		petVO2.setPet_gender(1);
//		petVO2.setPet_weight(10);
//		petVO2.setStatus(1);	
//		dao.update(petVO2);
		

		// 刪除
//		PetVO petVO4 = new PetVO();
//		petVO4.setStatus(0);
//		petVO4.setPet_id(1);
//		dao.delete(petVO4);

		// 查詢
//		PetVO petVO3 = dao.findByPrimaryKey(1);
//		System.out.print(petVO3.getPet_id() + ",");
//		System.out.print(petVO3.getMember_id() + ",");
//		System.out.print(petVO3.getPet_type() + ",");
//		System.out.print(petVO3.getPet_name() + ",");
//		System.out.print(petVO3.getPet_gender() + ",");
//		System.out.print(petVO3.getPet_weight() + ",");
//		System.out.print(petVO3.getStatus() + ",");
//		System.out.print(petVO3.getCreate_time() + ",");
//		System.out.println(petVO3.getLast_update_time());
//		System.out.println("---------------------");		


		// 查詢
		List<PetVO> list = dao.getAll();
		for (PetVO aPet : list) {
			System.out.print(aPet.getPet_id() + ",");
			System.out.print(aPet.getMember_id() + ",");
			System.out.print(aPet.getPet_type() + ",");
			System.out.print(aPet.getPet_name() + ",");
			System.out.print(aPet.getPet_gender() + ",");
			System.out.print(aPet.getPet_weight() + ",");
			System.out.print(aPet.getStatus() + ",");
			System.out.print(aPet.getCreate_time() + ",");
			System.out.print(aPet.getLast_update_time());
			System.out.println();

		}
	}
}