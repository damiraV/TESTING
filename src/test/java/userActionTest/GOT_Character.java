package userActionTest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GOT_Character {
	@JsonProperty("_id")
String id;
String gender;
String house;
String name;
public  GOT_Character() {
	
}
public GOT_Character(String id, String gender, String house, String name) {
	super();
	this.id = id;
	this.gender = gender;
	this.house = house;
	this.name = name;
}

@Override
public String toString() {
	return "GOT_Character [id=" + id + ", gender=" + gender + ", house=" + house + ", name=" + name + "]";
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getHouse() {
	return house;
}

public void setHouse(String house) {
	this.house = house;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}
