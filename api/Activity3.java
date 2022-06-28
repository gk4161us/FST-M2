package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity3 {

    // request specification declaration
        RequestSpecification requestSpec;

    // response specification declaration
        ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp(){
        //Create request specification
        requestSpec = new RequestSpecBuilder()
        // To Set Content Type
            .setContentType(ContentType.JSON)
        // To Set Base URI
            .setBaseUri("https://petstore.swagger.io/v2/pet")
       // Build request specification
                .build();

        responseSpec = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(200)
                //Check response content type
                .expectContentType("application/json")
                // check if response contains name property
                .expectBody("status", equalTo("alive"))
                // Build response specification
                .build();
    }
    @DataProvider
    public Object[][] petInfoDataProvider(){
        // parameters to pass to the test case
        Object[][] testData = new Object[][]{
                {78910, "Riley", "alive"},
                {78911,"Hansel", "alive"}

        };
        return testData;
    }
        @Test(priority = 1)
        public void addPets(){
            String requestBody =  "{\"id\":78910,\"name\":\"Riley\", \"status\":\"alive\"}";

            Response response = given().spec(requestSpec) // using requestSpec
                .body(requestBody) // send request body
                .when().post();   // send post request

            requestBody =  "{\"id\":78911,\"name\":\"Hansel\", \"status\":\"alive\"}";

            response = given().spec(requestSpec) // using requestSpec
                    .body(requestBody) // send request body
                    .when().post();   // send post request

            //Assertions
            response.then().spec(responseSpec);
        }

        //Test case using  a Data Provider
    @Test(dataProvider = "petInfoDataProvider", priority = 2)
    public void getPets(int id, String name, String status){
        Response response = given().spec(requestSpec)
                .pathParam("petId",id)
                .when().get("/{petId}");

        //print response
        System.out.println(response.asPrettyString());

        //Assertions
        response.then()
                .spec(responseSpec)
                .body("name", equalTo(name));
    }
    // Test case using a DataProvider
    @Test(dataProvider = "petInfoDataProvider", priority=3)
    public void deletePets ( int id, String name, String status){
        Response response = given().spec(requestSpec)
                .pathParam("petId",id)
                .when().delete("/{petId}");
        // Assertions
        response.then().body("code",equalTo(200));
    }

}
