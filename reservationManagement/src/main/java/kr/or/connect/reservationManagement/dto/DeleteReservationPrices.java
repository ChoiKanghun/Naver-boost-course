package kr.or.connect.reservationManagement.dto;

public class DeleteReservationPrices {
	private int reservationInfoId;
	private int reservationInfoPriceId;
	private int productPriceId;
	private int count;
	
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}
	public void setReservationInfoPriceId(int reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}
	public int getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "DeleteResultPrices [reservationInfoId=" + reservationInfoId + ", reservationInfoPriceId="
				+ reservationInfoPriceId + ", productPriceId=" + productPriceId + ", count=" + count + "]";
	}
	
	
}
