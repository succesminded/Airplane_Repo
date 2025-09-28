package dk.sb_airplane_mvc.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.sb_airplane_mvc.db.FlightsRepository;
import dk.sb_airplane_mvc.dto.CaptainDto;
import dk.sb_airplane_mvc.dto.FlightDto;
import dk.sb_airplane_mvc.dto.FlightsDataDto;
import dk.sb_airplane_mvc.dto.RoutePlannerDto;
import dk.sb_airplane_mvc.dto.RoutePlannerDtoList;
import dk.sb_airplane_mvc.model.Flight;

@Service
public class AppService {

	private FlightsRepository flightsRepository;

	@Autowired
	public AppService(FlightsRepository flightsRepository) {
		super();
		this.flightsRepository = flightsRepository;
	}

	public FlightsDataDto getAllFlightsDataOrderByDepartureDateTimeAsc() {

		List<FlightDto> flightDtoList = new ArrayList<>();
		
		List<Flight> flightList = this.flightsRepository.getAllFlightsDataOrderByDepartureDateTimeAsc();
		
		for(Flight flight : flightList)
		{

			FlightDto flightDto = new FlightDto(
												flight.getId(),
												flight.getDepartureCity(),
												flight.getDepartureDatetime(),
												flight.getArrivalCity(),
												flight.getArrivalDatetime(),
												flight.getFlightNumber(),
												flight.getCaptain()
												);
			flightDtoList.add(flightDto);
		}
		
		FlightsDataDto flightsDataDto = new FlightsDataDto(
															false,
															flightDtoList
															);
		
		
		return flightsDataDto;
	}

	public FlightsDataDto getAllFlightsDataOrderByDepartureDateTimePlusFlightTime() {
		
		//get flight's data from previous method
		FlightsDataDto flightsDataDtoList = this.getAllFlightsDataOrderByDepartureDateTimeAsc();
		
		//modify seeFlightTime attribute
		flightsDataDtoList.setSeeFlightTime(true);
		
		return flightsDataDtoList;
	}

	public List<CaptainDto> getFlyTimeForCaptains() {

		List<CaptainDto> captainDtoList = new ArrayList<>();
		
		//get flight's data from previous method
		FlightsDataDto flightsDataDto = this.getAllFlightsDataOrderByDepartureDateTimeAsc();
		
		for(FlightDto flightDto: flightsDataDto.getFlightDtoList())
		{
			//if captainDtoList is not empty
			if(captainDtoList.size() > 0)
			{
					
				boolean found = false;
				
				for(CaptainDto captainDto : captainDtoList)
				{
					//captain is already in captainDtoList (equals by name)
					if(flightDto.getCaptain().equals(captainDto.getName()))
					{					
						//captain's fly time is being raise by flight time of current flight
						captainDto.addToFlyTime(flightDto.getFlightTime());
						//captain is found in captainDtoList - no need to add again
						found = true;
					}
					
				}
				//if not found in captainDtoList than create captainDto and add it to list
				if(found == false)
				{
					CaptainDto newCaptainDto = new CaptainDto(
																flightDto.getCaptain(),
																flightDto.getFlightTime()
																);
					captainDtoList.add(newCaptainDto);	
				}
			}
			//if captainDtoList is empty - create first captainDto and add to list
			else
			{				
				CaptainDto newCaptainDto = new CaptainDto(
															flightDto.getCaptain(),
															flightDto.getFlightTime()
															);
				captainDtoList.add(newCaptainDto);
			}
		}
		
		return captainDtoList;
	}

	public RoutePlannerDtoList getFlightsForRoute(String departureCity, String arrivalCity) {
		
		//get flight's data from previous method
		FlightsDataDto flightsDataDto = this.getAllFlightsDataOrderByDepartureDateTimeAsc();
		
		/** FIND FLIGHTDTO WITH DEPARTURE CITY OF USER*/
				
		RoutePlannerDtoList routePlannerDtoList = new RoutePlannerDtoList(getDepOrArrCityList("DEP"), getDepOrArrCityList("ARR"));
		

		List<RoutePlannerDto> draftRoutePlannerDtoList = new ArrayList<>();
		
		List<FlightDto> originalList = flightsDataDto.getFlightDtoList();
		
		for(int index = 0; index < originalList.size(); index++)
		{	
			String depCity = originalList.get(index).getDepartureCity();
			FlightDto flightDto = originalList.get(index);
			
			//if user's departure city = flight departure city
			if(depCity.equals(departureCity))
			{
				
				RoutePlannerDto routePlannerDto = new RoutePlannerDto();
				//add the flight to routePlannerDto's flight list as first element
				routePlannerDto.addToroutePlannerDto(flightDto);
				//add the rest of the list in ascending order by departure time
				for(FlightDto addFlightDto : originalList)
				{
					if(flightDto.getArrivalDatetime().isBefore(addFlightDto.getDepartureDatetime()))
					{
						routePlannerDto.addToroutePlannerDto(addFlightDto);
					}
				}
				draftRoutePlannerDtoList.add(routePlannerDto);
			}
		}
		
		/** REMOVE FlightDto's which doesn't mathes in the row (departure city != previous arrival city) */
		for(RoutePlannerDto routePlannerDto : draftRoutePlannerDtoList)
		{
						
			List<FlightDto> removeList = new ArrayList<>();	
			
			List<FlightDto> checkList = routePlannerDto.getFlightDtoList();
			
			boolean arrCityFound = false;
			boolean lastRemoved = false;
			
			for(int checkIdx = 1; checkIdx < checkList.size(); checkIdx++)
			{
				String depCity = "";
				
				if(lastRemoved == false)
				{
					depCity = checkList.get(checkIdx-1).getArrivalCity();
				}
				else
				{
					depCity = checkList.get(checkIdx-2).getArrivalCity();
				}
				
				String arrCity = checkList.get(checkIdx).getDepartureCity();	
			
				
				if(arrCityFound == true)
				{
					removeList.add(checkList.get(checkIdx));
				}
				
				if(depCity.equals(arrCity))
				{
					if(checkList.get(checkIdx).getArrivalCity().equals(arrivalCity))
					{
						arrCityFound = true;
					}
				}
				else
				{
					removeList.add(checkList.get(checkIdx));
					lastRemoved = true;
					
				}
			}
			
			routePlannerDto.getFlightDtoList().removeAll(removeList);
		}
		
		/** REMOVE RoutePlannerDto which doesn't have the arrivalCity in the last FlightDto */
		List<RoutePlannerDto> removeRPList = new ArrayList<>();
		
		for(RoutePlannerDto routePlannerDto : draftRoutePlannerDtoList)
		{
			boolean routeIsFalse = false;
			
			List<FlightDto> checkList = routePlannerDto.getFlightDtoList();
			for(int checkIdx = checkList.size()-1; checkIdx < checkList.size(); checkIdx++)
			{
				if(!checkList.get(checkIdx).getArrivalCity().equals(arrivalCity))
				{
					routeIsFalse = true;
				}
			}
			
			if(routeIsFalse == true)
			{
				removeRPList.add(routePlannerDto);
			}
		}
		draftRoutePlannerDtoList.removeAll(removeRPList);
		
		/** SET WAITING TIME */
		for(RoutePlannerDto routePlannerDto : draftRoutePlannerDtoList)
		{
			List<FlightDto> checkList = routePlannerDto.getFlightDtoList();
			for(int checkIdx = 0; checkIdx < checkList.size() - 1; checkIdx++)
			{
				LocalDateTime actArrTime = checkList.get(checkIdx).getArrivalDatetime();
				LocalDateTime nextDepTime = null;
				Duration d = null;
				
				if(checkIdx < checkList.size()-1)
				{
					nextDepTime = checkList.get(checkIdx+1).getDepartureDatetime();
					d = Duration.between( actArrTime, nextDepTime );				
					checkList.get(checkIdx).setWaitingTime(d.toMinutes());
				}
				else
				{
					checkList.get(checkIdx).setWaitingTime(0);
				}
				
				
			}
		}

		routePlannerDtoList.setRoutePlannerDtoList(draftRoutePlannerDtoList);
	
		return routePlannerDtoList;
	}
	
	
	public List<CaptainDto> getCaptainsArrivedBackToTheStartCity()
	{
		List<String> captainList = new ArrayList<>();
		
		List<CaptainDto> captainDtoList = new ArrayList<>();
		/** GET FLIGHT LIST FROM PREVIOUS METHOD*/
		List<Flight> flightList = this.flightsRepository.getAllFlightsDataOrderByDepartureDateTimeAsc();		
		
		/** GET CAPTAIN'S NAME FROM FLIGHT RECORDS */
		for(Flight flight : flightList)
		{
			String captain = flight.getCaptain();
			if(!captainList.contains(captain))
			{
				captainList.add(captain);
			}
		}
		
		/** CREATE LIST FOR CAPTAINS TO BE REMOVED FROM CAPTAIN LIST */
		List<String> removeCaptainList = new ArrayList<>();
		
		/** CHECK ALL CAPTAIN IN FLIGHTS LIST */
		for (String captain : captainList)
		{
			/** ITERATE FLIGHTS (ascending departure time order)*/
			for(int flightIdx = 0; flightIdx < flightList.size(); flightIdx++)
			{
				String actCaptain = flightList.get(flightIdx).getCaptain();
				
				/** IF CAPTAIN'S NAME MATCHING */
				if(actCaptain.equals(captain))
				{
					String depCity = "";
					
					/** ITERATE REST OF THE FLIGHTS (still ascending departure time order) */
					for(int restFlightIdx = flightIdx; restFlightIdx < flightList.size()-1; restFlightIdx++)
					{
						
						depCity = flightList.get(restFlightIdx + 1).getDepartureCity();
						
						String arrCity = flightList.get(restFlightIdx).getArrivalCity();
						
						/** IF ACTUAL ARRIVAL CITY DOES NOT MATCH WITH NEXT FLIGHT'S DEPARTURE CITY */
						if(!arrCity.equals(depCity))
						{
							/** ADD CAPTAIN TO REMOVE LIST */
							removeCaptainList.add(captain);
							/** BREAK REST FLIGHT LIST ITERATION */
							break;
						}
					}
					/** BREAK FLIGHT LIST ITERATION */
					break;
				}
			}		
		}
		
		/** REMOVE CAPTIANS WHO WON'T CAME BACK */
		captainList.removeAll(removeCaptainList);	
		
		/** CREATE CAPTAINDTO LIST */
		for(String captain : captainList)
		{
			CaptainDto captainDto = new CaptainDto(
													captain,
													0
													);
			captainDtoList.add(captainDto);
		}
		
		return captainDtoList;
	}

	private List<String> getDepOrArrCityList(String type)
	{
		List<String> cityList = new ArrayList<>();
		
		if(type.equals("DEP"))
		{
			cityList = this.flightsRepository.getAllDeaprtureCityList();
		}
		else if (type.equals("ARR"))
		{
			cityList = this.flightsRepository.getAllArrivalCityList();
		}
		
		return cityList;
	}
	
}
