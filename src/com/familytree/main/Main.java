package com.familytree.main;

import com.familytree.manager.PersonManager;
import com.familytree.model.Person;
import com.familytree.util.PeopleJsonReader;

public final class Main {
	public static void main(String[] args) {
		PersonManager.readPeopleJson("people.json");
		PersonManager.readMarriagesJson("marriages.json");
		
		PersonManager.forEach((Person p) -> {
			System.out.println("Filhos de " + p.getName() + ':');
			
			for (Person child : PersonManager.getChildren(p))
				System.out.println("  " + child.getName());
		});
	}
}
