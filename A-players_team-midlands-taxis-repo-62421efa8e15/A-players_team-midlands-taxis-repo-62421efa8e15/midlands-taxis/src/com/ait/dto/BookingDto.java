package com.ait.dto;

import java.util.Date;

public class BookingDto {
	
	private int bookingId;
	private String bookingNo;
	private String status;
	private String pickupLocation;
	private Date pickupTime;
	private String dropLocation;
	private int driverId;
	private String driverName;
	
	public boolean isPending() {
		return status.equals("PENDING");
	}
	
	public boolean isStarted() {
		return status.equals("STARTED");
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@Override
	public String toString() {
		return "BookingDto [bookingId=" + bookingId + ", bookingNo=" + bookingNo + ", status=" + status
				+ ", pickupLocation=" + pickupLocation + ", pickupTime=" + pickupTime + ", dropLocation=" + dropLocation
				+ ", driverName=" + driverName + "]";
	}

}
