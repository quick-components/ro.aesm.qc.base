package ro.aesm.qc.base;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import ro.aesm.qc.api.exception.QcResourceException;

public abstract class AbstractXmlResourceParser extends AbstractXmlDomParser {

	protected abstract Object parse(Node node) throws QcResourceException;

	public Object parse(String content) throws QcResourceException {
		DocumentBuilder builder = this.getXmlDocumentBuilder();
		Document doc = this.getXmlDocument(content, builder);
		return this.parse(doc.getDocumentElement());
	}

	public Object parse(InputStream inputStream) throws QcResourceException {
		DocumentBuilder builder = this.getXmlDocumentBuilder();
		Document doc = this.getXmlDocument(inputStream, builder);
		return this.parse(doc.getDocumentElement());
	}

	public Object parse(File file) throws QcResourceException {
		DocumentBuilder builder = this.getXmlDocumentBuilder();
		Document doc = this.getXmlDocument(file, builder);
		return this.parse(doc.getDocumentElement());
	}
}
