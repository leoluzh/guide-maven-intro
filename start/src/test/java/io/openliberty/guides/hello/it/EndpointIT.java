package io.openliberty.guides.hello.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EndpointIT {

	private static String URL;
	
	@BeforeAll
	public static void init() {
		String port = System.getProperty("http.port");
		String war = System.getProperty("war.name");
		URL = "http://localhost:" + ( port == null ? "80" : port ) + "/" + war + "/" + "servlet" ;
		System.out.println("URL: " + URL);
	}
	
	@Test
	public void testServlet() throws Exception {
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(URL);
		
		try {
			int statusCode = client.executeMethod(method);
			assertEquals( HttpStatus.SC_OK , statusCode , "HTTP GET failed" );
			String response = method.getResponseBodyAsString(1000);
			System.out.println("Response: " + response );
			assertTrue(response.contains("Hello! How are you today?"),"Unexpected response body.");
		}finally {
			method.releaseConnection();
		}
		
	}
	
}
