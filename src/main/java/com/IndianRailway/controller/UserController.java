package com.IndianRailway.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.IndianRailway.entities.CancelTrain;
import com.IndianRailway.entities.Message;
import com.IndianRailway.entities.MyBooking;
import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.TrainDetails;
import com.IndianRailway.entities.User;
import com.IndianRailway.entities.UserBookingDTO;
import com.IndianRailway.repository.AdminRepository;
import com.IndianRailway.repository.CancelTrainRepository;
import com.IndianRailway.repository.MyBookingRepository;
import com.IndianRailway.repository.PassengerRepository;
import com.IndianRailway.repository.UserRepository;
import com.IndianRailway.services.JReportService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PassengerRepository passengerRepo;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private MyBookingRepository myBookingRepo;

	@Autowired
	private CancelTrainRepository cancelTrainRepo;

	@Autowired
	private JReportService service;

	// Method for adding Common date to response
	@ModelAttribute
	public void addCommonDate(Model model, Principal principal) {

		String userName = principal.getName();
		System.out.println("USERNAME" + userName);

		// get the user using userName(emailId)
		User user = userRepo.findByEmailId(userName);
		model.addAttribute("user", user);

	}

	@RequestMapping("/Userlogin")
	public String UserLogig() {
		return "/login";
	}

	@RequestMapping("/User")
	public String RegistrattionPage() {
		return "UserRegistration";
	}

	@RequestMapping("/search")
	public String UserAndAdminLogig() {
		return "/UserLogin/SearchTrain";
	}

	@RequestMapping(path = "/findTrain")
	public String FindTrains(@RequestParam("trainFrom") String trainFrom, @RequestParam("trainTo") String trainTo,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") String departureDate,
			HttpSession session, Model model) {
		List<TrainDetails> findTrains = adminRepo.findTrains(trainFrom, trainTo, departureDate);

		if (findTrains.isEmpty()) {
			session.setAttribute("message", new Message(" There is no train on this Date !!", "success"));
			return "/UserLogin/displayTrains";
		} else {
			model.addAttribute("findTrains", findTrains);

		}

		return "/UserLogin/displayTrains";

	}

	@GetMapping(path = "/BookTicket/{id}")
	public String ShowBookTicketTrain(@PathVariable("id") long id, Model model) {
		Optional<TrainDetails> temp = adminRepo.findById(id);
		TrainDetails trainDetails = temp.get();
		model.addAttribute("trainDetails", trainDetails);
		return "/UserLogin/BookTicket";
	}

	@PostMapping("/savePassengers")
	public String ShowPaymentDetails(@ModelAttribute Passenger passenger, @RequestParam("id") long id,
			Principal principal, Model model) {

		String name = principal.getName();
		User user = this.userRepo.findByEmailId(name);
		passenger.setUser(user);

		user.getPassengers().add(passenger);

		this.userRepo.save(user);

		// Method-1

//			Optional<Passenger> findById = passengerRepo.findById(id);			 			  			   
//			   Passenger passengers = findById.get();		   
//			   model.addAttribute("passenger", passengers);  
//			   System.out.println(passenger);

		// Method-2

//			 Passenger passengers = passengerRepo.findById(id);

		model.addAttribute("passengers", passenger);
		return "/UserLogin/paymenDetails";
	}

	@RequestMapping(path = "/PaymentMode")
	public String ShowPaymentMode() {
		return "/UserLogin/PaymentMode";

	}

	// open change password handler

	@GetMapping("/change-password")
	public String ChangePasswordNew() {
		return "/UserLogin/ChangePassword";
	}

	// change password handler

	@PostMapping("/changepassword")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal, HttpSession session) {
		System.out.println("OLD PASSWORD " + oldPassword);
		System.out.println("NEW PASSWORD " + newPassword);

		String emailId = principal.getName();
		User currentUser = this.userRepo.findByEmailId(emailId);
		System.out.println(currentUser.getPassword());

		if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {

			// change the password

			currentUser.setPassword(this.passwordEncoder.encode(newPassword));
			this.userRepo.save(currentUser);

//				session.setAttribute("message", "Your password is successfully changed...!!!");
		} else {

			session.setAttribute("message", "Please Enter correct old password !!!");
			return "redirect:/user/change-password";
		}

		return "redirect:/signin?change=Your password is successfully changed...";
//				return "redirect:/user/search";
	}

	// My profile

	@RequestMapping("/myProfile")
	public String Myprofile() {
		return "/UserLogin/MyProfile";
	}

	// My Booking

	// per page =5[n]
	// current page= 0 [page]

//			   @RequestMapping("/myBooking/{page}")
//				public String MyBooking(@PathVariable("page")Integer page,Model model,@ModelAttribute Passenger passenger ,Principal principal) {
//				   
//	          String userName = principal.getName();
//			  User user = this.userRepo.findByEmailId(userName);
//				    
//			  
//			  
//		      //get the user using userName(emailId)
//			  
//			  Pageable pageable = PageRequest.of(page, 3);
//			  
//              Page<Passenger> passengers =this.passengerRepo.findPassengerByEmailId(user.getId(),pageable);
//			  
//			  List<MyBooking> myBookings =this.myBookingRepo.findMyBookingByPaymentId(user.getId());   
//			  
//			  List<String> statusList = myBookings.stream()
//		                .map(MyBooking::getStatus)
//		                .collect(Collectors.toList());
//			  
//			  model.addAttribute("statusList", statusList);
//			  
//              model.addAttribute("passenger", passengers);
//                            
//              model.addAttribute("CurrentPage", page);
//              
//              model.addAttribute("totalPages", passengers.getTotalPages());
//              
//			  return "/UserLogin/MyBooking";
//				}

	@RequestMapping("/myBooking")
	public String MyBooking(Model model, @ModelAttribute Passenger passenger, Principal principal) {

		String userName = principal.getName();
		User user = this.userRepo.findByEmailId(userName);

		List<Passenger> passengers = this.passengerRepo.findPassengerByEmailId(user.getId());

		List<MyBooking> bookTicketList = this.myBookingRepo.findMyBookingByPaymentId(user.getId());

		List<UserBookingDTO> userBookingList = new ArrayList<>();

		// Combine the data from PaymentBook and BookTicket entities into UserBookingDTO
		// objects
		for (int i = 0; i < bookTicketList.size(); i++) {

			UserBookingDTO userBookingDTO = new UserBookingDTO();
			userBookingDTO.setPassenger(passengers.get(i));
			userBookingDTO.setBookTicket(bookTicketList.get(i));
			userBookingList.add(userBookingDTO);

		}

		model.addAttribute("userBookingList", userBookingList);
		return "/UserLogin/MyBooking";
	}

	// My Booking1

	@GetMapping("/viewMore/{id}")
	public String displayTrainPassngrViewMore(@PathVariable("id") Long id, Model model) {
		Optional<Passenger> findById = passengerRepo.findById(id);
		Passenger passengerview = findById.get();
		model.addAttribute("myBooking", passengerview);
		return "/UserLogin/MyBooking1";
	}

	@RequestMapping("/Cancel")
	public String CancelTrain(@RequestParam("id") long id, @RequestParam("myBookingId") long myBookingId, Model model,
			Principal principal) {

		Passenger passenger = passengerRepo.findById(id);
//			      Optional<MyBooking> findById2 = myBookingRepo.findById(myBookingId);
//			      MyBooking myBooking = findById2.get();

		CancelTrain cancel = new CancelTrain();

		cancel.setId(passenger.getId());
		cancel.setTrainName(passenger.getTrainName());
		cancel.setDepartureDate(passenger.getDepartureDate());
		cancel.setTrainFrom(passenger.getTrainFrom());
		cancel.setTrainTo(passenger.getTrainTo());
		cancel.setFirstName(passenger.getFirstName());
		cancel.setEmail(passenger.getEmail());
		cancel.setPhone(passenger.getPhone());
		cancel.setTicketPrice(passenger.getTicketPrice());
		cancel.setUser(passenger.getUser());

//				   cancel.setStatus(myBooking.getStatus());

		cancelTrainRepo.save(cancel);
		System.out.println(cancel);

		passengerRepo.deleteById(id);
		myBookingRepo.deleteById(myBookingId);

		String userName = principal.getName();
		User user = this.userRepo.findByEmailId(userName);

		List<CancelTrain> cancelTrains = cancelTrainRepo.findCancelByUser(user.getId());

		model.addAttribute("cancelTrains", cancelTrains);

		return "/UserLogin/CancelTicket";

	}

	@RequestMapping("/myCancelTicket")
	public String MyCancelTicket(Model model, Principal principal) {
		String userName = principal.getName();
		User user = this.userRepo.findByEmailId(userName);

		List<CancelTrain> cancelTrains = cancelTrainRepo.findCancelByUser(user.getId());

		model.addAttribute("cancelTrains", cancelTrains);

		return "/UserLogin/CancelTicket";
	}

	@RequestMapping("/pay")
	public String images() {
		return "pay";
	}

	// creating order for payment
	@PostMapping("/create_order")
	@ResponseBody
	public String CreatingOrder(@RequestBody Map<String, Object> data, Principal principal) throws Exception {

		// System.out.println("hey order function ex..");
		System.out.println(data);

		int amt = Integer.parseInt(data.get("amount").toString());

		RazorpayClient Client = new RazorpayClient("rzp_test_g6NFrWWAVFQhBh", "VWz9mfua6b3BgjN15w0rNY14");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amt * 100); // amount in the smallest currency unit
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "txn_235425");

		// creating new order

		Order order = Client.Orders.create(orderRequest);
		System.out.println(order);

		// save the order in database

		MyBooking myBooking = new MyBooking();

		myBooking.setAmount(order.get("amount") + "");
		myBooking.setBookingId(order.get("id"));
		myBooking.setPaymentId(null);
		myBooking.setStatus("Failed");
		myBooking.setUser(this.userRepo.findByEmailId(principal.getName()));

		myBooking.setReceipt(order.get("receipt"));

		this.myBookingRepo.save(myBooking);

		return order.toString();

	}

	@PostMapping("/update_booking")
	public ResponseEntity<?> updateBooking(@RequestBody Map<String, Object> data) {

		MyBooking myBooking = this.myBookingRepo.findByBookingId(data.get("order_id").toString());

		myBooking.setPaymentId(data.get("payment_id").toString());
		myBooking.setStatus(data.get("status").toString());

		this.myBookingRepo.save(myBooking);

		System.out.println(data);

		Map<String, String> response = Collections.singletonMap("msg", "updated");

		return ResponseEntity.ok(response);
	}

	@GetMapping("/jasperpdf/export/{id}")
	public void createPDF(HttpServletResponse response, Principal principal, @PathVariable("id") Long id)
			throws IOException, JRException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=BookingTicket_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
//
//		      String userName = principal.getName();
//			  User user = this.userRepo.findByEmailId(userName);
//				    		  
//			    List<Passenger> passengerList = new ArrayList<>();
//			    Passenger passenger = this.passengerRepo.findById(user.getId());
//			    passengerList.add(passenger);
//            
//            
//            
//            
//			List<MyBooking> bookTicketList =this.myBookingRepo.findMyBookingByPaymentId(user.getId());   
//			  
		service.exportJasperReport(response, principal.getName(), id);
	}

}
