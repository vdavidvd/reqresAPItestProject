package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static RequestSpecification httpRequest;
    public static Response response;
    public static Properties prop;
    public static ExtentReports extentReport;

    @BeforeSuite
    public void setup() throws IOException {
        RestAssured.baseURI = "https://reqres.in/api";
        prop = new Properties();
        prop.load(new FileInputStream("src/test/java/base/info.properties"));
        extentReport = getReporter();
    }

    @AfterSuite
    public void flushReport(){
        extentReport.flush();
    }

    public ExtentReports getReporter(){
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                "extentReports/eReport.html");
        sparkReporter.config().setReportName("ReqRes Tests");
        sparkReporter.config().setDocumentTitle("ReqRes Test Project");
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tested By","David");
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Operating System Version",System.getProperty("os.version"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("API URL","https://reqres.in/api");
        return extentReports;
    }
}
