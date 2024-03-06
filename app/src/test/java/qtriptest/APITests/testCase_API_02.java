package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
public class testCase_API_02 {

@Test  (groups = {"API Tests"})
public static void tc02(){
    RestAssured.baseURI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
    RestAssured.basePath="/api/v1/cities";
    Response res=RestAssured.given().queryParam("q", "beng").log().all().when().get();
    Assert.assertEquals(res.getStatusCode(),200);

    JsonPath jp=new JsonPath(res.body().asString());

   System.out.println(jp.getString("[0].description"));
   Assert.assertEquals(jp.getString("[0].description"), "100+ Places");
   List<JSONObject> list=jp.getList("$");
   Assert.assertEquals(list.size(), 1);

   //--------------schema
   File schemaFile=new File("src/test/resources/tc02schema.json");
   JsonSchemaValidator matcher=JsonSchemaValidator.matchesJsonSchema(schemaFile);
    RestAssured.given().queryParam("q", "beng").log().all().
    when().get().then().log().all().assertThat().body( matcher);

    RestAssured.given().queryParam("q", "beng").log().all().
    when().get().then().statusCode(200);

}

}
