package userActionTest;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONPath {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://www.damira.dev.cc/wp-json";
		basePath = "/wp/v2";
	}
@Test
public void JsonPath() {
	Response response=given().relaxedHTTPSValidation()
			.when().log().all()
			.get("/users");
	System.out.println(response.asString());
	JsonPath json=response.jsonPath();
	System.out.println("NAME OF THE USER :"+json.getString("name"));
	System.out.println("LINK OF HOW TO GET THE USER IS : "+json.getString("link"));
	System.out.println(json.getString("_links.self[0]"));
	System.out.println(json.getString("slug"));
}
@Test
public void testDriversJSONPath() {
	
Response response = 
			
	given()
		.relaxedHTTPSValidation()
	.when()
		.log().all()
		.get("http://ergast.com/api/f1/drivers.json");

	System.out.println(response.asString());
	response.prettyPrint();
	
	JsonPath jsonPath = response.jsonPath() ;
	
	
	System.out.println(jsonPath.getString("MRData.DriverTable.Drivers[1].givenName"));
   
    
    
  }
	

}
