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


}
