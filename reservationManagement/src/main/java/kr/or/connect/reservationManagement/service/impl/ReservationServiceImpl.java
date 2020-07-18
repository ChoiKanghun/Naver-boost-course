package kr.or.connect.reservationManagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.dao.DeleteResultDao;
import kr.or.connect.reservationManagement.dao.ReservationsDao;
import kr.or.connect.reservationManagement.dao.ReserveItemDao;
import kr.or.connect.reservationManagement.dao.ReserveItemPriceDao;
import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.dto.ReserveItemPrice;
import kr.or.connect.reservationManagement.service.ProductPromotionService;
import kr.or.connect.reservationManagement.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationsDao reservationsDao;
	@Autowired
	DeleteResultDao deleteResultDao;
	@Autowired
	ReserveItemDao reserveItemDao;
	@Autowired
	ReserveItemPriceDao reserveItemPriceDao;
	@Autowired
	ProductPromotionService productPromotionService;

	@Override
	public List<Reservations> getReservations(String reservationEmail){
		return reservationsDao.getReservations(reservationEmail);
	}

	@Override
	public DeleteReservationResult getDeleteResult(Integer reservationId) {
		return deleteResultDao.getDeleteResult(reservationId);
	}
	
	@Override
	public List<DeleteReservationPrices> getDeleteResultPrices(Integer reservationId) {
		return deleteResultDao.getDeleteResultPrices(reservationId);
	}
	
	@Override
	public ReserveItem reserveAnItem(ReserveItem reserveItem, List<ReserveItemPrice> prices) {
		Date now = new Date();
		reserveItem.setCreateDate(now);
		reserveItem.setModifyDate(now);
		reserveItem.setCancelFlag(false);
		
		int reservationInfoId = reserveItemDao.reserveAnItem(reserveItem);
		reserveItem.setReservationInfoId(reservationInfoId);
		
		List<ReserveItemPrice> reserveItemPrices = new ArrayList<>();

		for (ReserveItemPrice price : prices) {
			ReserveItemPrice reserveItemPrice = new ReserveItemPrice();
			reserveItemPrice.setCount(price.getCount());
			reserveItemPrice.setProductPriceId(price.getProductPriceId());
			reserveItemPrice.setReservationInfoId(reservationInfoId);
			
			int reservationInfoPriceId = reserveItemPriceDao.reserveAnItemPrice(reserveItemPrice);
			
			reserveItemPrice.setReservationInfoPriceId(reservationInfoPriceId);
			reserveItemPrices.add(reserveItemPrice);
		}
		reserveItem.setReserveItemPrices(reserveItemPrices);
		return reserveItem;
	}
	
	@Override
	public ReserveItem cancelReservation(Integer reservationInfoId) {
		return reserveItemDao.cancelReserve(reservationInfoId);
	}
	
	@Override
	public List<ReserveItemPrice> getResereveItemPriceByReservationInfoId(Integer reservationInfoId){
		return reserveItemPriceDao.getPricesByReservationInfoId(reservationInfoId);
	}
	
	@Override
	public void setDisplayInfoOfReservations(List<Reservations> reservations) {
		for (int i = 0; i < reservations.size(); i++)
			reservations.get(i).setDisplayInfo(productPromotionService.getDisplayInfo(reservations.get(i).getDisplayInfoId()));
	}
}
