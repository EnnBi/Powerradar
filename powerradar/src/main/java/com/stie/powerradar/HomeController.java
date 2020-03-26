package com.stie.powerradar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	private AmazonClient amazonClient;
	
	
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
}
