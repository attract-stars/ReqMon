package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

public class ModelInformationTestHelper extends AbstractModelInformationHelper {
	
	private MappingTestModel model;
	
	public ModelInformationTestHelper() {
		
	}
	
	@Override
	public void setModel(IMappingModel model) {
		if (model instanceof MappingTestModel) {
			this.model = (MappingTestModel) model;
		}
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
		return model.getAllAtributesOf(objectName);
	}

	@Override
	public String getSystemAttribut(String name) {
		return model.getSystemAttribute(name);
	}

	@Override
	public String getCorrespondingAttribute(String monAttr) {
		return model.getCorrespondingAttribute(monAttr);
	}

	@Override
	public List<String> getSignalBoundarys(String attr) {
		return model.isSignal(attr);
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
	public String getFilterVersion() {
		return "0, 1, 0";
	}

	@Override
	public List<String> getInputPinsNames() {
		ArrayList<String> list = new ArrayList<String>();
		switch(getFilterType()) {
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
			break;
		case TEST_COVERAGE_MONITOR:
			break;
		}
		
		return list;
	}

	@Override
	public List<String> getOutputPinsNames() {
		ArrayList<String> list = new ArrayList<String>();
		switch(getFilterType()) {
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

	@Override
	public CharSequence getHeaderTemplateProtectedFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
	}

	@Override
	public CharSequence getTemplateEvaluateContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequence getTemplateConstructorContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequence getTemplateDeconstructorContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMoreConstructorValues() {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

	


	

}
