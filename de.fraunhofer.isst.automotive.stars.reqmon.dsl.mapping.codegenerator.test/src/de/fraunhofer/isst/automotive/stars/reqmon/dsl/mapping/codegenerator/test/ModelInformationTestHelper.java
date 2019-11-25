package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

public class ModelInformationTestHelper extends AbstractModelInformationHelper {
	
	//private MappingTestModel model;
	private int inputNumber;
	private List<String> messages;
	
	public ModelInformationTestHelper(IMappingModel model, int inputNumber) {
		super(model);
		if (model instanceof MappingTestModel) {
			//this.model = (MappingTestModel) model;
			this.inputNumber = inputNumber;
		}
		createPins(FilterType.ABSTRACT_FUNCTION, createInputPinsNames(FilterType.ABSTRACT_FUNCTION), createOutputPinsNames(FilterType.ABSTRACT_FUNCTION));
		createPins(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE, createInputPinsNames(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE), createOutputPinsNames(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE));
		createPins(FilterType.SCENE_ABSTRACTION, createInputPinsNames(FilterType.SCENE_ABSTRACTION), createOutputPinsNames(FilterType.SCENE_ABSTRACTION));
		createPins(FilterType.TEST_COVERAGE_MONITOR, createInputPinsNames(FilterType.TEST_COVERAGE_MONITOR), createOutputPinsNames(FilterType.TEST_COVERAGE_MONITOR));
		Pin pin = getPin("m_oSceneInput", FilterType.SCENE_ABSTRACTION);
		pin.setCoderDescName("m_pCoderDesc");
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
	public List<String> getMediaSubTypes() {
		messages = new ArrayList<String>();		
		messages.add("SCENE 0x0010");
		messages.add("TIME 0x0020");
		messages.add("CATEGORIZATION 0x0030");
		messages.add("ABSTRACT_TARGETS 0x0040");
		messages.add("CONCRETE_TARGETS 0x0050");
		messages.add("CAN 0x0060");
		
		return messages;
	}
	
	
	
	@Override
	public boolean isDebugOpt() {
		return false;
	}

	@Override
	public CharSequence getTemplateEvaluateContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequence getTemplateTransmitContent() {
		// TODO Auto-generated method stub
		return null;
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
	public List<String> getReqInheritance(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getReqEnums() {
		// TODO Auto-generated method stub
		return null;
	}

	
	


	

}
