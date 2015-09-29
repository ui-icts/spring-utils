package edu.uiowa.icts.sql;

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

import org.hibernate.sql.JoinType;

/**
 * <p>Alias class.</p>
 *
 * @author rrlorent
 * @since May 15, 2014
 * @version $Id: $
 */
public class Alias {

//	public enum JoinType {
//		
//		INNER_JOIN( JoinFragment.INNER_JOIN ), FULL_JOIN( JoinFragment.FULL_JOIN ), LEFT_JOIN( JoinFragment.LEFT_OUTER_JOIN ), RIGHT_JOIN( JoinFragment.RIGHT_OUTER_JOIN );
//		int value;
//
//		JoinType( int value ) {
//			this.value = value;
//		}
//
//		public int getValue() {
//			return value;
//		}
//	}

	private String associationPath;
	private String alias;
	private JoinType joinType;

	/**
	 * <p>Constructor for Alias.</p>
	 *
	 * @param associationPath a {@link java.lang.String} object.
	 * @param alias a {@link java.lang.String} object.
	 */
	public Alias( String associationPath, String alias ) {
		super();
		this.associationPath = associationPath;
		this.alias = alias;
		this.joinType = JoinType.INNER_JOIN;
	}
	
	/**
	 * <p>Constructor for Alias.</p>
	 *
	 * @param associationPath a {@link java.lang.String} object.
	 * @param alias a {@link java.lang.String} object.
	 * @param joinType a {@link org.hibernate.sql.JoinType} object.
	 */
	public Alias( String associationPath, String alias, JoinType joinType ) {
		super();
		this.associationPath = associationPath;
		this.alias = alias;
		this.joinType = joinType;
	}

	/**
	 * <p>Getter for the field <code>associationPath</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getAssociationPath() {
		return associationPath;
	}

	/**
	 * <p>Setter for the field <code>associationPath</code>.</p>
	 *
	 * @param associationPath a {@link java.lang.String} object.
	 */
	public void setAssociationPath( String associationPath ) {
		this.associationPath = associationPath;
	}

	/**
	 * <p>Getter for the field <code>alias</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * <p>Setter for the field <code>alias</code>.</p>
	 *
	 * @param alias a {@link java.lang.String} object.
	 */
	public void setAlias( String alias ) {
		this.alias = alias;
	}

	/**
	 * <p>Getter for the field <code>joinType</code>.</p>
	 *
	 * @return a {@link org.hibernate.sql.JoinType} object.
	 */
	public JoinType getJoinType() {
		return joinType;
	}

	/**
	 * <p>Setter for the field <code>joinType</code>.</p>
	 *
	 * @param joinType a {@link org.hibernate.sql.JoinType} object.
	 */
	public void setJoinType( JoinType joinType ) {
		this.joinType = joinType;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Alias [associationPath=" + associationPath + ", alias=" + alias + ", joinType=" + joinType + "]";
	}

}
