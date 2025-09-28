package dk.sb_airplane_mvc.db;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dk.sb_airplane_mvc.model.Flight;

@Repository
public interface FlightsRepository extends CrudRepository<Flight, Integer>{

	@Query("SELECT * FROM flights ORDER BY departure_datetime ASC")
	List<Flight> getAllFlightsDataOrderByDepartureDateTimeAsc();
	
	@Query("SELECT DISTINCT departure_city FROM flights ORDER BY 1 ASC")
	List<String> getAllDeaprtureCityList();
	
	@Query("SELECT DISTINCT arrival_city FROM flights ORDER BY 1 ASC")
	List<String> getAllArrivalCityList();

}
