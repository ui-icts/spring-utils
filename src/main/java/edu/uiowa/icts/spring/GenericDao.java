package edu.uiowa.icts.spring;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.icts.criterion.CastAsVarcharLike;
import edu.uiowa.icts.criterion.DateLike;
import edu.uiowa.icts.criterion.IntegerLike;
import edu.uiowa.icts.sql.Alias;
import edu.uiowa.icts.util.SortColumn;

@Transactional
public class GenericDao<Type> implements GenericDaoInterface<Type> {

	private static final Log log = LogFactory.getLog( GenericDao.class );

	public SessionFactory sessionFactory;
	private String domainName;

	public void setSessionFactory( boolean usesf ) {
		if ( usesf ) {
			sessionFactory = SessionFactoryUtil.getInstance();
		}
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public void setSessionFactory( SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Transactional( readOnly = true )
	@SuppressWarnings( "unchecked" )
	public List<Type> search( String property, String search, Integer limit ) {
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( getDomainName() ) );
			criteria.add( Restrictions.ilike( property, search + "%" ) );
			criteria.setMaxResults( limit == null ? 25 : limit );
			return criteria.list();
		} catch ( HibernateException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return null;
	}

	@Transactional
	public void saveOrUpdate( Type obj ) {
		getSession().saveOrUpdate( obj );
	}

	@Transactional
	public void save( Type obj ) {
		getSession().save( obj );
	}

	@Transactional
	public void justSave( Type obj ) {
		getSession().save( obj );
	}

	@Transactional
	public void persist( Type obj ) {
		getSession().persist( obj );
	}

	@Transactional
	public void merge( Type obj ) {
		getSession().merge( obj );
	}

	@Transactional
	public void flush() {
		getSession().flush();
	}

	@Transactional
	public void close() {
		getSession().close();
	}

	@Transactional
	public void refresh( Object obj ) {
		getSession().refresh( obj );
	}

	@Transactional
	public long count() {
		long val = (Long) getSession().createQuery( "select count(*) from " + domainName ).uniqueResult();
		return val;
	}

	@Transactional
	@SuppressWarnings( "unchecked" )
	public void delete( int id ) {
		Type obj = (Type) getSession().get( domainName, id );
		getSession().delete( obj );
	}

	@Transactional
	@SuppressWarnings( "unchecked" )
	public void delete( long id ) {
		Type obj = (Type) getSession().get( domainName, id );
		getSession().delete( obj );
	}

	public void delete( Type obj ) {
		getSession().delete( obj );
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName( String domainName ) {
		this.domainName = domainName;
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public Type findByProperty( String propertyName, Object value ) {
		Type ob = null;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( domainName ) );
			criteria.add( Restrictions.eq( propertyName, value ) );
			ob = (Type) criteria.uniqueResult();
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return ob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.uiowa.icts.spring.GenericDaoInterface#findByProperties(java.util.
	 * HashMap)
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public Type findByProperties( HashMap<String, Object> propertyValues ) {
		Type theThing = null;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( domainName ) );
			for ( String prp : propertyValues.keySet() ) {
				criteria.add( Restrictions.eq( prp, propertyValues.get( prp ) ) );
			}
			theThing = (Type) criteria.uniqueResult();
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return theThing;
	}

	@Transactional( readOnly = true )
	public Integer countByProperty( String propertyName, Object value ) {
		Integer count = 0;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( domainName ) );
			criteria.add( Restrictions.eq( propertyName, value ) );
			count = ( (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult() ).intValue();
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return count;
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> listByProperty( String propertyName, Object value ) {
		List<Type> list = null;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( domainName ) );
			criteria.add( Restrictions.eq( propertyName, value ) );
			list = (List<Type>) criteria.list();
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return list;
	}

	@Override
	public Integer countByProperties( HashMap<String, Object> propertyValues ) {
		Integer count = 0;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( domainName ) );
			for ( String prp : propertyValues.keySet() ) {
				criteria.add( Restrictions.eq( prp, propertyValues.get( prp ) ) );
			}
			count = ( (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult() ).intValue();
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return count;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List<Type> listByProperties( HashMap<String, Object> propertyValues ) {
		List<Type> list = null;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( domainName ) );
			for ( String prp : propertyValues.keySet() ) {
				criteria.add( Restrictions.eq( prp, propertyValues.get( prp ) ) );
			}
			list = (List<Type>) criteria.list();
		} catch ( ClassNotFoundException e ) {
			log.error( "error creating criteria for class " + domainName, e );
		}
		return list;
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	/**
	 * @deprecated use {@link listByProperty(String propertyName, Object value)} instead.  
	 */
	@Deprecated
	public Type findByCriteria( String s ) {
		return (Type) getSession().createQuery( "from " + getDomainName() + " where " + s ).uniqueResult();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public Type findByQuery( String s ) {
		return (Type) getSession().createQuery( s ).uniqueResult();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> listByQuery( String s ) {
		return getSession().createQuery( s ).list();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> list() {
		try {
			return getSession().createCriteria( Class.forName( getDomainName() ) ).list();
		} catch ( HibernateException e ) {
			log.error( "error Class.forName for " + getDomainName(), e );
			throw e;
		} catch ( ClassNotFoundException e ) {
			log.error( "error Class.forName for " + getDomainName(), e );
			return null;
		}
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public String dump() {
		String result = "Dumping " + getDomainName() + "\n";
		List<Type> lt = getSession().createQuery( "from " + getDomainName() ).list();
		for ( Type t : lt ) {
			try {
				Class<?> ob = Class.forName( getDomainName() );
				Method[] list_method = ob.getMethods();
				for ( Method m : list_method ) {
					try {
						if ( m.getName().startsWith( "get" ) ) {
							result = result + m.invoke( t ) + "\t";
							log.debug( m.invoke( t ) + "\t" );
						}
					} catch ( Exception e ) {
						log.error( "error calling dump", e );
						continue;
					}
				}
				result = result + "\n";
			} catch ( Exception e ) {
				log.error( "error calling dump", e );
				continue;
			}
		}
		return result;
	}

	@SuppressWarnings( "unchecked" )
	@Transactional
	public List<Type> exec( String sql ) {
		Query query = getSession().createSQLQuery( sql );
		try {
			query.setResultTransformer( Transformers.aliasToBean( Class.forName( getDomainName() ) ) );
		} catch ( ClassNotFoundException e ) {
			log.error( "error Class.forName for " + getDomainName(), e );
		}
		return query.list();
	}

	@Transactional
	public void execute( String sql ) {
		Query query = getSession().createSQLQuery( sql );

		try {
			query.executeUpdate();
			//query.setResultTransformer( Transformers.aliasToBean( Class.forName( getDomainName() ) ) );
		} catch ( HibernateException e ) {
			log.error( "error Class.forName for " + getDomainName(), e );
		}
		//return query.list();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional
	public void clean() {
		List<Type> lt = getSession().createQuery( "from " + getDomainName() ).list();
		for ( Type t : lt ) {
			log.debug( "Cleanning..." + t.getClass().getName() );
			getSession().delete( t );
		}
	}

	@Transactional( readOnly = true )
	public List<Type> list( Integer start, Integer limit ) {
		return list( start, limit, null, new ArrayList<String>(), new ArrayList<SortColumn>(), new HashMap<String, Object>() );
	}

	@Transactional( readOnly = true )
	public List<Type> list( Integer start, Integer limit, ArrayList<SortColumn> sorts ) {
		return list( start, limit, null, new ArrayList<String>(), sorts, new HashMap<String, Object>() );
	}

	@Transactional( readOnly = true )
	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts ) {
		return list( start, limit, search, searchColumns, sorts, new HashMap<String, Object>() );
	}

	public Integer count( String search, ArrayList<String> searchColumns ) {
		GenericDaoListOptions options = new GenericDaoListOptions();
		options.setSearch( search );
		options.setSearchColumns( searchColumns );
		return count( options );
	}

	public Integer count( String search, ArrayList<String> searchColumns, HashMap<String, Object> properties ) {
		GenericDaoListOptions options = new GenericDaoListOptions();
		options.setSearch( search );
		options.setSearchColumns( searchColumns );
		options.setIndividualEquals( properties );
		return count( options );
	}

	@Override
	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts, HashMap<String, Object> individualLikes ) {
		return list( start, limit, search, searchColumns, sorts, individualLikes, null );
	}

	@Override
	public Integer count( String search, ArrayList<String> searchColumns, HashMap<String, Object> individualLikes, HashMap<String, Object> individualEquals ) {
		GenericDaoListOptions options = new GenericDaoListOptions();
		options.setSearch( search );
		options.setSearchColumns( searchColumns );
		options.setIndividualLikes( individualLikes );
		options.setIndividualEquals( individualEquals );
		return count( options );
	}

	@Transactional( readOnly = true )
	public Integer count( GenericDaoListOptions options ) {
		return ( (Number) criteria( options ).setProjection( Projections.rowCount() ).uniqueResult() ).intValue();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> list( GenericDaoListOptions options ) {
		List<Type> list;
		try {
			Criteria criteria = criteria( options );
			addSorts( criteria, options );
			if ( options.getStart() != null ) {
				criteria.setFirstResult( options.getStart() );
			}
			if ( options.getLimit() != null && options.getLimit() != -1 ) { // datatable sets iDisplayLength = -1 when bPaginate = false
				criteria.setMaxResults( options.getLimit() );
			}
			list = criteria.list();
		} catch ( Exception e ) {
			log.error( "Error getting List with options", e );
			log.debug( "options: " + options.toString() );
			return new ArrayList<Type>();
		}
		return list;
	}

	@Transactional( readOnly = true )
	protected Criteria criteria( GenericDaoListOptions options ) {
		Criteria criteria = null;
		try {

			if ( options.getAlias() != null && !StringUtils.equals( options.getAlias().trim(), "" ) ) {
				criteria = getSession().createCriteria( Class.forName( domainName ), options.getAlias().trim() );
			} else {
				criteria = getSession().createCriteria( Class.forName( domainName ) );
			}

			Dialect dialect = ( (SessionFactoryImplementor) getSessionFactory() ).getDialect();
			ClassMetadata classMetaData = getSessionFactory().getClassMetadata( Class.forName( domainName ) );

			processAliases( criteria, options );

			addIndividualEquals( criteria, options );

			addJunctions( criteria, options );

			addLikeCriteria( criteria, options, classMetaData, dialect );

			addNotEquals( criteria, options );

			addSearchCriteria( criteria, options, classMetaData, dialect );

		} catch ( ClassNotFoundException e ) {
			log.error( "error listing " + domainName, e );
		}

		return criteria;
	}

	public void addNotEquals( Criteria criteria, GenericDaoListOptions options ) {
		if ( options.getNotEquals() != null && !options.getNotEquals().isEmpty() ) {
			for ( String key : options.getNotEquals().keySet() ) {
				criteria.add( Restrictions.ne( key, options.getNotEquals().get( key ) ) );
			}
		}
	}

	public void addIndividualEquals( Criteria criteria, GenericDaoListOptions options ) {
		if ( options.getIndividualEquals() != null && !options.getIndividualEquals().isEmpty() ) {
			for ( String key : options.getIndividualEquals().keySet() ) {
				criteria.add( Restrictions.eq( key, options.getIndividualEquals().get( key ) ) );
			}
		}
	}

	/**
	 * @author rrlorent
	 * @param criteria
	 * @param options
	 */
	public void addJunctions( Criteria criteria, GenericDaoListOptions options ) {
		if ( options.getJunctions() != null && !options.getJunctions().isEmpty() ) {
			for ( Junction junction : options.getJunctions() ) {
				criteria.add( junction );
			}
		}
	}

	/**
	 * @author rrlorent
	 * @date May 28, 2014
	 * @param criteria
	 * @param options
	 */
	public void addSorts( Criteria criteria, GenericDaoListOptions options ) {
		if ( options.getSorts() != null ) {
			for ( SortColumn sortColumn : options.getSorts() ) {
				if ( "asc".equals( sortColumn.getDirection() ) ) {
					criteria.addOrder( Order.asc( sortColumn.getColumn() ) );
				} else if ( "desc".equals( sortColumn.getDirection() ) ) {
					criteria.addOrder( Order.desc( sortColumn.getColumn() ) );
				}
			}
		}
	}

	/**
	 * @author rrlorent
	 * @date May 23, 2014
	 * @param criteria
	 * @param options
	 */
	public void processAliases( Criteria criteria, GenericDaoListOptions options ) {
		if ( options.getAliases() != null ) {
			for ( Alias alias : options.getAliases() ) {
				criteria.createAlias( alias.getAssociationPath(), alias.getAlias(), alias.getJoinType() );
			}
		}
	}

	/**
	 * @param criteria
	 * @param options
	 * @param dialect 
	 * @param classMetaData 
	 */
	public void addSearchCriteria( Criteria criteria, GenericDaoListOptions options, ClassMetadata classMetaData, Dialect dialect ) {
		if ( options.getSearch() != null && !"".equals( options.getSearch() ) && options.getSearchColumns() != null && !options.getSearchColumns().isEmpty() ) {
			for ( String theSearch : StringUtils.split( options.getSearch().trim(), ' ' ) ) {
				Disjunction disj = Restrictions.disjunction();
				for ( String propertyName : options.getSearchColumns() ) {
					boolean fail = false;
					String propertyType = null;
					try {
						propertyType = classMetaData.getPropertyType( propertyName ).getName();
					} catch ( QueryException e ) {
						fail = true;
					}
					if ( fail ) {
						// cast it to varchar to avoid errors
						disj.add( new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + theSearch + "%" ) );
					} else {
						if ( StringUtils.equalsIgnoreCase( propertyType, "integer" ) || StringUtils.equalsIgnoreCase( propertyType, "boolean" ) || StringUtils.equalsIgnoreCase( propertyType, "float" ) ) {
							disj.add( new IntegerLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + theSearch + "%" ) );
						} else if ( StringUtils.equalsIgnoreCase( propertyType, "date" ) || StringUtils.equalsIgnoreCase( propertyType, "time" ) || StringUtils.equalsIgnoreCase( propertyType, "timestamp" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar_date" ) ) {
							disj.add( new DateLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + theSearch + "%", dialect ) );
						} else if ( StringUtils.equalsIgnoreCase( propertyType, "string" ) ) {
							disj.add( Restrictions.ilike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + theSearch + "%" ) );
						} else {
							log.error( propertyType + " not supported in individual likes for " + domainName + " : " + propertyName );
							// cast it to varchar to avoid errors
							disj.add( new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + theSearch + "%" ) );
						}
					}
				}
				criteria.add( disj );
			}
		}
	}

	/**
	 * @param criteria
	 * @param options
	 * @param dialect 
	 * @param classMetaData 
	 */
	public void addLikeCriteria( Criteria criteria, GenericDaoListOptions options, ClassMetadata classMetaData, Dialect dialect ) {
		if ( options.getLikes() != null && !options.getLikes().isEmpty() ) {
			Junction mainJunction = Restrictions.disjunction();
			for ( String propertyName : options.getLikes().keySet() ) {

				if ( options.getLikes().get( propertyName ) != null && !options.getLikes().get( propertyName ).isEmpty() ) {

					boolean fail = false;
					String propertyType = null;
					try {
						propertyType = classMetaData.getPropertyType( propertyName ).getName();
					} catch ( QueryException e ) {
						fail = true;
					}

					Disjunction disjunction = Restrictions.disjunction();
					for ( Object value : options.getLikes().get( propertyName ) ) {
						if ( fail ) {
							// cast it to varchar to avoid errors
							disjunction.add( new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" ) );
						} else {
							if ( StringUtils.equalsIgnoreCase( propertyType, "integer" ) || StringUtils.equalsIgnoreCase( propertyType, "boolean" ) || StringUtils.equalsIgnoreCase( propertyType, "float" ) ) {
								disjunction.add( new IntegerLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" ) );
							} else if ( StringUtils.equalsIgnoreCase( propertyType, "date" ) || StringUtils.equalsIgnoreCase( propertyType, "time" ) || StringUtils.equalsIgnoreCase( propertyType, "timestamp" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar_date" ) ) {
								disjunction.add( new DateLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%", dialect ) );
							} else if ( StringUtils.equalsIgnoreCase( propertyType, "string" ) ) {
								disjunction.add( Restrictions.ilike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" ) );
							} else {
								log.error( propertyType + " not supported in individual likes for " + domainName + " : " + propertyName );
								// cast it to varchar to avoid errors
								disjunction.add( new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" ) );
							}
						}
					}
					mainJunction.add( disjunction );
				}
			}
			criteria.add( mainJunction );
		} else if ( options.getIndividualLikes() != null && !options.getIndividualLikes().isEmpty() ) {
			for ( String propertyName : options.getIndividualLikes().keySet() ) {
				boolean fail = false;
				String propertyType = null;
				try {
					propertyType = classMetaData.getPropertyType( propertyName ).getName();
				} catch ( QueryException e ) {
					fail = true;
				}
				if ( fail ) {
					// cast it to varchar to avoid errors
					criteria.add( new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + options.getIndividualLikes().get( propertyName ) + "%" ) );
				} else {
					if ( StringUtils.equalsIgnoreCase( propertyType, "integer" ) || StringUtils.equalsIgnoreCase( propertyType, "boolean" ) || StringUtils.equalsIgnoreCase( propertyType, "float" ) ) {
						criteria.add( new IntegerLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + options.getIndividualLikes().get( propertyName ) + "%" ) );
					} else if ( StringUtils.equalsIgnoreCase( propertyType, "date" ) || StringUtils.equalsIgnoreCase( propertyType, "time" ) || StringUtils.equalsIgnoreCase( propertyType, "timestamp" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar_date" ) ) {
						criteria.add( new DateLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + options.getIndividualLikes().get( propertyName ) + "%", dialect ) );
					} else if ( StringUtils.equalsIgnoreCase( propertyType, "string" ) ) {
						criteria.add( Restrictions.ilike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + options.getIndividualLikes().get( propertyName ) + "%" ) );
					} else {
						log.error( propertyType + " not supported in individual likes for " + domainName + " : " + propertyName );
						// cast it to varchar to avoid errors
						criteria.add( new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + options.getIndividualLikes().get( propertyName ) + "%" ) );
					}
				}
			}
		}
	}

	@Transactional( readOnly = true )
	public List<Type> list( Integer start, Integer limit, String search, ArrayList<String> searchColumns, ArrayList<SortColumn> sorts, HashMap<String, Object> individualLikes, HashMap<String, Object> individualEquals ) {
		GenericDaoListOptions options = new GenericDaoListOptions();
		options.setIndividualLikes( individualLikes );
		options.setIndividualEquals( individualEquals );
		options.setSearch( search );
		options.setSearchColumns( searchColumns );
		options.setLimit( limit );
		options.setSorts( sorts );
		options.setStart( start );
		return list( options );
	}

	public void save( Collection<Type> list ) {
		StatelessSession session = this.getSessionFactory().openStatelessSession();
		session.beginTransaction();
		for ( Object o : list ) {
			session.insert( o );
		}
		session.getTransaction().commit();
		session.close();
	}

	public void saveOrUpdate( Collection<Type> list ) {
		StatelessSession session = getSessionFactory().openStatelessSession();
		session.beginTransaction();
		for ( Object o : list ) {
			session.update( o );
		}
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> listOrdered( String order, String direction ) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria( Class.forName( domainName ) );
			if ( "desc".equalsIgnoreCase( direction ) ) {
				criteria.addOrder( Order.desc( order ) );
			} else {
				criteria.addOrder( Order.asc( order ) );
			}
		} catch ( HibernateException e ) {
			log.error( "error", e );
		} catch ( ClassNotFoundException e ) {
			log.error( "error", e );
		}
		return criteria.list();
	}

	@Transactional
	@SuppressWarnings( "unchecked" )
	public List<Type[]> query( String st ) {
		return getSession().createQuery( st ).list();
	}

	@Transactional
	public Integer maxOf( String s ) {
		return (Integer) getSession().createQuery( "select max(" + s + ") from " + getDomainName() + " " ).uniqueResult();
	}

	@SuppressWarnings( { "unchecked", "unused" } )
	@Transactional( readOnly = true )
	@Deprecated
	public List<Object[]> listSearchPaged( int numResults, int firstResult, String searchst, List<String[]> orderData, List<String> colNames, List<String> searchCols, List<String> colType ) {
		searchst = "%" + searchst + "%";
		String alias = "i";
		String prefix = alias + ".";

		/*
		 * Build the WHERE clause
		 */
		StringBuffer where = new StringBuffer();
		int count = 0;
		for ( String att : searchCols ) {
			String type = colType.get( count );
			if ( "string".equalsIgnoreCase( type ) )
				where.append( prefix + att + "  LIKE ? " );
			else
				where.append( "str(" + prefix + att + ")  LIKE ? " );

			if ( count < searchCols.size() - 1 )
				where.append( " OR " );

			count++;
		}

		/**
		 * Build the SELECT clause
		 * 
		 */
		StringBuffer select = new StringBuffer();
		count = 0;
		for ( String col : colNames ) {
			select.append( " " + prefix + col + " " );

			if ( count < colNames.size() - 1 )
				select.append( ", " );

			count++;
		}

		/**
		 * Build the ORDER BY clause
		 * 
		 */
		StringBuffer orderby = new StringBuffer();
		count = 0;
		for ( String[] order : orderData ) {
			if ( order.length < 2 )
				continue;

			String col = order[0];
			String dir = order[1];
			if ( "desc".equalsIgnoreCase( dir ) )
				orderby.append( " " + prefix + col + " DESC" );
			else
				orderby.append( " " + prefix + col + " ASC" );

			if ( count < orderData.size() - 1 )
				orderby.append( ", " );
			count++;

		}

		/**
		 * Create Query
		 * 
		 * 
		 */
		String qstring = "SELECT " + select + " FROM " + getDomainName() + " as " + alias + " WHERE " + where + " ORDER BY " + orderby + "";
		log.debug( "FULL QUERY:" + qstring );
		Query q = getSession().createQuery( qstring );
		q.setMaxResults( numResults );
		q.setFirstResult( firstResult );

		/**
		 * Add search parameters
		 * 
		 */
		count = 0;
		for ( String att : searchCols ) {
			// String type = colType.get(count);
			q.setString( count, searchst );
			count++;
		}

		List<Object[]> list = q.list();
		log.debug( "result Size:" + list.size() );

		return list;
	}

	@SuppressWarnings( "unused" )
	@Transactional( readOnly = true )
	@Deprecated
	public Long countSearch( String searchst, List<String> searchCols, List<String> colType ) {
		searchst = "%" + searchst + "%";
		String alias = "i";
		String prefix = alias + ".";

		StringBuffer where = new StringBuffer();
		int count = 0;
		for ( String att : searchCols ) {
			String type = colType.get( count );
			if ( "string".equalsIgnoreCase( type ) )
				where.append( prefix + att + "  LIKE ? " );
			else
				where.append( "str(" + prefix + att + ")  LIKE ? " );

			if ( count < searchCols.size() - 1 )
				where.append( " OR " );

			count++;
		}

		String select = "count(i)";

		log.debug( "select:" + select.toString() );
		log.debug( "where:" + where.toString() );

		String qstring = "SELECT " + select + " FROM " + getDomainName() + " as " + alias + " WHERE " + where + "";
		log.debug( "FULL QUERY:" + qstring );

		Query q = getSession().createQuery( qstring );

		count = 0;
		for ( String att : searchCols ) {

			q.setString( count, searchst );
			count++;
		}

		Long size = (Long) q.uniqueResult();
		log.debug( "result Size:" + size );

		return size;
	}

	@Override
	public void update( Type obj ) {
		getSession().update( obj );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public List<Type> list( Comparator<Type> comparator ) {
		List<Type> list = null;
		try {
			Criteria criteria = getSession().createCriteria( Class.forName( getDomainName() ) );
			list = criteria.list();
			Collections.sort( list, comparator );
		} catch ( HibernateException e ) {
			log.error( "error calling list", e );
		} catch ( ClassNotFoundException e ) {
			log.error( "error calling list", e );
		}
		return list;
	}

}