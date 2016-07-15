package com.bicjo.rmi.jmeter.sampler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bicjo.rmi.module.AddressService;

public class RemoteSampler extends AbstractSampler {

	private static final long serialVersionUID = -983742104224545101L;

	private final Logger LOG = LogManager.getLogger(getClass());

	@Override
	public SampleResult sample(Entry arg0) {
		LOG.info("remote sampler");

		try {

			/*
			 * TODO refactor
			 * 
			 * 1. create object pooling 2. create sample reporting
			 */
			AddressService addressService = (AddressService) Naming.lookup("rmi://localhost:1066/addressService");
			addressService.addAddress("a", "b", "c");

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			LOG.error("Can't connect to rmi-server");
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

}