package restAuth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class bearerToken
{
    @Test
    public void TestToken() throws IOException
    {
        byte[] file = Files.readAllBytes(Paths.get("repo.json"));
        RestAssured.baseURI = "https://api.github.com/user/repos";
        RequestSpecification request = RestAssured.given().auth().oauth2("DFGDFGDFGDFGDFGDFGDFGDFGFD");

        Response response = request.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(file)
                .post();

        String resBody = response.getBody().asString();
        int resCode = response.statusCode();
        System.out.println(resBody+ " "+resCode);
    }
}
