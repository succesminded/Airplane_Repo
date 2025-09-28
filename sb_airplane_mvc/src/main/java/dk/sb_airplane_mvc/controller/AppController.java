package dk.sb_airplane_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dk.sb_airplane_mvc.dto.CaptainDto;
import dk.sb_airplane_mvc.dto.FlightsDataDto;
import dk.sb_airplane_mvc.dto.RoutePlannerDtoList;
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
	
	@GetMapping("/routeplanner")
	public String getFlightsForRoute(
			Model model
			)
	{
		
		RoutePlannerDtoList routePlannerDtoList = this.service.getFlightsForRoute("", "");
		model.addAttribute("routePlannerDtoList", routePlannerDtoList);
		
		return "routeplanner.html";
	}
	
	@PostMapping("/routeplanner/getroute")
	public String getFlightsForRoute(
			Model model,
			@RequestParam("depcity") String depCity,
			@RequestParam("arrcity") String arrCity
			)
	{
		
		RoutePlannerDtoList routePlannerDtoList = this.service.getFlightsForRoute(depCity, arrCity);
		model.addAttribute("routePlannerDtoList", routePlannerDtoList);
		
		return "routeplanner.html";
	}
	
	@GetMapping("/captainsback")
	public String getCaptainsArrivedBackToTheStartCity(
			Model model)
	{
		List<CaptainDto> captainDtoList = this.service.getCaptainsArrivedBackToTheStartCity();
		model.addAttribute("captainDtoList", captainDtoList);
		
		return "captainsback.html";
	}
	
}
