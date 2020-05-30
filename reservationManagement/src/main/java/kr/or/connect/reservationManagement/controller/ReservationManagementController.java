package kr.or.connect.reservationManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Controller
public class ReservationManagementController {

	@Autowired
	ReservationManagementService reservationManagementService;
	
	@GetMapping(path = "/mainpage")
	public String mainpage() {
		return "mainpage";
	}
	
	@GetMapping(path = "/detail")
	public String detailPage() {
		return "detail";
	}
	
	@GetMapping(path = "/review")
	public String reviewPage() {
		return "review";
	}
	
	@GetMapping(path = "/reserve")
	public String reservePage() {
		return "reserve";
	}
	
	@GetMapping(path = "/myreservation")
	public String myreservationPage() {
		return "myreservation";
	}
}
