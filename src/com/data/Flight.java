package com.data;

public class Flight {
	private int flightid;
	private String name;
	private int seats;
	
	public Flight(int flightid, String name, int seats) {
		super();
		this.flightid = flightid;
		this.name = name;
		this.seats = seats;
	}

	public int getFlightid() {
		return flightid;
	}

	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	
	
}
