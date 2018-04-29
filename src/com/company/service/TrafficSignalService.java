package com.company.service;

import com.company.bean.Road;
import com.company.factory.Service;

/**
 * Interface class for Traffic Signal Service.
 * 
 * @author Taha
 *
 */
public interface TrafficSignalService extends Service{
	
	Road getCurrentTrafficPattern(final Road road, final int second);
}
