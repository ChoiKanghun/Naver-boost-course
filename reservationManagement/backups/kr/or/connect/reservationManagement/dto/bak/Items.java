package kr.or.connect.reservationManagement.dto.bak;

public class Items {

	int displayInfoId;
	String productContent;
	int productId;
	String placeName;
	String productDescription;
	String productImageUrl;
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	
	@Override
	public String toString() {
		return "Products [displayInfoId=" + displayInfoId + ", productContent=" + productContent + ", productId="
				+ productId + ", placeName=" + placeName + ", productDescription=" + productDescription
				+ ", productImageUrl=" + productImageUrl + "]";
	}
	

}
