package testCases.deleteTests;

import base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testCases.postTests.HTTP_POST_CreateNewUser_Tests;

public class HTTP_DELETE_RemoveUser_Tests extends Base {

    ExtentTest eTest;
    boolean result;

    @BeforeClass
    public void deleteUser(){
        httpRequest = RestAssured.given();
        response = httpRequest.delete("users/"+ HTTP_POST_CreateNewUser_Tests.userId);
    }

    @Test
    public void validateStatusCode(){
        eTest = extentReport.createTest("DELETE_RemoveUser > validateStatusCode");
        eTest.log(Status.INFO,"Validating Status Code");

        if (response.statusCode()==204){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status code is 204");
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
        eTest = extentReport.createTest("DELETE_RemoveUser > validateStatusLine");

        if (response.statusLine().equals("HTTP/1.1 204 No Content")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status line is HTTP/1.1 204 No Content");
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Status line is not correct");
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseTime(){
        eTest = extentReport.createTest("DELETE_RemoveUser > validateResponseTime");

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
        eTest = extentReport.createTest("GET_SingleUserNotFound > validateResponseHeaders");

        if (response.header("Server").equals("cloudflare") &&
                response.header("Content-Length").equals("0")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));
            eTest.log(Status.INFO,"Content-Length header is "+response.header("Content-Length"));
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));
            eTest.log(Status.INFO,"Content-Length header is "+response.header("Content-Length"));
            eTest.log(Status.FAIL,"Test case failed");
        }
    }
}