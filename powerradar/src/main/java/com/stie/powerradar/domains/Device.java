package com.stie.powerradar.domains;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Device{

	@Id
	long id;
	
	@Column(name="Name")
	String name;
	
	@Column(name="type")
	String type;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="Panel_Id")
	Panel panel; 

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="Zone_Id")
	Zone zone; 
	
	@Transient
	long panel_id;
	
	@Transient
	String panel_name;
	
	@Transient
	long site_id;
	
	@Transient
	String site_name;
	
	@Transient
	long zone_id;
	
	@Transient
	String zone_name;
	
	String device_category;
	
	String device_type;
	
	String electrical_type;
	
	String description;
	@Transient
	long parent_id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Site_Id")
	Site site;
	
	@JsonBackReference
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="parentDevice")
    private Device parentDevice;
   
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="parentDevice",cascade=CascadeType.ALL)
	private Set<Device> devices = new HashSet<Device>();

	

	public Device() {
		super();
	}

	public Device(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}



	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getElectrical_type() {
		return electrical_type;
	}

	public void setElectrical_type(String electrical_type) {
		this.electrical_type = electrical_type;
	}

	public String getPanel_name() {
		return panel_name;
	}

	public void setPanel_name(String panel_name) {
		this.panel_name = panel_name;
	}



	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public String getDevice_category() {
		return device_category;
	}

	public void setDevice_category(String device_category) {
		this.device_category = device_category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Device getParentDevice() {
		return parentDevice;
	}

	public void setParentDevice(Device parentDevice) {
		this.parentDevice = parentDevice;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}
		
	public long getPanel_id() {
		return panel_id;
	}

	public void setPanel_id(long panel_id) {
		this.panel_id = panel_id;
	}

	public long getSite_id() {
		return site_id;
	}

	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}

	public long getZone_id() {
		return zone_id;
	}

	public void setZone_id(long zone_id) {
		this.zone_id = zone_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Device other = (Device) obj;
		if (id != other.id)
			return false;
		return true;
	}

    
}
