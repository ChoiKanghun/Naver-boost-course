package kr.or.connect.reservationManagement.dto;

import java.util.Date;

public class EnrollComment {
	private String comment;
	private int commentId;
	private Date createDate;
	private Date modifyDate;
	private int productId;
	private int reservationInfoId;
	private float score;
	private EnrollCommentImage enrollCommentImage;
	
	public EnrollCommentImage getEnrollCommentImage() {
		return enrollCommentImage;
	}
	public void setEnrollCommentImage(EnrollCommentImage enrollCommentImage) {
		this.enrollCommentImage = enrollCommentImage;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "EnrollComment [comment=" + comment + ", commentId=" + commentId + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", productId=" + productId + ", reservationInfoId=" + reservationInfoId
				+ ", score=" + score + ", enrollCommentImage=" + enrollCommentImage + "]";
	}
}
