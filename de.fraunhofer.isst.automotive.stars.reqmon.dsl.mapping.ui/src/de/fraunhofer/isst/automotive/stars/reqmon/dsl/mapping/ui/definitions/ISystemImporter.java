package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

/**
 * An implementation of this class should be able to import a system file:
 * parse its content and create an (ecore) model that is suitable to complete 
 * the mapping language with system elements.
 * 
 * @author sgraf
 *
 */
public interface ISystemImporter {
	
	/**
	 * Executes the parsing of the given system file and 
	 * provides system language elements for the mapping language. 
	 * @param path the path of the system file
	 */
	public void execute(String path); 
	
	

}
