package com.example.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.OTP.Postobjmapping;
import com.example.OTP.RaspunsCapitala;
import com.example.OTP.data;

@Service 
public class CapitalService 
{
	data d = new data();
    
	public RaspunsCapitala getCapitalByQueryParam(String tara)
	{
		
        RaspunsCapitala r = new RaspunsCapitala();
        if (d.findRecord(tara) != null)
        {
        	r.setMesaj(d.findRecord(tara));
        	return r;
        }
        else
        {
        	r.setMesaj("Nu exista in Stuctura de date de pe server");
        	return r;
        }
        	
	}
	

	public Map<String, String> retreiveAll()
	{
	
        return d.returnMap();
	}
	
	public boolean addInMap(Postobjmapping z)
	{
		String tara=z.getTara();
		String capitala =z.getCapitala();
		
			return d.addRecord(tara, capitala);
	}
	
}
