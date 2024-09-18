package restAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class getRequest
{
    String resBody;
    public void Test()
    {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification requestCall = RestAssured.given();
        //Response responseCall = RestAssured.given().get();
        Response responseCall = requestCall.get();
        resBody = responseCall.getBody().asString();

        System.out.println(resBody+" "+responseCall.statusCode());
        System.out.println(responseCall.statusCode());
    }
    public void fetch_name()
    {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification requestCall = RestAssured.given();
        //Response responseCall = RestAssured.given().get();
        Response responseCall = requestCall.get();
        resBody = responseCall.getBody().asString();

        JsonPath jPath = responseCall.jsonPath();
        List<String> firstnames = jPath.get("data.first_name");
        for(String fname:firstnames)
        {
            System.out.println(fname);
        }
        System.out.println("Third Name - " + firstnames.get(3));

        System.out.println(resBody+" "+responseCall.statusCode());
        System.out.println(responseCall.statusCode());
    }
}
