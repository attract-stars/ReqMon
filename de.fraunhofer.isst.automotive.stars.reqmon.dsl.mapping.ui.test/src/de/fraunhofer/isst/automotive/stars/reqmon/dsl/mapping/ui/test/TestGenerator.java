package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;

/**
 * This class implements the IGenerator interface to test if this extension is registered correctly. 
 * 
 * @author sgraf
 *
 */
public class TestGenerator implements IGenerator {

	
	
	public void generate() {
		System.out.println("TestGenerator called!");
		
	}

	@Override
	public void generate(List<Resource> resource) {
		// TODO Auto-generated method stub
		
	}

	

}
