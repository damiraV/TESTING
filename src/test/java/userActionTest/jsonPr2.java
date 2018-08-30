package userActionTest;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class jsonPr2 {
	  Faker faker=new Faker();
	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://www.batch8-api-site.dev.cc";
		basePath = "wp-json/wp/v2";
	}
	  @Test
	  public void test() {
		  
	    given()
	    .relaxedHTTPSValidation()
	    .auth().preemptive().basic("admin", "admin")
	    .contentType(ContentType.JSON)
	    .body("{\"title\":\"my newTitle\",\"content\""
	    		+ ":\"my super content with tag\",\"tags\" "
	    		+ ": 2,\"status\":\"publish\"}").
	  when()
	    .log().all()
	    .post("/posts").
	  then()
	    //.statusCode(HttpStatus.SC_CREATED)
	    .statusCode(201)
	    .header("Content-Type" , containsString("application/json") );
//	    .body("title.raw", is("abc") ) ; 
	    
	  }
	  @Test
	  public void test_PassPOJO_ToBoy() {
	    

//		    Map<String,Object> mp = new HashMap<>() ; 
//		    mp.put("title", faker.book().title());
//		    mp.put("content", "super");
//		    mp.put("status", "publish");
		
		    
	    PostBody p = new PostBody("myTitle","content of post","publish");

	    given()
	    .relaxedHTTPSValidation()
	    .auth().preemptive().basic("admin", "admin")
	    .contentType(ContentType.JSON)
	    .body(p).
//	    .body("{\n" + 
//	        "  \n" + 
//	        "  \"title \":\"abc\",\n" + 
//	        "  \" content\":\"nmy super\",\n" + 
//	        "  \"status\":\"publish\"\n" + 
//	        "\n" + 
//	        "}").
	  when()
	    .log().all()
	    .post("/posts").
	  then()
	    //.statusCode(HttpStatus.SC_CREATED)
	    .statusCode(201)
	    .header("Content-Type" , containsString("application/json") )
	    ;
	    //.body("title.raw", is("abc") ) ; 
	    
	  }

}
