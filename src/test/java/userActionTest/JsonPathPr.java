package userActionTest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathPr {
	Faker faker = new Faker();
	int newUserID;

	@BeforeClass
	public void init() {

		RestAssured.baseURI = "http://www.batch8-api-site.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";

	}

	@Test
	public void simpleGetRequest() {

		Response response=given().relaxedHTTPSValidation().when().log().all()
				// .queryParam("per_page",2)
				.get("/posts");
		JsonPath jp=response.jsonPath();
		System.out.println(jp.getInt("author[0]"));
		List<Object>lst=jp.getList("author");
		List<Integer> lstNum=jp.getList("author", Integer.class);
		 List<String>titles=jp.getList("title.rendered",String.class);
		 //_links.version-history[0].count
		 List<Integer> lstCount=jp.getList("_links.version-history[0].count",Integer.class);
		 System.out.println(lstCount);
	
	}
	
	@Test
	public void warmUpTask() throws Exception {
		

			Response response=given().relaxedHTTPSValidation().when().log().all()
					// .queryParam("per_page",2)
					.get("http://ergast.com/api/f1/drivers.json");
			JsonPath jp=response.jsonPath();
			List<Object> drivers=jp.getList("MRData.DriverTable.Drivers.givenName");
//			System.out.println(drivers);
			System.out.println(drivers.size());
//			Map<String,String>map=jp.getMap("MRData.DriverTable.Drivers[0]");
			//JSONPATH IS THE G PATH FROM GROOVY
			//predicate
			List<Object> info=jp.get("MRData.DriverTable.Drivers.findAll{it.givenName=='George'"
					+ " &&it.nationality=='American'}");
//			System.out.println("MRData.DriverTable.Drivers.findAll{ it.givenName.length()==3}");
			 List<String> names=jp.get("MRData.DriverTable.Drivers.findAll{it.givenName.length()==3}.familyName");
			System.out.println(names);
			
		    Driver driverObj = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class) ; 
		    System.out.println( driverObj );
		    String jsonString="{ \"driverId\": \"abate\",\"url\": "
		    		+ "\"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\","
		    		+ "\"givenName\": \"Carlo\",\"familyName\": \"Abate\","
		    		+ "\"dateOfBirth\": \"1932-07-10\",\"nationality\": \"Italian\"}";
		    ObjectMapper om  = new ObjectMapper() ; 
		    Driver obj=om.readValue(jsonString, Driver.class);
		    System.out.println( "Data with ObjectMapper : "+obj);
		    
		  }
			
			@Test
			public void anotherTest() throws Exception {
			
				    String jsonString="{ \"driverId\": \"abate\",\"url\": "
				    		+ "\"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\","
				    		+ "\"givenName\": \"Carlo\",\"familyName\": \"Abate\","
				    		+ "\"dateOfBirth\": \"1932-07-10\",\"nationality\": \"Italian\"}";
				    ObjectMapper om  = new ObjectMapper() ; 
				    Driver obj=om.readValue(jsonString, Driver.class);
				    System.out.println(obj);
			}
			
			@Test
			public void databindTestWithCollection() throws Exception {
			  
			  String jsonStringArr = 
			        "[ {\"name\":\"Adam\", \"age\":10} , {\"name\":\"john\", \"age\":12} , {\"name\":\"yuAN\", \"age\":25} ] " ;
			  
			  ObjectMapper om  = new ObjectMapper() ; 
			  Person[] arr = om.readValue(jsonStringArr, Person[].class) ; 
			  
			   System.out.println(  Arrays.toString( arr)   );
			  String jsonArr=om.writeValueAsString(arr);
			  System.out.println(jsonArr);
			  List<Person> ppl = Arrays.asList( new Person("aaa",11) , new Person("bbb",12) , new Person("ccc",13) );
			    String jsonPPL =  om.writeValueAsString(ppl) ;
			    System.out.println("JSON List ---> " +  jsonPPL  );
			    
			    // converting to an Arraylist instead of Array
			    // we need to use a typeReference object --> Type reference is a abstact class with no abstract method thats why you see body{}
			    List<Person> lst = om.readValue(jsonStringArr, new TypeReference<List<Person>>() {}  ) ;  
			    
			    System.out.println("List ---> " + lst);
			    
			}
//		
	}
	
	


