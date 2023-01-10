package ro.aesm.qc.base.db;

import ro.aesm.qc.api.base.IDbConfig;

public class DbConfig implements IDbConfig {
	private String name;
	private String engine;
	private String host;
	private String port;
	private String database;
	private String user;
	private char[] password;

	public DbConfig() {
		super();
	}

	public DbConfig(String name, String engine, String host, String port, String database, String user,
			char[] password) {
		super();
		this.name = name;
		this.engine = engine;
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEngine() {
		return engine;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getPort() {
		return port;
	}

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public char[] getPassword() {
		return password;
	}
}
