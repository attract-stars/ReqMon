package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;

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
	
	@Override
	public void generate() {
		System.out.println("Call the " + generateLabels.get(index));
	}

}
