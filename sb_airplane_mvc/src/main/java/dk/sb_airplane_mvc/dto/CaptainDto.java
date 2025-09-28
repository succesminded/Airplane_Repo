package dk.sb_airplane_mvc.dto;

public class CaptainDto {

	private String name;
	private long flyTime;
	
	private String departureCity;
	
	public CaptainDto(String name, long flyTime) {
		super();
		this.name = name;
		this.flyTime = flyTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(long flyTime) {
		this.flyTime = flyTime;
	}
	
	public void addToFlyTime(long flyTime) {
		this.flyTime+= flyTime;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	
}
