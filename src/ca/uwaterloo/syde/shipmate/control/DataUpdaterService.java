package ca.uwaterloo.syde.shipmate.control;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.NodeList;
import ca.uwaterloo.syde.shipmate.entity.NodeDatabase;

/**
 * DataUpdaterService performs dirty work of subscribing to a networked data source.
 * @author Brian Stock
 *
 */
public class DataUpdaterService {
	private final String deliveryStandardsDocumentUrl = "https://dl.dropboxusercontent.com/u/28237570/dpfLookup.xml";
	private final String remoteLookupUrl = "https://dl.dropboxusercontent.com/u/28237570/remoteLookup.xml";

	private HtmlParser htmlParser = new HtmlParser();
	
	public DataUpdaterService() {
		
	}
	
	/**
	 * updateNodeSources fetches and savesxml data tables
	 */
	public void updateNodeSources() {
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		        try {
		        	NodeList remoteNodes = htmlParser.parseRemoteLookupFile(httpGetRequest(remoteLookupUrl));
		        	NodeList dpfNodes = htmlParser.parseDpfLookupFile(httpGetRequest(deliveryStandardsDocumentUrl));
		        	NodeDatabase.setFsaDpfLookup(dpfNodes);
		        	NodeDatabase.setRemoteLookup(remoteNodes);
		    		
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		});

		thread.start(); 
		NodeDatabase.setupDpfGrid();
	}
	
	/**
	 * httpGetRequest requests web resources.
	 * @param url - URL of resource to request
	 * @return html source of the data source
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String httpGetRequest(String url) throws ClientProtocolException, IOException {
		
		HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
		HttpGet httpget = new HttpGet(url); // Set the action you want to do
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

		return resString;
        
        
	}
	

}
