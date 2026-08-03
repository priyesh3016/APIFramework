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
import resources.TestDataBuild;
import resources.Utils;
import pojo.AddPlace;
import pojo.Location;

public class stepDefination extends Utils{
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
@Given("Add Place Payload")
public void add_place_payload() throws IOException {
	res = given().spec(requestSpecification())
            .body(data.addPlacePayload());
}

@When("user calls {string} with Post http request")
public void user_calls_with_post_http_request(String string) {
	resspec =new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
	response=res.when().post("/maps/api/place/add/json").
			then().spec(resspec).extract().response();
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