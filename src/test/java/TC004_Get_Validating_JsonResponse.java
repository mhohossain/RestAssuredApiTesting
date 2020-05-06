import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Get_Validating_JsonResponse {
	@Test
	public void getWeatherDetails() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET, "/Dhaka");
		String responsebody = response.getBody().asString();
		System.out.println("Response body is "+responsebody);
		
		//validating Json response 
		Assert.assertEquals(responsebody.contains("Dhaka"),  true);
		
		
	}
	

}
