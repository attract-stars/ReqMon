package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import java.util.Comparator;

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

	@Override
	public Comparator<? super IRequirementElement> getComparator() {
		
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
				return 1;
			}
		};
			
	}

	

}
