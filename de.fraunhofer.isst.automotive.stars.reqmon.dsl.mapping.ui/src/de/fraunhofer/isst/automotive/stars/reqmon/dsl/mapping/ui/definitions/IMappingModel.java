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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * The instance of this interface should provide all the information of the mapping page needed for code generation.
 * @author sgraf
 *
 */
public interface IMappingModel {
	
	/**
	 * Returns the list of mapping resources (the resources contain the contents of the mapping fields).
	 * @return mapping resources
	 */
	public List<Resource> getMappingResourceList();

	/**
	 * Sets the list of mapping resources (the resources contain the contents of the mapping fields).
	 * @param mappingResourceList the mapping resources
	 */
	public void setMappingResourceList(List<Resource> mappingResourceList);
	
	/**
	 * Returns the list of requirement elements.
	 * @return requirement elements
	 */
	public List<? extends IRequirementElement> getRequirementList();
	
	/**
	 * Sets the list of requirement elements.
	 * @param requirementList the requirement elements
	 */
	public void setRequirementList(List<? extends IRequirementElement> requirementList);
	
	/**
	 * Returns the system model.
	 * @return system model.
	 */
	public EObject getSystemModel();
	
	/**
	 * Sets the system model.
	 * @param systemModel the system model
	 */
	public void setSystemModel(EObject systemModel);
	
	public String getProjectName();
	
	public void setProjectName(String projectName);

}
