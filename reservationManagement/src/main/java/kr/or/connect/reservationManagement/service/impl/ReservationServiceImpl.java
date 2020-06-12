package kr.or.connect.reservationManagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservationManagement.dao.DeleteResultDao;
import kr.or.connect.reservationManagement.dao.ReservationsDao;
import kr.or.connect.reservationManagement.dao.ReserveItemDao;
import kr.or.connect.reservationManagement.dao.ReserveItemPriceDao;
import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.dto.ReserveItemPrice;
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
	@Transactional(readOnly=false)
	public ReserveItem reserveAnItem(ReserveItem reserveItem, List<ReserveItemPrice> prices) {
		System.out.println("service in");
		Date now = new Date();
		reserveItem.setCreateDate(now);
		reserveItem.setModifyDate(now);
		reserveItem.setCancelFlag(false);
		int reservationInfoId = reserveItemDao.reserveAnItem(reserveItem);
		System.out.println("service middel");
		reserveItem.setReservationInfoId(reservationInfoId);
		System.out.println(prices.size());
		int i = 0;
		int pricesSize = prices.size();
		while (i < pricesSize) {
			ReserveItemPrice reserveItemPrice = new ReserveItemPrice();
			reserveItemPrice.setCount(prices.get(i).getCount());
			reserveItemPrice.setProductPriceId(prices.get(i).getProductPriceId());
			reserveItemPrice.setReservationInfoId(reservationInfoId);
			int reservationInfoPriceId = reserveItemPriceDao.reserveAnItemPrice(reserveItemPrice);
			reserveItemPrice.setReservationInfoPriceId(reservationInfoPriceId);
			reserveItem.getReserveItemPrices().set(i, reserveItemPrice);
			System.out.println(i); 
			i++;
		}
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
}
