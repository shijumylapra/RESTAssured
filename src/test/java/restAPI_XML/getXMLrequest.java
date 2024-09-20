package restAPI_XML;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class getXMLrequest
{
    Response response;
    @Test
    public void fetch_details() {
        RestAssured.baseURI = "https://chercher.tech/sample/api/books.xml";
        RequestSpecification request = RestAssured.given();
        response = request.get();
        String resBody = response.getBody().asString();
        int code =  response.statusCode();
        ftech_book_title();ftech_category();
    }
    void ftech_book_title()
    {
        NodeChildrenImpl books = this.response.then().extract().path("bookstore.book.title");
        System.out.println("All the books are "+books.toString());
        System.out.println("First Book is : "+books.get(0)+"   Second Book is : "+books.get(1));
    }
    void ftech_category()
    {
        NodeChildrenImpl bookTag = this.response.then().extract().path("bookstore.book");
        for(int i=0; i <bookTag.size(); i++)
        {
            System.out.println(bookTag.get(i).getAttribute("category"));
        }
    }

}
