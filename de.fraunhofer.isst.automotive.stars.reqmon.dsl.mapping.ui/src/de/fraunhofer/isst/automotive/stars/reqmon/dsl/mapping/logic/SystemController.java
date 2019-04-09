package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.SystemElement;

public class SystemController {
	
	private ExtensionsHandler handler;
	private SystemElement sysElem;
	private String[] filter;
	private boolean isExtSys;
	
	public SystemController(ExtensionsHandler handler) {
		this.handler = handler;
		this.sysElem = new SystemElement();
		this.isExtSys = handler.isSystem();
		
		if (isExtSys) {
			handler.createSystemElement();
		}
		else {
			this.sysElem = new SystemElement();
		}
	}
	
	public void setPath(String path) {
		if (isExtSys) {
			handler.setSystemPath(path);
		}
		else {
			sysElem.setPath(path);
		}
	}
	
	public void setFilterExt(String[] filterExt) {
		this.filter = filterExt;
	}
	
	public String[] getFilterExt() {
		if (isExtSys) {
			handler.setSystemFilterExt(this);
			System.out.println("filter: " + filter[0]);
			return this.filter;
		}
		else {
			System.out.println("Default-Filter");
			return sysElem.getFilterExt();
		}
	}
	
	public void executeSystem() {
		if (isExtSys) {
			System.out.println("Extern System executed");
		}
		else {
			sysElem.execute();
		}
	}

	
	
}
