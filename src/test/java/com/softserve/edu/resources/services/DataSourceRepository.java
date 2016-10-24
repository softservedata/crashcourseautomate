package com.softserve.edu.resources.services;

public final class DataSourceRepository {

	//private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
	private static volatile DataSourceRepository instance = null;

	private DataSourceRepository() {
	}

	public static DataSourceRepository get() {
		if (instance == null) {
			synchronized (DataSourceRepository.class) {
				if (instance == null) {
					instance = new DataSourceRepository();
				}
			}
		}
		return instance;
	}

	public DataSource getJdbcMsSqlDefault() {
		return getJtdsMsSqlSsu();
	}

	// TODO Demo
	//	public DataSource getJdbcMsSqlSsu() {
	//		return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
	//				"jdbc:sqlserver://ssu-sql12\\tc;databasename=ssu-oms;", "ssu-oms", "ssu-oms");
	//	}

	// TODO Demo
	public DataSource getJtdsMsSqlSsu() {
		return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
				"jdbc:jtds:sqlserver://ssu-sql12/ssu-oms;instance=tc;", "ssu-oms", "ssu-oms");
	}

}
