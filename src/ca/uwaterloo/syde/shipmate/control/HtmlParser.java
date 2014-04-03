package ca.uwaterloo.syde.shipmate.control;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/*
 * This class parses local html tables into RAM for use by the application's
 * various lookups.
 */
public class HtmlParser {
	private static NodeList remoteLookup;
	private static NodeList dpfLookup;
		
	public NodeList parseRemoteLookupFile(String remoteXml) {
		Object result = null;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			InputSource source = new InputSource(new StringReader(remoteXml));
			Document doc = builder.parse(source);
			docBuilderFactory.setNamespaceAware(true);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

			XPathExpression expr = xpath.compile("//table/tr/td");
			result = expr.evaluate(doc, XPathConstants.NODESET);
			remoteLookup = (NodeList) result;

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return remoteLookup;
	}

	public NodeList parseDpfLookupFile(String dpfXml)
	{
		System.out.println(dpfXml);
		Object result = null;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			InputSource source = new InputSource(new StringReader(dpfXml));
			Document doc = builder.parse(source);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
			XPathExpression expr = xpath.compile("//table/tr/td");
		    result = expr.evaluate(doc, XPathConstants.NODESET);		    
		    dpfLookup = (NodeList) result;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dpfLookup;
	}


}