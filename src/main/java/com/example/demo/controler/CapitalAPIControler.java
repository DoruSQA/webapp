package com.example.demo.controler;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OTP.Postobjmapping;
import com.example.OTP.RaspunsCapitala;
import com.example.demo.service.CapitalService;



//@CrossOrigin(origins = "http://localhost:9090")

//This class automatically trigger when a Rest APi request is sent !!! @RestController
//When u send a request with this endpoint "/motan" this class will be triggered
@RestController


public class CapitalAPIControler 
{
	@Autowired
	public CapitalService cs;
	
	@GetMapping("/capitala/{tara}")
	public ResponseEntity<RaspunsCapitala> receiveCapitalByPathParam(@PathVariable("tara") String tara_primita)
	{
		RaspunsCapitala r = new RaspunsCapitala();
		r.setMesaj("Am primit tara, dar nu am gasit capitala");
		
		return new ResponseEntity<>(r, HttpStatus.ACCEPTED); 
		
	} // site http://localhost:9090/homepage.html
	  //endpoint: http://localhost:9090/capitala/Romania
	
	@GetMapping("/capitala")
	public ResponseEntity<RaspunsCapitala> receiveCapitalByQueryParam(@RequestParam("t2ra") String tara_primita) 
	{
		RaspunsCapitala r =cs.getCapitalByQueryParam(tara_primita);
		if (!r.getMesaj().equals("Nu exista in Stuctura de date de pe server"))
			return new ResponseEntity<>(r, HttpStatus.ACCEPTED); 
		else
			return new ResponseEntity<>(r, HttpStatus.OK); 

	    
	} // site: http://localhost:9090/homepage.html
	  //  GET http://localhost:9090/capitala?t2ra=Romania

	@GetMapping("/all/capitals")
	public Map<String, String> retreiveAll()
	{
		return cs.retreiveAll();
		
	} // site http://localhost:9090/homepage.html 
     //http://localhost:9090/all/capitals

	
	
	@PostMapping("/add/entity")
	public String addRecord(@RequestBody Postobjmapping z)
	{
		boolean status = cs.addInMap(z);
		if (status)
			return "Success";
		else
			return "Record already present";
	}
	
	
	
	
	
	@GetMapping("/motan/{ipathid}")
	public String test2(@PathVariable("ipathid") String path)

	{
		return "parametru path preluat este " + path;
	}// GET http://localhost:9090/motan/abc
	
	@GetMapping("/motan/motanel")
	public String test3(@RequestParam(value="pagina") int pag,@RequestParam(value="lim") int pag2)
	{
		return "query parameters sunt pagina: " +pag +"si lim: " +pag2;
	}/// GET http://localhost:9090/motan/motanel?pagina=1&lim=10
	
	//http://localhost:9090/motanel.html ->pt src/main/resources/static motanel.html


}
