package testCases.postTests;

import base.Base;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomPayloadData;

public class HTTP_POST_CreateNewUser_Tests extends Base {
    static JSONObject payload;
    public static int userId;

    @BeforeClass
    public void createNewUser(){
        payload = new JSONObject();
        payload.put("name", RandomPayloadData.getName());
        payload.put("job", "tester");

        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(payload.toJSONString());
        response = httpRequest.post("/users");

        userId = Integer.parseInt(response.body().jsonPath().get("id").toString());
    }

    @Test
    public void validateStatusCode(){
        Assert.assertEquals(response.statusCode(),201);
    }

    @Test
    public void validateStatusLine(){
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 201 Created");
    }

    @Test
    public void validateResponseBody(){
        Object responseName = response.body().jsonPath().get("name");
        Object responseJob = response.body().jsonPath().get("job");
        int responseId = Integer.parseInt(response.body().jsonPath().get("id").toString());

        Assert.assertEquals(responseName,payload.get("name"));
        Assert.assertEquals(responseJob,payload.get("job"));
        Assert.assertEquals(responseId,userId);
    }

    @Test
    public void validateResponseTime(){
        Assert.assertTrue(response.time()<4000);
    }

    @Test
    public void validateResponseHeaders(){
        Assert.assertEquals(response.header("Content-Type")
                ,"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server")
                ,"cloudflare");
    }
}