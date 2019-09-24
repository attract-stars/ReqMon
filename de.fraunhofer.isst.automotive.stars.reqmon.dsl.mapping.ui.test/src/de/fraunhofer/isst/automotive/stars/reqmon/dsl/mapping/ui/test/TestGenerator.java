package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;


import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.MappingModel;

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
	public void generate(MappingModel model) {
		// TODO Auto-generated method stub
		
	}

	

}
