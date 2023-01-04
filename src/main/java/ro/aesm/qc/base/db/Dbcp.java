package ro.aesm.qc.base.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import ro.aesm.qc.api.base.IDbcp;

 

public class Dbcp implements IDbcp {

	protected Map<String, DataSource> dataSourcesMap;

//	protected IConfig config;
//
//	public Dbcp(IConfig config) {
//		super();
//		this.config = config;
//		this.dataSourcesMap = new HashMap<String, DataSource>();
//	}

	@Override
	public Connection getConnection(String dbName) throws SQLException {
		return this.getDataSource(dbName).getConnection();
	}

	@Override
	public DataSource getDataSource(String name) {
//		if (!this.dataSourcesMap.containsKey(name)) {
//			IDbConfig dbConfig = this.config.getDatabase(name);
//			BasicDataSource ds = new BasicDataSource();
//			ds.setUrl(this.buildUrl(dbConfig));
//			ds.setUsername(dbConfig.getUser());
//			ds.setPassword(String.valueOf(dbConfig.getPassword()));
//			ds.setMinIdle(1);
//			ds.setMaxIdle(2);
//			ds.setMaxOpenPreparedStatements(100);
//			this.dataSourcesMap.put(name, ds);
//		}
		return this.dataSourcesMap.get(name);
	}

//	protected String buildUrl(IDbConfig dbConfig) {
//		if (dbConfig.getEngine().equals(Const.DB_MARIADB)) {
//			return "jdbc:mariadb://" + dbConfig.getHost() + ":" + dbConfig.getPort() + "/" + dbConfig.getDatabase();
//		} else if (dbConfig.getEngine().equals(Const.DB_MSSQL)) {
//			return "jdbc:mysql://" + dbConfig.getHost() + ":" + dbConfig.getPort() + "/" + dbConfig.getDatabase();
//		} else if (dbConfig.getEngine().equals(Const.DB_ORACLE)) {
//			return "jdbc:oracle:thin:@" + dbConfig.getHost() + ":" + dbConfig.getPort() + ":" + dbConfig.getDatabase();
//		}
//		return null;
//	}

}
