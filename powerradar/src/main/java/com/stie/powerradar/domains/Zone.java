package com.stie.powerradar.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Zone {

	@Id
	long zone_id;
	
	
	@Column(name="Name")
	String zone_name;


	

	public Zone() {
		super();
	}


	public Zone(long zone_id, String zone_name) {
		super();
		this.zone_id = zone_id;
		this.zone_name = zone_name;
	}


	public long getZone_id() {
		return zone_id;
	}


	public void setZone_id(long zone_id) {
		this.zone_id = zone_id;
	}


	public String getZone_name() {
		return zone_name;
	}


	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (zone_id ^ (zone_id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zone other = (Zone) obj;
		if (zone_id != other.zone_id)
			return false;
		return true;
	}	
	
}
