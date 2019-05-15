package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

/**
 * An implementation of this interface should be able to parse the input Strings of the mapping text field 
 * to validate if the input is correct.
 * 
 * @author sgraf
 *
 */
public interface IMappingParser {
	
	/**
	 * Parses and validates the input String of the mapping text field.
	 * @param text the input String of the mapping text field
	 */
	public void parserInput(String text); // TODO: Add a boolean return type ?

}
