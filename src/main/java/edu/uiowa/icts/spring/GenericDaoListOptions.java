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
 * @author rrlorent
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

	public Integer getStart() {
		return start;
	}

	public void setStart( Integer start ) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit( Integer limit ) {
		this.limit = limit;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch( String search ) {
		this.search = search;
	}

	public List<String> getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns( List<String> searchColumns ) {
		this.searchColumns = searchColumns;
	}

	public List<SortColumn> getSorts() {
		return sorts;
	}

	public void setSorts( List<SortColumn> sorts ) {
		this.sorts = sorts;
	}

	public void setIndividualLikes( Map<String, Object> individualLikes ) {
		this.individualLikes = individualLikes;
	}

	public Map<String, Object> getIndividualEquals() {
		return individualEquals;
	}

	public void setIndividualEquals( Map<String, Object> individualEquals ) {
		this.individualEquals = individualEquals;
	}

	@SuppressWarnings( "rawtypes" )
	public Class getTransformation() {
		return transformation;
	}

	@SuppressWarnings( "rawtypes" )
	public void setTransformation( Class transformation ) {
		this.transformation = transformation;
	}

	@SuppressWarnings( "rawtypes" )
	public List<Class> getDetached() {
		return detached;
	}

	@SuppressWarnings( "rawtypes" )
	public void setDetached( List<Class> detached ) {
		this.detached = detached;
	}

	public boolean isDoubleWildCard() {
		return doubleWildCard;
	}

	public void setDoubleWildCard( boolean doubleWildCard ) {
		this.doubleWildCard = doubleWildCard;
	}

	public Map<String, Object> getNotEquals() {
		return notEquals;
	}

	public void setNotEquals( Map<String, Object> notEquals ) {
		this.notEquals = notEquals;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns( List<String> columns ) {
		this.columns = columns;
	}

	/**
	 * @deprecated use more generic setJunctions( {@link List}<{@link Junction}> ) instead
	 */
	public void setDisjunctions( List<Disjunction> disjunctions ) {
		if ( this.junctions == null ) {
			this.junctions = new ArrayList<Junction>();
		}
		this.junctions.addAll( disjunctions );
	}

	/**
	 * @deprecated use more generic setJunctions( {@link List}<{@link Junction}> ) instead
	 */
	@Deprecated
	public void setConjunctions( List<Conjunction> conjunctions ) {
		if ( this.junctions == null ) {
			this.junctions = new ArrayList<Junction>();
		}
		this.junctions.addAll( conjunctions );
	}

	public String getAlias() {
		return alias;
	}

	/**
	 * alias for the main domain object 
	 * @param alias
	 */
	public void setAlias( String alias ) {
		this.alias = alias;
	}

	public List<Alias> getAliases() {
		return aliases;
	}

	public void setAliases( List<Alias> aliases ) {
		this.aliases = aliases;
	}

	/**
	 * This method does not allow the setting of the alias join type. 
	 * @deprecated use setAliases( {@link List}<{@link Alias}> ) instead.
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

	public Map<String, Object> getIndividualLikes() {
		return individualLikes;
	}

	public List<Junction> getJunctions() {
		return junctions;
	}

	public void setJunctions( List<Junction> junctions ) {
		this.junctions = junctions;
	}

	public Map<String, List<Object>> getLikes() {
		return likes;
	}

	public void setLikes( Map<String, List<Object>> likes ) {
		this.likes = likes;
	}

	public Map<String, List<String>> getPropertyNameMap() {
		return propertyNameMap;
	}

	public void setPropertyNameMap( Map<String, List<String>> propertyNameMap ) {
		this.propertyNameMap = propertyNameMap;
	}

	public List<Criterion> getCriterion() {
		return criterion;
	}

	public void setCriterion( List<Criterion> criterion ) {
		this.criterion = criterion;
	}

	@Override
	public String toString() {
		return "GenericDaoListOptions [alias=" + alias + ", columns=" + columns + ", start=" + start + ", limit=" + limit + ", search=" + search + ", searchColumns=" + searchColumns + ", sorts=" + sorts + ", likes=" + likes + ", individualLikes=" + individualLikes + ", individualEquals=" + individualEquals + ", notEquals=" + notEquals + ", doubleWildCard=" + doubleWildCard + ", propertyNameMap=" + propertyNameMap + ", junctions=" + junctions + ", aliases=" + aliases + ", criterion=" + criterion + "]";
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders( List<Order> orders ) {
		this.orders = orders;
	}
}