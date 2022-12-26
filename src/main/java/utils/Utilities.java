package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Utilities {

    public static ExtentReports generateExtentReport(){
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReports/eReport.html");
        sparkReporter.config().setReportName("ReqRes API Automation Test");
        sparkReporter.config().setDocumentTitle("ReqRes | QA");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");

        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application URL","https://reqres.in/api");
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Operating System Version",System.getProperty("os.version"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("Tested By","David");

        return extentReports;
    }
}
