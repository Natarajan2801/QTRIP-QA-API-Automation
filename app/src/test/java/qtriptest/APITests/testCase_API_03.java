package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.UUID;

public class testCase_API_03 {
    @Test  (groups = {"API Tests"})
    public static void tc03(){
        RestAssured.baseURI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath="/api/v1/reservations/new";
        String token ="Bearer "+testCase_API_01.token;
        String userid=testCase_API_01.userID;

        JSONObject js=new JSONObject();
        js.put("userId", userid);
        js.put("name", "testUser");
        js.put("date", "2024-15-09");
        js.put("person", "2");
        js.put("adventure","2447910730");

        Response res=RestAssured.given().contentType("application/json").
        header("Authorization",token).body(js.toString()).log().all().when().post();
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertTrue(res.body().jsonPath().getBoolean("success"));
    }
}
