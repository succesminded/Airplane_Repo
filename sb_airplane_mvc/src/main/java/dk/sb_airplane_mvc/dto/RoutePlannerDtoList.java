package dk.sb_airplane_mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class RoutePlannerDtoList {

	private List<String> depCityList;
	private List<String> arrCityList;
	private List<RoutePlannerDto> routePlannerDtoList;

	public RoutePlannerDtoList(List<String> depCityList, List<String> arrCityList) {
		super();
		this.depCityList = depCityList;
		this.arrCityList = arrCityList;
		this.routePlannerDtoList = new ArrayList<>();
	}

	public List<RoutePlannerDto> getRoutePlannerDtoList() {
		return routePlannerDtoList;
	}

	public void setRoutePlannerDtoList(List<RoutePlannerDto> routePlannerDtoList) {
		this.routePlannerDtoList = routePlannerDtoList;
	}
	

	public List<String> getDepCityList() {
		return depCityList;
	}

	public void setDepCityList(List<String> depCityList) {
		this.depCityList = depCityList;
	}

	public List<String> getArrCityList() {
		return arrCityList;
	}

	public void setArrCityList(List<String> arrCityList) {
		this.arrCityList = arrCityList;
	}
	
	
}
