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
