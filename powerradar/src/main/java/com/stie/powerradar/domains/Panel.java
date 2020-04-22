package com.stie.powerradar.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Panel {

	@Id
	long panel_id;
	
	@Column(name="Name")
	String panel_name;

	
	
	public Panel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Panel(long panel_id, String panel_name) {
		super();
		this.panel_id = panel_id;
		this.panel_name = panel_name;
	}

	public long getPanel_id() {
		return panel_id;
	}

	public void setPanel_id(long panel_id) {
		this.panel_id = panel_id;
	}

	public String getPanel_name() {
		return panel_name;
	}

	public void setPanel_name(String panel_name) {
		this.panel_name = panel_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (panel_id ^ (panel_id >>> 32));
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
		Panel other = (Panel) obj;
		if (panel_id != other.panel_id)
			return false;
		return true;
	}	
	
	
}
