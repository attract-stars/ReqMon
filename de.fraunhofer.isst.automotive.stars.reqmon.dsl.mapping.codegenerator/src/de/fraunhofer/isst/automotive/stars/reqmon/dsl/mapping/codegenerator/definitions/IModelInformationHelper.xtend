package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType

/**
 * The implementation of this interface should help to extract the informations of the IMappingModel.
 */
interface IModelInformationHelper {
	
	def void setModel(IMappingModel model)
	
	def void setFilterType(FilterType filtertype)
	
	def int getSourceCount()
	
	def List<String> getClasses()
	
	def List<String> getSignals()
	
	/**
	 * Returns a list of header file names.
	 */
	def List<String> getIncludes()
	
	/**
	 * Returns the debugging option value.
	 */
	def boolean isDebugOpt()
	
	/**
	 * Returns a list of all input and output pins.
	 */
	def List<IPin> getPins()
	
	/**
	 * Returns a list of all input pins.
	 */
	def List<IPin> getInputPins()
	
	/**
	 * Returns a list of all output pins.
	 */
	def List<IPin> getOutputPins()
	
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
	
	
	
}