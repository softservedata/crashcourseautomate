package com.softserve.edu.resources.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private final static String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
	private static volatile ConnectionUtils instance = null;
	//
	// TODO Develop Multithreading for Connection
	private Connection connection = null;
	private DataSource dataSource;

	private ConnectionUtils(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static ConnectionUtils get() {
		if (instance == null) {
			//throw new RuntimeException(FAILED_REGISTRATE_DRIVER);
			return get(DataSourceRepository.get().getJdbcMsSqlDefault());
		}
		return instance;
	}

	public static ConnectionUtils get(DataSource dataSource) {
		if (instance == null) {
			synchronized (ConnectionUtils.class) {
				if (instance == null) {
					instance = new ConnectionUtils(dataSource);
					try {
						DriverManager.registerDriver(dataSource.getJdbcDriver());
					} catch (SQLException e) {
						throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
					}
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		if (connection == null) {
			// TODO Remove synchronized block for Multithreading
			synchronized (ConnectionUtils.class) {
				if (connection == null) {
					try {
						connection = DriverManager.getConnection(dataSource.getConnectionUrl(),
								dataSource.getUsername(), dataSource.getPassword());
					} catch (SQLException e) {
						throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
					}
				}
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (instance != null) {
			// TODO Develop Multithreading for()
			if (instance.getConnection() != null) {
				try {
					instance.getConnection().close();
				} catch (SQLException e) {
					throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
				}
			}
		}
	}

}
