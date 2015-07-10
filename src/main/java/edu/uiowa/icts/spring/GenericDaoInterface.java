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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import edu.uiowa.icts.util.SortColumn;

public interface GenericDaoInterface<Type> {

	public void setSessionFactory( SessionFactory sessionFactory );

	public Session getSession();

	public SessionFactory getSessionFactory();

	public void saveOrUpdate( Type obj );

	public void update( Type obj );

	public void save( Type obj );

	public void persist( Type obj );

	public void merge( Type obj );

	public void delete( int id );

	public void delete( long id );

	public void delete( Type obj );

	public String getDomainName();

	public List<Type> search( String property, String search, Integer limit );

	public Type findByProperty( String propertyName, Object value );

	public Type findByProperties( HashMap<String, Object> propertyValues );

	public void saveOrUpdate( Collection<Type> list );

	public void save( Collection<Type> list );

	public List<Type[]> query( String st );

	public void setDomainName( String domainName );

	public Integer maxOf( String property );

	public void justSave( Type obj );

	public List<Type> list();

	public List<Type> list( Order order );

	public List<Type> list( GenericDaoListOptions options );

	public List<Type> list( Comparator<Type> comparator );

	public List<Type> list( Integer start, Integer limit );

	public List<Type> list( Integer start, Integer limit, ArrayList<SortColumn> sorts );

	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts );

	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts, HashMap<String, Object> individualLikes );

	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts, HashMap<String, Object> individualLikes, HashMap<String, Object> individualEquals );

	public List<Type> listByProperty( String propertyName, Object value );

	public List<Type> listByProperties( HashMap<String, Object> propertyValues );

	public List<Type> listOrdered( String order, String direction );

	public List<Type> listByQuery( String query );

	public long count();

	public Integer count( GenericDaoListOptions options );

	public Integer count( String search, ArrayList<String> searchColumns );

	public Integer count( String search, ArrayList<String> searchColumns, HashMap<String, Object> individualLikes );

	public Integer count( String search, ArrayList<String> searchColumns, HashMap<String, Object> individualLikes, HashMap<String, Object> individualEquals );

	public Integer countByProperty( String propertyName, Object value );

	public Integer countByProperties( HashMap<String, Object> propertyValues );

	public void flush();

	public void close();

	public void refresh( Type obj );

	public String dump();

	public List<Type> exec( String sql );

	public void execute( String sql );

	public void clean();

}