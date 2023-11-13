package com.IndianRailway.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="master")
public class Master extends EntityClass {
	
	private long  numberofStop;

	public long getNumberofStop() {
		return numberofStop;
	}

	public void setNumberofStop(long numberofStop) {
		this.numberofStop = numberofStop;
	}
	
	
	

}
