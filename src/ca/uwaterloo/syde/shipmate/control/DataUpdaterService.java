package ca.uwaterloo.syde.shipmate.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ca.uwaterloo.syde.shipmate.entity.NodeDatabase;

public class DataUpdaterService {
	private final String deliveryStandardsDocumentUrl = "https://dl.dropboxusercontent.com/u/28237570/dpfLookup.html";
	private HtmlParser htmlParser = new HtmlParser();
	
	public DataUpdaterService() {
		
	}
	
	public void updateNodeSources() {
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		        try {
		            postData();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		});

		thread.start(); 
	}
	
	public void postData() throws ClientProtocolException, IOException {
		
		HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
		HttpGet httpget = new HttpGet(deliveryStandardsDocumentUrl); // Set the action you want to do
		HttpResponse response = httpclient.execute(httpget); // Executeit
		HttpEntity entity = response.getEntity(); 
		InputStream is = entity.getContent(); // Create an InputStream with the response
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) // Read line by line
		    sb.append(line + "\n");

		String resString = sb.toString(); // Result is here

		is.close(); // Close the stream
		
        // Execute HTTP Post Request
        NodeDatabase.setFsaDpfLookup(htmlParser.parseDpfLookupFile(response.toString()));
	} 
	

}
