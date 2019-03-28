package de.fraunhofer.isst.automotive.stars.reqmon.mapping.logic;

import java.util.ArrayList;
import java.util.List;

public class GeneratorController {
	
	private int index;
	private List<String> generators;
	private List<String> generateLabels;
	
	public GeneratorController() {
		this.index = 0;
		this.generators = new ArrayList<String>();
		this.generateLabels = new ArrayList<String>();
	}

	public int getIndex() {
		return index;
	}
	
	public List<String> getGenerators() {
		return generators;
	}

	public void setGenerators(List<String> generators) {
		this.generators = generators;
	}
	
	public List<String> getGenerateLabels() {
		return generateLabels;
	}

	public void setGenerateLabels(List<String> generateLabels) {
		this.generateLabels = generateLabels;
	}

	public boolean addGenerator(String name, String label) {
		if (generators.contains(name)) {
			return false;
		}
		generators.add(name);
		generateLabels.add(label);
		return true;
	}
	
	public boolean deleteGenerator(String name, String label) {
		if (generators.contains(name)) {
			generators.remove(name);
			generateLabels.remove(label);
			return true;
		}
		return false;
	}
	
	public void generateSampleList() {
		addGenerator("Generator 1", "Generate (G1)");
		addGenerator("Generator 2", "Generate (G2)");
		addGenerator("Generator 3", "Generate (G3)");
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
	
	public String getActiveGenerator() {
		System.out.println("Call the " + generateLabels.get(index));
		return generators.get(index);
	}

}
