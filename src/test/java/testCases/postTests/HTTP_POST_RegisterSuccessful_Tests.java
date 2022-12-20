package testCases.postTests;

import base.Base;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomPayloadData;

import java.util.Properties;

public class HTTP_POST_RegisterSuccessful_Tests extends Base {
    static JSONObject payload;

    @BeforeClass
    public void registerSuccessful(){
        payload = new JSONObject();
        payload.put("email", prop.getProperty("emailSuccessfulRegister"));
        payload.put("password", prop.getProperty("passSuccessfulRegister"));

        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(payload.toJSONString());
        response = httpRequest.post("/register");
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
        Assert.assertTrue(!(response.toString().contains("error: Missing password")));
    }

    @Test
    public void validateResponseTime(){
        Assert.assertTrue(response.time()<2000);
    }

    @Test
    public void validateResponseHeaders(){
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"cloudflare");
    }
}