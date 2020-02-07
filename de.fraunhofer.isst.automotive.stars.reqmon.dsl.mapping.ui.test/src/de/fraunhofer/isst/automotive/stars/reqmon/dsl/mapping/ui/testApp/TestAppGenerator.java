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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

/**
 * This class implements the IGenerator interface and creates a sample generator list.
 * Additional it provides similar methods as the GeneratorController.
 * 
 * @author sgraf
 *
 */
public class TestAppGenerator implements IGenerator {
	
	private int index;
	private List<String> generators;
	private List<String> generateLabels;
	
	public TestAppGenerator() {
		this.generators = new ArrayList<String>();
		this.generateLabels = new ArrayList<String>();
		
		generateSampleList();
	}
	
	private void generateSampleList() {
		addGenerator("Generator 1", "Generate (G1)");
		addGenerator("Generator 2", "Generate (G2)");
		addGenerator("Generator 3", "Generate (G3)");
		this.index = 0;
	}
	
	public boolean addGenerator(String name, String label) {
		if (generators.contains(name)) {
			return false;
		}
		generators.add(name);
		generateLabels.add(label);
		return true;
	}
	
	public List<String> getGenerators() {
		return generators;
	}
	
	public List<String> getGenerateLabels() {
		return generateLabels;
	}
	
	
	public String getLabel(String name) {
		for (int i = 0; i < generators.size(); i++) {
			if (name.contains(generators.get(i))) {
				index = i;
				break;
			}
		}
		if (index < generateLabels.size()) {
			return generateLabels.get(index);
		}
		return "";
	}
	
	
	public void generate() {
		System.out.println("Call the " + generateLabels.get(index));
	}



	@Override
	public void generate(IMappingModel model) {
		// TODO Auto-generated method stub
		
	}

}
