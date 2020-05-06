import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Post_request {
	
	@Test
	public void registrationSucessfull() {
		
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		
		RequestSpecification httprequest = RestAssured.given();
		
		//Request payload sending along with post request
		JSONObject requestparams = new JSONObject();
		
		requestparams.put("FirstName", "JohnXYZ");
		requestparams.put("LastName", "XYZJohn");
		requestparams.put("UserName", "JohnXYZ");
		requestparams.put("Password", "XYZJohnxyz");
		requestparams.put("Email", "JhonXYZ@gmail.com");
				
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestparams.toJSONString());//attach data to the request
		Response response = httprequest.request(Method.POST, "/register");
		
		
		String responsebody = response.getBody().asString();
		System.out.println("Response body is : "+responsebody);
		
		int statuscode = response.getStatusCode();
		System.out.println("Status code is : "+statuscode);
		Assert.assertEquals(201, statuscode);
		
		String successcode = response.jsonPath().get("SuccessCode");
		System.out.println("Success code is : "+successcode);
		Assert.assertEquals(successcode, "OPERATION_SUCCESS");
		
		
		
		
		
	}

}
