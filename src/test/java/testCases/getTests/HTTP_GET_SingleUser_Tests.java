package testCases.getTests;

import base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HTTP_GET_SingleUser_Tests extends Base {

    ExtentTest eTest;
    boolean result;

    @BeforeClass
    public void getSingleUser() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("/users/2");
    }

    @Test
    public void validateStatusCode(){
        eTest = extentReport.createTest("GET_SingleUser > validateStatusCode");
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
        eTest = extentReport.createTest("GET_SingleUser > validateStatusLine");

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
        eTest = extentReport.createTest("GET_SingleUser > validateResponseBody");

        Object responseId = response.body().jsonPath().get("data.id");
        Object responseEmail = response.body().jsonPath().get("data.email");
        Object responseFirstName = response.body().jsonPath().get("data.first_name");
        Object responseLastName = response.body().jsonPath().get("data.last_name");

        if ((responseId.equals(Integer.parseInt(prop.getProperty("id"))) &&
                responseEmail.equals(prop.getProperty("email")))
        && (responseFirstName.equals(prop.getProperty("first_name"))
        && responseLastName.equals(prop.getProperty("last_name")))){
            result = true;
            Assert.assertTrue(result);
            eTest.log(Status.INFO,"Response ID is "+responseId);
            eTest.log(Status.INFO,"Response Email is "+responseEmail);
            eTest.log(Status.INFO,"Response First Name is "+responseFirstName);
            eTest.log(Status.INFO,"Response Last Name is "+responseLastName);
            eTest.log(Status.PASS,"Test case passed");
        }else {
            result = false;
            Assert.assertFalse(result);
            eTest.log(Status.INFO,"Response ID is "+responseId);
            eTest.log(Status.INFO,"Response Email is "+responseEmail);
            eTest.log(Status.INFO,"Response First Name is "+responseFirstName);
            eTest.log(Status.INFO,"Response Last Name is "+responseLastName);
            eTest.log(Status.FAIL,"Test case failed");
        }
    }

    @Test
    public void validateResponseTime(){
        eTest = extentReport.createTest("GET_SingleUser > validateResponseTime");

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
        eTest = extentReport.createTest("GET_SingleUser > validateResponseHeaders");

        if (response.header("Content-Type").equals("application/json; charset=utf-8")&&
                response.header("Server").equals("cloudflare")){
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