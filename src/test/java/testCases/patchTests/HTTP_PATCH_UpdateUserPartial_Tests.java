package testCases.patchTests;

import base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testCases.postTests.HTTP_POST_CreateNewUser_Tests;
import utils.RandomPayloadData;

public class HTTP_PATCH_UpdateUserPartial_Tests extends Base {
    static JSONObject payload;
    ExtentTest eTest;
    boolean result;

    @BeforeClass
    public void updateUserPartial(){
        payload = new JSONObject();
        payload.put("name", RandomPayloadData.getName());

        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(payload.toJSONString());
        response = httpRequest.put("/users/"+ HTTP_POST_CreateNewUser_Tests.userId);
    }

    @Test
    public void validateStatusCode(){
        eTest = extentReport.createTest("PATCH_UpdateUserPartial > validateStatusCode");
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
        eTest = extentReport.createTest("PATCH_UpdateUserPartial > validateStatusLine");

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
        eTest = extentReport.createTest("PATCH_UpdateUserPartial > validateResponseBody");

        if (response.body().jsonPath().get("name").equals(payload.get("name"))){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Name is "+response.body().jsonPath().get("name"));
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Name is "+response.body().jsonPath().get("name"));
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseTime(){
        eTest = extentReport.createTest("PATCH_UpdateUserPartial > validateResponseTime");

        if (response.time()<4000){
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
        eTest = extentReport.createTest("PATCH_UpdateUserPartial > validateResponseHeaders");

        if (response.header("Content-Type").equals("application/json; charset=utf-8")
                && response.header("Server").equals("cloudflare")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Content-Type header is "+response.header("Content-Type"));
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Content-Type header is "+response.header("Content-Type"));
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));
            eTest.log(Status.FAIL,"Test case failed");
        }
    }
}