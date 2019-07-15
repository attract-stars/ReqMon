package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

public class TestAppRequirementElement implements IRequirementElement {
	
	private String name;
	private RequirementType type;
	
	public TestAppRequirementElement() {}
	
	public TestAppRequirementElement(String name, RequirementType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getElementName() {
		return this.name;
	}

	@Override
	public RequirementType getElementType() {
		return this.type;
	}

	@Override
	public void setElementName(String name) {
		this.name = name;
	}

	@Override
	public void setElementType(RequirementType type) {
		this.type = type;
	}
	
	
	
	

}
