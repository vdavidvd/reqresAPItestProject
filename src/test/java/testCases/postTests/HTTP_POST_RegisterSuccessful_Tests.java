package testCases.postTests;

import base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomPayloadData;

import java.util.Properties;

public class HTTP_POST_RegisterSuccessful_Tests extends Base {
    static JSONObject payload;
    ExtentTest eTest;
    boolean result;

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
        eTest = extentReport.createTest("POST_RegisterSuccessful > validateStatusCode");
        eTest.log(Status.INFO,"Validating Status Code");

        if (response.statusCode()==200){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status code is 200");
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Status code is "+response.statusCode());
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateStatusLine(){
        eTest = extentReport.createTest("POST_RegisterSuccessful > validateStatusLine");

        if (response.statusLine().equals("HTTP/1.1 200 OK")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status line is HTTP/1.1 200 OK");
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Status line is not correct");
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseBody(){
        eTest = extentReport.createTest("POST_RegisterSuccessful > validateResponseBody");

        if (!(response.toString().contains("error: Missing password"))){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Response message is "+response.toString());
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Response message is "+response.toString());
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseTime(){
        eTest = extentReport.createTest("POST_RegisterSuccessful > validateResponseTime");

        if (response.time()<2000){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Response time is "+response.time());
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Response time is "+response.time());
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseHeaders(){
        eTest = extentReport.createTest("POST_RegisterSuccessful > validateResponseHeaders");

        if (response.header("Content-Type").equals("application/json; charset=utf-8")
                && response.header("Server").equals("cloudflare")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Content-Type header is "+response.header("Content-Type"));
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));
            eTest.log(Status.INFO,"Content-Length header is "+response.header("Content-Length"));
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Content-Type header is "+response.header("Content-Type"));
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));            eTest.log(Status.INFO,"Content-Length header is "+response.header("Content-Length"));
            eTest.log(Status.INFO,"Content-Length header is "+response.header("Content-Length"));
            eTest.log(Status.FAIL,"Test case failed");
        }
    }
}