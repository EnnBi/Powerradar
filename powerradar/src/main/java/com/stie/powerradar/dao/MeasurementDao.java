package com.stie.powerradar.dao;

import org.springframework.data.repository.CrudRepository;

import com.stie.powerradar.domains.Measurement;

public interface MeasurementDao extends CrudRepository<Measurement, Long> {

}
