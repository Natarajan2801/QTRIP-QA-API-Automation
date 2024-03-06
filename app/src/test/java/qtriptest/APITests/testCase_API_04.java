package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class testCase_API_04 {

    @Test (groups = {"API_Tests"} ,priority = 4)
    public static void tc04(){

        RestAssured.baseURI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath="/api/v1/register";
        String email="natraj"+UUID.randomUUID()+"@gmail.com";
        String password=UUID.randomUUID().toString();
        String confirmpassword= password;
         
        JSONObject data = new JSONObject();
        data.put("email",email );
        data.put("password", password);
        data.put("confirmpassword",confirmpassword);
      Response res=  RestAssured.given()
        .contentType("application/json")
        .body(data.toString()).when().post();
        Assert.assertEquals(res.getStatusCode(),201);

        Response res1=  RestAssured.given()
        .contentType("application/json")
        .body(data.toString()).when().post();
        Assert.assertEquals(res1.getStatusCode(),400);
        Assert.assertEquals( res1.body().jsonPath().getString("message"),"Email already exists");

    }
    }

  

