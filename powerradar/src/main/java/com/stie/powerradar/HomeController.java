package com.stie.powerradar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public String get(){
		System.err.println("data");
		return "how are you";
	}
	
	
	@RequestMapping(value="/home",method=RequestMethod.POST)
	@ResponseBody
	public HttpStatus post(@RequestBody String data){
		System.err.println(data);
		return HttpStatus.OK;
	}
	
}
