package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model;

import java.io.Serializable;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * This class holds the information of a requirement element name and type and the associated embedded editor content.
 * 
 * @author sgraf
 *
 */
public class MappingObject implements Serializable {
	
	/**
	 * Generated SerialVersionUID.
	 */
	private static final long serialVersionUID = -1545520843536443118L;
	private String reqElemName;
	private RequirementType reqElemType;
	private String mappingText;
	
	
	public String getReqElemName() {
		return reqElemName;
	}
	
	public void setReqElemName(String reqElemName) {
		this.reqElemName = reqElemName;
	}
	
	public RequirementType getReqElemType() {
		return reqElemType;
	}
	
	public void setReqElemType(RequirementType requirementType) {
		this.reqElemType = requirementType;
	}
	
	public String getMappingText() {
		return mappingText;
	}
	
	public void setMappingText(String mappingText) {
		this.mappingText = mappingText;
	}
	
	

}
