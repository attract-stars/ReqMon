package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;


import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Model;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

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
	public void generate(IMappingModel model) {
		if (model.getMappingResourceList() != null) {
			System.out.println("Resource list size: " + model.getMappingResourceList().size());
		}
		if (model.getRequirementList() != null) {
			System.out.println("Requirements size: " + model.getRequirementList().size());
		}
		if (model.getSystemModel() != null) {
			EObject sysModel = model.getSystemModel();
			System.out.println("System model: " + sysModel);
			if (sysModel instanceof Model) {
				Model m = (Model)sysModel;
				System.out.println("One model content: " + m.eAllContents().next());
			}
		}
	}

	

}
