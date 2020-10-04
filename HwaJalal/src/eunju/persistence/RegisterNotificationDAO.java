package eunju.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eunju.domain.RegisterNotificationVO;

public class RegisterNotificationDAO {


	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?useSSL=false&serverTimezone=UTC";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook","passwd");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean add(RegisterNotificationVO rv) {
		connect();
		String sql = "insert into registernotification values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1,  rv.getMemberID());
			pstmt.setInt(2,  rv.getNotificationID());
			
			
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;
	}
	
}
