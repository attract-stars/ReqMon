package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;


public class TestGenerator {

	private String name;
	private String label;
	
	public TestGenerator() {
		this.name = "FirstGenerator";
		this.label = "Generate First Code";
	}

	//@Override
	public String getName() {
		return name;
	}

	//@Override
	public String getLabel() {
		return label;
	}

	//@Override
	public void generate() {
		System.out.println("FirstGenerator called!");
		
	}

}
