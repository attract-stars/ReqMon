package de.fraunhofer.isst.automotive.stars.reqmon.mapping.editor;

import java.util.ArrayList;
import java.util.List;

public class Generator {

	private int index;
	private List<String> generators;
	
	public Generator() {
		this.index = 0;
		this.generators = new ArrayList<String>();
	}

	public int getIndex() {
		return index;
	}
	
	public boolean hasNext() {
		return index < generators.size();
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<String> getGenerators() {
		return generators;
	}

	public void setGenerators(List<String> generators) {
		this.generators = generators;
	}
	
	public void addGenerator(String name) {
		generators.add(name);
	}
	
	public boolean deleteGenerator(String name) {
		if (generators.contains(name)) {
			generators.remove(name);
			return true;
		}
		return false;
	}
	
	public void generateSampleList() {
		generators.add("Generator 1");
		generators.add("Generator 2");
		generators.add("Generator 3");
	}
	
	public String getNextGenerator() {
		return generators.get(index++);
	}
	
	public void activateGenerator(String name) {
		System.out.println("Call the " + name);
	}
	
}
