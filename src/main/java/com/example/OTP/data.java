package com.example.OTP;

import java.util.HashMap;
import java.util.Map;

public class data 
{
	Map<String, String> capitals = new HashMap<>();
	
	public data() // la prima initializare a obiectului
	{
		capitals.put("Romania", "Bucuresti");
	    capitals.put("Spania", "Madrid");
	    capitals.put("Italia", "Roma");
	    capitals.put("Franta", "Paris");
	    capitals.put("Marea Britanie", "Londra");
	    capitals.put("Germania", "Berlin");
	    capitals.put("Portugalia", "Lisabona");
	    capitals.put("Olanda", "Amsterdam");
	    capitals.put("Belgia", "Bruxelles");
	    capitals.put("Austria", "Viena");
	    capitals.put("Grecia", "Atena");
	}
	public Map<String, String> returnMap()
	{
		return capitals;
	}
	public String findRecord(String tara)
	{
		return capitals.get(tara);
	}
	public boolean addRecord(String tara, String capitala)
	{
		if (capitals.containsKey(tara))
			return false;
		else
		{
			capitals.put(tara,capitala);
			return true;
		}
	}
}
