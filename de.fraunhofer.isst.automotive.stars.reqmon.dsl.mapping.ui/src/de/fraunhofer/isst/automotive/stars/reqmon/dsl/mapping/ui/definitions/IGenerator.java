package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * An implementation of this interface should generate code based on the mapping of the requirements with the system.
 * 
 * @author sgraf
 *
 */
public interface IGenerator {
	
	/**
	 * Generates code based on the mapping of the requirements with the system.
	 * @param resource the resource of the mapping input
	 */
	public void generate(List<Resource> resource); // TODO: parameters ?

}
