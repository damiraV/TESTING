package userActionTest;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomSerilizeDeserlization {

	@Test
	public void test() throws Exception {
		// String greeting = "{\"name\":\"Adam\" , \"message\":\"Hello\"}" ;
		String greeting = "{\"name\":\"Adam\" , \"message\":\"Hello\" , \"extraMessage\":\"goodbye\"}";
		ObjectMapper mapper = new ObjectMapper();
		Greeting g1 = mapper.readValue(greeting, Greeting.class);
		System.out.println(g1);
		// converting mapper object to Json
		String str = mapper.writeValueAsString(g1);
		System.out.println("//converting mapper object to Json" + str);

	}

	@Test
	public void GOT_Testing() throws Exception {
		String str = " [\n" + "    {\n" + "        \"_id\": \"56ffc5be043244081938576d\",\n"
				+ "        \"male\": true,\n" + "        \"house\": \"House Hightower\",\n"
				+ "        \"slug\": \"Abelar_Hightower\",\n" + "        \"name\": \"Abelar Hightower\",\n"
				+ "        \"__v\": 0,\n" + "        \"pageRank\": 2.5,\n" + "        \"books\": [\n"
				+ "            \"The Hedge Knight\"\n" + "        ],\n"
				+ "        \"updatedAt\": \"2016-04-02T13:14:38.834Z\",\n"
				+ "        \"createdAt\": \"2016-04-02T13:14:38.834Z\",\n" + "        \"titles\": [\n"
				+ "            \"Ser\"\n" + "        ]\n" + "    },\n" + "    {\n"
				+ "        \"_id\": \"56ffc5be043244081938576e\",\n" + "        \"male\": true,\n"
				+ "        \"house\": \"House Frey\",\n" + "        \"slug\": \"Addam_Frey\",\n"
				+ "        \"name\": \"Addam Frey\",\n" + "        \"__v\": 0,\n" + "        \"pageRank\": 4.5,\n"
				+ "        \"books\": [\n" + "            \"The Mystery Knight\"\n" + "        ],\n"
				+ "        \"updatedAt\": \"2016-04-02T13:14:38.875Z\",\n"
				+ "        \"createdAt\": \"2016-04-02T13:14:38.875Z\",\n" + "        \"titles\": [\n"
				+ "            \"Ser\"\n" + "        ]\n" + "    },\n" + "    {\n"
				+ "        \"_id\": \"56ffc5be043244081938576f\",\n" + "        \"male\": true,\n"
				+ "        \"slug\": \"Addam\",\n" + "        \"name\": \"Addam\",\n" + "        \"__v\": 0,\n"
				+ "        \"pageRank\": 1.5,\n" + "        \"books\": [\n" + "            \"The Mystery Knight\"\n"
				+ "        ],\n" + "        \"updatedAt\": \"2016-04-02T13:14:38.877Z\",\n"
				+ "        \"createdAt\": \"2016-04-02T13:14:38.877Z\",\n" + "        \"titles\": [\n"
				+ "            \"Ser\"\n" + "        ]\n" + "    } ] ";
		
		ObjectMapper mapper = new ObjectMapper();
		GOT_Character[] characters = mapper.readValue(str, GOT_Character[].class);
		System.out.println("Game Of Thrones : "+Arrays.toString(characters));
		
		
		
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Greeting {

	String name;
	String message;

	public Greeting() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Greeting [name=" + name + ", message=" + message + "]";
	}

	public Greeting(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// extra field
	// String extraMessage ;

}