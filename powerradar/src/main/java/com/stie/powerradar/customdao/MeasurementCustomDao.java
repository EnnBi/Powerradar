package com.stie.powerradar.customdao;

import java.util.List;

import com.stie.powerradar.domains.Search;
import com.stie.powerradar.projections.MeasurementDTO;

public interface MeasurementCustomDao {
	List<MeasurementDTO> getData(Search search);
	
}
