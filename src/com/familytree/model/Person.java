package com.familytree.model;

import java.time.LocalDate;
import java.time.Period;

public interface Person {
	public long getId();
	public String getName();
	public Gender getGender();
	public LocalDate getBirthdate();
	public Woman getMother();
	public Man getFather();
	
	public default Man asMan() {
		throw new ClassCastException(getName() + " is not a Man");
	}
	
	public default Woman asWoman() {
		throw new ClassCastException(getName() + " is not a Woman");
	}
	
	public default int getAge() {
		return Period.between(getBirthdate(), LocalDate.now()).getYears();
	}
}
