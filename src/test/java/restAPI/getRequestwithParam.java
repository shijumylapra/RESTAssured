package restAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getRequestwithParam
{
    @Test
    public void Test()
    {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/3");

        System.out.println(response.getBody().asString());
        System.out.println(response.statusCode());

        int code = response.statusCode();
        String resBody = response.getBody().asString();
        Assert.assertEquals(code,200);
        Assert.assertTrue(resBody.contains("emma"));

        //Exactly finding the first name, create an object of json path
        JsonPath json = response.jsonPath();
        String firstname = json.get("data.first_name");
        System.out.println(firstname);
        Assert.assertEquals(firstname,"Emma");

    }
}
