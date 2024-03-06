package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.checkerframework.checker.units.qual.h;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matcher.*;


public class testCase_API_01 {
  static String token;
  static String userID; 
  @Test  (groups = {"API_Tests"},priority = 1)
  public static void tc01(){

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

        //-------------------------------
        RestAssured.basePath="/api/v1/login";
        data.remove("confirmpassword");
        res=  RestAssured.given()
        .contentType("application/json")
        .body(data.toString()).log().all().when().post();
        Assert.assertEquals(res.getStatusCode(),201);
        
      //  JsonPath jp=new JsonPath(res.body().toString());
       // Assert.assertTrue(jp.getBoolean("success"));
       Assert.assertTrue(  res.body().jsonPath().getBoolean("success") );
       // token=jp.getString("data.token");
       token= res.body().jsonPath().getString("data.token");
        Assert.assertNotNull(token);
       userID= res.body().jsonPath().getString("data.id");
        Assert.assertNotNull(userID);
    }
    
   
}
