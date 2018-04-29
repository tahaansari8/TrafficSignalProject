package com.company.main;

/**
 * Main class for Traffic Signal Problem.
 * This class will create two road objects and
 * print their statuses (number of cars are intersection)
 * at every second till 20 second.
 * 
 * @author Taha
 *
 */
public class TrafficSignalMain {

	public static void main(String[] args) {
		
		/*
		 * Time till output needs to be printed.
		 */
		int seconds = 20;
		
		System.out.println(TrafficSignalRunner.getOutput(seconds));
	}
}
