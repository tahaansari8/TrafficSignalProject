package com.company.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This factory class creates service implementation objects using java
 * reflection.
 * All the service classes must use this factory class to create instance.
 * 
 * @author Taha
 *
 */
public class ServiceFactory {

	/**
	 * This method returns the ServiceImpl object using reflection.
	 * The service Impl has getInstance() public method which is
	 * invoked to create an object of it.
	 * 
	 * @param serviceInterfaceName
	 * @return Service
	 */
	public static Service getInstance(Class serviceInterfaceName) {
		Class classObject = null;
		if (serviceInterfaceName != null) {
			try {
				//Impl is added to interafce name as all the implementation
				//Service classes name must end with Impl.
				classObject = Class.forName(serviceInterfaceName.getName() + "Impl");
				Method method = classObject.getMethod("getInstance", new Class[0]);

				Object object = null;
				object = method.invoke(null, new Object[0]);

				if (object instanceof Service) {
					return (Service) object;
				}
			} catch (ClassNotFoundException e) {
				// Log Exception.
				e.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// Log Exception.
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// Log Exception.
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				// Log Exception.
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// Log Exception.
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// Log Exception.
				e.printStackTrace();
			}
		}

		return null;
	}

}
