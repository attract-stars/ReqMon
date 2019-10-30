package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType
import java.util.ArrayList

/**
 * The implementation of this interface should help to extract the informations of the IMappingModel.
 */
abstract class AbstractModelInformationHelper {
	
	FilterType filtertype
	
	def void setModel(IMappingModel model)
	
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
		return getPins(inputPinsNames, outputPinsNames)
	}
	
	/**
	 * Returns a list of all input pins.
	 */
	def List<Pin> getInputPins() {
		return getInputPins(inputPinsNames)
	}
	
	/**
	 * Returns a list of all output pins.
	 */
	def List<Pin> getOutputPins() {
		return getOutputPins(outputPinsNames)
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
	 * Returns the version of the filter like "0, 0, 0".
	 */
	def String getFilterVersion()
	
	/**
	 * Returns a name list of the input pins.
	 */
	def List<String> getInputPinsNames()
	
	/**
	 * Returns a name list of the output pins.
	 */
	def List<String> getOutputPinsNames()
	
	def private List<Pin> getInputPins(List<String> inputs) {
		val list = new ArrayList
		val num = inputs.size
		for(i: 0..< num) {
			val input = new Pin(inputs.get(i) + "Input", true, "MEDIATYPE_DADAS_" + inputs.get(i).toUpperCase, inputs.get(i), "t" + inputs.get(i).toFirstUpper);
			list.add(input);
		}
		
		return list;
	}
	
	def private List<Pin> getOutputPins(List<String> outputs) {
		val list = new ArrayList
		val num = outputs.size
		for(i: 0..< num) {
			val input = new Pin(outputs.get(i) + "Output", true, "MEDIATYPE_DADAS_" + outputs.get(i).toUpperCase, outputs.get(i), "t" + outputs.get(i).toFirstUpper);
			list.add(input);
		}
		
		return list;
	}
	
	def private List<Pin> getPins(List<String> inputs, List<String> outputs) {
		val list = getInputPins(inputs)
		list.addAll(getOutputPins(outputs))
		
		return list;
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
	def CharSequence getHeaderTemplateProtectedFunctions()
	
	/**
	 * Returns an evaluate method declaration. For example: "tReturnType Evaluate(paramType paramName, ..);"
	 */
	def CharSequence getEvaluateMethod()
	
	/**
	 * Returns a template for the content of the evaluate method.
	 */
	def CharSequence getTemplateEvaluateContent()
	
	/**
	 * Returns a template for the Constructor content.
	 */
	def CharSequence getTemplateConstructorContent()
	
	/**
	 * Returns a template for the Deconstructor content.
	 */
	def CharSequence getTemplateDeconstructorContent()
	
	/**
	 * Returns a list of constructor value settings.
	 */
	def List<String> getMoreConstructorValues()
	
	
	
}