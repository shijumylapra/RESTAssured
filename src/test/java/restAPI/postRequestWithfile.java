package restAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class postRequestWithfile
{
    @Test
    public void Test() throws IOException
    {
        byte[] file = Files.readAllBytes(Paths.get("sample_data.json"));
        RestAssured.baseURI = "http://localhost:3000/employees";
        RequestSpecification request = RestAssured.given();
        Response response = request.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(file)
                .post();
        String resBody = response.getBody().asString();
        int resCode = response.statusCode();
        System.out.println(resBody+ " "+resCode);
    }
}
