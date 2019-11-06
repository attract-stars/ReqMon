package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

public class ModelInformationTestHelper extends AbstractModelInformationHelper {
	
	private MappingTestModel model;
	private int inputNumber;
	
	public ModelInformationTestHelper(IMappingModel model, int inputNumber) {
		super(model);
		if (model instanceof MappingTestModel) {
			this.model = (MappingTestModel) model;
			this.inputNumber = inputNumber;
		}
		createPins(FilterType.ABSTRACT_FUNCTION, createInputPinsNames(FilterType.ABSTRACT_FUNCTION), createOutputPinsNames(FilterType.ABSTRACT_FUNCTION));
		createPins(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE, createInputPinsNames(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE), createOutputPinsNames(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE));
		createPins(FilterType.SCENE_ABSTRACTION, createInputPinsNames(FilterType.SCENE_ABSTRACTION), createOutputPinsNames(FilterType.SCENE_ABSTRACTION));
		createPins(FilterType.TEST_COVERAGE_MONITOR, createInputPinsNames(FilterType.TEST_COVERAGE_MONITOR), createOutputPinsNames(FilterType.TEST_COVERAGE_MONITOR));
	}
	
	private List<String> createInputPinsNames(FilterType filter) {
		ArrayList<String> list = new ArrayList<String>();
		switch(filter) {
		case ABSTRACT_FUNCTION: 
			list.add("categorization");
			list.add("concreteTargets");
			break;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			list.add("can");
			list.add("categorization");
			list.add("abstractTargets");
			list.add("concreteTargets");
			break;
			
		case SCENE_ABSTRACTION:
			list.add("scene");
			if (inputNumber == 2) {
				list.add("time");
			}
			break;
		case TEST_COVERAGE_MONITOR:
			break;
		}
		
		return list;
	}


	private List<String> createOutputPinsNames(FilterType filter) {
		ArrayList<String> list = new ArrayList<String>();
		switch(filter) {
		case ABSTRACT_FUNCTION: 
			break;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			break;
		case SCENE_ABSTRACTION:
			list.add("categorization");
			break;
		case TEST_COVERAGE_MONITOR:
			break;
		}
		
		return list;
	}
	
	

	@Override
	public int getSourceCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSignals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDebugOpt() {
		return false;
	}

	@Override
	public List<String> getAttributes(String objectName) {
		if (model != null)
		return model.getAllAtributesOf(objectName);
		else return null;
	}

	@Override
	public String getSystemAttribut(String name) {
		if (model != null)
		return model.getSystemAttribute(name);
		else return null;
	}

	@Override
	public String getCorrespondingAttribute(String monAttr) {
		if (model != null)
		return model.getCorrespondingAttribute(monAttr);
		else return null;
	}

	@Override
	public List<String> getSignalBoundarys(String attr) {
		if (model != null)
		return model.isSignal(attr);
		else return null;
	}

	@Override
	public List<String> getReqObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReqAttribute(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getInheritance(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getReqEnums() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> getObjectPtrs() {
		ArrayList<String> list = new ArrayList<String>();
		switch(getFilterType()) {
		case ABSTRACT_FUNCTION: 
			break;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			break;
		case SCENE_ABSTRACTION:
			list.add("coderDesc");
			break;
		case TEST_COVERAGE_MONITOR:
			break;
		}
		
		return list;
	}

	@Override
	public CharSequence getHeaderTemplateDefines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequence getHeaderTemplateIncludes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequence getHeaderTemplatePrivateMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequence getHeaderTemplatePrivateFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public CharSequence getHeaderTemplateProtectedFunctions() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public CharSequence getEvaluateMethod() {
		CharSequence eval = "";
		switch(getFilterType()) {
		case ABSTRACT_FUNCTION:
			break;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			break;
		case SCENE_ABSTRACTION:
			if (getInputPins().size() == 1 && getOutputPins().size() == 1) {
				Pin in = getInputPins().get(0);
				Pin out = getOutputPins().get(0);
				eval = out.getPinObjectType() + " Evaluate(" + in.getPinObjectType() + " " + in.getPinObjectName() + ");";
			}
			break;
		case TEST_COVERAGE_MONITOR:
			break;
		}
		return eval;
	}*/

	@Override
	public CharSequence getTemplateEvaluateContent() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<String> getMoreConstructorValues() {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

	


	

}
