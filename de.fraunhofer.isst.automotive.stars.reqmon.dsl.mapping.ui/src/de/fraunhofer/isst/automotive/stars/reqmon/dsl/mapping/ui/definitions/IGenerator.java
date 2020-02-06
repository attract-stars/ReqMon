package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;


/**
 * An implementation of this interface should generate code based on the mapping of the requirements with the system.
 * 
 * @author sgraf
 *
 */
public interface IGenerator {
	
	/**
	 * Generates code based on the informations of the given model.
	 * @param model all informations that can be used for the code generation
	 * @param projectName the name of the mapping project
	 */
	public void generate(IMappingModel model, String projectName); 

}
