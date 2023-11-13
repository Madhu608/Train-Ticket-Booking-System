package com.IndianRailway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IndianRailway.entities.TrainDetails;

public interface AdminRepository extends JpaRepository<TrainDetails, Long> {

	  @Query("from TrainDetails where trainFrom=:trainFrom and trainTo=:trainTo and departureDate=:departureDate")
	   List<TrainDetails> findTrains(@Param("trainFrom")String trainFrom,@Param("trainTo") String trainTo,@Param("departureDate") String departureDate);
	
	//List<TrainDetails> findTrains(String trainFrom,String trainTo);
	
	  boolean existsById(String id);
}
