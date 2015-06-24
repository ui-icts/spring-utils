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
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
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
	private Class<?> domainClass;

	public GenericDao() {
		// no-arg constructor :: setDomainName must be called
	}

	/**
	 * This constructor is preferred because Class.forName( getDomainName() ) will not need to be executed.
	 * @param domainClass
	 */
	public GenericDao( Class<?> domainClass ) {
		this.domainClass = domainClass;
		this.domainName = domainClass.getCanonicalName();
	}

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
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.add( Restrictions.ilike( property, search + "%" ) );
		criteria.setMaxResults( limit == null ? 25 : limit );
		return criteria.list();
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
	public void refresh( Type obj ) {
		getSession().refresh( obj );
	}

	@Transactional
	public long count() {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.setProjection( Projections.rowCount() );
		return ( (Number) criteria.uniqueResult() ).longValue();
	}

	@Transactional
	@SuppressWarnings( "unchecked" )
	public void delete( int id ) {
		Type obj = (Type) getSession().get( getDomainClass(), id );
		getSession().delete( obj );
	}

	@Transactional
	@SuppressWarnings( "unchecked" )
	public void delete( long id ) {
		Type obj = (Type) getSession().get( getDomainClass(), id );
		getSession().delete( obj );
	}

	public void delete( Type obj ) {
		getSession().delete( obj );
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public Type findByProperty( String propertyName, Object value ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.add( Restrictions.eq( propertyName, value ) );
		return (Type) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public Type findByProperties( HashMap<String, Object> propertyValues ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		for ( String property : propertyValues.keySet() ) {
			criteria.add( Restrictions.eq( property, propertyValues.get( property ) ) );
		}
		return (Type) criteria.uniqueResult();
	}

	@Transactional( readOnly = true )
	public Integer countByProperty( String propertyName, Object value ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.add( Restrictions.eq( propertyName, value ) );
		criteria.setProjection( Projections.rowCount() );
		return ( (Number) criteria.uniqueResult() ).intValue();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> listByProperty( String propertyName, Object value ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.add( Restrictions.eq( propertyName, value ) );
		return (List<Type>) criteria.list();
	}

	@Override
	public Integer countByProperties( HashMap<String, Object> propertyValues ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.setProjection( Projections.rowCount() );
		for ( String prp : propertyValues.keySet() ) {
			criteria.add( Restrictions.eq( prp, propertyValues.get( prp ) ) );
		}
		return ( (Number) criteria.uniqueResult() ).intValue();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List<Type> listByProperties( HashMap<String, Object> propertyValues ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		for ( String property : propertyValues.keySet() ) {
			criteria.add( Restrictions.eq( property, propertyValues.get( property ) ) );
		}
		return (List<Type>) criteria.list();
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
		return getSession().createCriteria( getDomainClass() ).list();
	}

	@Override
	public void update( Type obj ) {
		getSession().update( obj );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public List<Type> list( Comparator<Type> comparator ) {
		List<Type> list = getSession().createCriteria( getDomainClass() ).list();
		Collections.sort( list, comparator );
		return list;
	}

	@Transactional( readOnly = true )
	public String dump() {
		String result = "Dumping " + getDomainClass() + "\n";
		for ( Type item : list() ) {
			try {
				Method[] methods = getDomainClass().getMethods();
				for ( Method method : methods ) {
					try {
						if ( method.getName().startsWith( "get" ) ) {
							result = result + method.invoke( item ) + "\t";
							log.debug( method.invoke( item ) + "\t" );
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

	@Transactional
	@SuppressWarnings( "unchecked" )
	public List<Type> exec( String sql ) {
		Query query = getSession().createSQLQuery( sql );
		query.setResultTransformer( Transformers.aliasToBean( getDomainClass() ) );
		return query.list();
	}

	@Transactional
	public void execute( String sql ) {
		Query query = getSession().createSQLQuery( sql );
		query.executeUpdate();
	}

	@Transactional
	public void clean() {
		log.debug( "Cleanning..." + getDomainClass() );
		for ( Type item : list() ) {
			getSession().delete( item );
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
		Criteria criteria = criteria( options );
		criteria.setProjection( Projections.rowCount() );
		return ( (Number) criteria.uniqueResult() ).intValue();
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = true )
	public List<Type> list( GenericDaoListOptions options ) {
		try {
			Criteria criteria = criteria( options );
			addSorts( criteria, options );
			if ( options.getStart() != null ) {
				criteria.setFirstResult( options.getStart() );
			}
			if ( options.getLimit() != null && options.getLimit() != -1 ) { // datatable sets iDisplayLength = -1 when bPaginate = false
				criteria.setMaxResults( options.getLimit() );
			}
			return criteria.list();
		} catch ( Exception e ) {
			log.error( "Error getting List with options", e );
			log.debug( "options: " + options.toString() );
			return new ArrayList<Type>();
		}
	}

	@Transactional( readOnly = true )
	protected Criteria criteria( GenericDaoListOptions options ) {

		Criteria criteria = null;
		if ( options.getAlias() != null && !StringUtils.equals( options.getAlias().trim(), "" ) ) {
			criteria = getSession().createCriteria( getDomainClass(), options.getAlias().trim() );
		} else {
			criteria = getSession().createCriteria( getDomainClass() );
		}

		Dialect dialect = ( (SessionFactoryImplementor) getSessionFactory() ).getDialect();
		ClassMetadata classMetaData = getSessionFactory().getClassMetadata( getDomainClass() );

		applyGenericDaoListOptions( criteria, options, classMetaData, dialect );

		return criteria;
	}

	public void applyGenericDaoListOptions( Criteria criteria, GenericDaoListOptions options, ClassMetadata classMetaData, Dialect dialect ) {
		processAliases( criteria, options );
		addIndividualEquals( criteria, options );
		addJunctions( criteria, options );
		addLikeCriteria( criteria, options, classMetaData, dialect );
		addNotEquals( criteria, options );
		addSearchCriteria( criteria, options, classMetaData, dialect );
		addCriterion( criteria, options );
	}

	private void addCriterion( Criteria criteria, GenericDaoListOptions options ) {
		if ( options.getCriterion() != null && !options.getCriterion().isEmpty() ) {
			for ( Criterion criterion : options.getCriterion() ) {
				criteria.add( criterion );
			}
		}
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
				if ( options.getPropertyNameMap() != null && options.getPropertyNameMap().get( sortColumn.getColumn() ) != null && !options.getPropertyNameMap().get( sortColumn.getColumn() ).isEmpty() ) {
					for ( String alternateName : options.getPropertyNameMap().get( sortColumn.getColumn() ) ) {
						if ( "asc".equals( sortColumn.getDirection() ) ) {
							criteria.addOrder( Order.asc( alternateName ) );
						} else if ( "desc".equals( sortColumn.getDirection() ) ) {
							criteria.addOrder( Order.desc( alternateName ) );
						}
					}
				} else {
					if ( "asc".equals( sortColumn.getDirection() ) ) {
						criteria.addOrder( Order.asc( sortColumn.getColumn() ) );
					} else if ( "desc".equals( sortColumn.getDirection() ) ) {
						criteria.addOrder( Order.desc( sortColumn.getColumn() ) );
					}
				}
			}
		}
	}

	/**
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
					disj.add( createLikeCriterion( options, classMetaData, dialect, propertyName, theSearch ) );
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
			for ( String propertyName : options.getLikes().keySet() ) {

				if ( options.getLikes().get( propertyName ) != null && !options.getLikes().get( propertyName ).isEmpty() ) {

					Disjunction disjunction = Restrictions.disjunction();

					if ( options.getPropertyNameMap() != null && options.getPropertyNameMap().get( propertyName ) != null && !options.getPropertyNameMap().get( propertyName ).isEmpty() ) {
						for ( String alternate : options.getPropertyNameMap().get( propertyName ) ) {
							for ( Object value : options.getLikes().get( propertyName ) ) {
								disjunction.add( createLikeCriterion( options, classMetaData, dialect, alternate, value ) );
							}
						}
					} else {
						for ( Object value : options.getLikes().get( propertyName ) ) {
							disjunction.add( createLikeCriterion( options, classMetaData, dialect, propertyName, value ) );
						}
					}

					criteria.add( disjunction );
				}
			}
		} else if ( options.getIndividualLikes() != null && !options.getIndividualLikes().isEmpty() ) {
			for ( String propertyName : options.getIndividualLikes().keySet() ) {
				if ( options.getPropertyNameMap() != null && options.getPropertyNameMap().get( propertyName ) != null && !options.getPropertyNameMap().get( propertyName ).isEmpty() ) {
					Disjunction disjunction = Restrictions.disjunction();
					for ( String alternate : options.getPropertyNameMap().get( propertyName ) ) {
						disjunction.add( createLikeCriterion( options, classMetaData, dialect, alternate, options.getIndividualLikes().get( propertyName ) ) );
					}
					criteria.add( disjunction );
				} else {
					criteria.add( createLikeCriterion( options, classMetaData, dialect, propertyName, options.getIndividualLikes().get( propertyName ) ) );
				}
			}
		}
	}

	/**
	 * @param options
	 * @param classMetaData
	 * @param dialect
	 * @param propertyName
	 * @param value
	 * @return {@link Criterion}
	 */
	private Criterion createLikeCriterion( GenericDaoListOptions options, ClassMetadata classMetaData, Dialect dialect, String propertyName, Object value ) {

		boolean fail = false;
		String propertyType = null;
		if ( classMetaData == null ) {
			log.debug( "classMetaData is null for " + getDomainClass() );
			fail = true;
		} else {
			try {
				org.hibernate.type.Type propertyTypeOb = classMetaData.getPropertyType( propertyName );
				if ( propertyTypeOb != null ) {
					propertyType = propertyTypeOb.getName();
				} else {
					log.debug( "property type is null for " + propertyName );
					fail = true;
				}
			} catch ( QueryException e ) {
				fail = true;
			}
		}

		Criterion criterion = null;
		if ( fail ) {
			// cast it to varchar to avoid errors
			criterion = new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" );
		} else {
			if ( StringUtils.equalsIgnoreCase( propertyType, "integer" ) || StringUtils.equalsIgnoreCase( propertyType, "boolean" ) || StringUtils.equalsIgnoreCase( propertyType, "float" ) ) {
				criterion = new IntegerLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" );
			} else if ( StringUtils.equalsIgnoreCase( propertyType, "date" ) || StringUtils.equalsIgnoreCase( propertyType, "time" ) || StringUtils.equalsIgnoreCase( propertyType, "timestamp" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar" ) || StringUtils.equalsIgnoreCase( propertyType, "calendar_date" ) ) {
				criterion = new DateLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%", dialect );
			} else if ( StringUtils.equalsIgnoreCase( propertyType, "string" ) ) {
				criterion = Restrictions.ilike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" );
			} else {
				log.error( propertyType + " not supported in individual likes for " + getDomainClass() + " : " + propertyName );
				// cast it to varchar to avoid errors
				criterion = new CastAsVarcharLike( propertyName, ( options.isDoubleWildCard() ? "%" : "" ) + value + "%" );
			}
		}

		return criterion;
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
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		if ( "desc".equalsIgnoreCase( direction ) ) {
			criteria.addOrder( Order.desc( order ) );
		} else {
			criteria.addOrder( Order.asc( order ) );
		}
		return criteria.list();
	}

	@Override
	@Transactional
	@SuppressWarnings( "unchecked" )
	public List<Type> list( Order order ) {
		Criteria criteria = getSession().createCriteria( getDomainClass() );
		criteria.addOrder( order );
		return criteria.list();
	}

	@Transactional
	@SuppressWarnings( "unchecked" )
	public List<Type[]> query( String st ) {
		return getSession().createQuery( st ).list();
	}

	@Transactional
	public Integer maxOf( String property ) {
		return (Integer) getSession().createQuery( "select max(" + property + ") from " + getDomainClass().getCanonicalName() + " " ).uniqueResult();
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName( String domainName ) {
		this.domainName = domainName;
		try {
			this.domainClass = Class.forName( domainName );
		} catch ( ClassNotFoundException e ) {
			throw new RuntimeException( "error setting domain name to " + domainName, e );
		}
	}

	public Class<?> getDomainClass() {
		return domainClass;
	}

	//	public void setDomainClass( Class<?> domainClass ) {
	//		this.domainClass = domainClass;
	//	}

}