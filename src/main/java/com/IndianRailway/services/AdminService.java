package com.IndianRailway.services;

import com.IndianRailway.entities.TrainDetails;
public interface AdminService {

	 TrainDetails getTrainDetailsById(Long id); 
	
	TrainDetails updateTrainDetails(TrainDetails trainDetail); 
	
}
