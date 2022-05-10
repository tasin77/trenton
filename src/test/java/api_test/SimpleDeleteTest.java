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


public class SimpleDeleteTest {
    private static final Logger LOGGER = LogManager.getLogger(SimpleDeleteTest.class);

    @Test
    public void deleteSingleUser() {
        LOGGER.info("------API Test: Delete Single User");

        //Get Endpoint
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Load Endpoint
        RequestSpecification httpRequest = RestAssured.given();

        String id = "2";

        //Request Type - DELTE
        Response response = httpRequest.request(Method.DELETE, id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Validate 204
        Assert.assertEquals(response.getStatusCode(), 204);

        LOGGER.info("-----End Test: Delete Single User");
    }
}
