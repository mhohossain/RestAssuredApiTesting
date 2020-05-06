import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_Get_Response {
	
	@Test
	public void getWeatherDetails() {
		
		//specify Base Uri
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		//create httprequest object
		RequestSpecification httprequest = RestAssured.given();
		//create response type
		Response response = httprequest.request(Method.GET, "Dhaka");
		//print response a console window or get response body		
		String responsebody = response.getBody().asString();
		System.out.println("Response body is : "+responsebody);
		
		//validation status code
		int statuscode = response.getStatusCode();
		System.out.println("Status code is : "+statuscode);
		Assert.assertEquals(200, statuscode);
		
		//verify status line
		String statusline = response.getStatusLine();
		System.out.println("Status line is : "+statusline);
		Assert.assertEquals("HTTP/1.1 200 OK", statusline);
		//validate headers
		String contenttype = response.header("Content-Type");//capture details of content type
		System.out.println("Content type is: "+contenttype);
		
		
	}

}
