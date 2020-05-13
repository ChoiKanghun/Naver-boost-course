package kr.or.connect.reservationManagement.dto;

import java.util.Date;

public class ReservationComments {
	private int productId;
	private String comment;
	private String score;
	private Date createDate = new Date();
	private String ProductImageUrl;
	private String reservationEmail;
	
	
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getProductImageUrl() {
		return ProductImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		ProductImageUrl = productImageUrl;
	}


	
}
