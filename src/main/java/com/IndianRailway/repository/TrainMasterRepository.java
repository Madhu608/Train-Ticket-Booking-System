package com.IndianRailway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IndianRailway.entities.Master;

public interface TrainMasterRepository extends JpaRepository<Master, Long> {

	
	

}
