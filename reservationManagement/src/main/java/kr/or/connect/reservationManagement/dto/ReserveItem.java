package kr.or.connect.reservationManagement.dto;

import java.util.Date;
import java.util.List;

public class ReserveItem {
	private int displayInfoId;
	private int productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTel;
	private String reservationDate;
	private boolean cancelFlag;
	private Date createDate;
	private Date modifyDate;
	private int reservationInfoId;
	private List<ReserveItemPrice> reserveItemPrices;
	
	public List<ReserveItemPrice> getReserveItemPrices() {
		return reserveItemPrices;
	}
	public void setReserveItemPrices(List<ReserveItemPrice> reserveItemPrices) {
		this.reserveItemPrices = reserveItemPrices;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTelephone) {
		this.reservationTel = reservationTelephone;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public boolean isCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	@Override
	public String toString() {
		return "ReserveItem [displayInfoId=" + displayInfoId + ", productId=" + productId + ", reservationEmail="
				+ reservationEmail + ", reservationName=" + reservationName + ", reservationTel=" + reservationTel
				+ ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", reservationInfoId=" + reservationInfoId + ", reserveItemPrices="
				+ reserveItemPrices + "]";
	}



	
}
