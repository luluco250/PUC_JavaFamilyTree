package com.familytree.util;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.familytree.model.Marriage;
import com.familytree.model.Person;

public final class MarriagesJsonReader {
	public MarriagesJsonReader(String jsonFilePath, List<Person> people, List<Marriage> marriages) {
		JSONArray ja;
		
		try {
			ja = (JSONArray)new JSONParser().parse(new FileReader(jsonFilePath));
		} catch (Exception e) {
			System.err.println("Error parsing '" + jsonFilePath + "':");
			e.printStackTrace();
			return;
		}
		
		try {
			for (Object obj : ja) {
				JSONObject jo = (JSONObject)obj;
				
				long person1Id = ((Long)jo.get("person1")).longValue();
				long person2Id = ((Long)jo.get("person2")).longValue();
				
				Person person1 = null;
				Person person2 = null;
				
				for (Person p : people)
					if (p.getId() == person1Id)
						person1 = p;
					else if (p.getId() == person2Id)
						person2 = p;
				
				if (person1 == null)
					throw new RuntimeException("There is no person with ID #" + person1Id);
				if (person2 == null)
					throw new RuntimeException("There is no person with ID #" + person2Id);
				
				LocalDate date = LocalDate.parse((String)jo.get("date"));
				Marriage.State state;
				
				switch ((String)jo.get("state")) {
				case "married":
					state = Marriage.State.MARRIED;
					break;
				case "divorced":
					state = Marriage.State.DIVORCED;
					break;
				case "widowed":
					state = Marriage.State.WIDOWED;
					break;
				default:
					throw new RuntimeException("Invalid marriage state, must be 'married', 'divorced' or 'widowed'");
				}
				
				marriages.add(new Marriage(person1, person2, date, state));
			}
		} catch (Exception e) {
			System.err.println("Error parsing '" + jsonFilePath + ":");
			e.printStackTrace();
		}
	}
}
