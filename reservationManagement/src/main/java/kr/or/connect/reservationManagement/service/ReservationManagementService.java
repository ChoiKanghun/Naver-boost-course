package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.DetailPageItems;
import kr.or.connect.reservationManagement.dto.Items;

public interface ReservationManagementService {

	int LIMIT = 4;
	int getAllCountOfProduct();	
	int getAllCountProductByCategoryId(Integer categoryId);
	List<Items> getAllProducts();
	List<Items> getLimitedProducts(Integer start);
	List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId);
	List<Items> getPromotionInfo();
	List<Items> getCategoriesInfoGroupByCategoryId();
	List<DetailPageItems> getDetailListItems(Integer id);
}
