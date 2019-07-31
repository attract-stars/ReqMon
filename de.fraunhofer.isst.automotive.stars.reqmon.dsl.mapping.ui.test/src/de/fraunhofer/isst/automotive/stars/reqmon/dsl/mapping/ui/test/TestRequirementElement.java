package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * This class implements the IRequirementElement.
 * 
 * @author sgraf
 *
 */
public class TestRequirementElement implements IRequirementElement {
	
	private String name;
	private RequirementType type;
	
	
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
