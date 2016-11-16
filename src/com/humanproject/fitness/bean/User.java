package com.humanproject.fitness.bean;

import java.util.Date;

public class User {
	private long userId;
	private String name;
	private String email;
	private String password;
	private String location;
	private long totalFeets;
	private int dailyFeets;
	private long lastWalkedAt; //in millis
	private int milestone;
	private int rank;
	public User() {
	}
	public User(String username) {
		this.email = username;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getTotalFeets() {
		return totalFeets;
	}

	public void setTotalFeets(long totalFeets) {
		this.totalFeets = totalFeets;
	}

	public int getDailyFeets() {
		return dailyFeets;
	}

	public void setDailyFeets(int dailyFeets) {
		this.dailyFeets = dailyFeets;
	}

	public long getLastWalkedAt() {
		return lastWalkedAt;
	}
	public void setLastWalkedAt(long lastWalkedAt) {
		this.lastWalkedAt = lastWalkedAt;
	}
	public int getMilestone() {
		return milestone;
	}
	public void setMilestone(int milestone) {
		this.milestone = milestone;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
