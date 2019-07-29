package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import java.util.List;

/**
 * An implementation of this interface should be able to manage several IRequirementImporters.
 * It must implement the updateList method to make the updating of the mapping list possible.
 * 
 * @author sgraf
 *
 */
public interface IRequirementController {
	
	/**
	 * Updates the mapping list with the given elements.
	 * @param requirements List of RequirementElements
	 */
	public void updateList(List<? extends IRequirementElement> requirements);
	

}
