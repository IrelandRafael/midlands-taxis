package com.ait.dto;

import java.io.Serializable;

public class DriverDto implements Serializable {
	
	private static final long serialVersionUID = 8976568028192142443L;

	private int driverId;
	private String fullName;
	
	public DriverDto(int driverId, String fullName) {
		this.driverId = driverId;
		this.fullName = fullName;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
