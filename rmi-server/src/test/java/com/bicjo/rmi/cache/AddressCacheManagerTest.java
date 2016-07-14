package com.bicjo.rmi.cache;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.bicjo.rmi.cache.specification.AddressByStreet;
import com.bicjo.rmi.module.object.Address;

public class AddressCacheManagerTest extends Assert {

	private final Logger LOG = LogManager.getLogger(AddressCacheManagerTest.class);
	private AddressCacheManager cacheManager = SimpleAddressCacheManager.INSTANCE;

	private void setupData() {
		cacheManager.add(new Address("a", "b", "c"));
		cacheManager.add(new Address("d", "e", "f"));
		cacheManager.add(new Address("g", "h", "i"));
	}

	@Test
	public void add_address_test() {
		setupData();

		LOG.debug("cacheManager.size {}", 3);
		assertEquals(3, cacheManager.size());

		cacheManager.clear();
	}

	@Test
	public void query_addresses_test() {
		setupData();

		AddressByStreet addressByStreet = new AddressByStreet("a");
		List<Address> addresses = cacheManager.query(addressByStreet);

		LOG.debug("addresses.size {}", 1);
		assertEquals(1, addresses.size());

		cacheManager.clear();
	}
}
