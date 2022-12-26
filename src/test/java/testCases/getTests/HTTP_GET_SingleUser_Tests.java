package testCases.getTests;

import base.Base;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HTTP_GET_SingleUser_Tests extends Base {

    @BeforeClass
    public void getSingleUser() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("/users/2");
    }

    @Test
    public void validateStatusCode(){
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void validateStatusLine(){
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");
    }

    @Test
    public void validateResponseBody(){
        Object responseId = response.body().jsonPath().get("data.id");
        Object responseEmail = response.body().jsonPath().get("data.email");
        Object responseFirstName = response.body().jsonPath().get("data.first_name");
        Object responseLastName = response.body().jsonPath().get("data.last_name");

        Assert.assertEquals(responseId,Integer.parseInt(prop.getProperty("id")));
        Assert.assertEquals(responseEmail,prop.getProperty("email"));
        Assert.assertEquals(responseFirstName,prop.getProperty("first_name"));
        Assert.assertEquals(responseLastName,prop.getProperty("last_name"));
    }

    @Test
    public void validateResponseTime(){
        Assert.assertTrue(response.time()<4000);
    }

    @Test
    public void validateResponseHeaders(){
        Assert.assertEquals(response.header("Content-Type"),
                "application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),
                "cloudflare");
    }
}