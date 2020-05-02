package com.stie.powerradar.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.stie.powerradar.customdao.MeasurementCustomDao;
import com.stie.powerradar.domains.Measurement;
import com.stie.powerradar.domains.Search;
import com.stie.powerradar.projections.MeasurementDTO;

@Repository
@Transactional
public class MeasurementDaoImpl implements MeasurementCustomDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<MeasurementDTO> getData(Search search) {//CAST(m.measurement_time as time)FUNCTION('ROUND', (SUM(m.power),2))
		//Map<Date,Double> map = new LinkedHashMap<Date, Double>();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MeasurementDTO> cq = cb.createQuery(MeasurementDTO.class);
		Root<Measurement> root = cq.from(Measurement.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(search.getSiteId()>0){
			predicates.add(cb.equal(root.get("site").get("id"),search.getSiteId()));
		}		
		
		if(search.getDevice_category() !=null)
			if(!search.getDevice_category().isEmpty()){
				predicates.add(cb.equal(root.get("device").get("deviceCategory"),search.getDevice_category()));
			}
		
		if(search.getDevice_type() !=null)
			if(!search.getDevice_type().isEmpty()){
				predicates.add(cb.equal(root.get("device").get("deviceType"),search.getDevice_type()));
			}
		predicates.add(cb.greaterThanOrEqualTo(root.get("measurement_time"),search.getStartDate()));
		predicates.add(cb.lessThan(root.get("measurement_time"),search.getEndDate()));

		
		cq.where(cb.and(predicates.toArray(new Predicate[0])));	
		cq.groupBy(root.get("measurement_time"));
		cq.select(cb.construct(MeasurementDTO.class,cb.sum(root.get("power")),root.get("measurement_time")));
		
		

		return em.createQuery(cq).getResultList();  
		

	} 

	 

}
