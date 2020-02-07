/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
