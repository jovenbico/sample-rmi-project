package com.bicjo.rmi.jmeter.sampler;

import org.apache.commons.lang3.RandomStringUtils;

public class RamdomAddressData {

	public static String randomStreet() {
		String street = "street" + RandomStringUtils.random(1, 'A', 'B', 'C');
		return street;
	}

	public static String randomCity() {
		String city = "city" + RandomStringUtils.random(1, 'A', 'B', 'C');
		return city;
	}

	public static String randomZip() {
		String zip = "zip" + RandomStringUtils.random(1, 'A', 'B', 'C');
		return zip;
	}
}
