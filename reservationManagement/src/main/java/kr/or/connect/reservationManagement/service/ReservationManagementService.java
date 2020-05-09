package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.Items;

public interface ReservationManagementService {

	Integer LIMIT = 4;
	public int getAllCountProduct();	
	public int getAllCountProductByCategoryId(Integer categoryId);
	public List<Items> getAllProducts();
	public List<Items> getLimitedProducts(Integer start);
	public List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId);
	public List<Items> getPromotionInfo();
	public List<Items> getCategories();
}
