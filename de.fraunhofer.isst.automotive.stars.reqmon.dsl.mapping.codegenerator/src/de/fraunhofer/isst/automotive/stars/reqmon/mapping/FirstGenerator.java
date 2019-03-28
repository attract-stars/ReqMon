package de.fraunhofer.isst.automotive.stars.reqmon.mapping;

import de.fraunhofer.isst.automotive.stars.reqmon.mapping.definitions.IGenerator;

public class FirstGenerator implements IGenerator {

	private String name;
	private String label;
	
	public FirstGenerator() {
		this.name = "FirstGenerator";
		this.label = "Generate First Code";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void generate() {
		System.out.println("FirstGenerator called!");
		
	}

}
