package com.ait.dto;

import java.util.Date;

public class DriverJourneyDto {
	
	private String driverFirstName;
	private String driverLastName;
	private String bookingNo;
	private String journeyStatus;
	private Date journeyStartedTime;
	private Date journeyCompletedTime;
	public DriverJourneyDto(String driverFullName, String driverLastName, String bookingNo, String journeyStatus,
			Date journeyStartedTime, Date journeyCompletedTime) {
		super();
		this.driverFirstName = driverFullName;
		this.driverLastName = driverLastName;
		this.bookingNo = bookingNo;
		this.journeyStatus = journeyStatus;
		this.journeyStartedTime = journeyStartedTime;
		this.journeyCompletedTime = journeyCompletedTime;
	}
	public String getDriverFirstName() {
		return driverFirstName;
	}
	public void setDriverFirstName(String driverFullName) {
		this.driverFirstName = driverFullName;
	}
	public String getDriverLastName() {
		return driverLastName;
	}
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public String getJourneyStatus() {
		return journeyStatus;
	}
	public void setJourneyStatus(String journeyStatus) {
		this.journeyStatus = journeyStatus;
	}
	public Date getJourneyStartedTime() {
		return journeyStartedTime;
	}
	public void setJourneyStartedTime(Date journeyStartedTime) {
		this.journeyStartedTime = journeyStartedTime;
	}
	public Date getJourneyCompletedTime() {
		return journeyCompletedTime;
	}
	public void setJourneyCompletedTime(Date journeyCompletedTime) {
		this.journeyCompletedTime = journeyCompletedTime;
	}
}
