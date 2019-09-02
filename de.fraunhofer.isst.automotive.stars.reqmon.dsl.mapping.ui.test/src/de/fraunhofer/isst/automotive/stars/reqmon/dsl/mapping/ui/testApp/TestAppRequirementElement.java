package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.util.Comparator;

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
	
	
	@Override
	public Comparator<? super IRequirementElement> getElementTypeComparator() {
		
		return new Comparator<IRequirementElement>() {

			@Override
			public int compare(IRequirementElement o1, IRequirementElement o2) {
				if (o1.getElementType().equals(o2.getElementType())) {
					return 0;
				}
				else if (o1.getElementType().equals(RequirementType.OBJECT)) {
					return -1;
				}
				else if (o1.getElementType().equals(RequirementType.FUNCTION) && 
						o2.getElementType().equals(RequirementType.RELATION)) {
					return -1;
				}
				else {
					return 1;
				}
			}
		};
			
	}
	
	

}
