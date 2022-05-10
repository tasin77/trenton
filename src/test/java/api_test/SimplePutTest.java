package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePutTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePutTest.class);

    @Test
    public void updateUserFields() {
        LOGGER.info("------API Test: Update User Fields");

        //Get Endpoint
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Load Endpoint
        RequestSpecification httpRequest = RestAssured.given();

        //Fake Data Generation
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        LOGGER.debug("Updated User Full Name: " + fullName);

        String userRole = faker.job().title();
        LOGGER.debug("Updated User Job: " + userRole);

        //Add Data to Raw JSON Body
        JSONObject reqBody = new JSONObject();
        reqBody.put("name", fullName);
        reqBody.put("job", userRole);

        //Create Header
        httpRequest.header("Content-Type", "application/json");

        //Add Body into HTTP Request
        httpRequest.body(reqBody);

        String id = "2";

        //Request Type - PUT
        Response response = httpRequest.request(Method.PUT, id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate 200
        Assert.assertEquals(response.getStatusCode(), 200);

        //Validate Response Body
        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("name");
        Assert.assertEquals(actualName, fullName);

        LOGGER.info("-----End Test: Update User Fields");
    }
}
