package com.qa.rest.tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC_GETandDelete {
	
//	Below method perform "get all employee detail",in this method i am testing get functionality of the api
   @Test(priority = 1)
	public void test_getAllEmployeeDetails() {
		
		given()
		.when()
		      .get("http://dummy.restapiexample.com/api/v1/employees")    
		.then()
		      .statusCode(200);
		
	}
	
//   Below method perform "get specific employee detail by ID", here i am testing get api with specific id.
	@Test(priority = 2)
	public void test_getSpecificEmployeeDetail() {
		given()
		.when()
		      .get("http://dummy.restapiexample.com/api/v1/employee/1")
		.then()
		.statusCode(200)
		.body("data.id", equalTo(1))
		.body("data.employee_name", equalTo("Tiger Nixon"));
	}
	
//	Below method perform "delete operation" for specific given id, here i am testing the delete functionality to do that first i am validating what exist
//	at id = 2 then i will test delete functionality with passing id=2 and at last i will test get functionality id =2 that should be different from first get operation.
	
	@Test(priority = 3)
	public void test_getSpecificEmployeeDetailForDelete() {
		given()
		.when()
		      .get("http://dummy.restapiexample.com/api/v1/employee/2")
		.then()
		      .statusCode(200)
		      .body("data.id", equalTo(2))
		      .body("data.employee_name", equalTo("Garrett Winters"));
	}
	@Test(priority = 3)
	public void test_deleteEmployeeDetail() {
		Response res = 
		given()
		.when()
		      .delete("http://dummy.restapiexample.com/public/api/v1/delete/2")
		.then()
		      .statusCode(200)
		      .log().body()
		      .extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("Successfully! Record has been deleted"), true);
	}

	@Test(priority = 3)
	public void test_getSpecificEmployeeDetailForValidating() {
		given()
		.when()
		      .get("http://dummy.restapiexample.com/api/v1/employee/2")
		.then()
		      .statusCode(200)
		      .body("data.id", equalTo(2))
		      .body("data.employee_name", is(not(equalTo("Garrett Winters"))));
		      
	}

}
