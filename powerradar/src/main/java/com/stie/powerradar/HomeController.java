package com.stie.powerradar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stie.powerradar.dao.DeviceDao;
import com.stie.powerradar.dao.MeasurementDao;
import com.stie.powerradar.dao.PanelDao;
import com.stie.powerradar.dao.SiteDao;
import com.stie.powerradar.dao.ZoneDao;
import com.stie.powerradar.domains.Device;
import com.stie.powerradar.domains.MeasurementsWrapper;
import com.stie.powerradar.domains.Panel;
import com.stie.powerradar.domains.Search;
import com.stie.powerradar.domains.Site;
import com.stie.powerradar.domains.Sites;
import com.stie.powerradar.domains.Zone;
import com.stie.powerradar.projections.IdAndName;
import com.stie.powerradar.projections.MeasurementDTO;


@RestController
@CrossOrigin 
public class HomeController {

	@Autowired
	private AmazonClient amazonClient;
	
	@Autowired
	SiteDao siteDao;
	
	@Autowired
	DeviceDao deviceDao;
	
	@Autowired 
	PanelDao panelDao;
	
	@Autowired 
	ZoneDao zoneDao;
	@Autowired
	MeasurementDao measurementDao;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public String get(){
		System.err.println("data");
		return "Welcome Power Radar Data Fetch Api";
	}
	
	
	@RequestMapping(value="/catalog",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus post(@RequestBody String data) throws IOException{
		File file = new File("Catalog");
		FileWriter fileWriter= new FileWriter(file,true);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
		amazonClient.uploadFile(file, "Catalog");
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/measurement",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus measurement(@RequestBody String data) throws IOException{
		File file = new File("Measurement");
		FileWriter fileWriter= new FileWriter(file,true);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
		amazonClient.uploadFile(file, "Measurement");
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/pulsemetermeasurement",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus pulsemetermeasurement(@RequestBody String data) throws IOException{
		File file = new File("Pulse_Meter_Measurement");
		FileWriter fileWriter= new FileWriter(file,true);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
		amazonClient.uploadFile(file, "Pulse_Meter_Measurement");
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/pan42measurement",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus pan42measurement(@RequestBody String data) throws IOException{
		File file = new File("Pan_42_Measurement");
		FileWriter fileWriter= new FileWriter(file,true);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
		amazonClient.uploadFile(file, "Pan_42_Measurement");
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/sites",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus saveSite(@RequestBody Sites sites) throws JsonProcessingException, IOException{

		for(Site site:sites.getSites()){
			site.getDevices().forEach(device->{
				device.setSite(site);
				device.setPanel(setPanel(device.getPanel_id(),device.getPanel_name()));
				device.setZone(setZone(device.getZone_id(), device.getZone_name()));
				recursiveDevice(device,site);
			});
			siteDao.save(site);
		}

		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/measurements",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus  saveMeasurement(@RequestBody MeasurementsWrapper measurements) throws JsonProcessingException, IOException{
		measurements.getMeasurements().forEach(m->{
			//add time zone
			measurementDao.save(m);
		});
		return HttpStatus.OK; 
	}
	
	
	@RequestMapping("/realtimecost")
	public ResponseEntity<?> getData() throws ParseException{
		
		LocalTime time = LocalTime.of(00, 00,00);
		LocalDateTime startDate = LocalDateTime.of(LocalDate.now(),time);
		LocalDateTime endDate = LocalDateTime.of(LocalDate.now().plusDays(1),time);
		Search search = new Search(50341,"Heating and Cooling",startDate,endDate);
		List<MeasurementDTO> dtos = measurementDao.getData(search);
		return new ResponseEntity<List<MeasurementDTO>>(dtos,HttpStatus.OK);
	}
	
	@RequestMapping("/realpowerconsumption")
	public ResponseEntity<?> powerConsumption() {

		LocalDateTime localDateTime =  measurementDao.findMaxTime();
		Long value = measurementDao.findPowerConsumption(localDateTime, 50341);
		
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		map.put("powerconsumption", value);
		map.put("devicegroup",measurementDao.findPowerConsumptionGroupByDeviceCategory(localDateTime, 50341));
		return new ResponseEntity<Map>(map,HttpStatus.OK);
	}
	
	@RequestMapping("/sites")
	public ResponseEntity<?> getSites(){
		return new ResponseEntity<Iterable<Site>>( siteDao.findAll(),HttpStatus.OK);
	}
	 
	@RequestMapping("/categories/{site}")
	public ResponseEntity<?> getDeviceCategoriesOfSite(@PathVariable("site") long id){
		return new ResponseEntity<Iterable<String>>(deviceDao.findDeviceCategoryBySite_Id(id),HttpStatus.OK);
	}
	
	@RequestMapping("/types/{category}")
	public ResponseEntity<?> getDeviceTypesOnCategory(@PathVariable("category") String category){
		return new ResponseEntity<Iterable<String>>(deviceDao.findDeviceTypeByDeviceCategory(category),HttpStatus.OK);
	}
	
	@RequestMapping("/devices/{type}")
	public ResponseEntity<?> getDevicesOnDeviceType(@PathVariable("name") String type){
		return new ResponseEntity<Iterable<IdAndName>>(deviceDao.findByDeviceType(type),HttpStatus.OK);
	}
	
	public void recursiveDevice(Device device,Site site){
		if(device.getDevices().size()>0){ 
			device.getDevices().forEach(device2->{
				device2.setParentDevice(device);
				device2.setSite(site);
				device2.setPanel(setPanel(device.getPanel_id(),device.getPanel_name()));
				device2.setZone(setZone(device.getZone_id(), device.getZone_name()));
				recursiveDevice(device2,site);
			});
		}

	}
	public Panel setPanel(long id,String name){
		 Panel panel= panelDao.findById(id).orElse(null);
			if(panel!=null)
				return panel;
			else{
				return panelDao.save(new Panel(id,name));
			}
	}
	
	public Zone setZone(long id,String name){
		Zone zone= zoneDao.findById(id).orElse(null);
			if(zone!=null)
				return zone;
			else{
				return zoneDao.save(new Zone(id,name));
			}
	}
	
}
