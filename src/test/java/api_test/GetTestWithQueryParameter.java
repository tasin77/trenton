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

import java.util.List;

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getUserwithQueryParameter() {
        LOGGER.info("------API Test: Get All Users with Query Parameter");

        //Get Endpoint
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Load Endpoint
        RequestSpecification httpRequest = RestAssured.given();

        //Request Type
        Response response = httpRequest.queryParam("page", "2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate 200
        Assert.assertEquals(response.getStatusCode(), 200);

        //Validate Response Body
        JsonPath jsonPath = response.jsonPath();

        //Validate Email
        List<String> listOfEmail = jsonPath.get("data.email");

        //Validate One particular email
        String expectedEmail = "michael.lawson@reqres.in";
        boolean emailExist = listOfEmail.contains(expectedEmail);
        Assert.assertTrue(emailExist, expectedEmail + "does not exist");

        LOGGER.info("-----End Test: Get All Users with Query Parameter");
    }
}
