package com.IndianRailway.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="bookings")
public class MyBooking {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private Long myBookingId;
	
	 private String bookingId;
	 
	 private String amount;
	 
	 private String  receipt;
	 
	 private String  status;
	 
	 private String paymentId;
	 
	 
	 @ManyToOne
	 private User user;


	public Long getMyBookingId() {
		return myBookingId;
	}


	public void setMyBookingId(Long myBookingId) {
		this.myBookingId = myBookingId;
	}


	public String getBookingId() {
		return bookingId;
	}


	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getReceipt() {
		return receipt;
	}


	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	 
	 
	 

	 
	 
	 
	 
}
