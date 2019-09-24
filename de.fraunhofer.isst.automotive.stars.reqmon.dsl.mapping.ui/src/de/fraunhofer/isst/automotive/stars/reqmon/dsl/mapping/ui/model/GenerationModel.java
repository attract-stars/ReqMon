package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * This class contains all the information the mapping page offers to the generators. 
 * 
 * @author sgraf
 *
 */
public class GenerationModel {
	
	private List<Resource> mappingResourceList;
	

	public List<Resource> getMappingResourceList() {
		return mappingResourceList;
	}

	public void setMappingResourceList(List<Resource> mappingResourceList) {
		this.mappingResourceList = mappingResourceList;
	}
	
	

}
