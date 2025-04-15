package com.IndianRailway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.TrainDetails;
import com.IndianRailway.repository.PassengerRepository;
import com.IndianRailway.repository.UserRepository;

@Controller
public class PassengerController {

//	@Autowired
//	private PassengerRepository passengerRepo;
//	
//	@Autowired
//	private UserRepository userRepo;
//
//	
//	   @PostMapping("/Savepassengerdetails")
//       public String ShowPaymentDetails(Passenger passenger,@RequestParam("id")long id ,Model model) {				  
//		   passengerRepo.save(passenger);				   
//		   Optional<Passenger> findById = passengerRepo.findById(id);
//	       Passenger passengers = findById.get();		   
//	       model.addAttribute("passenger", passengers);		   
//	       return "/UserLogin/PaymentDetails";  	   	
//    }
////	   
//	   @GetMapping(path ="/Savepassengerdetails")
//	   public String ListAllTrain(Model model) {
//		    List<Passenger> passenger = passengerRepo.findAll();
//		   model.addAttribute("passenger", passenger);
//		   return "/UserLogin/PaymentDetails";
//		   
//	   }

//	   @GetMapping(path ="/")
//	    public String ShowBookTicketTrain(@PathVariable("id")long id, Model model) {
//		   Optional<Passenger> temp = passengerRepo.findById(id);
//		   Passenger passenger =temp.get();
//		   model.addAttribute("passenger", passenger);
//		   return "/UserLogin/PaymentDetails";   	
//	    }
//	 

}
