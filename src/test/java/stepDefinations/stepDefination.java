package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import pojo.AddPlace;
import pojo.Location;

public class stepDefination extends Utils {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

//constructor will be  called  with value of resource which you pass  		
			APIResources resourceAPI = APIResources.valueOf(resource);
			System.out.println(resourceAPI.getResource());
			resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			if(method.equalsIgnoreCase("POST")) 
			response = res.when().post(resourceAPI.getResource());
			else if  (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
		
			
		
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		String respString = response.asString();
		// Debug output if needed
		System.out.println("Response Body: " + respString);
		JsonPath js = new JsonPath(respString);
		String actualValue = js.get(key);
		// Assert.assertEquals(actualValue, expectedValue);
	}
}