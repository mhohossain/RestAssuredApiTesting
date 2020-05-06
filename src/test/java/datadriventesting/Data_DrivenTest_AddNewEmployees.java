package datadriventesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Data_DrivenTest_AddNewEmployees {
	
	@Test(dataProvider="empdataprovider")
	public void postNewEmployees(String ename, String esalary, String eage) {
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		
		//creating a json obj class 
		JSONObject requestprams = new JSONObject();
		requestprams.put("name", ename);
		requestprams.put("salary", esalary);
		requestprams.put("age", eage);
		
		//add a header starting with request boday is a json
		httprequest.header("Content-Type","application/json");
		//add the json body of the request
		httprequest.body(requestprams.toJSONString());
		
		//create response object type
		Response response = httprequest.request(Method.POST,"/create");
		//get response body
		String responsebody = response.getBody().asString();
		System.out.println("Resonse body is: "+responsebody);
		//validating
		Assert.assertEquals(responsebody.contains(ename), true);
		Assert.assertEquals(responsebody.contains(esalary), true);
		Assert.assertEquals(responsebody.contains(eage), true);
		//validate status code
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
			 
	}
	@DataProvider(name="empdataprovider")
	public String[][] getEmpData() {
		String empdata [][]= {{"abc123","3000","30"}, {"xyz123","4000","40"}, {"pqt123","5000","50"}};
		return(empdata);
	}
	

}
