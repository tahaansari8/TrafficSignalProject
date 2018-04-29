package com.company.bean;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This is a simple POJO class for Road object.
 * The road object will have several attributes
 * including the status queue.
 * 
 * @author Taha
 *
 */
public class Road {

	/*
	 * Road Name. eg: snell, weaver, etc.
	 */
	private String roadName;
	
	/*
	 * Road Direction.
	 * Only two values 'N' and 'E' are used.
	 * 
	 * N - North
	 * E - East
	 */
	private String roadDirection;
	
	/*
	 * Current Signal of Road.
	 * true = Green
	 * false = Red
	 */
	private boolean currentSignal;
	
	/*
	 * Time since current signal (red or green) has elapsed.
	 */
	private int timeSinceCurrentSignal;
	
	/*
	 * Status Queue of the road.
	 * Keeps the Integer as a representation of a Car.
	 * Value 1 is added for each car entering the road.
	 * Whenever road object is created, the status queue 
	 * is initialized to a new (empty) linked list.
	 * 
	 */
	private Queue<Integer> statusQueue;
	
	/*
	 * This flag maintains the initial signal 
	 * state of the road.
	 * 
	 * True if at start is Green, else false.
	 * Will be set to false, once turned red and will stay false (via setCurrentSignal method)
	 */
	private boolean initialState = true;
	
	public Road(String roadName, String roadDirection) {
		this.roadName = roadName;
		this.roadDirection = roadDirection;
		this.statusQueue = new LinkedList<Integer>();
	}

	public String getRoadName() {
		return roadName;
	}

	public String getRoadDirection() {
		return roadDirection;
	}

	public boolean isCurrentSignal() {
		return currentSignal;
	}

	public void setCurrentSignal(boolean currentSignal) {
		this.currentSignal = currentSignal;
		//Flip initial state to false.
		if(!currentSignal && this.initialState) {
			this.initialState = false;
		}
	}

	public int getTimeSinceCurrentSignal() {
		return timeSinceCurrentSignal;
	}

	public void setTimeSinceCurrentSignal(int timeSinceCurrentSignal) {
		this.timeSinceCurrentSignal = timeSinceCurrentSignal;
	}

	public Queue<Integer> getStatusQueue() {
		return statusQueue;
	}

	public boolean isInitialState() {
		return initialState;
	}

}
