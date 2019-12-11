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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.MessageNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.SystemNode

/**
 * This class extends the AbstractModelInformationHelper.
 * @author sgraf
 */
class ModelInformationHelperImpl extends AbstractModelInformationHelper {
	
	ArrayList<String> objects
	ArrayList<String> attributes
	List<List<String>> enums
	Map<String,ClassNode> mapObjects
	Map<String,AttributeNode> mapAttributes
	Map<String,SignalNode> mapSignals
	Map<String,String> mapStructs
	boolean isEnum
	
	List<String> mediaSubTypes
	List<String> messageNames
	
	new(IMappingModel model) {
		super(model)
		setup
	}
	
	def private void setup() {
		mapObjects = new HashMap
		mapAttributes = new HashMap
		mapSignals = new HashMap
		mapStructs = new HashMap
		objects = new ArrayList
		attributes = new ArrayList
		enums = new ArrayList
		
		mediaSubTypes = new ArrayList
		messageNames = new ArrayList
		
		createMediaSubTypes
		
		createPins(FilterType.ABSTRACT_FUNCTION, FilterType.ABSTRACT_FUNCTION.createInputPinsNames, FilterType.ABSTRACT_FUNCTION.createOutputPinsNames)
		createPins(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE, FilterType.FUNCTIONAL_CORRECTNESS_ORACLE.createInputPinsNames, FilterType.FUNCTIONAL_CORRECTNESS_ORACLE.createOutputPinsNames)
		createPins(FilterType.SCENE_ABSTRACTION, FilterType.SCENE_ABSTRACTION.createInputPinsNames, FilterType.SCENE_ABSTRACTION.createOutputPinsNames)
		createPins(FilterType.TEST_COVERAGE_MONITOR, FilterType.TEST_COVERAGE_MONITOR.createInputPinsNames, FilterType.TEST_COVERAGE_MONITOR.createOutputPinsNames)
		
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
	
	private def createInputPinsNames(FilterType filter) {
		val list = new ArrayList
		switch(filter) {
			case ABSTRACT_FUNCTION: {
				list.add("categorization")
				list.add("concrete targets")}
			case FUNCTIONAL_CORRECTNESS_ORACLE: {
				list.add("can")
				list.add("categorization")
				list.add("abstract targets")
				list.add("concrete targets")}
			case SCENE_ABSTRACTION: {
				for (name : messageNames) {
					list.add(name)
				}
			}
			case TEST_COVERAGE_MONITOR: {}
		}
		
		return list;
	}
	
	private def createOutputPinsNames(FilterType filter) {
		val list = new ArrayList
		switch(filter) {
		case ABSTRACT_FUNCTION: 
			{list.add("targets")}
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			{}
		case SCENE_ABSTRACTION:
			{list.add("categorization")}
		case TEST_COVERAGE_MONITOR:
			{}
		}
		
		return list;
	}
	
	
	
	//---- Methods for the Filters -----------------------
	
	override getTemplateEvaluateContent() {
		return 
	'''
	return false;
	'''
	}
	
	override isDebugOpt() {
		return false
	}
	
	override getTemplateTransmitContent() {
		return ''''''
	}
	
	
	
	//---- Methods for the Requirement Data Types -----------------------
	
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
			enumTypes.set(0, "eReq" + obj.toFirstUpper + "Type")
			array.add("eReq" + obj.toFirstUpper + "Type")
		}
		enums.add(enumTypes)
		return array
	}
	
	override getReqInheritance(String obj) {
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
	
	
	
	//---- Methods for the MediaTypes and MediaSubTypes -----------------------
	
	override getMediaSubTypes() {
		return mediaSubTypes
	}
	
	def private createMediaSubTypes() {
		for (mes : messages) {
			mediaSubTypes.add('''«mes.name.transformName.toUpperCase» 0x«mes.alloc.loc.name»''')
			messageNames.add(mes.name.transformName)
		}
		mediaSubTypes.add("TIME 0x0020");
		mediaSubTypes.add("CATEGORIZATION 0x0030");
		mediaSubTypes.add("ABSTRACT_TARGETS 0x0040");
		mediaSubTypes.add("CONCRETE_TARGETS 0x0050");
		mediaSubTypes.add("TARGETS 0x0060");
		mediaSubTypes.add("CAN 0x0070");
	}
	
	def private List<MessageNode> getMessages() {
		val list =  new ArrayList
		if (model.systemModel === null) return list
		if (model.systemModel.eAllContents.toIterable.filter(SystemNode) === null) return list 
		for (sys : model.systemModel.eAllContents.toIterable.filter(SystemNode)) {
			if (sys.messageNode !== null) {
				list.add(sys.messageNode)
			}
		}
		return list
	}

	private def transformName(String name) {
		if (name.contains('_')) {
			val nameSplit = name.split('_')
			return '''«nameSplit.get(0)»«FOR int i : 1..< nameSplit.length»«nameSplit.get(i).toFirstUpper»«ENDFOR»'''
		}
		else if (name.contains(' ')) {
			val nameSplit = name.split(' ')
			return '''«nameSplit.get(0)»«FOR int i : 1..< nameSplit.length»«nameSplit.get(i).toFirstUpper»«ENDFOR»'''
		}
		else {
			return name
		}
	}
	
	
	
}