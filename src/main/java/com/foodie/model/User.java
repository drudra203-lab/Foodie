package com.foodie.model;

import java.sql.Timestamp;

public class User {

	private int userId;
	private String username;
	private String password;
	private String email;
	private String address;
	private String role; // "Admin" or "Customer"
	private Timestamp createdDate; 
	private Timestamp lastLoginDate;

	// ─── Constructor ─────────────────────────────────────────

	public User() {

	}
	
	// Constructor for signup
	public User(String username, String password, String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		
	}
	
	
	

	public User(int userId, String username, String password, String email, String address, String role,
			Timestamp createdDate, Timestamp lastLoginDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	// ─── Getters & Setters ─────────────────────────────────────────

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", role=" + role + ", createdDate=" + createdDate + ", lastLoginDate="
				+ lastLoginDate + "]";
	}
}