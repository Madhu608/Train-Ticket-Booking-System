package com.IndianRailway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IndianRailway.entities.CancelTrain;

public interface CancelTrainRepository extends JpaRepository<CancelTrain, Long> {
	
	
	 @Query(" from CancelTrain as c where c.user.id =:userId")
     public  List<CancelTrain>findCancelByUser(@Param("userId") Long userName);

}
