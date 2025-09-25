package dk.sb_airplane_mvc.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("flights")
public class Flight {

	@Id
	@Column("id")
	private Integer id;
	
	@Column("departure_city")
	private String departureCity;
	
	@Column("departure_datetime")
	private LocalDateTime departureDatetime;
	
	@Column("arrival_city")
	private String arrivalCity;
	
	@Column("arrival_datetime")
	private LocalDateTime arrivalDatetime;
	
	@Column("flight_number")
	private String flightNumber;
	
	@Column("captain")
	private String captain;
	

	public Flight(Integer id, String departureCity, LocalDateTime departureDatetime, String arrivalCity, LocalDateTime arrivalDatetime,
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
	
}
