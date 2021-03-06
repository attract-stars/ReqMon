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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;
//import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin;
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
		//Pin pin = getPin("m_oSceneInput", FilterType.SCENE_ABSTRACTION);
		//pin.setCoderDescName("m_pCoderDesc");
	}
	
	private List<String> createInputPinsNames(FilterType filter) {
		ArrayList<String> list = new ArrayList<String>();
		switch(filter) {
		case ABSTRACT_FUNCTION: 
			list.add("categorization");
			list.add("concrete targets");
			break;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			list.add("can");
			list.add("categorization");
			list.add("abstract targets");
			list.add("concrete targets");
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
			list.add("targets");
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
		switch(getFilterType()) {
		case ABSTRACT_FUNCTION: 
			return "return false;";
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			return "return false;";
		case SCENE_ABSTRACTION:
			return "return nullptr;";
		case TEST_COVERAGE_MONITOR:
			return "return false;";
		}
		return "return false;";
	}

	@Override
	public CharSequence getTemplateTransmitContent() {
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

	
	@Override
	public CharSequence getComment() {
		String comment = "/*\n* Generated by STARS Dadas.\n* All Rights reserved by Fraunhofer-Institut Software- und Systemtechnik ISST.\n"
				+ "* \n* Generated by stars\n* Project: test\n* \n*/";
		return comment;
	}


	/*
	* Generated by STARS Dadas.
	* All Rights reserved by Fraunhofer-Institut Software- und Systemtechnik ISST.
	* 
	* Generated by stars
	* Project: test
	* 
	*/

}
