/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import java.util.Comparator;

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
	
	/**
	 * Returns a comparator to allow the sorting of the requirement elements by RequirementType.
	 * @return requirement element comparator
	 */
	public Comparator<? super IRequirementElement> getElementTypeComparator();
	
}
