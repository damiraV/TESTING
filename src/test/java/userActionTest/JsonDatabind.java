package userActionTest;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDatabind {
	
	@Test
	  public void databindTest() throws Exception {
	    
	    String jsonString = "{\"name\" : \"Adam\",\"age\" : 21}" ; 
	    ObjectMapper om  = new ObjectMapper() ; 
	    Person obj = om.readValue( jsonString , Person.class ) ; 
	    
	    System.out.println(jsonString);
	    System.out.println(obj);
	    
	    String convertedStr = om.writeValueAsString(obj) ; 
	    System.out.println( convertedStr );
	    

	  }

}
class Person{
	String name;
	int age;
	
	public Person() {
		super();
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}