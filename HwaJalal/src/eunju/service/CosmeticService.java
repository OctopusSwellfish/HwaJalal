package eunju.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.ResultSet;

import eunju.domain.CosmeticVO;

public class CosmeticService {


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
	
	public ArrayList<CosmeticVO> getMyCosmeList(int memberID) {
		connect();
		ArrayList<CosmeticVO> cosList = new ArrayList<>();
		String sql = "select c.* from cosmetic c, member m, registercosmetic r where m.memberid=? and m.memberid=r.memberid and r.cosmeticid=c.cosmeticid";
		String temp;
		java.util.Date date = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberID);
			
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
