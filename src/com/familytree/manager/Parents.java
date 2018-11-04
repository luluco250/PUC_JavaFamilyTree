package com.familytree.manager;

import com.familytree.model.Man;
import com.familytree.model.Woman;

public final class Parents {
	public Woman mother = null;
	public Man father = null;
	
	public Parents() {}
	
	public Parents(Woman mother, Man father) {
		this.mother = mother;
		this.father = father;
	}
}
