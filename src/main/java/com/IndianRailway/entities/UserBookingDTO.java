package com.IndianRailway.entities;




public class UserBookingDTO {
	
    private Passenger  passenger;
    private MyBooking bookTicket;
    
    
    
    
    
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public MyBooking getBookTicket() {
		return bookTicket;
	}
	public void setBookTicket(MyBooking bookTicket) {
		this.bookTicket = bookTicket;
	}
    
    
 
    
}
