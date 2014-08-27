package edu.uiowa.icts.spring.ldap;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
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
			LDAPPerson p = new LDAPPerson();
			p.setFullName( context.getStringAttribute( "displayName" ) );
			p.setLastName( context.getStringAttribute( "sn" ) );
			// p.setDescription(context.getStringAttribute("description"));
			// The roleNames property of Person is an String array
			// p.setRoleNames(context.getStringAttributes("roleNames"));
			p.setTitle( context.getStringAttribute( "title" ) );
			p.setDepartment( context.getStringAttribute( "department" ) );
			p.setCollege( context.getStringAttribute( "company" ) );
			p.setCompany( context.getStringAttribute( "company" ) );
			p.setTelephoneNumber( context.getStringAttribute( "uiowaOfficePhone" ) );
			// p.setDn(context.getStringAttribute("distinguishedName"));
			p.setMail( context.getStringAttribute( "mail" ) );
			p.setGivenName( context.getStringAttribute( "givenName" ) );
			p.setUsername( context.getStringAttribute( "cn" ) );
			return p;
		}
	}
}