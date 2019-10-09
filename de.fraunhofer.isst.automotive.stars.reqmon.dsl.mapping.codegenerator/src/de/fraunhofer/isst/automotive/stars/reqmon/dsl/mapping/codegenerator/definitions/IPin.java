package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions;

public interface IPin {
	
	public String getPinName();
	
	public boolean isInputPin();
	
	public boolean isOutputPin();
	
	public String getMediaType();
	
	public String getMediaSubType();
	
	public String getObjectName();
	
	public String getPinObject();

}
