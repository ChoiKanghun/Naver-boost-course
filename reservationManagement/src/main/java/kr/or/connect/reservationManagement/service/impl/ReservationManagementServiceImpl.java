package kr.or.connect.reservationManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.dao.ItemsDao;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Service
public class ReservationManagementServiceImpl implements ReservationManagementService {

	@Autowired
	ItemsDao productsDao;
	
	@Override
	public List<Items> getAllProducts(){
		List<Items> list = productsDao.selectAll();
		return list;
	}
	
	@Override
	public List<Items> getLimitedProducts(Integer start){
		List<Items> list = productsDao.selectLimit(start, ReservationManagementService.LIMIT);
		
		return list;
	}

	@Override
	public List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId){
		List<Items> list = productsDao.selectLimitByCategoryId(start, ReservationManagementService.LIMIT, categoryId);
		
		return list;
	}
	
	@Override
	public List<Items> getCategories(){
		return (productsDao.categories());
	}

	@Override
	public int getAllCountProduct() {
		return productsDao.getAllCountProduct();
	}
	
	@Override
	public int getAllCountProductByCategoryId(Integer categoryId) {
		return productsDao.getAllCountProductByCategoryId(categoryId);
	}

	@Override
	public List<Items> getPromotionInfo(){
		return (productsDao.promotionInfo());
	}

}
