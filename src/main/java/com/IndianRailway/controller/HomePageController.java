package com.IndianRailway.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IndianRailway.entities.Message;
import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.User;
import com.IndianRailway.repository.PassengerRepository;
import com.IndianRailway.repository.UserRepository;
import com.IndianRailway.services.JReportService;

import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
@Controller
public class HomePageController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PassengerRepository passengerRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JReportService service;

//    @Autowired
//    private JReportService service;

	// http://localhost:8080/

//	@RequestMapping("")
//	public String view(){
//	return "index";
//	}

	@RequestMapping("")
	public String webpage() {
		return "webpage";
	}

	@RequestMapping("web")
	public String view() {
		return "webpage";
	}

//	@RequestMapping("web1")
//	public String imag() {
//		return "web1";
//	}

//	@RequestMapping(path = "/index")
//	public String HomePage() {
//		return "index";
//	}

//	@RequestMapping(path = "/login")
//	public String LoginPage() {
//		return "/User&Admin";
//	}

	@GetMapping(path = "/signin")
	public String UserLogig() {
		return "/Login";
	}

	@RequestMapping("/User")
	public String RegistrattionPage() {
		return "UserRegistration";
	}

	@PostMapping("/SaveReg")
	public String processRegistration(@ModelAttribute User user, HttpSession session) {
		boolean f = this.userRepo.existsByEmailId(user.getEmailId());
		if (f) {
			session.setAttribute("message", new Message("Email Alredy Exist !!", "danger"));
			System.out.println("Email Already Exist !!");
		} else {
			session.setAttribute("message", new Message("User Data Saved Successfully !!", "success"));

//			user.setPassword(passwordEncoder.encode(user.getPassword()));
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encode = encoder.encode(user.getPassword());
			user.setPassword(encode);
//			
			user.setRole("ROLE_USER");
			this.userRepo.save(user);
		}
		return "UserRegistration";

	}

//	@RequestMapping(path = "/about")
//	public String AboutUsPage() {
//		return "aboutus";
//	}

	// new about
	@RequestMapping(path = "/about")
	public String AboutUsPage() {
		return "aboutweb";
	}

	@RequestMapping(path = "/contact")
	public String ContactUsPage() {
		return "Contactweb";
	}

//    @GetMapping("/getAddress")
//    public List<Passenger> getAddress() {
//        List<Passenger> address = (List<Passenger>) passengerRepo.findAll();
//        return address;
//    }

//    @GetMapping("/jasperpdf/export")
//    public void createPDF(HttpServletResponse response) throws IOException, JRException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//  
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=BookingTicket_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//  
//        service.exportJasperReport(response);
//    }

//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootJReportApp.class, args);
//    }

//  @GetMapping("/getAddress")
//  public List<Passenger> getAddress(Passenger passenger ,Principal principal) {
//	  String userName = principal.getName();
//	  User user = this.userRepo.findByEmailId(userName);	  
//    List<Passenger> address =this.passengerRepo.findPassengerByEmailId(user.getId());   
//      return address;
//  }	

//  @GetMapping("/jasperpdf/export")
//  public void createPDF(HttpServletResponse response,Principal principal) throws IOException, JRException {
//	  String userName = principal.getName();
//	  User user = this.userRepo.findByEmailId(userName);	  
//      List<Passenger> address =this.passengerRepo.findPassengerByEmailId(user.getId());  
//	  
//	  
//	  
//	  response.setContentType("application/pdf");
//      DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//      String currentDateTime = dateFormatter.format(new Date());
//
//      String headerKey = "Content-Disposition";
//      String headerValue = "attachment; filename=BookingTicket_" + currentDateTime + ".pdf";
//      response.setHeader(headerKey, headerValue);
//
//      service.exportJasperReport(response, principal);
//  }

}
