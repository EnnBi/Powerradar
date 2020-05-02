package com.stie.powerradar.domains;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Search {

	
	long siteId;
	
	String device_category;
	
	LocalDateTime startDate;

	LocalDateTime endDate;
	
	String device_type;
	
	String period;
	
	
	
	public Search(long siteId, String device_category,LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.siteId = siteId;
		this.device_category = device_category;
		this.startDate=startDate;
		this.endDate=endDate;
	}

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public String getDevice_category() {
		return device_category;
	}

	public void setDevice_category(String device_category) {
		this.device_category = device_category;
	}


	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}


	
}
