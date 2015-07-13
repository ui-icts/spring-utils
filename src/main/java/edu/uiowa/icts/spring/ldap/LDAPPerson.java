package edu.uiowa.icts.spring.ldap;

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

import java.util.Arrays;


/**
 * <p>LDAPPerson class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class LDAPPerson {

	private String fullName;
	private String lastName;
	private String description;
	private String[] roleNames;
	private String mail;
	private String givenName;
	private String displayName;
	private String dn;
	private String telephoneNumber;
	private String title;
	private String company;
	private String college;
	private String department;
	private String username;
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "LDAPPerson [fullName=" + fullName + ", lastName=" + lastName
				+ ", description=" + description + ", roleNames="
				+ Arrays.toString(roleNames) + ", mail=" + mail
				+ ", givenName=" + givenName + ", displayName=" + displayName
				+ ", dn=" + dn + ", telephoneNumber=" + telephoneNumber
				+ ", title=" + title + ", company=" + company + ", college="
				+ college + ", department=" + department + ", username="
				+ username + "]";
	}
	/**
	 * <p>Getter for the field <code>fullName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * <p>Setter for the field <code>fullName</code>.</p>
	 *
	 * @param fullName a {@link java.lang.String} object.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * <p>Getter for the field <code>lastName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * <p>Setter for the field <code>lastName</code>.</p>
	 *
	 * @param lastName a {@link java.lang.String} object.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * <p>Getter for the field <code>description</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * <p>Setter for the field <code>description</code>.</p>
	 *
	 * @param description a {@link java.lang.String} object.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * <p>Getter for the field <code>roleNames</code>.</p>
	 *
	 * @return an array of {@link java.lang.String} objects.
	 */
	public String[] getRoleNames() {
		return roleNames;
	}
	/**
	 * <p>Setter for the field <code>roleNames</code>.</p>
	 *
	 * @param roleNames an array of {@link java.lang.String} objects.
	 */
	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}
	/**
	 * <p>Getter for the field <code>mail</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * <p>Setter for the field <code>mail</code>.</p>
	 *
	 * @param mail a {@link java.lang.String} object.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * <p>Getter for the field <code>givenName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * <p>Setter for the field <code>givenName</code>.</p>
	 *
	 * @param givenName a {@link java.lang.String} object.
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * <p>Getter for the field <code>displayName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * <p>Setter for the field <code>displayName</code>.</p>
	 *
	 * @param displayName a {@link java.lang.String} object.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * <p>Getter for the field <code>dn</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDn() {
		return dn;
	}
	/**
	 * <p>Setter for the field <code>dn</code>.</p>
	 *
	 * @param dn a {@link java.lang.String} object.
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}
	/**
	 * <p>Getter for the field <code>telephoneNumber</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	/**
	 * <p>Setter for the field <code>telephoneNumber</code>.</p>
	 *
	 * @param telephoneNumber a {@link java.lang.String} object.
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	/**
	 * <p>Getter for the field <code>title</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * <p>Setter for the field <code>title</code>.</p>
	 *
	 * @param title a {@link java.lang.String} object.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * <p>Getter for the field <code>company</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * <p>Setter for the field <code>company</code>.</p>
	 *
	 * @param company a {@link java.lang.String} object.
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * <p>Getter for the field <code>department</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * <p>Setter for the field <code>department</code>.</p>
	 *
	 * @param department a {@link java.lang.String} object.
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * <p>Getter for the field <code>username</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * <p>Setter for the field <code>username</code>.</p>
	 *
	 * @param username a {@link java.lang.String} object.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * <p>Getter for the field <code>college</code>.</p>
	 *
	 * @return the college
	 */
	public String getCollege() {
		return college;
	}
	/**
	 * <p>Setter for the field <code>college</code>.</p>
	 *
	 * @param college the college to set
	 */
	public void setCollege(String college) {
		this.college = college;
	}
	
	

}
