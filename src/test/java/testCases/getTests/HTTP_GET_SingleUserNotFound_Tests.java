package testCases.getTests;

import base.Base;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HTTP_GET_SingleUserNotFound_Tests extends Base {

    @BeforeClass
    public void getSingleUserNotFound(){
        httpRequest = RestAssured.given();
        response = httpRequest.get("/users/23");
    }

    @Test
    public void validateStatusCode(){
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test
    public void validateStatusLine(){
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 404 Not Found");
    }

    @Test
    public void validateResponseTime(){
        Assert.assertTrue(response.time()<2000);
    }

    @Test
    public void validateResponseHeaders(){
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"cloudflare");
        Assert.assertEquals(response.header("Content-Length"),"2");
    }

}
