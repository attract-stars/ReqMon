package de.fraunhofer.isst.automotive.stars.editor.editors;

import java.util.ArrayList;
import java.util.List;

public class RequirementElement {
	
private List<String> nameList;
	
	public RequirementElement() {
		this.nameList = new ArrayList<String>();
	}
	
	public void createSampleElements() {
		// TODO getModelelemts
		// add Modelelemts to list
		
		for (int i = 0; i < 10; i++) {
			nameList.add("An Object");
			nameList.add("A Relation");
			nameList.add("A Function");
		}
		
	}
	
	public String getElement(int i) {
		if (i < nameList.size()) {
			return nameList.get(i);
		}
		return null;
	}
	
	public int getElementSize() {
		return nameList.size();
	}

}
