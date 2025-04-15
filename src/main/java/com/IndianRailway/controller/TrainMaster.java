package com.IndianRailway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.IndianRailway.entities.Master;
import com.IndianRailway.repository.TrainMasterRepository;

@Controller
@RequestMapping("/Master")
public class TrainMaster {

	@Autowired
	private TrainMasterRepository masterRepo;

	@RequestMapping(path = "/trainMaster")
	public String NumberofStop() {
		return "/Master/Master";
	}

	@PostMapping("/SaveNumberofstops")
	public String SaveNumberofStops(Master masters) {
		masterRepo.save(masters);
		return "/Master/Master";

	}
//		// get Master Train By Train Id
//		@GetMapping("/trainMaster/details/{id}")
//		public String getTrainMasterdataByTrainMasterId(@PathVariable("id")long id, Model model) {
//			Optional<Master> findById = masterRepo.findById(id);
//			Master master = findById.get();
//			model.addAttribute("trainmaster", master);
////			
////			List<Master> listnoOfstop = masterRepo.findAll();
////			model.addAttribute("listnoOfstops", listnoOfstop);
//			return "/AdminLogin/addtrain";
//		}
//
//	   

//		// get Master Train By Train Id
//		@GetMapping("/trainMaster/details/{trainId}")
//		public String getTrainMasterdataByTrainMasterId(@PathVariable Long trainId, Model model) {
//			TrainMaster trainMaster = this.adminService.getTrainMasterDetailsByTrainId(trainId);
//			model.addAttribute("trainmaster", trainMaster);
//			List<TrainMaster> listOfTrainMasters = this.adminService.findAllTrainMaster();
//			model.addAttribute("listOfTrainMaster", listOfTrainMasters);
//			model.addAttribute("listOfNoOfStop", adminService.findAllNoOfStops());
//			return "admin/add_train";
//		}

}
