package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType
import java.util.ArrayList
import java.util.Map
import java.util.HashMap

/**
 * The implementation of this interface should help to extract the informations of the IMappingModel.
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
			val input = new Pin(inputs.get(i) + "Input", true, "MEDIATYPE_DADAS_" + inputs.get(i).toUpperCase, inputs.get(i), "t" + inputs.get(i).toFirstUpper);
			inputList.add(input)
			allList.add(input)
		}
		inputPins.put(filter,inputList);
		val numOut = outputs.size
		for(i: 0..< numOut) {
			val output = new Pin(outputs.get(i) + "Output", true, "MEDIATYPE_DADAS_" + outputs.get(i).toUpperCase, outputs.get(i), "t" + outputs.get(i).toFirstUpper);
			outputList.add(output);
			allList.add(output)
		}
		outputPins.put(filter, outputList)
		allPins.put(filter, allList)
	}
	
	def IMappingModel getModel() {
		return this.model
	}
	
	def void setFilterType(FilterType filtertype) {
		this.filtertype = filtertype
	}
	
	def FilterType getFilterType() {
		return this.filtertype
	}
	
	def int getSourceCount()
	
	def List<String> getClasses()
	
	def List<String> getSignals()
	
	/**
	 * Returns a list of header file names.
	 */
	def List<String> getIncludes() {
		val list = new ArrayList
		list.add("system-types")
		list.add("mediatypes")
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
	 * Returns a list of all attribute names of the given object name.
	 */
	def List<String> getAttributes(String objectName)
	
	/**
	 * Returns the name of the corresponding system attribute.
	 */
	def String getSystemAttribut(String name)
	
	/** 
	 * Returns the corresponding attribute to the given monitoring attribute like this: class_name attr_name.
	 */
	def String getCorrespondingAttribute(String monAttr)
	
	/**
	 * Returns the min, max and preffered value if the given attribute is a signal.
	 */
	def List<String> getSignalBoundarys(String attr)
	
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
	def List<String> getInheritance(String obj)
	
	/**
	 * Returns all enumerations.
	 */
	def List<List<String>> getReqEnums()
	
	/**
	 * Returns the version of the filter. The default value is "0, 1, 0".
	 */
	def String getFilterVersion() {
		'''0, 1, 0'''
	}
	
	/**
	 * Returns a name list of object pointers
	 */
	def List<String> getObjectPtrs()
	
	/**
	 * Returns a template for the defines of the header. For example: "#define name value"
	 */
	def CharSequence getHeaderTemplateDefines()
	
	/**
	 * Returns a template for the includes of the header.
	 */
	def CharSequence getHeaderTemplateIncludes()
	
	/**
	 * Returns a template for the private members of the header.
	 */
	def CharSequence getHeaderTemplatePrivateMembers()
	
	/**
	 * Returns a template for the private functions of the header.
	 */
	def CharSequence getHeaderTemplatePrivateFunctions()
	
	/**
	 * Returns a template for the protected functions of the header.
	 */
	def CharSequence getHeaderTemplateProtectedFunctions() '''tResult TransmitEvaluationResult(type* name);'''
	
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
				
			}
			case TEST_COVERAGE_MONITOR:
			{
				templateDestructorDefault
			}
		}
	}
	
	/**
	 * Returns a list of constructor value settings.
	 */
	def List<String> getMoreConstructorValues()
	
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
	def CharSequence getInitNormalTemplate() {
		'''//Nothing to do'''
	}
	
	/**
	 * Returns a template for the init graph ready stage. The default value is a timeout creation if the cConditionTriggeredFilter
	 * is extended and otherwise "//Nothing to do".
	 */
	def CharSequence getInitGraphReadyTemplate() {
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
	def CharSequence getRunClearTemplate() {
		''''''
	}
	
	/**
	 * Returns the return type of the evaluate method. 
	 * The default value is tBool or the pin output object type for the scene abstraction filter with one output pin.
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
	def CharSequence getMoreOnTriggerActionsTemplate() {
		''''''
	}
	
	/**
	 * Returns the return type of the evaluate method call in the onTrigger method. The default value is an empty String.
	 */
	def CharSequence getOnTriggerEvaluateReturnType() {
		''''''
	}
	
	/**
	 * Returns a template for the Log method. The default Value is null which leads to a default Log method.
	 */
	def CharSequence getLogTemplate() {
		return null
	}
	
	/**
	 * Returns a template for more protected methods implementations. 
	 * The template should end with an empty line to keep distance to the Log method. 
	 * The default value is an empty String.
	 */
	def CharSequence getMoreProtectedMethodsTemplate() {
		''''''
	}
	
	/**
	 * Returns more super classes like ", public superclass_name". The default value is an empty string.
	 */
	def CharSequence getMoreSuperClasses() {
		''''''
	}
	
	
	
	
	
	
	
	
	
}