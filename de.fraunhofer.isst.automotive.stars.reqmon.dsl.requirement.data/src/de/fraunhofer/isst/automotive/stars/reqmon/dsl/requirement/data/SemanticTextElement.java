package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

//TODO BUILD A SPECIFIC SUB CLASS THAT CAN CONTAIN THE REFERENCES
public class SemanticTextElement implements IRequirementElement {

	
	public SemanticTextElement(String str) {
		text=str;
	}
	
	public SemanticTextElement() {
		// TODO Auto-generated constructor stub
	}
	
	//TODO INSERT TYPE
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SemanticTextElement [text=" + text + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		return true;
	}

	@Override
	public String getElementName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequirementType getElementType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElementName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setElementType(RequirementType type) {
		// TODO Auto-generated method stub
		
	}
	
}
