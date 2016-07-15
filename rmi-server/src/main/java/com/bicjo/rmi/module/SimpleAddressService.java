package com.bicjo.rmi.module;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bicjo.rmi.cache.AddressCacheManager;
import com.bicjo.rmi.cache.SimpleAddressCacheManager;
import com.bicjo.rmi.module.object.Address;

public class SimpleAddressService extends UnicastRemoteObject implements AddressService {

	public SimpleAddressService() throws RemoteException {
	}

	private static final long serialVersionUID = 8213272722089817659L;

	private final Logger LOG = LogManager.getLogger(SimpleAddressService.class);

	@Override
	public void addAddress(String street, String city, String zip) throws RemoteException {
		LOG.debug("Address [{}] [{}] [{}]", street, city, zip);
		AddressCacheManager addressCacheManager = SimpleAddressCacheManager.INSTANCE;
		addressCacheManager.add(new Address(street, city, zip));
		LOG.debug(addressCacheManager.size());
	}

	@Override
	public List<Address> searchStreet(String street) throws RemoteException {
		LOG.debug("Search {}", street);
		return null;
	}

}
