import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Request_PrintAll_Headers {
	
	@Test
	public void getWeatherDetails() {
		
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET, "Dhaka");
		String responsebody = response.getBody().asString();
		System.out.println("Response body is "+responsebody);
		
		//print all the headers
		Headers allheaders = response.headers();
		
		for(Header header: allheaders) {
			System.out.println(header.getName()+"     "+header.getValue());
			
			
		}
		
	}

}
