package edu.uiowa.icts.sql;

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