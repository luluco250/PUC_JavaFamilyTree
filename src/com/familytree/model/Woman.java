package com.familytree.model;

import java.time.LocalDate;

public class Woman extends Man {
	private double weight, height;
	
	public Woman(long id, String name, LocalDate birthdate, double height, double weight, Woman mother, Man father) {
		super(id, name, birthdate, mother, father);
		this.height = height;
		this.weight = weight;
	}
	
	public Woman(long id, String name, LocalDate birthdate, double height, double weight) {
		this(id, name, birthdate, height, weight, null, null);
	}
	
	@Override
	public Woman asWoman() {
		return this;
	}

	@Override
	public Gender getGender() {
		return Gender.FEMALE;
	}
	
	public double getIMC() {
		return (height * height) / weight;
	}
}
