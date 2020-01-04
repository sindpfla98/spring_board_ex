package com.javalec.ex;

import java.sql.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLConnectionTest {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static final String DRIVER = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/board?useSSL=false";
	static final String USERNAME = "root";
	static final String PASSWORD = "217230114";

	@Test
	public void getMySQLConnectionTest() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			logger.info("MySQLConnection START");
			
			Class.forName(DRIVER);
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			
			String sql = "SELECT BOARD_SUBJECT, BOARD_CONTENT, BOARD_WRITER FROM tb_board";

			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String sub = rs.getString("BOARD_SUBJECT");
				String cont = rs.getString("BOARD_CONTENT");
				String writer = rs.getString("BOARD_WRITER");
				
				logger.info("게시판 제목: {}", sub);
				logger.info("게시판 내용: {}", cont);
				logger.info("게시판 작성자: {}", writer);
				logger.info("================================");
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		logger.info("MySQLConnection END");
	}
}

