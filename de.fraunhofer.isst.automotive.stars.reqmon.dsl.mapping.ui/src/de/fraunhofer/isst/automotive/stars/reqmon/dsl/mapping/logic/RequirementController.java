package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.RequirementElement;

public class RequirementController {
	
	private ExtensionsHandler handler;
	private RequirementElement reqElem;
	private boolean isExtReq;
	private String[] filter;
	private int elemSize;
	private String element;
	private String type;
	
	public RequirementController(ExtensionsHandler handler) {
		this.handler = handler;
		isExtReq = handler.isRequirement();
		
		if (isExtReq) {
			handler.createRequirementElement();
			handler.createSampleElements();
		}
		else {
			this.reqElem = new RequirementElement();
			reqElem.createSampleElements();
		}
	}
	
	public void setPath(String path) {
		if (isExtReq) {
			handler.setRequirementPath(path);
		}
		else {
			reqElem.setPath(path);
		}
	}
	
	public void setFilterExt(String[] filter) {
		this.filter = filter;
	}
	
	public String[] getFilterExt() {
		if (isExtReq) {
			handler.setRequirementFilterExt(this);
			return filter;
		}
		else {
			return reqElem.getFilterExt();
		}
	}

	public void setElementSize(int elemSize) {
		this.elemSize = elemSize;
	}

	public int getElementSize() {
		if (isExtReq) {
			handler.setRequirementElemSize(this);
			return elemSize;
		}
		else {
			return reqElem.getElementSize();
		}
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	public String getElement(int index) {
		if (isExtReq ) {
			handler.setRequirementElement(index, this);
			return element;
		}
		else {
			return reqElem.getElement(index);
		}
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType(int index) {
		if (isExtReq) {
			handler.setRequirementType(index, this);
			return type;
		}
		else {
			return reqElem.getType(index);
		}
	}

	public void executeRequirement() {
		if (isExtReq) {
			handler.readRequirements();
		}
		else {
			reqElem.readFile();
		}
	}

}
