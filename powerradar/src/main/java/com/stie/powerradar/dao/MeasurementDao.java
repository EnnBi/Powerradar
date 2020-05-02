package com.stie.powerradar.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stie.powerradar.customdao.MeasurementCustomDao;
import com.stie.powerradar.domains.Measurement;
import com.stie.powerradar.projections.MeasurementDTO;

public interface MeasurementDao extends CrudRepository<Measurement, Long>,MeasurementCustomDao {

	@Query("Select ROUND(SUM(m.power),0) from Measurement m where m.measurement_time  = :time and m.site.id = :site")
	Long findPowerConsumption(@Param("time") LocalDateTime time,@Param("site") long site);
	
	@Query("Select new com.stie.powerradar.projections.MeasurementDTO(ROUND(SUM(m.power),0),m.device.deviceCategory) from Measurement m where m.measurement_time  = :time and m.site.id = :site GROUP BY m.device.deviceCategory")
	List<MeasurementDTO> findPowerConsumptionGroupByDeviceCategory(@Param("time") LocalDateTime time,@Param("site") long site);
	
	@Query("Select Max(m.measurement_time) from Measurement m")
	LocalDateTime findMaxTime();
}
  