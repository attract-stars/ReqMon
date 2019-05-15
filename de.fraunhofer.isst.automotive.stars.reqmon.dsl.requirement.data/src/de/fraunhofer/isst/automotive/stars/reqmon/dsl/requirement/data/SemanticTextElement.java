package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data;

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
	
}
