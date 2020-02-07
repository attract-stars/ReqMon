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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data;

import java.util.Comparator;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

//TODO BUILD A SPECIFIC SUB CLASS THAT CAN CONTAIN THE REFERENCES
public class SemanticTextElement implements IRequirementElement {

	
	public SemanticTextElement(String text,RequirementType type) {
		this.text=text;
		this.type = type;
	}
	
	public SemanticTextElement() {
		// TODO Auto-generated constructor stub
	}
	
	String text;
	RequirementType type;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the type
	 */
	public RequirementType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(RequirementType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SemanticTextElement [text=" + text +"; type="+ type+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SemanticTextElement other = (SemanticTextElement) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String getElementName() {
		return getText();
	}

	@Override
	public RequirementType getElementType() {
		return getType();
	}

	@Override
	public void setElementName(String name) {
		setText(name);
	}

	@Override
	public void setElementType(RequirementType type) {
		setType(type);		
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
