package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType
import java.util.ArrayList
import java.util.Map
import java.util.HashMap
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

/**
 * The implementation of this interface should help to extract the informations of the IMappingModel 
 * for the generation of the adtf filter structures, the standard Types file, the MediaTypes and SubTypes file, 
 * and the Requirement and System Types files.
 * @author sgraf
 */
abstract class AbstractModelInformationHelper {
	
	FilterType filtertype
	IMappingModel model
	Map<FilterType,List<Pin>> inputPins
	Map<FilterType,List<Pin>> outputPins
	Map<FilterType,List<Pin>> allPins
	
	new(IMappingModel model) {
		this.model = model
		inputPins = new HashMap
		outputPins = new HashMap
		allPins = new HashMap
	}
	
	
	/**
	 * Creates the pins and uses therefore the given pin name lists.
	 */
	def void createPins(FilterType filter, List<String> inputs, List<String> outputs) {
		val numIn = inputs.size
		val inputList = new ArrayList
		val outputList = new ArrayList
		val allList = new ArrayList
		for(i: 0..< numIn) {
			//val input = new Pin(inputs.get(i) + "Input", true, "MEDIATYPE_DADAS_" + inputs.get(i).toUpperCase, inputs.get(i), "t" + inputs.get(i).toFirstUpper);
			val input = new Pin(inputs.get(i), true);
			inputList.add(input)
			allList.add(input)
		}
		inputPins.put(filter,inputList);
		val numOut = outputs.size
		for(i: 0..< numOut) {
			//val output = new Pin(outputs.get(i) + "Output", true, "MEDIATYPE_DADAS_" + outputs.get(i).toUpperCase, outputs.get(i), "t" + outputs.get(i).toFirstUpper);
			val output = new Pin(outputs.get(i), false);
			outputList.add(output);
			allList.add(output)
		}
		outputPins.put(filter, outputList)
		allPins.put(filter, allList)
	}
	
	/**
	 * Returns the instance of the IMappingModel.
	 */
	def IMappingModel getModel() {
		return this.model
	}
	
	/**
	 * Sets the filter type to the given value.
	 */
	def void setFilterType(FilterType filtertype) {
		this.filtertype = filtertype
	}
	
	/**
	 * Returns the filter type.
	 */
	def FilterType getFilterType() {
		return this.filtertype
	}
	
	/**
	 * Returns a list of header file names.
	 */
	def List<String> getIncludes() {
		val list = new ArrayList
		list.add("dtypes")
		list.add("stdafx")
		list.add("requirement_types")
		list.add("system-types")
		switch(filtertype) {
			case ABSTRACT_FUNCTION:
			{
				list.add("abstract_function_filter")
				return list
			}
			case FUNCTIONAL_CORRECTNESS_ORACLE:
			{
				list.add("function_correctness_oracle_filter")
				return list
			}
			case SCENE_ABSTRACTION:
			{ 
				list.add("scene_abstraction_filter") 
				return list
			}
			case TEST_COVERAGE_MONITOR:
			{
				list.add("test_coverage_filter")
				return list
			}
		}
	}
	
	/**
	 * Returns the debugging option value.
	 */
	def boolean isDebugOpt()
	
	/**
	 * Returns a list of all input and output pins.
	 */
	def List<Pin> getPins() {
		return allPins.get(filtertype)
	}
	
	/**
	 * Returns the pin of the given name and filter type.
	 */
	def Pin getPin(String name, FilterType filter) {
		for (pin : allPins.get(filter)) {
			if (pin.pinName.equals(name)) {
				return pin;
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of all input pins.
	 */
	def List<Pin> getInputPins() {
		return this.inputPins.get(filtertype)
	}
	
	/**
	 * Returns a list of all output pins.
	 */
	def List<Pin> getOutputPins() {
		return this.outputPins.get(filtertype)
	}
	
	/**
	 * Returns the version of the filter. The default value is "0, 1, 0".
	 */
	def String getFilterVersion() {
		'''0, 1, 0'''
	}
	
	/**
	 * Returns a template for the defines of the header. For example: "#define name value".
	 * The default value is an empty String.
	 */
	def CharSequence getHeaderTemplateDefines() ''''''
	
	/**
	 * Returns a template for the includes of the header.
	 * The default value is an empty String.
	 */
	def CharSequence getHeaderTemplateIncludes() ''''''
	
	/**
	 * Returns a template for the private members of the header.
	 * The default value is an empty String.
	 */
	def CharSequence getHeaderTemplatePrivateMembers() ''''''
	
	/**
	 * Returns a template for the private functions of the header.
	 * The default value is an empty String.
	 */
	def CharSequence getHeaderTemplatePrivateFunctions() ''''''
	
	/**
	 * Returns a template for the protected functions of the header.
	 * The default value is an empty String.
	 */
	def CharSequence getHeaderTemplateProtectedFunctions() ''''''
	
	/**
	 * Returns an evaluate method declaration. For example: "tReturnType Evaluate(paramType paramName, ..);"
	 */
	def CharSequence getEvaluateMethod() '''«getEvaluateReturnType» Evaluate(«true.getEvaluateParameters»);'''
	
	/**
	 * Returns default evaluate parameters with type when isTyped is true.
	 */
	def CharSequence getEvaluateParameters(boolean isTyped) {
		val inputs = getInputPins
		val num = getInputPins.size
		val type = '''«IF isTyped»«IF num === 1»«inputs.get(0).pinObjectType» «ELSE»IMediaSample* «ENDIF»«ELSE»&«ENDIF»'''
		
		if (num == 1) {
			return '''«type»«inputs.get(0).pinObjectName»'''
		}
		if (num >= 2) {
			return '''«type»«inputs.get(0).sampleName», «type»«inputs.get(1).sampleName»'''
		}
		else if (num >= 3) {
			return '''«FOR i : 0..< num-1»«type»«inputs.get(i).sampleName», «ENDFOR»«type»«inputs.get(num-1).sampleName»'''
		}
	}
	
	def private getSampleName(Pin input) '''p«input.pinObjectName.toFirstUpper»Sample'''
	
	/**
	 * Returns a template for the content of the evaluate method.
	 */
	def CharSequence getTemplateEvaluateContent()
	
	/**
	 * Returns a template for the Constructor content.
	 */
	def CharSequence getTemplateConstructorContent() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION:
			{
				templateConstructorDefault
			}
			case FUNCTIONAL_CORRECTNESS_ORACLE:
			{
				templateConstructorDefault
			}
			case SCENE_ABSTRACTION:
			{ 
				if (getInputPins.size > 1) {
					templateConstructorDefault
				}
			}
			case TEST_COVERAGE_MONITOR:
			{
				templateConstructorDefault
			}
		}
	}
	
	def private getTemplateConstructorDefault() {
		'''
		kernelMutex.Create();
		
		SetPropertyInt("timeout", $timeout_value$);
		SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
			"Demo timeout that will issue a warning when no trigger has occurred "
			"in the specified amount of time (microseconds). 0 disables the timeout.");
		SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
		'''
	}
	
	def private getTemplateDestructorDefault() {
		'''kernelMutex.Release();'''
	}
	
	/**
	 * Returns a template for the Deconstructor content.
	 */
	def CharSequence getTemplateDeconstructorContent() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION:
			{
				templateDestructorDefault
			}
			case FUNCTIONAL_CORRECTNESS_ORACLE:
			{
				templateDestructorDefault
			}
			case SCENE_ABSTRACTION:
			{ 
				if (getInputPins.size > 1) {
					templateDestructorDefault
				}
			}
			case TEST_COVERAGE_MONITOR:
			{
				templateDestructorDefault
			}
		}
	}
	
	/**
	 * Returns a list of constructor value settings.
	 * The default value is an empty list.
	 */
	def List<String> getMoreConstructorValues() {
		return new ArrayList
	}
	
	/**
	 * Returns true if the class extends cConditionTriggeredFilter otherwise false.
	 */
	def boolean isTriggeredFilter() {
		if (inputPins.get(filtertype) !== null && inputPins.get(filtertype).size > 1) {
			return true;
		}
		return false
	}
	
	/**
	 * Returns true if the class extends cFilter otherwise false.
	 */
	def boolean isCFilter() {
		if (inputPins.get(filtertype) !== null && inputPins.get(filtertype).size === 1) {
			return true;
		}
		return false
	}
	
	/**
	 * Returns a template for the init normal stage. The default value is "//Nothing to do".
	 */
	def CharSequence getTemplateInitNormal() {
		'''//Nothing to do'''
	}
	
	/**
	 * Returns a template for the init graph ready stage. The default value is a timeout creation if the cConditionTriggeredFilter
	 * is extended and otherwise "//Nothing to do".
	 */
	def CharSequence getTemplateInitGraphReady() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: AFFInitGraphReady
			case FUNCTIONAL_CORRECTNESS_ORACLE: FCOFInitGraphReady
			case SCENE_ABSTRACTION: SAFInitGraphReady
			case TEST_COVERAGE_MONITOR: TCMFInitGraphReady
			default: '''$init_graphReady$'''
		}
	}
	
	def private getTCMFInitGraphReady() {
		'''«timeoutCreation»'''
	}	
	
	def private getSAFInitGraphReady() {
		if (isTriggeredFilter) {
			'''«timeoutCreation»'''
		}
		else {
			'''//Nothing to do'''
		}
	}
	
	def private getFCOFInitGraphReady() {
		'''«timeoutCreation»'''
	}
	
	def private getAFFInitGraphReady() {
		'''«timeoutCreation»'''
	}
	
	def private getTimeoutCreation() '''
	// create a new timeout if required
	tTimeStamp nTimeout = GetPropertyInt("timeout");
	if (nTimeout < 0)
	{
		THROW_ERROR_DESC(ERR_INVALID_ARG, "The timeout value can not be negative");
	}
	else if (nTimeout != 0)
	{
		m_bTimeout = tTrue;
		RETURN_IF_FAILED(m_oTimeout.Create(this, nTimeout, OIGetInstanceName()));
	}
	'''
	
	def boolean isDescriptionManager() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: true
			case FUNCTIONAL_CORRECTNESS_ORACLE: true
			case SCENE_ABSTRACTION: false
			case TEST_COVERAGE_MONITOR: true
			default: false
		}
	}
	
	/**
	 * Returns more conditions for the start method. The default value is null.
	 */
	def CharSequence getMoreStartConditions() {
		return null
	}
	
	/**
	 * Returns more conditions for the stop method. The default value is null.
	 */
	def CharSequence getMoreStopConditions() {
		return null
	}
	
	/**
	 * Returns a template for the clear section in the run method. The default value is an empty String.
	 */
	def CharSequence getTemplateRunClear() ''''''
	
	/**
	 * Returns the return type of the evaluate method. 
	 * The default value is tBool or the output pin object type for the scene abstraction filter with one output pin.
	 */
	def CharSequence getGetEvaluateReturnType() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''tBool'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''tBool'''
			case SCENE_ABSTRACTION: '''«IF getOutputPins.size === 1»«getOutputPins.get(0).pinObjectType»«ENDIF»'''
			case TEST_COVERAGE_MONITOR: '''tBool'''
			default: '''tBool'''
		}
	}
	
	/**
	 * Returns a template for more actions in the OnTrigger method. The default value is an empty String.
	 */
	def CharSequence getMoreOnTriggerActionsTemplate() ''''''
	
	/**
	 * Returns a template for the Log method. The default Value is null which leads to a default Log method.
	 */
	def CharSequence getTemplateLog() {
		return null
	}
	
	/**
	 * Returns a template for more protected methods implementations. 
	 * The template should end with an empty line to keep distance to the Log method. 
	 * The default value is an empty String.
	 */
	def CharSequence getTemplateMoreProtectedMethods() ''''''
	
	/**
	 * Returns more super classes like ", public superclass_name". The default value is an empty string.
	 */
	def CharSequence getMoreSuperClasses() ''''''
	
	/**
	 * Returns a template for more parameters of the transmit method like "type1 name1, type2 name2".
	 * The default value is null.
	 */
	def CharSequence getMoreTransmitParameters() {
		return null
	}
	
	/**
	 * Returns a template for the content of the transmit method. 
	 * Returns a default content if the size of the output pins is equal one.
	 */
	def CharSequence getTemplateTransmitContent() 
	
	/** 
	 * Returns an defined oidName for the header. The default string is "OID_DADAS_[filtertype]".  
	 */
	def CharSequence getOidName() {
		'''OID_DADAS_«filtertype»'''
	}
	
	/**
	 * Returns an oidString for the header. 
	 * The default string is "de.fraunhofer.isst.automotive.stars.reqmon.dsl.data.monitoring.[filter.type]"
	 */
	def CharSequence getHeaderOidString() {
		return null
	}
	
	/**
	 * Returns the class name. 
	 */
	def CharSequence getGetClassName() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''cDadasAbstractFunctionFilter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''cDadasFunctionalCorrectnessOracleFilter'''
			case SCENE_ABSTRACTION: '''cDadasSceneAbstractionFilter'''
			case TEST_COVERAGE_MONITOR: '''cDadasTestCoverageMonitorFilter'''
			default: '''$class_name$'''
		}
	}
	
	/**
	 * Returns a name for the adtf declare filter version.
	 */
	def CharSequence getGetAdtfDeclareFilterVersionName() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''DADAS Abstract Function Filter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''DADAS Functional Correctness Oracle Filter'''
			case SCENE_ABSTRACTION: '''DADAS Scene Abstraction Filter'''
			case TEST_COVERAGE_MONITOR: '''DADAS Test Coverage Monitor Filter'''
			default: '''$filter_name$'''
		}
	}
	
	/**
	 * Returns a designation for the adtf declare filter version.
	 */
	def CharSequence getGetAdtfDeclareFilterVersionDesignation() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''Abstract Function'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''Functional Correctness Oracle'''
			case SCENE_ABSTRACTION: '''Scene Abstraction'''
			case TEST_COVERAGE_MONITOR: '''Test Coverage Monitor'''
			default: '''$oid_designation$'''
		}
	}
	
	/**
	 * Returns a template for the private methods implementation.
	 * The default value is an empty String.
	 */
	def CharSequence getTemplatePrivateMethods() ''''''
	
	
	
	
	/**
	 * Returns all requirement objects that are mapped to a system class.
	 */
	def List<String> getReqObjects()
	
	/**
	 * Returns all attributes of the given object.
	 */
	def List<String> getReqAttribute(String obj)
	
	/**
	 * Returns all super classes of the given object.
	 */
	def List<String> getReqInheritance(String obj)
	
	/**
	 * Returns all enumerations.
	 */
	def List<List<String>> getReqEnums()
	
	
	
	
	/**
	 * Returns an address offset for the MediaType.
	 * The default value is 0xC350.
	 */
	def CharSequence getMediaTypeOffset() {
		'''0xC350'''
	}
	
	/**
	 * Returns a list of MediaSubTypes and its location. The default value is an empty list.
	 */
	def List<String> getMediaSubTypes() {
		new ArrayList
	}
	
	/**
	 * Generates the comment on top of each generated file.
	 */
	def CharSequence getComment() '''
	/*
	* Generated by STARS Dadas.
	* All Rights reserved by Fraunhofer-Institut Software- und Systemtechnik ISST.
	* 
	* Generated by «user»
	* Project: «model.projectName»
	* «projectInfos»
	*/
	'''
	
	def String getProjectInfos() {
		val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   		val now = LocalDateTime.now();
		return '''File created on «dtf.format(now)»'''
	}
		
	def String getUser() {
		'''stars'''
	}
	
	
	
	
}