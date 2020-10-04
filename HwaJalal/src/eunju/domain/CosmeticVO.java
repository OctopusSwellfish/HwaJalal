package eunju.domain;

import java.sql.Date;

public class CosmeticVO {
	private int cosmeticID;
	private String cosmeticName;
	private String cosmeticBrand;
	private String cosmeticType;
	private String cosmeticShape;
	private Date cosmeticExpirationdate;
	private Date cosmeticOpendate;
	private Date cosmeticOpenExpirationdate;
	private Date cosmeticDate;
	
	public int getCosmeticID() {
		return cosmeticID;
	}
	public void setCosmeticID(int cosmeticID) {
		this.cosmeticID = cosmeticID;
	}
	public String getCosmeticName() {
		return cosmeticName;
	}
	public void setCosmeticName(String cosmeticName) {
		this.cosmeticName = cosmeticName;
	}
	public String getCosmeticBrand() {
		return cosmeticBrand;
	}
	public void setCosmeticBrand(String cosmeticBrand) {
		this.cosmeticBrand = cosmeticBrand;
	}
	public String getCosmeticType() {
		return cosmeticType;
	}
	public void setCosmeticType(String cosmeticType) {
		this.cosmeticType = cosmeticType;
	}
	public String getCosmeticShape() {
		return cosmeticShape;
	}
	public void setCosmeticShape(String cosmeticShape) {
		this.cosmeticShape = cosmeticShape;
	}
	public Date getCosmeticExpirationdate() {
		return cosmeticExpirationdate;
	}
	public void setCosmeticExpirationdate(Date cosmeticExpirationdate) {
		this.cosmeticExpirationdate = cosmeticExpirationdate;
	}
	public Date getCosmeticOpendate() {
		return cosmeticOpendate;
	}
	public void setCosmeticOpendate(Date cosmeticOpendate) {
		this.cosmeticOpendate = cosmeticOpendate;
	}
	public Date getCosmeticOpenExpirationdate() {
		return cosmeticOpenExpirationdate;
	}
	public void setCosmeticOpenExpirationdate(Date cosmeticOpenExpirationdate) {
		this.cosmeticOpenExpirationdate = cosmeticOpenExpirationdate;
	}
	public Date getCosmeticDate() {
		return cosmeticDate;
	}
	public void setCosmeticDate(Date cosmeticDate) {
		this.cosmeticDate = cosmeticDate;
	}
}
