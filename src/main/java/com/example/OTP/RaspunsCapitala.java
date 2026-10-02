package com.example.OTP;

import com.fasterxml.jackson.annotation.JsonProperty;

/* Dc este necesar ? Pt ca doar obiectele rezultate din clase, sunt convertite automat in json ! */
public class RaspunsCapitala {

	@JsonProperty("cv") // Putem pune ce nume dorim la proietatea data.cv (din fetch)
	private String capitala;

    public String getMesaj() {
        return capitala;
    }

    public void setMesaj(String abc) {
        this.capitala = abc;
    }
    
}
