package liveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GitHubProject {
    RequestSpecification requestSpec;
    String sshKey;
    int sshId;

    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token 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")
                .setBaseUri("https://api.github.com")
                .build();
        sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCfB88Qnb5GKa7Kf4hGNXiLvcp015HYY2XQUrUMaVnFmX26JxrtAOMdKFQcv1L9J04JD10GIeQH9DqxIP/2yMaBvJASrLyJoePNdpv2C4QmT6rAoBMZ8N8lx+hqMGFRWAl7JuiMuAxUfwa+xpVcmhvDsS0hIr2K+UpgL6t5kAO9UQ==";
    }
    @Test(priority=1)
    public void addSSHKeyPostTest(){
        String reqBody = "{ \"title\":\"TestAPIKey\", \"key\": \"" +sshKey+ "\"}";
        Response response =
                given().spec(requestSpec)
                        .body(reqBody)
                .post("/user/keys");

        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);
       sshId = response.then().extract().path("id");
       System.out.println(sshId);

        //Assertions:
        response.then().statusCode(201);

    }
    @Test(priority=2)
    public void getKeyIdTest(){
        Response response =
                given().spec(requestSpec)
                        .when().get("/user/keys");
        //Print Response
        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);

        //Assertion:
        response.then().statusCode(200);
    }

    @Test(priority=3)
    public void deleteKeyIdTest(){
        System.out.println();
        Response response = given().spec(requestSpec).pathParam("keyId",sshId)
                        .when().delete("/user/keys/{keyId}"); // send delete request
        //Print Response
        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);

        //Assertion:
        response.then().statusCode(204);

    }


}