package com.bicjo.rmi.jmeter;

import java.io.File;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import com.bicjo.rmi.jmeter.sampler.RemoteSampler;

public class JMeterDemo {

	// private static final Logger LOG =
	// LogManager.getFormatterLogger(JMeterDemo.class);

	public static void main(String[] args) {
		// JMeter Engine
		StandardJMeterEngine jMeterEngine = new StandardJMeterEngine();

		// JMeter initialization
		JMeterUtils.loadJMeterProperties(getPropertiesAbsolutePath());
		JMeterUtils.initLogging();
		JMeterUtils.initLocale();

		// JMeter Test Plan
		HashTree testPlanTree = new HashTree();

		// My Remote Sampler
		RemoteSampler remoteSampler = new RemoteSampler();

		// Loop Sampler
		LoopController loopController = new LoopController();
		loopController.setLoops(10);
		loopController.addTestElement(remoteSampler);
		loopController.setFirst(Boolean.TRUE);
		loopController.initialize();

		// Thread Group
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setNumThreads(1);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController(loopController);

		// Test Plan
		TestPlan testPlan = new TestPlan("My JMeter Script to Remote Server");
		testPlanTree.add("testPlan", testPlan);
		testPlanTree.add("loopController", loopController);
		testPlanTree.add("threadGroup", threadGroup);
		testPlanTree.add("remoteSampler", remoteSampler);

		// Run Test Plan
		jMeterEngine.configure(testPlanTree);
		jMeterEngine.run();
	}

	public static String getPropertiesAbsolutePath() {
		File resourcesDirectory = new File("src/main/resources/jmeter.properties");
		return resourcesDirectory.getAbsolutePath();
	}

}
