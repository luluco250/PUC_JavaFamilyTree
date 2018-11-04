package com.familytree.model;

import java.time.LocalDate;

public final class Marriage {
	public enum State {
		UNDEFINED,
		MARRIED,
		DIVORCED,
		WIDOWED
	}
	
	private Person person1, person2;
	private LocalDate date;
	private State state;
	
	public Marriage(Person person1, Person person2, LocalDate date, State state) {
		this.person1 = person1;
		this.person2 = person2;
		this.date = date;
		this.state = state;
	}
	
	public Marriage(Person person1, Person person2, LocalDate date) {
		this(person1, person2, date, State.MARRIED);
	}
	
	public Marriage(Person person1, Person person2) {
		this(person1, person2, LocalDate.now());
	}
	
	public Person getPerson1() {
		return person1;
	}
	
	public Person getPerson2() {
		return person2;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public State getState() {
		return state;
	}
	
	public boolean areMarried() {
		return state == State.MARRIED;
	}
	
	public boolean areDivorced() {
		return state == State.DIVORCED;
	}
	
	public boolean areWidowed() {
		return state == State.WIDOWED;
	}
	
	public void divorce() {
		if (state == State.DIVORCED)
			throw new RuntimeException(person1.getName() + " and " + person2.getName() + " are already divorced");
		
		state = State.DIVORCED;
	}
	
	public void makeWidowed() {
		if (state == State.WIDOWED)
			throw new RuntimeException(person1.getName() + " and " + person2.getName() + " are already widowed");
		
		state = State.WIDOWED;
	}
}
