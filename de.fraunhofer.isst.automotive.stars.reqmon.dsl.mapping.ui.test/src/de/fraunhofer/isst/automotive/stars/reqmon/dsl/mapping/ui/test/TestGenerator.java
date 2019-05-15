package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;

/**
 * This class implements the IGenerator interface to test if this extension is registered correctly. 
 * 
 * @author sgraf
 *
 */
public class TestGenerator implements IGenerator {

	
	@Override
	public void generate() {
		System.out.println("TestGenerator called!");
		
	}

}
