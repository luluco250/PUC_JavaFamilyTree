package com.familytree.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.familytree.model.Gender;
import com.familytree.model.Man;
import com.familytree.model.Marriage;
import com.familytree.model.Person;
import com.familytree.model.Woman;
import com.familytree.util.MarriagesJsonReader;
import com.familytree.util.PeopleJsonReader;

public final class PersonManager {
	private static List<Person> people = new ArrayList<Person>();
	private static List<Marriage> marriages = new ArrayList<Marriage>();
	
	public static void readPeopleJson(String jsonFilePath) {
		new PeopleJsonReader(jsonFilePath, people);
	}
	
	public static void readMarriagesJson(String jsonFilePath) {
		new MarriagesJsonReader(jsonFilePath, people, marriages);
	}
	
	public static Man register(Man m) {
		people.add(m);
		return m;
	}
	
	public static Woman register(Woman w) {
		people.add(w);
		return w;
	}
	
	public static Person register(Person p) {
		people.add(p);
		return p;
	}
	
	public static void forEach(Consumer<Person> func) {
		for (Person p : people)
			func.accept(p);
	}
	
	public static List<Person> getChildren(Person p) {
		List<Person> l = new ArrayList<Person>();
		
		if (p.getGender() == Gender.MALE)
			forEach((Person c) -> {
				if (c.getFather() == p)
					l.add(c);
			});
		else if (p.getGender() == Gender.FEMALE)
			forEach((Person c) -> {
				if (c.getMother() == p)
					l.add(c);
			});
		
		return l;
	}
}
