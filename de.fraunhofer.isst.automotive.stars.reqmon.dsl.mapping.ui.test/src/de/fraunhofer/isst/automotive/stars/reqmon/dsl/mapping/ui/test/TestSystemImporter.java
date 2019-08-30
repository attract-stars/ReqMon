package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;

public class TestSystemImporter implements ISystemImporter {

	@Override
	public boolean check(String path) {
		System.out.println("TestSystemImporterCheck");
		return false;
	}
	
	

}
