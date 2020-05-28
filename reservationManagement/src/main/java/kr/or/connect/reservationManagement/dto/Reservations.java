package kr.or.connect.reservationManagement.dto;

public class Reservations {
	private DisplayInfo displayInfo;
	private boolean cancelFlag;
	private String createDate;
	private String modifyDate;
	private int displayInfoId;
	private int productId;
	private String reservationDate;
	private int reservationInfoId;
	private String reservationName;
	private String reservationEmail;
	private String reservationTelephone;
	private Long totalPrice;
	
	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}
	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public boolean isCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	@Override
	public String toString() {
		return "Reservations [displayInfo=" + displayInfo + ", cancelFlag=" + cancelFlag + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", displayInfoId=" + displayInfoId + ", productId=" + productId
				+ ", reservationDate=" + reservationDate + ", reservationInfoId=" + reservationInfoId
				+ ", reservationName=" + reservationName + ", reservationEmail=" + reservationEmail
				+ ", reservationTelephone=" + reservationTelephone + ", totalPrice=" + totalPrice + "]";
	}

	
}
