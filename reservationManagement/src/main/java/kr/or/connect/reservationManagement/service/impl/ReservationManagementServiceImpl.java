package kr.or.connect.reservationManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.dao.DetailPageItemsDao;
import kr.or.connect.reservationManagement.dao.ItemsDao;
import kr.or.connect.reservationManagement.dao.ReservationCommentsDao;
import kr.or.connect.reservationManagement.dto.DetailPageItems;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ReservationComments;
import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Service
public class ReservationManagementServiceImpl implements ReservationManagementService {

	@Autowired
	ItemsDao productsDao;
	
	@Autowired
	DetailPageItemsDao detailPageItemsDao;
	
	@Autowired
	ReservationCommentsDao reservationCommentsDao;
	
	@Override
	public List<Items> getAllProducts(){
		return productsDao.selectAllProducts();
	}
	
	@Override
	public List<Items> getLimitedProducts(Integer start){
		return productsDao.selectLimitedProducts(start, ReservationManagementService.LIMIT);
	}

	@Override
	public List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId){
		return productsDao.selectLimitedProductsByCategoryId(start, ReservationManagementService.LIMIT, categoryId);
	}
	
	@Override
	public List<Items> getCategoriesInfoGroupByCategoryId(){
		return (productsDao.selectCategoriesInfoGroupByCategoryId());
	}

	@Override
	public int getAllCountOfProduct() {
		return productsDao.getCountOfProduct();
	}
	
	@Override
	public int getAllCountProductByCategoryId(Integer categoryId) {
		return productsDao.getCountOfProductByCategoryId(categoryId);
	}

	@Override
	public List<Items> getPromotionInfo(){
		return (productsDao.getPromotionInfo());
	}

	@Override
	public List<DetailPageItems> getDetailListItems(Integer id){
		return (detailPageItemsDao.getDetailPageItemsById(id));
	}
	
	@Override
	public List<ReservationComments> getUserCommentsById(Integer id){
		return reservationCommentsDao.getUserCommentsById(id);
	}
	
	@Override
	public List<ReservationComments> getLimitedUserCommentsById(Integer id, Integer limit){
		return reservationCommentsDao.getLimitedUserCommentById(id, limit);
	}
}
