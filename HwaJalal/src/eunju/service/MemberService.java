package eunju.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import eunju.domain.MemberVO;

public class MemberService {


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
	/*
	public boolean login(MemberVO memberVO) {
		connect();
		
		try {
			String sql = "select count(*) as cnt from member where memberloginid=? and memberpassword=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberVO.getMemberloginID());
			pstmt.setString(2,  memberVO.getMemberPassword());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getString("cnt").equals("1")) {
				
				return true;
			}else {
				return false;
			}

		}catch(Exception e) {
			disconnect();
			e.printStackTrace();
			return false;
		}
	}
	*/
	
	public MemberVO login(MemberVO memberVO) {
		connect();
		
		try {
			String sql = "select count(*) as cnt from member where memberloginid=? and memberpassword=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberVO.getMemberloginID());
			pstmt.setString(2,  memberVO.getMemberPassword());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getString("cnt").equals("1")) {
				String sql_2 = "select * from member where memberloginid=?";
				
				pstmt = conn.prepareStatement(sql_2);
				pstmt.setString(1, memberVO.getMemberloginID());
				ResultSet rs_s = pstmt.executeQuery();
				rs_s.next();
				java.util.Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(rs_s.getString("memberbirth"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				
				 
				memberVO.setMemberID(Integer.parseInt(rs_s.getString("memberid")));
				memberVO.setMemberPassword(rs_s.getString("memberpassword"));
				memberVO.setMemberloginID(rs_s.getString("memberloginid"));
				memberVO.setMemberName(rs_s.getString("membername"));
				memberVO.setMemberBirth(sqlDate);
				rs_s.close();
				rs.close();
				return memberVO;
			}else {
				rs.close();
				disconnect();
				return null;
			}

		}catch(Exception e) {
			disconnect();
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isAdmin(MemberVO memberVO) {
		connect();
		try {
		String sql = "select memberadmin from member where memberloginid=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, memberVO.getMemberloginID());
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		if(rs.getString("memberadmin").equals("1")) {
			disconnect();
			rs.close();
			return true;
		}else {
			disconnect();
			rs.close();
			return false;
		}

		}catch(Exception e) {
			disconnect();
			e.printStackTrace();
			return false;
		}
	
	}
	
	
}
