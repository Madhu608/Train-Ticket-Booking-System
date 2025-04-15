package com.IndianRailway.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="traindetails")
public class TrainDetails extends EntityClass {
	
	private String  trainName;
	private String  schedule;
	private String  trainFrom;
	private String  departureDate;
	private String  trainTo;
	private String  trainToTime;
	private String  trainFromTime;
	private long    numberOfStops;
	private long    ticketPrice;
	
	
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getTrainFrom() {
		return trainFrom;
	}
	public void setTrainFrom(String trainFrom) {
		this.trainFrom = trainFrom;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getTrainTo() {
		return trainTo;
	}
	public void setTrainTo(String trainTo) {
		this.trainTo = trainTo;
	}
	public String getTrainToTime() {
		return trainToTime;
	}
	public void setTrainToTime(String trainToTime) {
		this.trainToTime = trainToTime;
	}
	public String getTrainFromTime() {
		return trainFromTime;
	}
	public void setTrainFromTime(String trainFromTime) {
		this.trainFromTime = trainFromTime;
	}
	public long getNumberOfStops() {
		return numberOfStops;
	}
	public void setNumberOfStops(long numberOfStops) {
		this.numberOfStops = numberOfStops;
	}
	public long getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
