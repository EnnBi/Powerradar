package com.stie.powerradar.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.stie.powerradar.domains.Device;
import com.stie.powerradar.projections.IdAndName;

public interface DeviceDao extends CrudRepository<Device, Long>{

	Set<String> findDeviceCategoryBySite_Id(long id);
	Set<String> findDeviceTypeByDeviceCategory(String category);
	List<IdAndName> findByDeviceType(String type);
}
