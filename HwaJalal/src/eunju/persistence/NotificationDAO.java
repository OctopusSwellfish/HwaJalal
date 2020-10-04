package eunju.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import eunju.domain.NotificationVO;

public class NotificationDAO {


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
	
	
	public NotificationVO add(NotificationVO nb) {
		connect();
		String sql = "insert into notification values(null,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, 	nb.getNotificationTitle());
			pstmt.setString(2,  nb.getNotificationContent());
			
			
			
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
			String sql_2 = "select * from notification order by notificationid desc limit 1";
			
			pstmt = conn.prepareStatement(sql_2);
			ResultSet rs_s = pstmt.executeQuery();
			rs_s.next();
			
			
			java.util.Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(rs_s.getString("notificationdate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			
			
			//nb.setNotificationContent(rs_s.getString("notificationcontent"));
			nb.setNotificationDate(sqlDate);
			nb.setNotificationID(Integer.parseInt(rs_s.getString("notificationid")));
			//nb.setNotificationTitle(rs_s.getString("notificationtitle"));
			rs_s.close();
			return nb;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			disconnect();
		}
	}
	
	public ArrayList<NotificationVO> getNotificationList() {
		connect();
		ArrayList<NotificationVO> notiList = new ArrayList<>();
		String sql = "select * from notification";
		String temp;
		java.util.Date date = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				NotificationVO nv = new NotificationVO();
				temp = rs.getString("notificationid");
				nv.setNotificationID(Integer.parseInt(temp));
				nv.setNotificationTitle(rs.getString("notificationtitle"));
				nv.setNotificationContent(rs.getString("notificationcontent"));
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("notificationdate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				
				
				nv.setNotificationDate(sqlDate);
				notiList.add(nv);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return notiList;
	}
}
