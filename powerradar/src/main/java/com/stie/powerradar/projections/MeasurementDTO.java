package com.stie.powerradar.projections;

import java.time.LocalDateTime;

public class MeasurementDTO {

	LocalDateTime dateTime;
	
	Double power;

	String deviceCategory;
	
	
	public MeasurementDTO( Double power,LocalDateTime dateTime) {
		super();
		this.dateTime = dateTime;
		this.power = power;
	}
	
	public MeasurementDTO(Double power,String deviceCategory){
		super();
		this.deviceCategory = deviceCategory;
		this.power = power;
	} 

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}
	
	
	
}
