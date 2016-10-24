package com.softserve.edu.resources.services;

import java.sql.Driver;

public final class DataSource {
	private Driver jdbcDriver;
	private String connectionUrl;
	private String username;
	private String password;

	// TODO Create Factory, Builder
	public DataSource(Driver jdbcDriver, String connectionUrl,
			String username, String password) {
		this.jdbcDriver = jdbcDriver;
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
	}

	public void setJdbcDriver(Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Driver getJdbcDriver() {
		return jdbcDriver;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
