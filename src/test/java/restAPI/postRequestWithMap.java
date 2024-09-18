package restAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
public class postRequestWithMap
{
    @Test
    public void TestMap()
    {
        HashMap<String,String> obj = new HashMap<>();
        obj.put("id","4");
        obj.put("name","Alex");
        obj.put("salary","13000");

        RestAssured.baseURI = "http://localhost:3000/employees";
        RequestSpecification request = RestAssured.given();
        Response response = request.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(obj)
                .put("/4");

        String resBody = response.getBody().asString();
        int resCode = response.statusCode();
        System.out.println(resBody+ " "+resCode);

        //getRequest GR = new getRequest();
        //GR.fetch_name();

    }
}
