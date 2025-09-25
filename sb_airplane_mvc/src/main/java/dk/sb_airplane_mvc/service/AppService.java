package dk.sb_airplane_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.sb_airplane_mvc.db.FlightsRepository;
import dk.sb_airplane_mvc.dto.CaptainDto;
import dk.sb_airplane_mvc.dto.FlightDto;
import dk.sb_airplane_mvc.dto.FlightsDataDto;
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
	
}
