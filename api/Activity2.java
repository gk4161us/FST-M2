package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {
    final static String BASE_URI = "https://petstore.swagger.io/v2/user";

    @Test(priority = 1)
    public void addUserPostTest() {
        String requestBody = "{\"id\":7878,\"username\":\"GunaInc1\",\"firstName\":\"Gunasekhar\",\"lastName\":\"Keserla\",\"email\":\"simple@mail.com\", \"password\":\"password123\",\"phone\":\"1234567890\"}";

        Response response = given().contentType(ContentType.JSON).body(requestBody)
                .when().post(BASE_URI);


        //Assertions
        response.then().body("code", equalTo(200));
        response.then().body("message",equalTo("7878"));

    }
    @Test(priority = 2)
    public void  getUserTest(){
        Response response = given().contentType(ContentType.JSON)
                .pathParam("username", "GunaInc1")
                .when().get(BASE_URI + "/{username}");

        String responseBody = response.getBody().asPrettyString();

        // print responseBody
        System.out.println(responseBody);


        //Assertions;

        response.then().body("id",equalTo(7878));
        response.then().body("username",equalTo("GunaInc1"));
        response.then().body("firstName",equalTo("Gunasekhar"));
        response.then().body("lastName",equalTo("Keserla"));
        response.then().body("email",equalTo("simple@mail.com"));
        response.then().body("phone",equalTo("1234567890"));
        response.then().body("password",equalTo("password123"));

    }
    @Test(priority = 3)
    public void deleteUser(){
        Response response = given().contentType(ContentType.JSON)
                .pathParam("username", "GunaInc1")
                .when().delete(BASE_URI + "/{username}");

        //Assertion:

        response.then().body("code", equalTo(200));

    }
}


