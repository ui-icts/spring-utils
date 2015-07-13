package edu.uiowa.icts.test;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;

import edu.uiowa.icts.service.PropertyLoader;

/**
 * <p>Abstract BaseIctsTestCase class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public abstract class BaseIctsTestCase extends DBTestCase {

	protected final Log log = LogFactory.getLog( getClass().getName() );

	protected Properties properties;

	private String dataset = null;
	private boolean useAltData = false;
	private boolean buildSchema = false;
	private String schemaSqlFile = null;

	/**
	 * <p>Constructor for BaseIctsTestCase.</p>
	 */
	public BaseIctsTestCase() {
		log.debug( "setting database properties" );

		properties = PropertyLoader.loadProperties( "test.properties" );

		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:mem:testdb" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	/**
	 * <p>setUp.</p>
	 *
	 * @throws java.lang.Exception if any.
	 */
	@Before
	public void setUp() throws Exception {
		log.debug( "=== starting " + getName() + " =============================" );
		log.debug( getConnection().getConfig().getProperty( "http://www.dbunit.org/properties/datatypeFactory" ) );

		if ( isBuildSchema() ) {
			File f = new File( getSchemaSqlFile() );

			Class.forName( System.getProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS ) );

			Properties props = new Properties();
			props.setProperty( "user", System.getProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME ) );
			props.setProperty( "password", System.getProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD ) );

			Connection conn = DriverManager.getConnection( System.getProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL ), props );

			assertNotNull( conn );

			StringWriter output = new StringWriter();
			FileInputStream fis = new FileInputStream( f );
			try {
				IOUtils.copy( fis, output );
			} finally {
				fis.close();
			}

			Statement stmt = conn.createStatement();
			stmt.execute( "SET DATABASE SQL SYNTAX PGS TRUE" );
			stmt = conn.createStatement();
			stmt.executeUpdate( output.toString() );
			stmt.close();
		}

		super.setUp();
	}

	/**
	 * <p>tearDown.</p>
	 *
	 * @throws java.lang.Exception if any.
	 */
	@After
	public void tearDown() throws Exception {
		log.debug( "=== ending " + getName() + " =============================\n" );
		super.tearDown();
	}

	/** {@inheritDoc} */
	@Override
	protected IDataSet getDataSet() throws Exception {
		
		String file_name = getDatasetName();
		if ( !isUseAltData() ) {
			file_name = properties.getProperty( "dataset.file.name", "dataset.xml" );
		}

		log.debug( "loading " + file_name + " as data set" );

		InputStream in = this.getClass().getClassLoader().getResourceAsStream( file_name );
		
		FlatXmlDataSetBuilder fxdsb = new FlatXmlDataSetBuilder();
		fxdsb.setCaseSensitiveTableNames( false );
		fxdsb.setColumnSensing( true );

		IDataSet datset;
		try {
			datset = fxdsb.build( in );
		} finally {
			in.close();
		}

		return datset;
	}

	/** {@inheritDoc} */
	protected void setUpDatabaseConfig( DatabaseConfig config ) {
		log.debug( "setting database config" );
		config.setProperty( "http://www.dbunit.org/properties/datatypeFactory", new MsSqlDataTypeFactory() );
		config.setProperty( DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true );
		config.setProperty( DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, false );

	}

	/**
	 * <p>Setter for the field <code>dataset</code>.</p>
	 *
	 * @param dataset a {@link java.lang.String} object.
	 */
	public void setDataset( String dataset ) {
		this.dataset = dataset;
		setUseAltData( true );
	}

	/**
	 * <p>getDatasetName.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDatasetName() {
		if ( dataset == null ) {
			setDataset( "dataset.xml" );
		}
		return dataset;
	}

	/**
	 * <p>Setter for the field <code>useAltData</code>.</p>
	 *
	 * @param useAltData a boolean.
	 */
	public void setUseAltData( boolean useAltData ) {
		this.useAltData = useAltData;
	}

	/**
	 * <p>isUseAltData.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isUseAltData() {
		return useAltData;
	}

	/**
	 * <p>isBuildSchema.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isBuildSchema() {
		return buildSchema;
	}

	/**
	 * <p>Setter for the field <code>buildSchema</code>.</p>
	 *
	 * @param buildSchema a boolean.
	 */
	public void setBuildSchema( boolean buildSchema ) {
		this.buildSchema = buildSchema;
	}

	/**
	 * <p>Getter for the field <code>schemaSqlFile</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getSchemaSqlFile() {
		return schemaSqlFile;
	}

	/**
	 * <p>Setter for the field <code>schemaSqlFile</code>.</p>
	 *
	 * @param schemaSqlFile a {@link java.lang.String} object.
	 */
	public void setSchemaSqlFile( String schemaSqlFile ) {
		this.schemaSqlFile = schemaSqlFile;
	}

}
