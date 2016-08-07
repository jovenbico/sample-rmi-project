package com.bicjo.rmi.module;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.bicjo.rmi.jmeter.sampler.RamdomAddressData;
import com.bicjo.rmi.module.object.Address;

public class AddressServiceTest extends Assert {

	private final Logger LOG = LogManager.getLogger(getClass());

	private AddressService getAddressService() {
		AddressService addressService = null;

		try {
			addressService = (AddressService) Naming.lookup("rmi://localhost:1066/addressService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			LOG.error(e.getMessage(), e);
		}

		assertNotNull(addressService);

		return addressService;
	}

	@Test
	public void addAddress_main() {

		AddressService addressService = getAddressService();

		try {

			addressService.addAddress(RamdomAddressData.randomStreet(), RamdomAddressData.randomCity(),
					RamdomAddressData.randomZip());

		} catch (RemoteException e) {
			fail(e.getLocalizedMessage());
		}

	}

	@Test
	public void searchStreet_main() {

		AddressService addressService = getAddressService();

		try {

			String street = RamdomAddressData.randomStreet();
			List<Address> addresses = addressService.searchStreet(street);
			LOG.debug("find: '" + street + "' results: " + addresses.size());

		} catch (RemoteException e) {
			fail(e.getLocalizedMessage());
		}

	}

}
