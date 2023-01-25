package ro.aesm.qc.base;

public class MetaComponentReference {

	private String name;
	private String classFqn;
	private AbstractMetaModelRoot instance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassFqn() {
		return classFqn;
	}

	public void setClassFqn(String classFqn) {
		this.classFqn = classFqn;
	}

	public AbstractMetaModelRoot getInstance() {
		return instance;
	}

	public void setInstance(AbstractMetaModelRoot instance) {
		this.instance = instance;
	}

}
