package testCases.putTests;

import base.Base;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testCases.postTests.HTTP_POST_CreateNewUser_Tests;
import utils.RandomPayloadData;

public class HTTP_PUT_UpdateUser_Tests extends Base {
    static JSONObject payload;

    @BeforeClass
    public void updateUser(){
        payload = new JSONObject();
        payload.put("name", RandomPayloadData.getName());
        payload.put("job", "tester");

        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(payload.toJSONString());
        response = httpRequest.put("/users/"+ HTTP_POST_CreateNewUser_Tests.userId);
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
        Object responseName = response.body().jsonPath().get("name");
        Object responseJob = response.body().jsonPath().get("job");

        Assert.assertEquals(responseName,payload.get("name"));
        Assert.assertEquals(responseJob,(payload.get("job")));
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