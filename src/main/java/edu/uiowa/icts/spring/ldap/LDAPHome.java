package edu.uiowa.icts.spring.ldap;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings( { "unchecked", "rawtypes" } )
public class LDAPHome implements LDAPService {

	@Autowired
	private LdapTemplate ldapTemplate;

	public void setLdapTemplate( LdapTemplate ldapTemplate ) {
		this.ldapTemplate = ldapTemplate;
	}

	public List<LDAPPerson> findByUsername( String username ) {
		return ldapTemplate.search( "", "(cn=" + username + "*)", getContextMapper() );
	}

	public List<LDAPPerson> getByUsername( String username ) {
		return ldapTemplate.search( "", "(cn=" + username + ")", getContextMapper() );
	}

	public List<LDAPPerson> searchFirstName( String firstName ) {
		return ldapTemplate.search( "", "(givenName=" + firstName + "*)", getContextMapper() );
	}

	public List<LDAPPerson> searchLastName( String lastName ) {
		return ldapTemplate.search( "", "(sn=" + lastName + "*)", getContextMapper() );
	}

	public List<LDAPPerson> findByFullname( String firstname, String lastname ) {
		return ldapTemplate.search( "", "(&(givenName=" + firstname + "*)(sn=" + lastname + "*))", getContextMapper() );
	}

	public List search( String value ) {
		List list = ldapTemplate.search( "", "(cn=" + value + "*)",
			new AttributesMapper() {
				public Object mapFromAttributes( Attributes attrs )
					throws NamingException {
					return attrs.get( "DN" ).get();
				}
			} );
		return list;
	}

	public List getAllPersonNames() {
		List list = ldapTemplate.search( "", "(cn=*)", new AttributesMapper() {
			public Object mapFromAttributes( Attributes attrs )
				throws NamingException {
				return attrs.get( "DN" ).get();
			}
		} );
		return list;
	}

	protected ContextMapper getContextMapper() {
		return new PersonContextMapper();
	}

	private static class PersonContextMapper implements ContextMapper {
		public Object mapFromContext( Object ctx ) {

			DirContextAdapter context = (DirContextAdapter) ctx;
			DirContextOperations operations = (DirContextOperations) ctx;

			LDAPPerson ldapPerson = new LDAPPerson();
			ldapPerson.setFullName( context.getStringAttribute( "displayName" ) );
			ldapPerson.setLastName( context.getStringAttribute( "sn" ) );
			ldapPerson.setTitle( context.getStringAttribute( "title" ) );
			ldapPerson.setDepartment( context.getStringAttribute( "department" ) );
			ldapPerson.setCollege( context.getStringAttribute( "company" ) );
			ldapPerson.setCompany( context.getStringAttribute( "company" ) );
			ldapPerson.setTelephoneNumber( context.getStringAttribute( "uiowaOfficePhone" ) );
			ldapPerson.setMail( context.getStringAttribute( "mail" ) );
			ldapPerson.setGivenName( context.getStringAttribute( "givenName" ) );
			ldapPerson.setUsername( context.getStringAttribute( "cn" ) );

			String[] groupArray = operations.getStringAttributes( "memberOf" );
			if ( groupArray != null && groupArray.length > 0 ) {
				String[] roleNames = new String[ groupArray.length ];
				for ( int g = 0; g < groupArray.length; g++ ) {
					roleNames[g] = new DistinguishedName( groupArray[g] ).removeLast().getValue();
				}
				ldapPerson.setRoleNames( roleNames );
			}

			// p.setDn(context.getStringAttribute("distinguishedName"));
			// p.setDescription(context.getStringAttribute("description"));
			return ldapPerson;
		}
	}
}