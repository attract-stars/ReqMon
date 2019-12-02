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

}
