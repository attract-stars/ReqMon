package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

public interface ISystemImorter {
	
	public String getPath();
	
	public void setPath(String path);
	
	public void execute(); // TODO : Controller as parameter
	
	public String[] getFilterExt(); //TODO : Just for the moment

}
