package com.bicjo.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.bicjo.rmi.module.AddressService;

public class ApplicationServerTest extends Assert {

	private final Logger LOG = LogManager.getLogger(getClass());

	@Test
	public void lookup_test() {

		AddressService addressService = null;

		try {
			addressService = (AddressService) Naming.lookup("rmi://localhost:1066/addressService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			LOG.error("Can't connect to rmi-server");
			LOG.error(e.getMessage(), e);
		}

		LOG.debug("AddressService {}", addressService);
		assertNotNull(addressService);

	}

}
