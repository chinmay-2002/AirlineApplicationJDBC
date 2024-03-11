package com.data;

public class Passenger {
	private long adhar;
	private String name;
	private String dob;
	private String sex;
	private String address;
	private int flightid;
	
	public Passenger(long adhar, String name, String dob, String sex, String address, int flightid) {
		super();
		this.adhar = adhar;
		this.name = name;
		this.dob = dob;
		this.sex = sex;
		this.address = address;
		this.flightid = flightid;
	}

	public long getAdhar() {
		return adhar;
	}

	public void setAdhar(long adhar) {
		this.adhar = adhar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getFlightid() {
		return flightid;
	}

	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	
	
	
}
