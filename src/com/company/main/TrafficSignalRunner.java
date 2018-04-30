package com.company.main;

import com.company.bean.Road;
import com.company.bean.TrafficProperties;
import com.company.factory.ServiceFactory;
import com.company.service.TrafficSignalService;

/**
 * This class is the runner class for the program. It takes seconds as input and
 * produces a String as output.
 * 
 * @author Taha
 *
 */
public class TrafficSignalRunner {

	/**
	 * Performs the main operations of the program. Returns a String which has
	 * traffic status upto 'n' seconds.
	 * 
	 * @param seconds
	 * @return String
	 */
	public static String getOutput(int seconds) {

		/*
		 * As per the problem, there are two roads (Snell & Weaver). First the objects
		 * of these roads are created and initialized with current count and time since
		 * current signal as 0.
		 */
		Road snellRoad = new Road("snellRoad", "N");
		snellRoad.setTimeSinceCurrentSignal(0);

		Road weaverRoad = new Road("weaverRoad", "E");
		weaverRoad.setTimeSinceCurrentSignal(0);

		/*
		 * This indicator signifies which road has green signal at the start. Per the
		 * problem, snellRoad has green signal at start. So, it should be 'Y'. The
		 * property is loaded via external config file 'traffic.properties'.
		 */
		String snellRoadStartGreenStatus = "Y";

		try {
			snellRoadStartGreenStatus = TrafficProperties.getInstance().getProperty("snellRoadStartGreenStatus");
		} catch (Exception e) {
			//Log the exception here
			/*
			 * If there is an exception loading the property file, then the default value of
			 * 'Y' is assigned to snellRoadStartGreenStatus.
			 */
			snellRoadStartGreenStatus = "Y";
		}

		/*
		 * The current signal is set based on initial road status.
		 */
		if ("Y".equals(snellRoadStartGreenStatus)) {
			snellRoad.setCurrentSignal(true);
			weaverRoad.setCurrentSignal(false);
		} else {
			snellRoad.setCurrentSignal(false);
			weaverRoad.setCurrentSignal(true);
		}

		/*
		 * A loop of 20 seconds is created as per the problem ask. Inside the loop, for
		 * one iteration, status of each road is fetched for corresponding second.
		 * 
		 * The updated Road objects are then again passed to TrafficSignalService method
		 * to fetch the status for the next second.
		 */

		TrafficSignalService trafficSignalService = (TrafficSignalService) ServiceFactory
				.getInstance(TrafficSignalService.class);

		StringBuilder outputBuilder = new StringBuilder();

		String output = null;
		
		for (int i = 0; i <= seconds; i++) {

			snellRoad = trafficSignalService.getCurrentTrafficPattern(snellRoad, i);
			weaverRoad = trafficSignalService.getCurrentTrafficPattern(weaverRoad, i);

			outputBuilder.append(i);
			outputBuilder.append(": ");
			outputBuilder.append("N".equals(snellRoad.getRoadDirection()) ? "N = " : "E = ");
			outputBuilder.append(snellRoad.getStatusQueue().size());
			outputBuilder.append("; ");
			outputBuilder.append("N".equals(snellRoad.getRoadDirection()) ? "S = " : "W = ");
			outputBuilder.append(snellRoad.getStatusQueue().size());
			outputBuilder.append("; ");
			outputBuilder.append("N".equals(weaverRoad.getRoadDirection()) ? "N = " : "E = ");
			outputBuilder.append(weaverRoad.getStatusQueue().size());
			outputBuilder.append("; ");
			outputBuilder.append("N".equals(weaverRoad.getRoadDirection()) ? "S = " : "W = ");
			outputBuilder.append(weaverRoad.getStatusQueue().size());
			outputBuilder.append(System.getProperty("line.separator"));

		}

		output = outputBuilder.toString();

		return output.trim();
	}
}
