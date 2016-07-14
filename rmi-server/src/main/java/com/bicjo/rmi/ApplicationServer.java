package com.bicjo.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bicjo.rmi.module.AddressService;
import com.bicjo.rmi.module.SimpleAddressService;

public class ApplicationServer {

	private final Logger LOG = LogManager.getLogger(ApplicationServer.class);

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ApplicationServer _me = new ApplicationServer();
	}

	public ApplicationServer() {
		try {
			AddressService addressService = new SimpleAddressService();

			Registry registry = LocateRegistry.createRegistry(1066);
			registry.rebind("addressService", addressService);

			LOG.info("Application Server Started...");
		} catch (RemoteException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
