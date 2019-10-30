package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.ArrayList
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.DefinitionElememnt
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.ClassID
import org.eclipse.emf.ecore.resource.Resource
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.AttributeID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.SignalID
import java.util.Map
import java.util.HashMap
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.ClassNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.AttributeNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.SignalNode
import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper

class ModelInformationHelperImpl extends AbstractModelInformationHelper {
	
	IMappingModel model
	ArrayList<String> objects
	ArrayList<String> attributes
	List<List<String>> enums
	Map<String,ClassNode> mapObjects
	Map<String,AttributeNode> mapAttributes
	Map<String,SignalNode> mapSignals
	Map<String,String> mapStructs
	boolean isEnum
	
	override getSourceCount() {
		return 0
	}
	
	override getClasses() {
		return new ArrayList
	}
	
	override getSignals() {
		return new ArrayList
	}
	
	override setModel(IMappingModel model) {
		this.model = model
		setup
	}
	
	override isDebugOpt() {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return false
	}
	
	override getAttributes(String objectName) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	override getSystemAttribut(String string) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return ""
	}
	
	override getCorrespondingAttribute(String monAttr) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return ""
	}
	
	override getSignalBoundarys(String attr) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
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
	
	override getReqEnums() {
		return enums
	}
	
	override getFilterVersion() {
		return "0, 1, 0"
	}
	
	override getInputPinsNames() {
		val list = new ArrayList
		switch(filterType) {
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
	
	override getOutputPinsNames() {
		val list = new ArrayList
		switch(filterType) {
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
	
	override getObjectPtrs() {
		val list = new ArrayList
		switch(filterType) {
		case ABSTRACT_FUNCTION: 
			{}
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			{}
		case SCENE_ABSTRACTION:
			{list.add("coderDesc")}
		case TEST_COVERAGE_MONITOR:
			{}
		}
		
		return list;
	}
	
	override getHeaderTemplateDefines() {
		return ''''''
	}
	
	override getHeaderTemplateIncludes() {
		return ''''''
	}
	
	override getHeaderTemplatePrivateMembers() {
		return ''''''
	}
	
	override getHeaderTemplatePrivateFunctions() {
		return ''''''
	}
	
	override getHeaderTemplateProtectedFunctions() {
		return ''''''
	}
	
	
	override getTemplateEvaluateContent() {
		return ''''''
	}
	
	override getEvaluateMethod() {
		switch(filterType) {
		case ABSTRACT_FUNCTION:
			{}
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			{}
		case SCENE_ABSTRACTION:
			if (getInputPins().size() == 1 && getOutputPins().size() == 1) {
				val in = getInputPins().get(0);
				val out = getOutputPins().get(0);
				return '''«out.getPinObjectType()»Evaluate("«in.getPinObjectType()» «in.getPinObjectName()»);'''
			}
			
		case TEST_COVERAGE_MONITOR:
			{}
		}
		return "";
	}
	
	override getTemplateConstructorContent() {
		return ''''''
	}
	
	override getTemplateDeconstructorContent() {
		return ''''''
	}
	
	override getMoreConstructorValues() {
		return new ArrayList
	}
	
	
	
}