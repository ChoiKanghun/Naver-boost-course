package kr.or.connect.reservationManagement.dto;

public class ReserveItemPrice {
	private int count;
	private int reservationInfoId;
	private int productPriceId;
	private int reservationInfoPriceId;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
	public int getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}
	public void setReservationInfoPriceId(int reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}
	@Override
	public String toString() {
		return "ReserveItemPrice [count=" + count + ", reservationInfoId=" + reservationInfoId + ", productPriceId="
				+ productPriceId + ", reservationInfoPriceId=" + reservationInfoPriceId + "]";
	}
	
	
}
