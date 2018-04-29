package com.company.service;

import com.company.bean.Road;
import com.company.bean.TrafficProperties;

/**
 * This is a service class which handles operations regarding the traffic
 * signal. This is a singleton class so that only object of it resides in JVM.
 * 
 * @author Taha
 *
 */
public class TrafficSignalServiceImpl implements TrafficSignalService {

	private static final TrafficSignalService instance = new TrafficSignalServiceImpl();

	private TrafficSignalServiceImpl() {
		init();
	}

	private static int timeTakenForIntersectionCarToLeave;
	private static int greenLightDuration;
	private static int redLightDuration;

	public static TrafficSignalService getInstance() {
		return instance;
	}

	/**
	 * This method loads initial configurations.
	 */
	private void init() {
		try {
			timeTakenForIntersectionCarToLeave = TrafficProperties.getInstance()
					.getIntProperty("timeTakenForIntersectionCarToLeave");
			greenLightDuration = TrafficProperties.getInstance().getIntProperty("greenLightDuration");
			redLightDuration = TrafficProperties.getInstance().getIntProperty("redLightDuration");
		} catch (Exception e) {
			// Log the exception here
			/*
			 * If there is ans exception loading the property file, then the default values are
			 * assigned.
			 */
			timeTakenForIntersectionCarToLeave = 2;
			greenLightDuration = 3;
			redLightDuration = 1;
		}
	}

	/**
	 * This method gets the current status of the road at intersection. Basically,
	 * what it does is work on existing road object and updated the status queue of
	 * it.
	 * 
	 * The logic is to add a car (which is an integer 1) to the status queue at
	 * every second and remove from queue based on the problem statement.
	 * 
	 * @param Road
	 * @param second
	 * @return Road
	 * 
	 */
	public Road getCurrentTrafficPattern(final Road road, final int second) {
		// Road object must be not null.
		if (road != null) {
			/*
			 * This check of second is done as at the start the roads at intersection would
			 * be 0, so no calculation is needed.
			 */
			if (second != 0) {
				// Car (integer 1) is added to the queue.
				road.getStatusQueue().add(1);
				// Time elapses is incremented by 1 seconnd.
				road.setTimeSinceCurrentSignal(road.getTimeSinceCurrentSignal() + 1);
				if (road.isCurrentSignal()) {
					// If the initial State is Green, the car will be removed from status queue.
					// This is done to handle the stating behavior as it is different
					// per the problem statement.
					if (road.isInitialState() || (road.getTimeSinceCurrentSignal() <= greenLightDuration
							&& road.getTimeSinceCurrentSignal() >= timeTakenForIntersectionCarToLeave)) {
						// Car will be removed from queue as the signal is green.
						road.getStatusQueue().remove();
					}
					if (road.getTimeSinceCurrentSignal() == greenLightDuration) {
						// Flip the signal to red.
						road.setCurrentSignal(false);
						road.setTimeSinceCurrentSignal(0);
					}
				} else {
					if (road.getTimeSinceCurrentSignal() == greenLightDuration + redLightDuration) {
						// Flip the signal to green.
						road.setCurrentSignal(true);
						road.setTimeSinceCurrentSignal(0);
					}
				}
			}
		}
		return road;
	}

}
