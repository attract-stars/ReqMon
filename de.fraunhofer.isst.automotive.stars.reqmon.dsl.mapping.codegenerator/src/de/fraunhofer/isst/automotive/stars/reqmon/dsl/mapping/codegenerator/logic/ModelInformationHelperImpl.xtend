package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.ArrayList
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType
import java.util.List
import java.util.Map
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.ClassNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.AttributeNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.SignalNode
import java.util.HashMap
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.ClassID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.AttributeID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.SignalID
import org.eclipse.emf.ecore.resource.Resource
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.DefinitionElememnt
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType

class ModelInformationHelperImpl extends AbstractModelInformationHelper {
	
	ArrayList<String> objects
	ArrayList<String> attributes
	List<List<String>> enums
	Map<String,ClassNode> mapObjects
	Map<String,AttributeNode> mapAttributes
	Map<String,SignalNode> mapSignals
	Map<String,String> mapStructs
	boolean isEnum
	
	new(IMappingModel model) {
		super(model)
		createPins(FilterType.ABSTRACT_FUNCTION, FilterType.ABSTRACT_FUNCTION.createInputPinsNames, FilterType.ABSTRACT_FUNCTION.createOutputPinsNames)
		createPins(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE, FilterType.FUNCTIONAL_CORRECTNESS_ORACLE.createInputPinsNames, FilterType.FUNCTIONAL_CORRECTNESS_ORACLE.createOutputPinsNames)
		createPins(FilterType.SCENE_ABSTRACTION, FilterType.SCENE_ABSTRACTION.createInputPinsNames, FilterType.SCENE_ABSTRACTION.createOutputPinsNames)
		createPins(FilterType.TEST_COVERAGE_MONITOR, FilterType.TEST_COVERAGE_MONITOR.createInputPinsNames, FilterType.TEST_COVERAGE_MONITOR.createOutputPinsNames)
		setup
	}
	
	private def createInputPinsNames(FilterType filter) {
		val list = new ArrayList
		switch(filter) {
			case ABSTRACT_FUNCTION: {
				list.add("categorization")
				list.add("concreteTargets")}
			case FUNCTIONAL_CORRECTNESS_ORACLE: {
				list.add("can")
				list.add("categorization")
				list.add("abstractTargets")
				list.add("concreteTargets")}
			case SCENE_ABSTRACTION: {
				 list.add("scene")}
			case TEST_COVERAGE_MONITOR: {}
		}
		
		return list;
	}
	
	private def createOutputPinsNames(FilterType filter) {
		val list = new ArrayList
		switch(filter) {
		case ABSTRACT_FUNCTION: 
			{list.add("targetsOutput")}
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			{}
		case SCENE_ABSTRACTION:
			{list.add("categorization")}
		case TEST_COVERAGE_MONITOR:
			{}
		}
		
		return list;
	}
	
	override getTemplateEvaluateContent() {
		return ''''''
	}
	
	override isDebugOpt() {
		return false
	}
	
	override getTemplateTransmitContent() {
		return ''''''
	}
	
	
	
	// Methods for the Requirement Data Types
	
	override getReqObjects() {
		return objects
	}
	
	override getReqAttribute(String obj) {
		val array = new ArrayList
		val enumTypes = new ArrayList
		isEnum = false
		enumTypes.add("")
		if (objects.empty || attributes.empty || !objects.contains(obj.replace('-', ' '))) return array
		for (attr : attributes) {
			if (attr.contains(obj)){
				if (attr.contains("´s ")) {
					array.add(attr.substring(attr.indexOf("´s ")+3).replace(' ', '_')) 
				} 
				else {
					val splitted = attr.split(' ')
					if (splitted.length == 2 && splitted.get(1).contains(obj)) {
						isEnum = true
						enumTypes.add(splitted.get(0))
					}
				} 
			}
		}
		if (isEnum) {
			enumTypes.set(0, "e" + obj.toFirstUpper + "Type")
			array.add("e" + obj.toFirstUpper + "Type")
		}
		enums.add(enumTypes)
		return array
	}
	
	override getInheritance(String obj) {
		val array = new ArrayList
		val classID = mapObjects.get(obj)
		if (classID.inheritance !== null) {
			val inh = classID.inheritance.name
			if (mapStructs.get(inh) !== null)
			array.add(mapStructs.get(inh))
		}
		return array
	}
	
	override getReqEnums() {
		return enums
	}
	
	
	def private void setup() {
		mapObjects = new HashMap
		mapAttributes = new HashMap
		mapSignals = new HashMap
		mapStructs = new HashMap
		objects = new ArrayList
		attributes = new ArrayList
		enums = new ArrayList
		
		val req = model.requirementList
		val map = model.mappingResourceList
		if (req !== null && map !== null) {
			val reqSize = req.size
			for (i : 0..< reqSize) {
				if (req.get(i).elementType.equals(RequirementType.OBJECT)) {
					val classID = map.get(i).getClassID
					val attrID = map.get(i).getAttrID
					val signalID = map.get(i).getSignalID
					if (classID !== null) {
						objects.add(req.get(i).elementName)
						mapObjects.put(req.get(i).elementName, classID.cla)
						mapStructs.put(classID.cla.name, req.get(i).elementName)
					}
					else if (attrID !== null) {
						attributes.add(req.get(i).elementName)
						mapAttributes.put(req.get(i).elementName, attrID.attr)
					}
					else if (signalID !== null) {
						attributes.add(req.get(i).elementName)
						mapSignals.put(req.get(i).elementName, signalID.signal)
					}
				}
			}
		}
	}
	
 	def private ClassID getClassID(Resource resource) {
		if (resource === null) return null 
		if (resource.allContents.toIterable.filter(DefinitionElememnt) === null) return null 
		for (def : resource.allContents.toIterable.filter(DefinitionElememnt)) {
			if (def.def !== null && def.def instanceof ClassID) {
				return def.def as ClassID
			}
		}
	}
	
	def private AttributeID getAttrID(Resource resource) {
		if (resource === null) return null 
		if (resource.allContents.toIterable.filter(DefinitionElememnt) === null) return null 
		for (def : resource.allContents.toIterable.filter(DefinitionElememnt)) {
			if (def.def !== null && def.def instanceof AttributeID)  {
				return def.def as AttributeID
			}
		}
	}
	
	def private SignalID getSignalID(Resource resource) {
		if (resource === null) return null 
		if (resource.allContents.toIterable.filter(DefinitionElememnt) === null) return null 
		for (def : resource.allContents.toIterable.filter(DefinitionElememnt)) {
			if (def.def !== null && def.def instanceof SignalID)  {
				return def.def as SignalID
			}
		}
	}
	
	
	
	
	
	

	
	
	
	
}