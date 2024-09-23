package restAuth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Authorization
{
    String resBody;
    @Test
    public void TestAuth()
    {
        RestAssured.baseURI = "http://httpbin.org/basic-auth/user/passwd";
        RequestSpecification request = RestAssured
                .given()
                .auth()
                .basic("user","passwd");

        Response response = request.get();
        System.out.println(response.getBody().asString());
        System.out.println(response.statusCode());

    }

}
