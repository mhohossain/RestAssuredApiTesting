import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_Get_Request_Autherization {
	
	@Test
	public void autherizationTest() {
		
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";		
		//create basic autherization
		PreemptiveBasicAuthScheme autheScheme = new PreemptiveBasicAuthScheme();
		
		//verify username and password
		autheScheme.setUserName("ToolsQA");
		autheScheme.setPassword("TestPassword");
		//specify what kind of authentication it is
		RestAssured.authentication=autheScheme;
		
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET, "/");
		
		String responsebody = response.getBody().asString();
		System.out.println("Response body is "+responsebody);
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
		long gettime = response.getTime();
		System.out.println("Response time is "+gettime);
	}

}
