package ro.aesm.qc.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ro.aesm.qc.api.exception.QcResourceException;

public abstract class AbstractXmlDomParser {

	private Map<String, String> namespaceMap = new HashMap<>();

	protected String getAttr(Node node, String attr) {
		return this.getAttr(node, attr, null);
	}

	protected String getAttr(Node node, String attr, String defaultValue) {
		Element element = (Element) node;
		if (element.hasAttribute(attr)) {
			return element.getAttribute(attr);
		}
		return defaultValue;
	}

	protected Integer getAttrAsInteger(Node node, String attr) {
		return this.getAttrAsInteger(node, attr, null);
	}

	protected Integer getAttrAsInteger(Node node, String attr, Integer defaultValue) {
		String v = this.getAttr(node, attr, ((defaultValue != null) ? "" + defaultValue : null));
		if (v != null) {
			return Integer.valueOf(v);
		}
		return defaultValue;
	}

	protected int getAttrAsInt(Node node, String attr) {
		return this.getAttrAsInt(node, attr, 0);
	}

	protected int getAttrAsInt(Node node, String attr, int defaultValue) {
		Integer v = this.getAttrAsInteger(node, attr, defaultValue);
		if (v != null) {
			return Integer.valueOf(v);
		}
		return defaultValue;
	}

	protected Boolean getAttrAsBoolean(Node node, String attr) {
		return this.getAttrAsBoolean(node, attr, null);
	}

	protected Boolean getAttrAsBoolean(Node node, String attr, Boolean defaultValue) {
		String v = this.getAttr(node, attr);
		if (v != null) {
			return Boolean.valueOf(v);
		}
		return defaultValue;
	}

	protected String getValue(Node node) {
		return this.getValue(node, null, null);
	}

	protected String getValue(Node node, String nsTag) {
		return this.getValue(node, nsTag, null);
	}

	protected String getValue(Node node, String nsTag, String defaultValue) {
		if (nsTag == null) {
			return ((Element) node).getTextContent();
		}
		Node targetNode = this.getNode(node, nsTag);
		if (targetNode == null) {
			return defaultValue;
		}
		return targetNode.getTextContent();
	}

	protected Node getNode(Node node, String nsTag) {
		NodeList nodeList = this.getNodeList(node, nsTag);
		if (nodeList.getLength() == 0) {
			return null;
		}
		return nodeList.item(0);
	}

	protected List<Node> getNodes(Node node, String nsTag) {
		List<Node> result = new ArrayList<Node>();
		NodeList nodeList = this.getNodeList(node, nsTag);
		for (int i = 0; i < nodeList.getLength(); i++) {
			result.add(nodeList.item(i));
		}
		return result;
	}

	protected NodeList getNodeList(Node node, String nsTag) {
		if (this.namespaceMap != null) {
			String[] tag = this.decodeTag(nsTag);
			return ((Element) node).getElementsByTagNameNS(this.namespaceMap.get(tag[0]), tag[1]);
		} else {
			return ((Element) node).getElementsByTagName(nsTag);
		}
	}

	protected String[] decodeTag(String nsTag) {
		String[] tokens = nsTag.split(":");
		String ns = "";
		String tag = nsTag;
		if (tokens.length == 2) {
			ns = tokens[0];
			tag = tokens[1];
		}
		return new String[] { ns, tag };
	}

	public Map<String, String> getNamespaceMap() {
		return this.namespaceMap;
	}

	public DocumentBuilder getXmlDocumentBuilder() throws QcResourceException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new QcResourceException("SAX parser configuration error, check the attached cause.", e);
		}
		return builder;
	}

	public Document getXmlDocument(String content) throws QcResourceException {
		return this.getXmlDocument(content, this.getXmlDocumentBuilder());
	}

	public Document getXmlDocument(String content, DocumentBuilder builder) throws QcResourceException {
		Document doc;
		try {
			// doc = builder.parse(inputStream);
			doc = builder.parse(new InputSource(new StringReader(content)));
		} catch (Exception e) {
			throw new QcResourceException("SAX parser error, check the attached cause.", e);
		}
		doc.getDocumentElement().normalize();
		return doc;
	}

	public Document getXmlDocument(InputStream inputStream) throws QcResourceException {
		return this.getXmlDocument(inputStream, this.getXmlDocumentBuilder());
	}

	public Document getXmlDocument(InputStream inputStream, DocumentBuilder builder) throws QcResourceException {
		Document doc;
		try {
			doc = builder.parse(inputStream);
		} catch (Exception e) {
			throw new QcResourceException("SAX parser error, check the attached cause.", e);
		}
		doc.getDocumentElement().normalize();
		return doc;
	}

	public Document getXmlDocument(File file) throws QcResourceException {
		return this.getXmlDocument(file, this.getXmlDocumentBuilder());
	}

	public Document getXmlDocument(File file, DocumentBuilder builder) throws QcResourceException {
		try (InputStream inputStream = new FileInputStream(file);) {
			return this.getXmlDocument(inputStream, builder);
		} catch (IOException e) {
			throw new QcResourceException(
					"Parsing file " + file.getPath() + " encountered an error. See attached cause.", e);
		}
	}

}
