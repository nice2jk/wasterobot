package com.secret4news.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.secret4news.model.ContentVO;

public class ContentDAO {

	private static final String localJdbcUrl = "jdbc:mysql://163.44.169.108:3306/secretnews";
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/secretnews";
	private static final String userId = "secret";
	private static final String userPw = "shine2";
	
	private static ContentDAO contentDAO;
	
	public static ContentDAO getInstance() {
		if(contentDAO == null) {
			contentDAO = new ContentDAO();
		}
		
		return contentDAO;
	}
	
	public Connection getDBConnection() {
		Connection conn = null;

		// System.out.println("Connecting to database starting...");

		try {
			//System.out.println("Trying to load Driver...");

			Class.forName("com.mysql.jdbc.Driver");

			//System.out.println("Connecting to database...");
			
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	public void setContent(ContentVO contentVO) {
		// 1 = insert Content
		// 2 = update Content
		// 3 = Skip Content
		
		switch(isContent(contentVO)) {
			case 1:
				insertContent(contentVO);
				break;
			case 2:
				updateContent(contentVO);
				break;
			case 3:				
			default:
				break;
		}
	}
	
	public int isContent(ContentVO contentVO) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		String savedLink = null;
		
		String str = "SELECT cid, link FROM content WHERE cid = \'" + contentVO.getCid() + "\'";
		
		try {
			conn = getDBConnection();
			statement = conn.createStatement();
			
			rs = statement.executeQuery(str);
			
			while(rs.next()) {				
				/*System.out.println(rs.getString(2));*/
				savedLink = rs.getString(2);
			}
			
		} catch (SQLException e) {
			System.out.println("isContent executeQuery SQLException: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException logOrIgnore) {
					
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException logOrIgnore) {
					
				}
			}	
		}
		
		if(savedLink != null) {
			if(contentVO.getLink().equals(savedLink)) {
				return 3;
			}
			
			return 2;
		}
		
		return 1;
	}
	
	public void insertContent(ContentVO contentVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		long currentTime = System.currentTimeMillis();
		Timestamp ts = new Timestamp(currentTime);
		
		String sqlStr = "INSERT INTO content (cid, cpname, title, link, category, grade, ctime) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getDBConnection();
			
			pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, contentVO.getCid());
			pstmt.setString(2, contentVO.getCpName());
			pstmt.setString(3, contentVO.getTitle());
			pstmt.setString(4, contentVO.getLink());
			pstmt.setString(5, contentVO.getCategory());
			pstmt.setInt(6, contentVO.getStatus());
			pstmt.setTimestamp(7, ts);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void updateContent(ContentVO contentVO) {
		Connection conn = null;
		Statement statement = null;

		String sqlStr = "UPDATE content SET link = \'"
				+ contentVO.getLink() + "\' WHERE cid = \'" + contentVO.getCid()
				+ "\'";

		try {
			conn = getDBConnection();

			statement = conn.createStatement();
			statement.execute(sqlStr);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
