package edu.uiowa.icts.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uiowa.icts.util.SortColumn;

public interface GenericDaoInterface<Type> {


	public void setSessionFactory(SessionFactory sessionFactory) ;
	public Session getSession();
	public SessionFactory getSessionFactory();
	public void  saveOrUpdate(Type obj);
	public void  update(Type obj);
	public void  save(Type obj);
	public void  persist(Type obj);
	public void  merge(Type obj);	
	public long count();
	public void  delete(int id);
	public void  delete(long id);
	public void  delete(Type obj);
	
	public String getDomainName() ;
	public List<Type> search( String property, String search, Integer limit );
	
	public Type findByProperty(String propertyName, Object value);
	public Type findByProperties(HashMap<String, Object> propertyValues);
	public List<Type> listByProperty(String propertyName, Object value);
	public List<Type> listByProperties(HashMap<String, Object> propertyValues);

	public void saveOrUpdate( Collection<Type> list );
	public void save( Collection<Type> list );
	
	public List<Type[]> query(String st); 
	public void setDomainName(String domainName) ;
	public Integer  maxOf(String s);
	public void  justSave(Type obj);
	public List<Type>  list();
	public List<Type>  list( Comparator<Type> comparator );
	public String dump();
	public List<Type> exec(String sql);
	public void execute( String sql );
	
	public void clean();
	public List<Type>  listOrdered(String order, String direction);
	
	public Integer count( GenericDaoListOptions options );
	public List<Type> list( GenericDaoListOptions options );
	public List<Type> list( Integer start, Integer limit );
	public List<Type> list( Integer start, Integer limit, ArrayList<SortColumn> sorts );
	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts );
	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts, HashMap<String,Object> individualLikes );
	public Integer countByProperty( String propertyName, Object value );
	
	public List<Type>  list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts, HashMap<String,Object> individualLikes, HashMap<String,Object> individualEquals);
	
	public List<Type> listByQuery(String s);

	public Integer countByProperties( HashMap<String, Object> propertyValues );
	public Integer count( String search, ArrayList<String> searchColumns );
	public Integer count( String search, ArrayList<String> searchColumns, HashMap<String,Object> individualLikes );
	public Integer count( String search, ArrayList<String> searchColumns, HashMap<String,Object> individualLikes, HashMap<String,Object> individualEquals);
	
	public void  flush();
	public void  close();
	public void  refresh(Object obj);
	
	/**
	 * @deprecated use {@link listByProperty(String propertyName, Object value)} instead.  
	 */
	@Deprecated 
	public Type  findByCriteria(String s);
	
	// used for serverside pagination on a table
	@Deprecated
	public Long countSearch(String searchst, List<String> searchCols, List<String> colType);
	
	@Deprecated
	public List<Object[]> listSearchPaged(int numResults, int firstResult, String searchst, List<String[]> orderData, List<String> colNames, List<String> searchCols, List<String> colType);
	
}