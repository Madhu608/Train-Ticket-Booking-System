package com.IndianRailway.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IndianRailway.entities.TrainDetails;
import com.IndianRailway.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	private AdminRepository adminRepo;

	 @Override
	public TrainDetails getTrainDetailsById(Long id) {		
		return adminRepo.findById(id).get();
	}

	@Override
	public TrainDetails updateTrainDetails(TrainDetails trainDetail) {		
		return adminRepo.save(trainDetail);
	}
	








}
