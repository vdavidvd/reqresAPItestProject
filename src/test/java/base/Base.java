package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static RequestSpecification httpRequest;
    public static Response response;
    public static Properties prop;

    @BeforeSuite
    public void setup() throws IOException {
        RestAssured.baseURI = "https://reqres.in/api";
        prop = new Properties();
        prop.load(new FileInputStream("src/test/java/base/info.properties"));
    }
}
