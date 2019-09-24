package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;


import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.MappingModel;

/**
 * This class implements the IGenerator interface. It is another generator to test, if the list of more than one 
 * generator works correctly. 
 * 
 * @author sgraf
 *
 */
public class TestOtherGenerator implements IGenerator {

	
	public void generate() {
		System.out.println("Other test generator executed");
		
	}


	@Override
	public void generate(MappingModel model) {
		// TODO Auto-generated method stub
		
	}

}
