package eunju.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import eunju.domain.CosmeticVO;

public class CosmeticDAO {


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
	
	public CosmeticVO add(CosmeticVO cv) {
		connect();
		String sql = "insert into cosmetic values(null,?,?,?,?,?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1,  cv.getCosmeticName());
			pstmt.setString(2,  cv.getCosmeticBrand());
			pstmt.setString(3,  cv.getCosmeticType());
			pstmt.setString(4, cv.getCosmeticShape());
			pstmt.setDate(5, cv.getCosmeticExpirationdate());
			pstmt.setDate(6,  cv.getCosmeticOpendate());
			pstmt.setDate(7,  cv.getCosmeticOpenExpirationdate());
			
			
			System.out.println(pstmt);
			pstmt.executeUpdate();
			
			String sql_2 = "select * from cosmetic order by cosmeticid desc limit 1";
			
			pstmt = conn.prepareStatement(sql_2);
			ResultSet rs_s = pstmt.executeQuery();
			rs_s.next();
			
			java.util.Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(rs_s.getString("cosmeticdate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			
			cv.setCosmeticDate(sqlDate);
			cv.setCosmeticID(Integer.parseInt(rs_s.getString("cosmeticid")));
			
			rs_s.close();
			return cv;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			disconnect();
		}
	}
	
	public ArrayList<CosmeticVO> getCosmeticList() {
		connect();
		ArrayList<CosmeticVO> cosList = new ArrayList<>();
		String sql = "select * from cosmetic";
		String temp;
		java.util.Date date = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CosmeticVO cv = new CosmeticVO();
				temp = rs.getString("cosmeticid");
				
				cv.setCosmeticID(Integer.parseInt(temp));
				cv.setCosmeticName(rs.getString("cosmeticname"));
				cv.setCosmeticBrand(rs.getString("cosmeticbrand"));
				cv.setCosmeticType(rs.getString("cosmetictype"));
				cv.setCosmeticShape(rs.getString("cosmeticshape"));
				
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("cosmeticexpirationdate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				
				cv.setCosmeticExpirationdate(sqlDate);
				
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("cosmeticopendate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sqlDate = new java.sql.Date(date.getTime());
				
				cv.setCosmeticOpendate(sqlDate);
				
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("cosmeticopenexpirationdate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sqlDate = new java.sql.Date(date.getTime());
				
				cv.setCosmeticOpenExpirationdate(sqlDate);
				
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("cosmeticdate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sqlDate = new java.sql.Date(date.getTime());
				
				cv.setCosmeticDate(sqlDate);
				
				cosList.add(cv);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			disconnect();
		}
		return cosList;
	}
}
