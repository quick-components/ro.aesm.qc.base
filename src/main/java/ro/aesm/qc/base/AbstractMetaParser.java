package ro.aesm.qc.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;

import ro.aesm.qc.api.Constants;
import ro.aesm.qc.api.exception.QcResourceException;
import ro.aesm.qc.api.meta.component.IMetaComponentModel;
import ro.aesm.qc.api.meta.component.IMetaComponentParser;

public abstract class AbstractMetaParser extends AbstractXmlDomParser implements IMetaComponentParser {

	public AbstractMetaParser() {
		super();
		this.getNamespaceMap().put(Constants.META_XMLNS_ALIAS, Constants.META_XMLNS);
	}

	public IMetaComponentModel parse(Node node) throws QcResourceException {
		return this.parse(node, false);
	}

	public abstract IMetaComponentModel parse(Node node, boolean nested) throws QcResourceException;

	public Map<String, IMetaComponentModel> parseChildrenAsMap(Node node) throws QcResourceException {
		Map<String, IMetaComponentModel> result = new HashMap<>();
		List<IMetaComponentModel> list = this.parseChildrenAsList(node);
		for (IMetaComponentModel model : list) {
			result.put(model.getName(), model);
		}
		return result;
	}

	public List<IMetaComponentModel> parseChildrenAsList(Node node) throws QcResourceException {
		List<IMetaComponentModel> result = new ArrayList<>();
		List<Node> nodes = this.getNodes(node, Constants.META_XMLNS_ALIAS + ":" + this.getTag());
		if (nodes != null && nodes.size() > 0) {
			for (Node childNode : nodes) {
				IMetaComponentModel _mm = this.parse(childNode);
				result.add(_mm);
			}
		}
		return result;
	}

//	public IMetaComponentModel parse(String content) throws QcResourceException {
//		DocumentBuilder builder = this.getXmlDocumentBuilder();
//		Document doc = this.getXmlDocument(content, builder);
//		return this.parse(doc.getDocumentElement());
//	}

}
