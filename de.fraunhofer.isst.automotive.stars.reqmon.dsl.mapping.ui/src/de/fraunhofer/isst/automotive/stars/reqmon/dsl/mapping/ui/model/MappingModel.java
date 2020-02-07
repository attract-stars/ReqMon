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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;

/**
 * This class contains all the information the mapping page offers to the generators. 
 * 
 * @author sgraf
 *
 */
public class MappingModel implements IMappingModel {
	
	private List<Resource> mappingResourceList;
	private List<? extends IRequirementElement> requirements;
	private EObject systemModel;
	private String projectName;
	

	@Override
	public List<Resource> getMappingResourceList() {
		return mappingResourceList;
	}

	@Override
	public void setMappingResourceList(List<Resource> mappingResourceList) {
		this.mappingResourceList = mappingResourceList;
	}

	@Override
	public List<? extends IRequirementElement> getRequirementList() {
		return requirements;
	}

	@Override
	public void setRequirementList(List<? extends IRequirementElement> requirementList) {
		this.requirements = requirementList;
		
	}

	@Override
	public EObject getSystemModel() {
		return systemModel;
	}

	@Override
	public void setSystemModel(EObject systemModel) {
		this.systemModel = systemModel;
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	@Override
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


}
