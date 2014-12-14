package com.test.shared.model;

public enum UserRole {
	USER, ADMIN;

	public static UserRole[] getRoles() {
		return new UserRole[]{USER, ADMIN};
	}
}
