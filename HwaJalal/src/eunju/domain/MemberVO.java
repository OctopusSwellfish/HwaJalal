package eunju.domain;

import java.sql.Date;


public class MemberVO {
	private int memberID;
	private String memberloginID;
	private String memberPassword;
	private String memberName;
	private Date memberBirth;
	public MemberVO() {
		
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getMemberloginID() {
		return memberloginID;
	}
	public void setMemberloginID(String memberloginID) {
		this.memberloginID = memberloginID;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public Date getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	


}
