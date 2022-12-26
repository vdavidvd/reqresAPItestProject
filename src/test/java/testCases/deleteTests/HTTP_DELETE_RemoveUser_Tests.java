package testCases.deleteTests;

import base.Base;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testCases.postTests.HTTP_POST_CreateNewUser_Tests;

public class HTTP_DELETE_RemoveUser_Tests extends Base {

    @BeforeClass
    public void deleteUser(){
        httpRequest = RestAssured.given();
        response = httpRequest.delete("users/"+ HTTP_POST_CreateNewUser_Tests.userId);
    }

    @Test
    public void validateStatusCode(){
        Assert.assertEquals(response.statusCode(),204);
    }

    @Test
    public void validateStatusLine(){
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 204 No Content");
    }

    @Test
    public void validateResponseTime(){
        Assert.assertTrue(response.time()<2000);
    }

    @Test
    public void validateResponseHeaders(){
        Assert.assertEquals(response.header("Server"),
                "cloudflare");
        Assert.assertEquals(response.header("Content-Length"),
                "0");
    }
}