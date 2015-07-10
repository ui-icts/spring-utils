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
 * @author rrlorent
 * @since May 15, 2014
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

	public Alias( String associationPath, String alias ) {
		super();
		this.associationPath = associationPath;
		this.alias = alias;
		this.joinType = JoinType.INNER_JOIN;
	}
	
	public Alias( String associationPath, String alias, JoinType joinType ) {
		super();
		this.associationPath = associationPath;
		this.alias = alias;
		this.joinType = joinType;
	}

	public String getAssociationPath() {
		return associationPath;
	}

	public void setAssociationPath( String associationPath ) {
		this.associationPath = associationPath;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias( String alias ) {
		this.alias = alias;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType( JoinType joinType ) {
		this.joinType = joinType;
	}

	@Override
	public String toString() {
		return "Alias [associationPath=" + associationPath + ", alias=" + alias + ", joinType=" + joinType + "]";
	}

}