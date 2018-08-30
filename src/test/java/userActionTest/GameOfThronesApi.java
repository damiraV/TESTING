package userActionTest;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GameOfThronesApi {
@Test
public void getAllStarks() {
	Response response=given().relaxedHTTPSValidation().when().log().all()
			// .queryParam("per_page",2)
			.get("https://api.got.show/api/characters/");
	JsonPath jp=response.jsonPath();
//	String str=response.asString();
	
	List<String> Starks=jp.getList("findAll{it.house=='House Stark'}.name",String.class);
	System.out.println(Starks);
}
}
