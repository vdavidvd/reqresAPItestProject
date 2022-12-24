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

public class HTTP_POST_CreateNewUser_Tests extends Base {
    static JSONObject payload;
    public static int userId;
    ExtentTest eTest;
    boolean result;

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
        eTest = extentReport.createTest("POST_CreateNewUser > validateStatusCode");
        eTest.log(Status.INFO,"Validating Status Code");

        if (response.statusCode()==201){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status code is 201");
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
        eTest = extentReport.createTest("POST_CreateNewUser > validateStatusLine");

        if (response.statusLine().equals("HTTP/1.1 201 Created")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status line is HTTP/1.1 201 Created");
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
        eTest = extentReport.createTest("POST_CreateNewUser > validateResponseBody");

        Object responseName = response.body().jsonPath().get("name");
        Object responseJob = response.body().jsonPath().get("job");
        int responseId = Integer.parseInt(response.body().jsonPath().get("id").toString());

        if ((responseName.equals(payload.get("name")) && responseJob.equals(payload.get("job")))
         && responseId == userId){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Response Name is "+responseName);
            eTest.log(Status.INFO,"Response Job is "+responseJob);
            eTest.log(Status.INFO,"Response Id is "+responseId);
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Response Name is "+responseName);
            eTest.log(Status.INFO,"Response Job is "+responseJob);
            eTest.log(Status.INFO,"Response Id is "+responseId);
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseTime(){
        eTest = extentReport.createTest("POST_CreateNewUser > validateResponseTime");

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
        eTest = extentReport.createTest("POST_CreateNewUser > validateResponseHeaders");

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