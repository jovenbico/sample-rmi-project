package com.bicjo.rmi.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bicjo.rmi.cache.specification.ObjectSpecification;
import com.bicjo.rmi.module.object.Address;

public class SimpleAddressCacheManager implements AddressCacheManager {

	private List<Address> addresses = Collections.synchronizedList(new ArrayList<>());

	public static SimpleAddressCacheManager INSTANCE = new SimpleAddressCacheManager();

	private SimpleAddressCacheManager() {
	}

	@Override
	public void add(Address address) {
		addresses.add(address);
	}

	@Override
	public List<Address> query(ObjectSpecification<Address> spec) {
		List<Address> results = new ArrayList<>();

		for (Address address : addresses) {
			if (spec.match(address)) {
				results.add(address);
			}
		}

		return results;
	}

	@Override
	public int size() {
		return addresses.size();
	}

	@Override
	public void clear() {
		addresses.clear();
	}

}
