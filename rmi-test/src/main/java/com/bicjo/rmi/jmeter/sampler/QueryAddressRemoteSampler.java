package com.bicjo.rmi.jmeter.sampler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bicjo.rmi.module.AddressService;
import com.bicjo.rmi.module.object.Address;

public class QueryAddressRemoteSampler extends AbstractSampler {

	private static final long serialVersionUID = -4317638259508904980L;

	private final Logger LOG = LogManager.getLogger(getClass());

	@Override
	public SampleResult sample(Entry arg0) {
		SampleResult sampleResult = new SampleResult();
		sampleResult.setSampleLabel("query-address");
		sampleResult.sampleStart();
		try {

			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			/*
			 * TODO refactor
			 * 
			 * 1. create object pooling
			 * 
			 * 2. create sample reporting
			 * 
			 * 3. create fixture address test-data
			 */
			AddressService addressService = (AddressService) Naming.lookup("rmi://localhost:1066/addressService");
			String street = RamdomAddressData.randomStreet();
			List<Address> addresses = addressService.searchStreet(street);

			stopWatch.stop();
			LOG.info("address '" + street + "' size " + addresses.size() + ", time-taken " + stopWatch.toString());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			LOG.error("Can't connect to rmi-server");
			LOG.error(e.getMessage(), e);
		} finally {
			sampleResult.setSuccessful(Boolean.TRUE);
			sampleResult.sampleEnd();
		}

		return sampleResult;
	}

}
