package kr.or.connect.reservationManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Controller
public class ReservationManagementController {

	@Autowired
	ReservationManagementService reservationManagementService;
	
	@GetMapping(path = "/mainpage")
	public String mainpage(ModelMap model) {

		return "mainpage";
	}
}
