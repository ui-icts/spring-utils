/**
 * Institute for Clinical and Translation Science (ICTS)
 * University of Iowa
 */
package edu.uiowa.icts.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bkusenda
 * @since January 5, 2011
 */
public class HsqlCreateSchema {

	private static final Log log = LogFactory.getLog( HsqlCreateSchema.class );
	private List<String> schemas;
	private String url = "jdbc:hsqldb:file:testdb";
	private boolean test = false;

	public void create() {

		String output = "";
		for ( String schema : schemas ) {
			output += schema + " ";
		}

		log.debug( "Request for schema(s): " + output );

		try {
			Class.forName( "org.hsqldb.jdbcDriver" );
		} catch ( ClassNotFoundException e1 ) {
			log.error( "error calling Class.forName( \"org.hsqldb.jdbcDriver\" );", e1 );
		}

		Connection conn = null;
		try {

			conn = DriverManager.getConnection( url, "sa", "" );
			
			for ( String schema : schemas ) {
				log.debug( "Checking to see if " + schema + " exist" );
				Statement checkst = conn.createStatement();
				String checkexp = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + schema.toUpperCase() + "'";
				ResultSet rs = checkst.executeQuery( checkexp );
				boolean exists = false;
				while ( rs.next() ) {
					if ( rs.getString( 1 ) != null && rs.getString( 1 ).equalsIgnoreCase( schema ) ) {
						exists = true;
						continue;
					}
				}
				checkst.close();

				if ( exists ){
					log.debug( "Schema " + schema + " exists...not creating" );
				} else {
					log.debug( "Creating schema :" + schema );
					Statement st = conn.createStatement();
					String expression = "CREATE SCHEMA " + schema + " AUTHORIZATION DBA";
					st.execute( expression ); // run the query
					st.close();
				}
			}

			if ( test ) {
				Statement st = conn.createStatement(); // statements
				st.execute( "SHUTDOWN" ); // run the query
				st.close();
			}
		} catch ( Exception e ) {
			log.error( "Exception occured", e );
		} finally {
			try {
				if ( conn != null && !conn.isClosed() ) {
					conn.close();
				}
			} catch ( SQLException e ) {
				log.error( "error closing connection", e );
			}
		}

	}

	public List<String> getSchemas() {
		return schemas;
	}

	public void setSchemas( List<String> schemas ) {
		this.schemas = schemas;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest( boolean test ) {
		this.test = test;
	}

}