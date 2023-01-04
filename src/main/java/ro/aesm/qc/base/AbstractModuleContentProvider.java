package ro.aesm.qc.base;

import java.io.InputStream;

public abstract class AbstractModuleContentProvider {

	protected String templatesPathPrefix = "templates";
	protected String templatesPathSuffix = ".tpl";

	public String getTemplatesPathPrefix() {
		return templatesPathPrefix;
	}

	public String getTemplatesPathSuffix() {
		return templatesPathSuffix;
	}

	public InputStream getResourceAsStream(String name) {
		return this.getClass().getClassLoader().getResourceAsStream(name);
	}

}
