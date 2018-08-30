package restassuredtest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
public class restassured {

	@BeforeClass
	public void setUp() {
		
		RestAssured.baseURI = "http://www.damira.dev.cc/wp-json" ;
		basePath = "/wp/v2" ; 
		
	}
	@Test @Ignore
	public void firstTest() {
	when().get("http://73.166.37.2:1000/ords/hr/jobs")
	.then().statusCode(200);	
	}
	
	@Test
	public void secondTest() {
		given()
		.relaxedHTTPSValidation().
		when()
		.log().all()
		.queryParam("per_page", 1)
		.get("http://www.damira.dev.cc/wp-json/wp/v2/posts/8")
		.then().statusCode(200);		
	}
	
	/* Given rest end point
     *   http://www.damira.dev.cc/wp-json/wp/v2/posts"      
     * When i send a HTTP Get request to the server
        Then i should get 200 OK result as status code
        and id field should be 24
     * */
	@Test
	public void idTest() {
		System.out.println("Damira");
		given()
		.relaxedHTTPSValidation().
		when()
		.get("http://www.damira.dev.cc/wp-json/wp/v2/posts/8")
		.then().log().all()
		.statusCode(200)
		.and()
		.body("id", equalTo(8))
		.log().all()
		.body("title.rendered", equalTo("abc"))
		.body("status", equalTo("publish"))
		.body("author", equalTo(1));	
		
	}
	@Test
	public void Four_Test(){
		given()
			.relaxedHTTPSValidation()	
		.when()
			.get("/posts")
		.then()
			.statusCode(200) ; 
		
	}
	
	 @Test
	  public void simpleGetRequestForSingleItem() {
	    
	    given()
	      .relaxedHTTPSValidation()
	    .when()
	      .log().all()
	      //.queryParam("per_page",2)  
	      .pathParam("whatever", 8)
	      .get("/posts/{whatever}") 
	      //.get("/posts/{whatever}" , 24)
	    .then()
	      .log().all() 
	      .assertThat()
	      .statusCode(200)
	      .and()
	      .body("id",  equalTo(8))
	      .body("title.rendered", is("abc") )
	      .body("sticky", is(false));
	
	 }
	 
	 @Test
	 public void simplePostTest() {
		 given()
		 .relaxedHTTPSValidation()
		 .auth()
		 .basic("damira_abd", "vTS5im9v@n594JE8X")
		 .contentType(ContentType.JSON)
		 .when()
		 .body("{\"title\",\"myTitle\"")
		 .post("/posts");
		 
	 }
	 
	 //@Test
	  public void simplePostTest1() {
	    
	    given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("damira_abd","admin")
	      .contentType(ContentType.JSON).
	      
	    when()
	      .body("{\n" + 
	          "  \"title\":\"YY -- API DAY 3 POST time for post request\" ,\n" + 
	          "  \"content\": \"YY ASWESOME CONTENT\",\n" + 
	          "  \"status\" : \"publish\"\n" + 
	          "}")
	      .log().all()
	      .post("/posts").
	    then()
	      .log().all()
	      .statusCode(201) ; 
	      
	      ; 
	    
	  }
	    
	  
	  
	  
	  
	  
	  //@Test
	  public void simplepPutTest() {
	    
	    given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("admin","admin")
	      .contentType(ContentType.JSON).
	      
	    when()
	      .body("{\n" + 
	          "  \"title\":\"YY -- API DAY 3 POST time for PUT request\" " + 
	          "}")
	      .log().all()
	      .pathParam("newID",29)
	      .put("/posts/{newID}").
	    then()
	      .log().all()
	      .statusCode(200)
	      .body("title.raw", is("YY -- API DAY 3 POST time for PUT request") ) 
	      
	      ; 
	    
	    
	    
	  }
	  
	  @Test
	  public void simpleDeleteTest() {
	    
	    
	    given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("admin", "admin").
	    when() 
	      .pathParam("deleteID", 43)
	      .queryParam("force", true)
	      .delete("/posts/{deleteID}").
	      //.delete("/posts/29")
	    then()
	      .statusCode(200)
	      .body("deleted",is(true) ) 
	      ;
	      
	      
	    
	    
	    
	  }
	 
	 
}
