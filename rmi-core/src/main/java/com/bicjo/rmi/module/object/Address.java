package com.bicjo.rmi.module.object;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = -5146099277903638259L;

	private String street;
	private String city;
	private String zip;

	public Address(String street, String city, String zip) {
		this.street = street;
		this.city = city;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
