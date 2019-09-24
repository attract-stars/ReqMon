package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model;

import java.util.List;

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
	

	public List<Resource> getMappingResourceList() {
		return mappingResourceList;
	}

	public void setMappingResourceList(List<Resource> mappingResourceList) {
		this.mappingResourceList = mappingResourceList;
	}

	@Override
	public List<IRequirementElement> getRequirementList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRequirementList(List<IRequirementElement> requirementList) {
		// TODO Auto-generated method stub
		
	}
	
	

}
