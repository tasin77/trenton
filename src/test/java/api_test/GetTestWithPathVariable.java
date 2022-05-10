package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetTestWithPathVariable {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUser() {
        LOGGER.info("------API Test: Get All Users with Query Parameter");

        //Get Endpoint
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Load Endpoint
        RequestSpecification httpRequest = RestAssured.given();

        String id = "2";

        //Request Type
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate 200
        Assert.assertEquals(response.getStatusCode(), 200);

        //Validate Response Body
        JsonPath jsonPath = response.jsonPath();

        //Validate Email
        String actualEmailId = jsonPath.getString("data.email");

        //Validate One particular email
        String expectedEmail = "janet.weaver@reqres.in";
        Assert.assertEquals(actualEmailId, expectedEmail);

        LOGGER.info("-----End Test: Get All Users with Query Parameter");
    }

    @Test
    public void attempToGetUserWithInvalidId() {

        LOGGER.info("------API Test: Attempt to retrieve user with invalid id");

        //Get Endpoint
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Load Endpoint
        RequestSpecification httpRequest = RestAssured.given();

        //Negative Testing with Invalid Data
        String id = "23";

        //Request Type
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate 404
        Assert.assertEquals(response.getStatusCode(), 404);

        //Validate NULL Response Body
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get().toString(), "{}");

        LOGGER.info("-----End Test: Attempt to retrieve user with invalid id");
    }
}
