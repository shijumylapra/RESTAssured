package restAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Delete
{
    @Test
    public void Test1() {
        RestAssured.baseURI = "http://localhost:3000/employees";
        RequestSpecification request = RestAssured.given();
        Response response = request.delete("/1");
        String resBody = response.getBody().asString();
        int resCode = response.statusCode();
        System.out.println(resBody + " " + resCode);

    }
}
