package edu.uiowa.icts.criterion;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.engine.spi.TypedValue;

/**
 * @author rrlorent
 * @date March 20, 2014
 */
public class IntegerLike implements Criterion {

	private static final long serialVersionUID = -6821722080754628376L;

	private String propertyName;
	private String value;

	public IntegerLike( String propertyName, String value ) {
		this.propertyName = propertyName;
		this.value = value;
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
		return " lower( cast( " + columns[0] + " as varchar ) ) like ? ";
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