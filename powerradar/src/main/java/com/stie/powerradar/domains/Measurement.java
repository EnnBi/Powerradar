package com.stie.powerradar.domains;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Measurement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Device_Id")
	@JsonProperty("device_id")
	Device device;
	
	@JsonProperty("measurement_time(UTC)")
	@Column(name="Measurement_Time")
	LocalDateTime measurement_time;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Site_Id")
	@JsonProperty("site_id")
	Site site;
	
	@JsonProperty("resolution(minutes)")
	@Column(name="Resolution")
	int resolution;
	
	@JsonProperty("power(W)")
	@Column(name="Power")
	Double power;
	
	@JsonProperty("energy(Wh)")
	@Column(name="Energy")
	Double energy;
	
	@JsonProperty("voltage(V)")
	@Column(name="Voltage")
	Double voltage;
	
	@Column(name="Power_Factor")
	Double power_factor;

	@Column(name="Current")
	@JsonProperty("current(A)")
	Double current;
	
	public Measurement() {
		super();
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public LocalDateTime getMeasurement_time() {
		return measurement_time;
	}

	public void setMeasurement_time(LocalDateTime measurement_time) {
		this.measurement_time = measurement_time;
	}

	public int getResolution() {
		return resolution;
	}

	public Double getPower() {
		return power;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}


	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Double getPower_factor() {
		return power_factor;
	}

	public void setPower_factor(Double power_factor) {
		this.power_factor = power_factor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getCurrent() {
		return current;
	}

	public void setCurrent(Double current) {
		this.current = current;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public void setPower(Double power) {
		this.power = power;
	}
	
	
	
	
	
}
