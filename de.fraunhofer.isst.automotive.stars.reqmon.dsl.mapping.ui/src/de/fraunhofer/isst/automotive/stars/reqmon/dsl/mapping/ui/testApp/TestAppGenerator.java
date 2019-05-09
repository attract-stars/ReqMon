package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.util.ArrayList;
import java.util.List;

public class TestAppGenerator {
	
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
	
	public void executeSelectedGenerator() {
		getActiveGenerator();
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
	
	private void getActiveGenerator() {
		System.out.println("Call the " + generateLabels.get(index));
	}

}
