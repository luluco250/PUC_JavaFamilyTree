package com.familytree.util;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.familytree.model.Man;
import com.familytree.model.Person;
import com.familytree.model.Woman;

public class PeopleJsonReader {
	public PeopleJsonReader(String jsonFilePath, List<Person> people) {
		JSONArray ja;
		
		try {
			ja = (JSONArray)new JSONParser().parse(new FileReader(jsonFilePath));
		} catch (Exception e) {
			System.err.println("Error parsing '" + jsonFilePath + "':");
			e.printStackTrace();
			return;
		}
		
		for (Object obj : ja) {
			JSONObject jo = (JSONObject)obj;
			Person person = null;
			Woman mother = null;
			Man father = null;
			
			if (jo.containsKey("mother")) {
				long id = ((Long)jo.get("mother")).longValue();
				try {
					for (Person p : people)
						if (p.getId() == id)
							mother = p.asWoman();
				} catch (Exception e) {
					System.err.println("Error assigning mother:");
					e.printStackTrace();
				}
			}
			
			if (jo.containsKey("father")) {
				long id = ((Long)jo.get("father")).longValue();
				try {
					for (Person p : people)
						if (p.getId() == id)
							father = p.asMan();
				} catch (Exception e) {
					System.err.println("Error assigning father:");
					e.printStackTrace();
				}
			}
			
			try {
				switch ((String)jo.get("gender")) {
				case "male":
					person = new Man(
						((Long)jo.get("id")).longValue(),
						(String)jo.get("name"),
						LocalDate.parse((String)jo.get("birthdate")),
						mother,
						father
					);
					break;
				case "female":
					person = new Woman(
						((Long)jo.get("id")).longValue(),
						(String)jo.get("name"),
						LocalDate.parse((String)jo.get("birthdate")),
						((Double)jo.get("height")).doubleValue(),
						((Double)jo.get("weight")).doubleValue(),
						mother,
						father
					);
					break;
				default:
					throw new RuntimeException("Unknown gender, must be 'male' or 'female'");
				}

				people.add(person);
			} catch (Exception e) {
				System.err.println("Error creating person:");
				e.printStackTrace();
			}
		}
	}
}
