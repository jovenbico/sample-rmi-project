package com.bicjo.rmi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.java.sampler.JUnitSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class JMeterJunitDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// JMeter Engine
		StandardJMeterEngine jMeterEngine = new StandardJMeterEngine();

		// JMeter initialization
		JMeterUtils.setJMeterHome(getJMeterHomeAbsolutePath());
		JMeterUtils.loadJMeterProperties(getPropertiesAbsolutePath());
		JMeterUtils.initLogging();// you can comment this line out to see extra
									// log messages of i.e. DEBUG level
		JMeterUtils.initLocale();

		// JMeter Test Plan
		HashTree testPlanTree = new HashTree();

		// My JUnit Sampler
		JUnitSampler addRemoteSampler = new JUnitSampler();
		addRemoteSampler.setJunit4(Boolean.TRUE);
		addRemoteSampler.setClassname("com.bicjo.rmi.module.AddressServiceTest");
		addRemoteSampler.setMethod("addAddress_main");
		JUnitSampler queryRemoteSampler = new JUnitSampler();
		queryRemoteSampler.setJunit4(Boolean.TRUE);
		queryRemoteSampler.setClassname("com.bicjo.rmi.module.AddressServiceTest");
		queryRemoteSampler.setMethod("searchStreet_main");

		// Loop Sampler
		LoopController loopController = new LoopController();
		loopController.setLoops(100);
		loopController.setFirst(Boolean.TRUE);
		loopController.initialize();

		// Thread Group
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setNumThreads(100);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController(loopController);

		// Test Plan
		TestPlan testPlan = new TestPlan("My JMeter Script to Remote Server");
		testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

		// Construct Test Plan from previously initialized elements
		testPlanTree.add(testPlan);
		HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
		threadGroupHashTree.add(addRemoteSampler);
		threadGroupHashTree.add(queryRemoteSampler);

		// save generated test plan to JMeter's .jmx file format
		SaveService.saveTree(testPlanTree, new FileOutputStream(getResultAbsolutePath() + "/junit_example.jmx"));

		// add Summarizer output to get test progress in stdout like:
		// summary = 2 in 1.3s = 1.5/s Avg: 631 Min: 290 Max: 973 Err: 0 (0.00%)
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
		}

		String logFile = getResultAbsolutePath() + "/junit_example.csv";
		ResultCollector resultCollector = new ResultCollector(summer);
		resultCollector.setFilename(logFile);
		testPlanTree.add(testPlanTree.getArray()[0], resultCollector);

		// Run Test Plan
		jMeterEngine.configure(testPlanTree);
		jMeterEngine.run();

	}

	private static String getResultAbsolutePath() {
		File file = new File("results");
		return file.getAbsolutePath();
	}

	private static String getJMeterHomeAbsolutePath() {
		File file = new File("src/main/resources");
		return file.getAbsolutePath();
	}

	private static String getPropertiesAbsolutePath() {
		File file = new File("src/main/resources/bin/jmeter.properties");
		return file.getAbsolutePath();
	}

}
