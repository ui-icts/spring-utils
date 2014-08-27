/**
 * Institute for Clinical and Translation Science (ICTS)
 * University of Iowa
 */
package edu.uiowa.icts.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * @author bkusenda
 * @since January 5, 2011
 */
public class HsqlAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean {

	private static final Log log = LogFactory.getLog( HsqlAnnotationSessionFactoryBean.class );

	private List<String> schemas;
	private String url = "jdbc:hsqldb:file:testdb";

	public void create() {
		log.debug( "Creating schemas :" + schemas.size() );

		try {
			Class.forName( "org.hsqldb.jdbcDriver" );
		} catch ( ClassNotFoundException e1 ) {
			log.error( "error calling Class.forName( \"org.hsqldb.jdbcDriver\" );", e1 );
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection( url, "sa", "" );
			for ( String schema : schemas ) {
				Statement st = conn.createStatement();
				String expression = "CREATE SCHEMA " + schema + " AUTHORIZATION DBA";
				st.executeUpdate( expression );
				st.close();
			}
		} catch ( Exception e ) {
			log.error( "error creating schema", e );
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

}