package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;


/**
 * An implementation of this class should be able to check, if the content of the imported system model file
 * has syntax errors regarding the system definition language. This model is needed to complete the mapping language.
 * 
 * @author sgraf
 *
 */
public interface ISystemImporter {
	
	/**
	 * Checks, if the system model file of the given path contains syntax errors.
	 * @param path the path of the system model file
	 * @return true, if the file content has no sysntax errors, otherwise false
	 */
	public boolean check(String path); 
	
	

}
