package userActionTest;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UserActionClass {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://www.damira.dev.cc/wp-json";
		basePath = "/wp/v2";
	}

	@Test
	public void testPublicUserOnly() {
		given().relaxedHTTPSValidation().auth().preemptive().basic("damira_abd", "admin").when().log().all()
				.get("/users").then().statusCode(200).header("Content-Type", "application/json; charset=UTF-8")
				.body("id", hasSize(1)).body("name", hasItem("damira_abd"));
	}

	@Test
	public void userShouldntBeAbleToCreateNewUSer() {
		given().relaxedHTTPSValidation().
		// .auth().preemptive().basic("admin", "admin").
				when().log().all()
				.body("{" + "  \"username\" : \"user_b\" ,\n" + "  \"name\" : \"user b\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" + "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"s@aaa.com\" ,\n" + "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.contentType(ContentType.JSON).post("/users").then()
				// .statusCode(HttpStatus.SC_UNAUTHORIZED)
				.statusCode(401).contentType(ContentType.JSON)
				// .header("Content-Type", "application/json; charset=UTF-8")
				.body("code", is("rest_cannot_create_user"))
		// .body("name", hasItem("admin") )
		//
		;

	}

	@Test
	public void testAdminUserShouldBeAble_CreateNewUser() {

		given().relaxedHTTPSValidation().auth().preemptive().basic("damira_abd", "admin").when().log().all()
				.body("{" + "  \"username\" : \"user_d\" ,\n" + "  \"name\" : \"user c\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" + "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"ss@aaa.com\" ,\n" + "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.contentType(ContentType.JSON).post("/users").then()
				// .statusCode(HttpStatus.SC_CREATED)
				.statusCode(201).contentType(ContentType.JSON)
				// .header("Content-Type", "application/json; charset=UTF-8")
				.body("username", is("user_d"))
		// .body("name", hasItem("admin") )
		;
	}
	@Test
	public void adminUserShouldBeAbleToUpdateTheUser() {
	given().relaxedHTTPSValidation()
	.auth().preemptive().basic("damira_abd", "admin")
	.when().log().all()
	.body("{\n" + 
			"\"username\":\"user_c\",\n" + 
			"	\"first_name\" : \"Damiraa\", \n" + 
			"	\"last_name\" : \"admin updated\" ,\n" + 
			"	\"email\" : \"sss@gg.com\" ,\n" + 
			"	\"role\":\"author\",\n" + 
			"	\"password\" : \"user\" \n" + 
			"	}")
	.contentType(ContentType.JSON)
	.put("/users/2")
	.then()
	.statusCode(200).contentType(ContentType.JSON)
//	.body("username", is("damira_abd"))
	;
	}

	  
	  @Test
	  public void testAdminUserShouldBeAbleToDeleteUser() {
	    given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("damira_abd", "admin")
	      .queryParam("force", true)
	      .param("reassign", 1)   /// you can also use this for query parameter
	      .pathParam("id",4)
	    .when()
	      .log().all()
	      .delete("/users/{id}")
	      //.delete("/users/4")
	      
	    .then()
	      .statusCode(HttpStatus.SC_OK)
	      //.statusCode(200)
	      .contentType(ContentType.JSON)
	      .body("deleted", is(true) )
	    
	      ;
	  }
	  @Test
	  public void publicUser_ShouldNotBeAbleto_View_ExistingUser_otherThanAdmin() {
	    
	    
	  given()
	    .relaxedHTTPSValidation()
	    .auth().preemptive().basic("user_c", "user")
	    .pathParam("id",2)
	  .when()
	    .log().all()
	    .get("/users/{id}")
	    //.delete("/users/4")
	    
	  .then()
	    .statusCode(HttpStatus.SC_FORBIDDEN)
	    //.statusCode(403)
	    .contentType(ContentType.JSON)
	    .body("message", is("Sorry, you are not allowed to list users.") )
	    ;
	    
	    
	  given()
	    .relaxedHTTPSValidation()
	    .auth().preemptive().basic("user_c", "user")
	    .pathParam("id",1)
	  .when()
	    .log().all()
	    .get("/users/{id}")
	    
	  .then()
	    .statusCode(HttpStatus.SC_OK)
	    //.statusCode(200)
	    .contentType(ContentType.JSON)
	    .body("id", is(1) )
	  ;
	  
	  
	  
	  }  
	  
}
