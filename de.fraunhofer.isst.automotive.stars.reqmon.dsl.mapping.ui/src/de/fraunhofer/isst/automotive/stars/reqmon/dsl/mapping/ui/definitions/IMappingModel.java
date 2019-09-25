package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface IMappingModel {
	
	
	public List<Resource> getMappingResourceList();

	public void setMappingResourceList(List<Resource> mappingResourceList);
	
	public List<IRequirementElement> getRequirementList();
	
	public void setRequirementList(List<IRequirementElement> requirementList);
	
	public EObject getSystemModel();
	
	public void setSystemModel(EObject systemModel);

}
