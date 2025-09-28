package dk.sb_airplane_mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class RoutePlannerDto {

	private List<FlightDto> flightDtoList;

	public RoutePlannerDto() {
		super();
		this.flightDtoList = new ArrayList<>();
	}

	public List<FlightDto> getFlightDtoList() {
		return flightDtoList;
	}

	public void setFlightDtoList(List<FlightDto> flightDtoList) {
		this.flightDtoList = flightDtoList;
	}
	
	public void addToroutePlannerDto(FlightDto flightDto)
	{
		flightDtoList.add(flightDto);
	}
	
}
