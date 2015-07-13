package edu.uiowa.icts.exception;

/*
 * #%L
 * spring-utils
 * %%
 * Copyright (C) 2010 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * <p>MappingNotFoundException class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class MappingNotFoundException extends Exception {

	private static final long serialVersionUID = 2890579965738044986L;
	
	private String mapping;

	// ----------------------------------------------
	// Default constructor - initializes instance variable to unknown

	/**
	 * <p>Constructor for MappingNotFoundException.</p>
	 */
	public MappingNotFoundException() {
		super(); // call superclass constructor
		mapping = "unknown";
	}

	// -----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance
	// variable.

	/**
	 * <p>Constructor for MappingNotFoundException.</p>
	 *
	 * @param mapping a {@link java.lang.String} object.
	 */
	public MappingNotFoundException(String mapping) {
		super(mapping); // call super class constructor
		this.mapping = mapping; // save message
	}

	// ------------------------------------------------
	// public method, callable by exception catcher. It returns the error
	// message.
	/**
	 * <p>getError.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getError() {
		return mapping;
	}
}
