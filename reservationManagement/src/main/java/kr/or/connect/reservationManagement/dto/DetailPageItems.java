package kr.or.connect.reservationManagement.dto;

public class DetailPageItems {

	private int displayInfoId;
	private int productId;
	private String productDescription;
	private String productContent;
	private String placeName;
	private String productImageUrl;

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
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	@Override
	public String toString() {
		return "DetailPageItemsDto [displayInfoId=" + displayInfoId + ", productId=" + productId
				+ ", productDescription=" + productDescription + ", productContent=" + productContent + ", placeName="
				+ placeName + ", productImageUrl=" + productImageUrl + "]";
	}
	
}
