package com.bicjo.rmi.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class LogTest {

	private final Logger LOG = LogManager.getLogger(LogTest.class);

	@Test
	public void first_test() {

		LOG.debug("...DEBUG...");
		LOG.info("...INFO...");
		LOG.warn("...WARN...");

	}

}
