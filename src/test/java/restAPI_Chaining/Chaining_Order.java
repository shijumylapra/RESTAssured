package restAPI_Chaining;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chaining_Order
{
    String uuId;Response URIresponse;RequestSpecification URIrequest;
    Gson gson = new Gson();
    @Test
    public void Test() throws IOException
    {
     UpdateOrderId();
    }
   void UpdateOrderId() throws IOException
   {
       // First API call to generate the Order ID
       RestAssured.baseURI = "http://httpbin.org/uuid";
       RequestSpecification request = RestAssured.given();
       Response response = request.get();
       System.out.println(response.statusCode()+" "+response.getBody().asString());
       System.out.println(uuId =response.jsonPath().get("uuid"));
       FileReader reader = new FileReader("order.json");
       JsonObject jsonReader = gson.fromJson(reader,JsonObject.class);
       // Update the JSON file with new OrderID
       jsonReader.addProperty("orderId",uuId);
       FileWriter FWriter = new FileWriter("order.json");
       // Write the OrderId back to the file
       gson.toJson(jsonReader,FWriter);
       FWriter.close();
       System.out.println("JSON file has been updated");


       // Second API Call for place new order
       byte[] JsonFile = Files.readAllBytes(Paths.get("order.json"));
       RestAssured.baseURI = "http://httpbin.org/post";
       URIrequest = RestAssured.given();
       System.out.println((URIresponse = URIrequest.contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(JsonFile)
               .post()).getBody().asString()+URIresponse.statusCode());
   }

}
//mvn clean test

