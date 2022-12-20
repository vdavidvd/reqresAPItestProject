package testCases.postTests;

import base.Base;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HTTP_POST_LoginUnsuccessful_Tests extends Base {
    static JSONObject payload;

    @BeforeClass
    public void loginUnsuccessful(){
        payload = new JSONObject();
        payload.put("email", prop.getProperty("emailUnsuccessfulLogin"));

        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(payload.toJSONString());
        response = httpRequest.post("/login");
    }

    @Test
    public void validateStatusCode(){
        Assert.assertEquals(response.statusCode(),400);
    }

    @Test
    public void validateStatusLine(){
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 400 Bad Request");
    }

    @Test
    public void validateResponseBody(){
        Assert.assertEquals(response.body().jsonPath().get("error"),"Missing password");
    }

    @Test
    public void validateResponseTime(){
        Assert.assertTrue(response.time()<2000);
    }

    @Test
    public void validateResponseHeaders(){
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"cloudflare");
        Assert.assertEquals(response.header("Content-Length"),"28");
    }
}