package com.bicjo.rmi.cache.specification;

import com.bicjo.rmi.module.object.Address;

public class AddressByStreet implements ObjectSpecification<Address> {

	private final String street;

	public AddressByStreet(String street) {
		this.street = street;
	}

	@Override
	public boolean match(Address object) {
		return object.getStreet().equals(street);
	}

}
