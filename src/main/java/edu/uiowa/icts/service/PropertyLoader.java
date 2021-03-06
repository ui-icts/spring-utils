package edu.uiowa.icts.service;

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

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Looks up a resource named 'name' in the classpath. The resource must map
 * to a file with .properties extension. The name is assumed to be absolute
 * and can use either "/" or "." for package segment separation with an
 * optional leading "/" and optional ".properties" suffix. Thus, the
 * following names refer to the same resource:
 * <pre>
 * some.pkg.Resource
 * some.pkg.Resource.properties
 * some/pkg/Resource
 * some/pkg/Resource.properties
 * /some/pkg/Resource
 * /some/pkg/Resource.properties
 * </pre>
 *
 * @author schappetj
 * @version $Id: $
 */
public abstract class PropertyLoader {
	
	private static final boolean THROW_ON_LOAD_FAILURE = true;
	private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
	private static final String SUFFIX = ".properties";
	
	/**
	 * <p>loadProperties.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param loader a {@link java.lang.ClassLoader} object.
	 * @return a {@link java.util.Properties} object.
	 */
	public static Properties loadProperties( String name, ClassLoader loader ) {
		
		if ( name == null ){
			throw new IllegalArgumentException( "null input: name" );
		}

		if ( name.startsWith( "/" ) ){
			name = name.substring( 1 );
		}

		if ( name.endsWith( SUFFIX ) ){
			name = name.substring( 0, name.length() - SUFFIX.length() );
		}

		Properties result = null;

		InputStream in = null;
		try {
			if ( loader == null ) {
				loader = ClassLoader.getSystemClassLoader();
			}

			if ( LOAD_AS_RESOURCE_BUNDLE ) {
				name = name.replace( '/', '.' );
				
				// Throws MissingResourceException on lookup failures:
				final ResourceBundle rb = ResourceBundle.getBundle( name, Locale.getDefault(), loader );

				result = new Properties();
				for ( Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements(); ) {
					final String key = (String) keys.nextElement();
					final String value = rb.getString( key );
					result.put( key, value );
				}
			} else {
				name = name.replace( '.', '/' );

				if ( !name.endsWith( SUFFIX ) ) {
					name = name.concat( SUFFIX );
				}

				// Returns null on lookup failures:
				in = loader.getResourceAsStream( name );
				if ( in != null ) {
					result = new Properties();
					result.load( in ); // Can throw IOException
				}
			}
		} catch ( Exception e ) {
			result = null;
		} finally {
			if ( in != null ) {
				try {
					in.close();
				} catch ( Throwable ignore ) {

				}
			}
		}

		if ( THROW_ON_LOAD_FAILURE && ( result == null ) ) {
			throw new IllegalArgumentException( "could not load [" + name + "]" + " as " + ( LOAD_AS_RESOURCE_BUNDLE ? "a resource bundle" : "a classloader resource" ) );
		}

		return result;
	}

	/**
	 * A convenience overload of {@link #loadProperties(String, ClassLoader)}
	 * that uses the current thread's context classloader.
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link java.util.Properties} object.
	 */
	public static Properties loadProperties( final String name ) {
		return loadProperties( name, Thread.currentThread().getContextClassLoader() );
	}

} // End of class
