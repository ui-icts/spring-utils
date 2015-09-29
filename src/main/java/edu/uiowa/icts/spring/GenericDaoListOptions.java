package edu.uiowa.icts.spring;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;

import edu.uiowa.icts.sql.Alias;
import edu.uiowa.icts.util.SortColumn;

/**
 * <p>GenericDaoListOptions class.</p>
 *
 * @author rrlorent
 * @version $Id: $
 */
public class GenericDaoListOptions {

	private String alias = null;
	private List<String> columns = null;
	private Integer start = null;
	private Integer limit = null;
	private String search = null;
	private List<String> searchColumns = null;
	private List<SortColumn> sorts = null;
	private List<Order> orders = null;
	private Map<String, List<Object>> likes = null;
	private Map<String, Object> individualLikes = null;
	private Map<String, Object> individualEquals = null;
	private Map<String, Object> notEquals = null;
	private boolean doubleWildCard = true;
	private Map<String, List<String>> propertyNameMap = null;
	private List<Junction> junctions = null;
	private List<Alias> aliases = null;
	private List<Criterion> criterion = null;

	@SuppressWarnings( "rawtypes" )
	private List<Class> detached = null;

	@SuppressWarnings( "rawtypes" )
	private Class transformation = null;

	@SuppressWarnings( "unused" )
	private List<Disjunction> disjunctions = null;

	@SuppressWarnings( "unused" )
	private List<Conjunction> conjunctions = null;

	/**
	 * <p>Getter for the field <code>start</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * <p>Setter for the field <code>start</code>.</p>
	 *
	 * @param start a {@link java.lang.Integer} object.
	 */
	public void setStart( Integer start ) {
		this.start = start;
	}

	/**
	 * <p>Getter for the field <code>limit</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * <p>Setter for the field <code>limit</code>.</p>
	 *
	 * @param limit a {@link java.lang.Integer} object.
	 */
	public void setLimit( Integer limit ) {
		this.limit = limit;
	}

	/**
	 * <p>Getter for the field <code>search</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * <p>Setter for the field <code>search</code>.</p>
	 *
	 * @param search a {@link java.lang.String} object.
	 */
	public void setSearch( String search ) {
		this.search = search;
	}

	/**
	 * <p>Getter for the field <code>searchColumns</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getSearchColumns() {
		return searchColumns;
	}

	/**
	 * <p>Setter for the field <code>searchColumns</code>.</p>
	 *
	 * @param searchColumns a {@link java.util.List} object.
	 */
	public void setSearchColumns( List<String> searchColumns ) {
		this.searchColumns = searchColumns;
	}

	/**
	 * <p>Getter for the field <code>sorts</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<SortColumn> getSorts() {
		return sorts;
	}

	/**
	 * <p>Setter for the field <code>sorts</code>.</p>
	 *
	 * @param sorts a {@link java.util.List} object.
	 */
	public void setSorts( List<SortColumn> sorts ) {
		this.sorts = sorts;
	}

	/**
	 * <p>Setter for the field <code>individualLikes</code>.</p>
	 *
	 * @param individualLikes a {@link java.util.Map} object.
	 */
	public void setIndividualLikes( Map<String, Object> individualLikes ) {
		this.individualLikes = individualLikes;
	}

	/**
	 * <p>Getter for the field <code>individualEquals</code>.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> getIndividualEquals() {
		return individualEquals;
	}

	/**
	 * <p>Setter for the field <code>individualEquals</code>.</p>
	 *
	 * @param individualEquals a {@link java.util.Map} object.
	 */
	public void setIndividualEquals( Map<String, Object> individualEquals ) {
		this.individualEquals = individualEquals;
	}

	/**
	 * <p>Getter for the field <code>transformation</code>.</p>
	 *
	 * @return a {@link java.lang.Class} object.
	 */
	@SuppressWarnings( "rawtypes" )
	public Class getTransformation() {
		return transformation;
	}

	/**
	 * <p>Setter for the field <code>transformation</code>.</p>
	 *
	 * @param transformation a {@link java.lang.Class} object.
	 */
	@SuppressWarnings( "rawtypes" )
	public void setTransformation( Class transformation ) {
		this.transformation = transformation;
	}

	/**
	 * <p>Getter for the field <code>detached</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	@SuppressWarnings( "rawtypes" )
	public List<Class> getDetached() {
		return detached;
	}

	/**
	 * <p>Setter for the field <code>detached</code>.</p>
	 *
	 * @param detached a {@link java.util.List} object.
	 */
	@SuppressWarnings( "rawtypes" )
	public void setDetached( List<Class> detached ) {
		this.detached = detached;
	}

	/**
	 * <p>isDoubleWildCard.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isDoubleWildCard() {
		return doubleWildCard;
	}

	/**
	 * <p>Setter for the field <code>doubleWildCard</code>.</p>
	 *
	 * @param doubleWildCard a boolean.
	 */
	public void setDoubleWildCard( boolean doubleWildCard ) {
		this.doubleWildCard = doubleWildCard;
	}

	/**
	 * <p>Getter for the field <code>notEquals</code>.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> getNotEquals() {
		return notEquals;
	}

	/**
	 * <p>Setter for the field <code>notEquals</code>.</p>
	 *
	 * @param notEquals a {@link java.util.Map} object.
	 */
	public void setNotEquals( Map<String, Object> notEquals ) {
		this.notEquals = notEquals;
	}

	/**
	 * <p>Getter for the field <code>columns</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * <p>Setter for the field <code>columns</code>.</p>
	 *
	 * @param columns a {@link java.util.List} object.
	 */
	public void setColumns( List<String> columns ) {
		this.columns = columns;
	}

	/**
	 * <p>Setter for the field <code>disjunctions</code>.</p>
	 *
	 * @deprecated use more generic setJunctions( {@link java.util.List}{@link org.hibernate.criterion.Junction} ) instead
	 * @param disjunctions a {@link java.util.List} object.
	 */
	public void setDisjunctions( List<Disjunction> disjunctions ) {
		if ( this.junctions == null ) {
			this.junctions = new ArrayList<Junction>();
		}
		this.junctions.addAll( disjunctions );
	}

	/**
	 * <p>Setter for the field <code>conjunctions</code>.</p>
	 *
	 * @deprecated use more generic setJunctions( {@link java.util.List} {@link org.hibernate.criterion.Junction} ) instead
	 * @param conjunctions a {@link java.util.List} object.
	 */
	@Deprecated
	public void setConjunctions( List<Conjunction> conjunctions ) {
		if ( this.junctions == null ) {
			this.junctions = new ArrayList<Junction>();
		}
		this.junctions.addAll( conjunctions );
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
	 * alias for the main domain object
	 *
	 * @param alias a {@link java.lang.String} object.
	 */
	public void setAlias( String alias ) {
		this.alias = alias;
	}

	/**
	 * <p>Getter for the field <code>aliases</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<Alias> getAliases() {
		return aliases;
	}

	/**
	 * <p>Setter for the field <code>aliases</code>.</p>
	 *
	 * @param aliases a {@link java.util.List} object.
	 */
	public void setAliases( List<Alias> aliases ) {
		this.aliases = aliases;
	}

	/**
	 * This method does not allow the setting of the alias join type.
	 *
	 * @deprecated use setAliases( {@link java.util.List} {@link edu.uiowa.icts.sql.Alias}  ) instead.
	 * @param aliases a {@link java.util.Map} object.
	 */
	@Deprecated
	public void setAliases( Map<String, String> aliases ) {
		List<Alias> list = this.aliases;
		if ( list == null ) {
			list = new ArrayList<Alias>();
		}
		for ( String key : aliases.keySet() ) {
			list.add( new Alias( key, aliases.get( key ) ) );
		}
		this.aliases = list;
	}

	/**
	 * <p>Getter for the field <code>individualLikes</code>.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> getIndividualLikes() {
		return individualLikes;
	}

	/**
	 * <p>Getter for the field <code>junctions</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<Junction> getJunctions() {
		return junctions;
	}

	/**
	 * <p>Setter for the field <code>junctions</code>.</p>
	 *
	 * @param junctions a {@link java.util.List} object.
	 */
	public void setJunctions( List<Junction> junctions ) {
		this.junctions = junctions;
	}

	/**
	 * <p>Getter for the field <code>likes</code>.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, List<Object>> getLikes() {
		return likes;
	}

	/**
	 * <p>Setter for the field <code>likes</code>.</p>
	 *
	 * @param likes a {@link java.util.Map} object.
	 */
	public void setLikes( Map<String, List<Object>> likes ) {
		this.likes = likes;
	}

	/**
	 * <p>Getter for the field <code>propertyNameMap</code>.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, List<String>> getPropertyNameMap() {
		return propertyNameMap;
	}

	/**
	 * <p>Setter for the field <code>propertyNameMap</code>.</p>
	 *
	 * @param propertyNameMap a {@link java.util.Map} object.
	 */
	public void setPropertyNameMap( Map<String, List<String>> propertyNameMap ) {
		this.propertyNameMap = propertyNameMap;
	}

	/**
	 * <p>Getter for the field <code>criterion</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<Criterion> getCriterion() {
		return criterion;
	}

	/**
	 * <p>Setter for the field <code>criterion</code>.</p>
	 *
	 * @param criterion a {@link java.util.List} object.
	 */
	public void setCriterion( List<Criterion> criterion ) {
		this.criterion = criterion;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "GenericDaoListOptions [alias=" + alias + ", columns=" + columns + ", start=" + start + ", limit=" + limit + ", search=" + search + ", searchColumns=" + searchColumns + ", sorts=" + sorts + ", likes=" + likes + ", individualLikes=" + individualLikes + ", individualEquals=" + individualEquals + ", notEquals=" + notEquals + ", doubleWildCard=" + doubleWildCard + ", propertyNameMap=" + propertyNameMap + ", junctions=" + junctions + ", aliases=" + aliases + ", criterion=" + criterion + "]";
	}

	/**
	 * <p>Getter for the field <code>orders</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * <p>Setter for the field <code>orders</code>.</p>
	 *
	 * @param orders a {@link java.util.List} object.
	 */
	public void setOrders( List<Order> orders ) {
		this.orders = orders;
	}
}
