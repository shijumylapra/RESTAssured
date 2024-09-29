package restAuth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class generateToken
{
    Response response;
    @Test
    public void Test()
    {
        getStudent(generateToken());
    }
    public String generateToken() {

        Response response =RestAssured.given()
                .baseUri("http://localhost:8088/oauth/token")
                .auth()
                .basic("rest-assured", "password")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("username", "onlyfullstack")
                .formParam("password", "secret")
                .when()
                .post();

        JsonPath json = response.jsonPath();
        String token = json.get("access_token");
        System.out.println("Token values is " + token);
        return token;
    }

    void getStudent(String sToken)
    {
        RestAssured.given()
                .baseUri("http://localhost:8088/students/3")
                .auth()
                .oauth2(sToken)
                .when()
                .get()
                .then()
                .statusCode(200)
                .log()
                .all();

    }
}
