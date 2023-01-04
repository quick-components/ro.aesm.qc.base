package ro.aesm.qc.base;

import java.util.Map;

import ro.aesm.qc.api.exception.QcResourceException;
import ro.aesm.qc.api.meta.component.IMetaComponentCompiler;
import ro.aesm.qc.api.meta.component.IMetaComponentModel;

public abstract class AbstractMetaCompiler implements IMetaComponentCompiler {

	public abstract Map<String, Object> compile(IMetaComponentModel metaModel) throws QcResourceException;
}
