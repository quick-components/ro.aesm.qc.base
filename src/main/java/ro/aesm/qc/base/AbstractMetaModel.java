package ro.aesm.qc.base;

import ro.aesm.qc.api.meta.component.IMetaComponentModel;

public abstract class AbstractMetaModel implements IMetaComponentModel {

	protected String name;

	public AbstractMetaModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
