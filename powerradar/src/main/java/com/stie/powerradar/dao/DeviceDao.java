package com.stie.powerradar.dao;

import org.springframework.data.repository.CrudRepository;

import com.stie.powerradar.domains.Device;

public interface DeviceDao extends CrudRepository<Device, Long>{

}
