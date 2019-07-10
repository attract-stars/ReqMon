package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;

public class TestSystemImporter implements ISystemImporter {

	public void execute(String path) {
		System.out.println("TestSystem for txt executed!");
	}
	
	

}
