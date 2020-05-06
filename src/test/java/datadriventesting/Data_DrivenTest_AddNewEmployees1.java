package datadriventesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Data_DrivenTest_AddNewEmployees1 {
	
	@Test(dataProvider="empdataprovider")
	public void postNewEmployees(String ename, String esalary, String eage) {
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		//create class of json obj
		JSONObject requestprams = new JSONObject();
		requestprams.put("name", ename);
		requestprams.put("salary", esalary);
		requestprams.put("age", eage);
		
		//add the header
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestprams.toJSONString());
		
		Response response = httprequest.request(Method.POST, "/create");
		String responsebody = response.getBody().asString();
		System.out.println("Response body is: "+responsebody);
		
		Assert.assertEquals(responsebody.contains(ename), true);
		Assert.assertEquals(responsebody.contains(esalary), true);
		Assert.assertEquals(responsebody.contains(eage), true);
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
						
	}
	@DataProvider(name="empdataprovider")
	public String[][] getData() {
		String emdata [][]= {{"abc123","2000","20"},{"xyz123","3000","30"},{"qtp123","400","40"}};
		return emdata;
	}
}
