package com.company.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.company.main.TrafficSignalRunner;

/**
 * JUnit Test Class.
 * Modify the 'seconds' variable for different outputs.
 * 
 * The method testOutput reads the output from ProgramOutput.txt file and 
 * compares with the one returned via the program.
 * 
 * @author Taha
 *
 */
public class JUnitTestRunner {

	@Test
	public void testOutput() {
		
		/*
		 * Change this value. Keep this upto 20 as the output file currently has the
		 * output till 20 seconds. Modify the ProgramOutput.txt file to further expand.
		 */
		int seconds = 20;
		
		int count = 0;

		/*
		 * Reading the output from the file. 
		 */
		InputStream input = this.getClass().getResourceAsStream("/ProgramOutput.txt");

		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		String line = null;

		StringBuilder responseData = new StringBuilder();
		try {
			/*
			 * Each line is read one by one till the end of file reached
			 * or count exceeds seconds.
			 */
			while (count <= seconds && (line = in.readLine()) != null) {
				count++;
				responseData.append(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * Compare using JUnit.
		 */
		assertEquals(responseData.toString().trim(), TrafficSignalRunner.getOutput(seconds));
	}
}
