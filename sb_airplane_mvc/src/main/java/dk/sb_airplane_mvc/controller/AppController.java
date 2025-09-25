package dk.sb_airplane_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dk.sb_airplane_mvc.dto.CaptainDto;
import dk.sb_airplane_mvc.dto.FlightsDataDto;
import dk.sb_airplane_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/flights")
	public String getAllFlightsDataOrderByDepartureDateTime(
			Model model)
	{
		FlightsDataDto flightsDataDtoList = this.service.getAllFlightsDataOrderByDepartureDateTimeAsc();
		model.addAttribute("flightsDataDtoList", flightsDataDtoList);
		
		return "flights.html";
	}
	
	@GetMapping("/flights/flighttime")
	public String getAllFlightsDataOrderByDepartureDateTimePlusFlightTime(
			Model model)
	{
		FlightsDataDto flightsDataDtoList  = this.service.getAllFlightsDataOrderByDepartureDateTimePlusFlightTime();
		model.addAttribute("flightsDataDtoList", flightsDataDtoList);
		
		return "flights.html";
	}
	
	@GetMapping("/captain")
	public String getFlyTimeForCaptains(
			Model model)
	{
		List<CaptainDto> captainDtoList = this.service.getFlyTimeForCaptains();
		model.addAttribute("captainDtoList", captainDtoList);
		
		return "captain.html";
	}
	
}
