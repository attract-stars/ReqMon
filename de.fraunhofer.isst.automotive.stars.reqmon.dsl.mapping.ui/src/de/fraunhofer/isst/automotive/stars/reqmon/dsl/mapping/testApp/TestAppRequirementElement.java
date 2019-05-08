package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.testApp;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.IRequirementElement;

public class TestAppRequirementElement implements IRequirementElement {
	
	private String name;
	private String type;
	
	public TestAppRequirementElement() {}
	
	public TestAppRequirementElement(String name, String type) {
		this.name = name;
		this.type = type;
	}

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
