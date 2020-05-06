import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Get_ExtractValueOfEachNodeInJson {
	
	@Test
	public void getWeatherDetails() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET, "/Dhaka");
		
		//how to capture all the values from response
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		System.out.println(jsonpath.get("Humidity"));
		System.out.println(jsonpath.get("WeatherDescription"));
		System.out.println(jsonpath.get("WindSpeed"));
		System.out.println(jsonpath.get("WindDirectionDegree"));
		
		//validate Json temperature
		Assert.assertEquals(jsonpath.get("Temperature"), "28 Degree celsius");
		
	}
}
