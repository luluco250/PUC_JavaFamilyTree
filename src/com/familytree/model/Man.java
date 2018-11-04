package com.familytree.model;

import java.time.LocalDate;

public class Man implements Person {
	private long id;
	private String name;
	private LocalDate birthdate;
	private Woman mother;
	private Man father;

	public Man(long id, String name, LocalDate birthdate, Woman mother, Man father) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.mother = mother;
		this.father = father;
	}
	
	public Man(long id, String name, LocalDate birthdate) {
		this(id, name, birthdate, null, null);
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public Man asMan() {
		return this;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Gender getGender() {
		return Gender.MALE;
	}

	@Override
	public LocalDate getBirthdate() {
		return birthdate;
	}

	@Override
	public Woman getMother() {
		return mother;
	}
	
	@Override
	public Man getFather() {
		return father;
	}
}
