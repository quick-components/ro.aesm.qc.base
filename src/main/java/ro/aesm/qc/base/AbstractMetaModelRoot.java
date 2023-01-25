package ro.aesm.qc.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMetaModelRoot extends AbstractMetaModel {

	protected List<MetaComponentReference> references = new ArrayList<>();
	protected Map<String, MetaComponentReference> referencesMap = new HashMap<>();

	public AbstractMetaModelRoot(String name) {
		super(name);
	}

	public void on$collectRefrences() {

	}

	public void on$ready(boolean propagate) {
		if (propagate) {
			for (MetaComponentReference r : this.references) {
				r.getInstance().on$ready(propagate);
			}
		}
	}

	public void addReference(MetaComponentReference ref) {
		this.references.add(ref);
		this.referencesMap.put(ref.getName(), ref);
	}

	public List<MetaComponentReference> getReferences() {
		return references;
	}

	public Map<String, MetaComponentReference> getReferencesMap() {
		return referencesMap;
	}

}
