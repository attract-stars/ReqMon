package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

/**
 * An implementation of this interface must be able to import a requirement file: parse its content 
 * and update the mapping list of the language mapping editor with the updateList method of the IRequirementController.  
 * 
 * @author sgraf
 *
 */
public interface IRequirementImporter {
	
	/**
	 * Executes the parsing of the input file and the updating of the mapping list
	 * @param rc an implementation of the IRequirementController 
	 * @param path the path of the input file
	 */
	public void execute(IRequirementController rc, String path);
	
	

}
