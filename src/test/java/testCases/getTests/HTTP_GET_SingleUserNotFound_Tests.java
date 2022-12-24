package testCases.getTests;

import base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HTTP_GET_SingleUserNotFound_Tests extends Base {

    ExtentTest eTest;
    boolean result;

    @BeforeClass
    public void getSingleUserNotFound(){
        httpRequest = RestAssured.given();
        response = httpRequest.get("/users/23");
    }

    @Test
    public void validateStatusCode(){
        eTest = extentReport.createTest("GET_SingleUserNotFound > validateStatusCode");
        eTest.log(Status.INFO,"Validating Status Code");

        if (response.statusCode()==404){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status code is 404");
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
        eTest = extentReport.createTest("GET_SingleUserNotFound > validateStatusLine");

        if (response.statusLine().equals("HTTP/1.1 404 Not Found")){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Status line is HTTP/1.1 404 Not Found");
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
        eTest = extentReport.createTest("GET_SingleUserNotFound > validateResponseTime");

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

        if (response.header("Content-Type").equals("application/json; charset=utf-8")&&
                response.header("Server").equals("cloudflare") &&
                response.header("Content-Length").equals("2")){
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
            eTest.log(Status.INFO,"Server header is "+response.header("Server"));
            eTest.log(Status.INFO,"Content-Length header is "+response.header("Content-Length"));
            eTest.log(Status.FAIL,"Test case failed");
        }
    }
}