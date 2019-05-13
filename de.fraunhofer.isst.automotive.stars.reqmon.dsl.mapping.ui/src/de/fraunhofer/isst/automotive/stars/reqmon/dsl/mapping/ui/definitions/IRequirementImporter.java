package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import java.util.List;

public interface IRequirementImporter {
	
	public void execute(IRequirementController rc);
	
	public List<IRequirementElement> getRequirements();

	public void setPath(String path);

	public String[] getFilterExt(); // TODO: get the information from extension attribute

}
