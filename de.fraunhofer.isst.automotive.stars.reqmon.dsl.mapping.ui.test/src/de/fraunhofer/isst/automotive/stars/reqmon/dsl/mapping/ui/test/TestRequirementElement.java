package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;

public class TestRequirementElement implements IRequirementElement {
	
	private String name;
	private String type;
	
	
	@Override
	public String getElementName() {
		return this.name;
	}

	@Override
	public String getElementType() {
		return this.type;
	}

	@Override
	public void setElementName(String name) {
		this.name = name;
	}

	@Override
	public void setElementType(String type) {
		this.type = type;
	}

}
