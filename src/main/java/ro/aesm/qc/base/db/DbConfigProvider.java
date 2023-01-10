package ro.aesm.qc.base.db;

import java.util.HashMap;
import java.util.Map;

import ro.aesm.qc.api.base.IDbConfig;
import ro.aesm.qc.api.base.IDbConfigProvider;

public class DbConfigProvider implements IDbConfigProvider {

	private Map<String, IDbConfig> configsMap = new HashMap<>();

	@Override
	public IDbConfig getConfig(String name) {
		return this.configsMap.get(name);
	}

	@Override
	public void addConfig(String name, IDbConfig config) {
		if (!this.configsMap.containsKey(name)) {
			this.configsMap.put(name, config);
		}
	}

}
