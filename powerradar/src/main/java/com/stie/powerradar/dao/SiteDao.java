package com.stie.powerradar.dao;

import org.springframework.data.repository.CrudRepository;
import com.stie.powerradar.domains.Site;

public interface SiteDao extends CrudRepository<Site, Long> {
		
}
