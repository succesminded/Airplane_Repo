package dk.sb_airplane_mvc.dto;

import java.util.List;

public class FlightsDataDto {

	private boolean seeFlightTime;
	private List<FlightDto> flightDtoList;
	
	
	public FlightsDataDto(boolean seeFlightTime, List<FlightDto> flightDtoList) {
		super();
		this.seeFlightTime = seeFlightTime;
		this.flightDtoList = flightDtoList;
	}


	public boolean isSeeFlightTime() {
		return seeFlightTime;
	}

	public void setSeeFlightTime(boolean seeFlightTime) {
		this.seeFlightTime = seeFlightTime;
	}

	public List<FlightDto> getFlightDtoList() {
		return flightDtoList;
	}

	public void setFlightDtoList(List<FlightDto> flightDtoList) {
		this.flightDtoList = flightDtoList;
	}
	
}
