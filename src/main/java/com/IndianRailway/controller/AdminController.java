package com.IndianRailway.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IndianRailway.entities.Master;
import com.IndianRailway.entities.Message;
import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.TrainDetails;
import com.IndianRailway.entities.User;
import com.IndianRailway.repository.AdminRepository;
import com.IndianRailway.repository.PassengerRepository;
import com.IndianRailway.repository.TrainMasterRepository;
import com.IndianRailway.repository.UserRepository;
import com.IndianRailway.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PassengerRepository passengerRepo;

	@Autowired
	private TrainMasterRepository masterRepo;

	@Autowired
	private TrainMasterRepository trainMasterRepo;

	@GetMapping("/AdminLogin")
	public String UserAndAdminLogig() {
		return "/AdminLogin/AdminLogin";
	}

	@RequestMapping("/addtrain")
	public String AddTrain(Model model) {
		List<Master> numberofStop = trainMasterRepo.findAll();
		model.addAttribute("numberofStops", numberofStop);
		return "/AdminLogin/addtrain";
	}

	@RequestMapping("/showtrain")
	public String ShowTrain() {
		return "/AdminLogin/ShowTrain";
	}

	@PostMapping("/Savetraindetails")
	public String SaveTrainDetails(@ModelAttribute TrainDetails trainDetails, HttpSession session) {
		boolean f = this.adminRepo.existsById(trainDetails.getId());
		if (f) {
			session.setAttribute("message", new Message("TrainId Is Alredy Exist !!", "danger"));
			System.out.println("TrainId Is Already Exist !!");
		} else {
			session.setAttribute("message", new Message(" Train Details Saved Successfully !!", "success"));

			adminRepo.save(trainDetails);
		}
		return "/AdminLogin/addtrain";

	}

	@GetMapping(path = "/showtrain/{page}")
	public String ListAllTrain(@PathVariable("page") Integer page, Model model) {

		Pageable pageable = PageRequest.of(page, 7);
		Page<TrainDetails> listTrains = adminRepo.findAll(pageable);
		model.addAttribute("listTrains", listTrains);
		model.addAttribute("CurrentPage", page);

		model.addAttribute("totalPages", listTrains.getTotalPages());
		return "/AdminLogin/ShowTrain";

	}

	@GetMapping("/listtrain/update/{id}")
	public String ShowUpdateTrain(@PathVariable("id") long id, Model model) {
		Optional<TrainDetails> temp = adminRepo.findById(id);
		TrainDetails TrainDetails = temp.get();
		model.addAttribute("trainDetails", TrainDetails);
		return "/AdminLogin/UpdateTrain";
	}

	@PostMapping(path = "/Saveupdatetraindetails")
	public String SaveUpdateTrainDetails(@ModelAttribute TrainDetails trainDetails) {
		adminRepo.save(trainDetails);
		return "redirect:/admin/showtrain/0";
	}

	@GetMapping("/listtrain/delete/{id}")
	public String DeleteTrain(@PathVariable("id") long id) {
		adminRepo.deleteById(id);
		return "redirect:/admin/showtrain/0";

	}

	@GetMapping("/listOfUsers/{page}")
	public String ListAllUsers(@PathVariable("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 6);
		Page<User> findAllUsers = userRepo.findAll(pageable);
		model.addAttribute("listUsers", findAllUsers);

		model.addAttribute("CurrentPage", page);

		model.addAttribute("totalPages", findAllUsers.getTotalPages());

		return "/AdminLogin/ListofUsers";

	}

	@GetMapping("/showtrain&passenger/{page}")
	public String DisplayTrainAndPassenger1(@PathVariable("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Passenger> listTrainsandpassenger = passengerRepo.findAll(pageable);
		model.addAttribute("listTrainsandpassenger", listTrainsandpassenger);

		model.addAttribute("CurrentPage", page);

		model.addAttribute("totalPages", listTrainsandpassenger.getTotalPages());
		return "/AdminLogin/DisplayTrainAndPassenger";

	}

	@GetMapping("/showtrain&passenger2")
	public String DisplayTrainAndPassenger2(Model model) {
		List<Passenger> listTrainsandpassenger = passengerRepo.findAll();
		model.addAttribute("listTrainsandpassenger", listTrainsandpassenger);
		return "/AdminLogin/DisplayTrainAndPassenger2";

	}

	@RequestMapping(path = "/trainMaster")
	public String NumberofStop() {
		return "/Master/Master";
	}

	@PostMapping("/SaveNumberofstops")
	public String SaveNumberofStops(Master masters) {
		masterRepo.save(masters);
		return "/Master/Master";

	}

}