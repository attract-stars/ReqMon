package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;

public class TestSystemImporter implements ISystemImporter {

	@Override
	public boolean check(String path) {
		System.out.println("TestSystemImporterCheck");
		return false;
	}

	@Override
	public EObject getSystemModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
