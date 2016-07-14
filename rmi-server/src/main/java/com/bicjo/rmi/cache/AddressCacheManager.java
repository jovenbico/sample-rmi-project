package com.bicjo.rmi.cache;

import java.util.List;

import com.bicjo.rmi.cache.specification.ObjectSpecification;
import com.bicjo.rmi.module.object.Address;

public interface AddressCacheManager {

	void add(Address address);

	List<Address> query(ObjectSpecification<Address> spec);

	int size();

	void clear();

}
