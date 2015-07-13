package edu.uiowa.icts.criterion;

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

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.engine.spi.TypedValue;

/**
 * <p>CastAsVarcharLike class.</p>
 *
 * @author rrlorent
 * @date March 20, 2014
 * @version $Id: $
 */
public class CastAsVarcharLike implements Criterion {

	private static final long serialVersionUID = -6821722080754628376L;

	private String propertyName;
	private String value;

	/**
	 * <p>Constructor for CastAsVarcharLike.</p>
	 *
	 * @param propertyName a {@link java.lang.String} object.
	 * @param value a {@link java.lang.String} object.
	 */
	public CastAsVarcharLike( String propertyName, String value ) {
		this.propertyName = propertyName;
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 *
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
	 * {@inheritDoc}
	 *
	 * (non-Javadoc)
	 * @see org.hibernate.criterion.Criterion#getTypedValues(org.hibernate.Criteria, org.hibernate.criterion.CriteriaQuery)
	 */
	@Override
	public TypedValue[] getTypedValues( Criteria criteria, CriteriaQuery criteriaQuery ) throws HibernateException {
		return new TypedValue[] { new TypedValue( new org.hibernate.type.StringType(), MatchMode.START.toMatchString( value.toLowerCase() ) ) };
	}

}
