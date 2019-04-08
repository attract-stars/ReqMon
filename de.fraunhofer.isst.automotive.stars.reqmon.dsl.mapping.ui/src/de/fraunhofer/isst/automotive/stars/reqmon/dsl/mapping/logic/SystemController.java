package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.SystemElement;

public class SystemController {
	
	private ExtensionsHandler handler;
	private SystemElement sysElem;
	
	public SystemController(ExtensionsHandler handler) {
		this.handler = handler;
		this.sysElem = new SystemElement();
	}
	
	public void setPath(String path) {
		sysElem.setPath(path);
	}
	
	public String[] getFilterExt() {
		return sysElem.getFilterExt();
	}
	
	public void executeSystem() {
		
	}
	

}
