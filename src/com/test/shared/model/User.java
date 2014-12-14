package com.test.shared.model;

import java.io.Serializable;

import com.google.gwt.view.client.ProvidesKey;

public class User implements Serializable{
	private static int idCounter=0;
	
	/**
	 * The key provider that provides the unique ID of a user.
	 */
	public static final ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>() {
		@Override
		public Object getKey(User item) {
			return item == null ? null : item.getId();
		}
	};
	private String id;
	private String name;
	private String surname;
	private String email;
	private UserRole role;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public User(){
		id = Integer.toString(idCounter++);
		name = "Aizek"+id;
		surname = "Azimov"+id;
		email = "AAz@gmail"+id+".com";
		role = UserRole.USER;
	}
}
