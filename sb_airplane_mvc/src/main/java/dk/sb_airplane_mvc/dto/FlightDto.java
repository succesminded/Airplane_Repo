package dk.sb_airplane_mvc.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class FlightDto {

	private Integer id;
	private String departureCity;
	private LocalDateTime departureDatetime;
	private String arrivalCity;
	private LocalDateTime arrivalDatetime;
	private String flightNumber;
	private String captain;
	private long waitingTime;
	
	public FlightDto(Integer id, String departureCity, LocalDateTime departureDatetime, String arrivalCity, LocalDateTime arrivalDatetime,
			String flightNumber, String captain) {
		super();
		this.id = id;
		this.departureCity = departureCity;
		this.departureDatetime = departureDatetime;
		this.arrivalCity = arrivalCity;
		this.arrivalDatetime = arrivalDatetime;
		this.flightNumber = flightNumber;
		this.captain = captain;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public LocalDateTime getDepartureDatetime() {
		return departureDatetime;
	}

	public void setDepartureDatetime(LocalDateTime departureDatetime) {
		this.departureDatetime = departureDatetime;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public LocalDateTime getArrivalDatetime() {
		return arrivalDatetime;
	}

	public void setArrivalDatetime(LocalDateTime arrivalDatetime) {
		this.arrivalDatetime = arrivalDatetime;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	public long getFlightTime() {
	
		Duration d = Duration.between( this.departureDatetime , this.arrivalDatetime );				
				
		return d.toMinutes();
	}

	public long getWaitingTime() {
		return waitingTime;
	}
	
	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}
}
