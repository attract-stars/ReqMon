package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;

public class TestOtherSystemImporter implements ISystemImporter{

	@Override
	public void execute(String path) {
		System.out.println("Other TestSystem for xml executed!");
		
	}

}
