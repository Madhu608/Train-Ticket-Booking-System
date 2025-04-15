package com.IndianRailway.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IndianRailway.entities.User;
import com.IndianRailway.repository.UserRepository;
import com.IndianRailway.util.Emailservice;

@Controller
public class ForgotController {

	Random random = new Random(1000);

	@Autowired
	private Emailservice emailService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPassword;

	@RequestMapping("/forgot")
	public String openEmailFrom() {
		return "ForgotPassword/forgotPassword";

	}

	@PostMapping("/send-otp")
	public String SendOTP(@RequestParam("emailId") String emailId, HttpSession session) {
		System.out.println("Email" + emailId);

		// generating otp 4 digit

		int otp = random.nextInt(9999);

		System.out.println("OTP" + otp);

		// write code for send otp to email...

		String subject = "OTP From SCM";

		String message = "" + "<div style='border:1px solid #e2e2e2; paddind:20px'>" + "<h1>" + "OTP is " + "<b>" + otp
				+ "</b>" + "</h1>" + "<div>";

		String to = emailId;

		boolean flag = this.emailService.SendEmail(subject, message, to);

		if (flag) {
			session.setAttribute("message", "Check your email id !!");
			return "ForgotPassword/forgotPassword";

		} else {
			session.setAttribute("myotp", otp);
			session.setAttribute("emailId", emailId);
			return "ForgotPassword/VerifyOtp";

		}
	}
	// verify-otp

	@PostMapping("/verify-otp")
	public String VerifyOtp(@RequestParam("otp") int otp, HttpSession session) {

		int myotp = (int) session.getAttribute("myotp");
		String emailId = (String) session.getAttribute("emailId");

		if (myotp == otp) {
			// password change form
			User user = this.userRepo.findByEmailId(emailId);
			if (user == null) {

				// send error message
				session.setAttribute("message", "User does not exits with this email  !!");
				return "ForgotPassword/forgotPassword";

			} else {

			}
			// send change password form
			return "ForgotPassword/PasswordChangeForm";
		} else {

			session.setAttribute("message", "You have entered wrong otp..");
			return "ForgotPassword/VerifyOtp";

		}

	}

	// Change Password

	@PostMapping("/ChangePassword")
	public String ChangePassword(@RequestParam("newpassword") String newpassword, HttpSession session) {

		String emailId = (String) session.getAttribute("emailId");
		User user = this.userRepo.findByEmailId(emailId);
		user.setPassword(this.bCryptPassword.encode(newpassword));
		this.userRepo.save(user);

		return "redirect:/signin?change=password changed successfully...";

	}

}
