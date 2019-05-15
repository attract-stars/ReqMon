package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * An implementation of this interface must provide a name and a type of a requirement element.
 * For any element it will be created a box item in the mapping list. 
 * The type is needed for the group name of the box item. The possible types are OBJECT, RELATION or FUNCTION.
 * 
 * @author sgraf
 *
 */
public interface IRequirementElement {
	
	/**
	 * Returns the element name.
	 * @return the element name
	 */
	public String getElementName();
	
	/**
	 * Returns the element enum type.
	 * @return OBJECT, RELATION or FUNCTION
	 */
	public RequirementType getElementType();
	
	/**
	 * Sets the element name.
	 * @param name
	 */
	public void setElementName(String name);
	
	/**
	 * Sets the element type.
	 * @param type OBJECT, RELATION or FUNCTION
	 */
	public void setElementType(RequirementType type);
	
}
