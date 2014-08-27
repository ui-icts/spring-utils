package edu.uiowa.icts.spring.ldap;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface LDAPService {
	
	public List getAllPersonNames();
	public List<LDAPPerson> findByUsername(String username);
	public List<LDAPPerson> getByUsername(String username);
	public List search(String value);
	public List<LDAPPerson> findByFullname(String firstname, String lastname);
	public List<LDAPPerson> searchFirstName( String firstName );
	public List<LDAPPerson> searchLastName( String lastName );
	
}