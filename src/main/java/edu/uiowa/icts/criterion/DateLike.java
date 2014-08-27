package edu.uiowa.icts.criterion;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.TypedValue;

/**
 * @author rrlorent
 * @date March 20, 2014
 */
public class DateLike implements Criterion {

	private static final long serialVersionUID = -6772056160232172164L;

	private final String SQL_SERVER = "org.hibernate.dialect.SQLServerDialect";
	private final String POSTGRES = "org.hibernate.dialect.PostgreSQLDialect";
	// private final String HSQL = "org.hibernate.dialect.HSQLDialect";
	// private final String MYSQL = "org.hibernate.dialect.MySQL5Dialect";

	private String propertyName;
	private String value;
	private Dialect dialect;

	public DateLike( String propertyName, String value, Dialect dialect ) {
		super();
		this.propertyName = propertyName;
		this.value = value;
		this.dialect = dialect;
	}

	/**
	 * (non-Javadoc)
	 * @see org.hibernate.criterion.Criterion#toSqlString(org.hibernate.Criteria, org.hibernate.criterion.CriteriaQuery)
	 */
	@Override
	public String toSqlString( Criteria criteria, CriteriaQuery criteriaQuery ) throws HibernateException {
		String[] columns = criteriaQuery.getColumnsUsingProjection( criteria, propertyName );
		if ( columns.length != 1 ) {
			throw new HibernateException( "like may only be used with single-column properties" );
		}
		if ( SQL_SERVER.equalsIgnoreCase( dialect.toString() ) ) {
			return " cast( convert( varchar( 10 ), " + columns[0] + ", 101 ) as varchar( 10 ) ) like lower( ? ) ";
		} else if ( POSTGRES.equalsIgnoreCase( dialect.toString() ) ) {
			return " cast( " + columns[0] + " as varchar ) like lower( ? ) ";
		} else {
			throw new HibernateException( "dialect not supported [" + dialect.toString() + "}" );
		}

	}

	/**
	 * (non-Javadoc)
	 * @see org.hibernate.criterion.Criterion#getTypedValues(org.hibernate.Criteria, org.hibernate.criterion.CriteriaQuery)
	 */
	@Override
	public TypedValue[] getTypedValues( Criteria criteria, CriteriaQuery criteriaQuery ) throws HibernateException {
		return new TypedValue[] { new TypedValue( new org.hibernate.type.StringType(), MatchMode.START.toMatchString( value.toLowerCase() ) ) };
	}

}