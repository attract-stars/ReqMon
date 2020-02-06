package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import org.eclipse.emf.ecore.EObject
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Model
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.ClassNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Types
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.SignalNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.AttributeNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.MessageNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Type

/**
 * This class generates the system data types.
 * @author sgraf
 */
class SystemDataTypesTemplate {
	
	/**
	 * Generates the system data types.
	 */
	def CharSequence generateTemplate(IMappingModel model) '''
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
	
	// classes
	«model.structs»
	
	// signals
	«model.signals»
	
	#endif
	'''
	
	// TODO: The order of the structs and enums is important
	// TODO: The names should be different from the requirement type names
	// TODO: the enum values must be unambiguously
			
	def private getIncludeGardsBegin() '''
		SYSTEM_DATA_TYPES_H
	'''
	
	/**
	 * struct tAllocation {
		int bit_size;
		char* location;
	};
	* 
	* 
	* // messages
	«model.messages»
	 */
	
	/**
	 * Returns structures with the class informations given in the resources of the model.
	 */
	def private getStructs(IMappingModel model) '''
	«IF model.systemModel !== null»
		«model.systemModel.struct»
	«ENDIF»
	'''
		
	def private getStruct(EObject obj) '''
	«IF obj instanceof Model»
		«FOR clas : obj.classes»
			«clas.compileClass»
		«ENDFOR»
	«ENDIF»
	'''
	
	def private getClasses(Model model) {
		for (node : model.nodes) {
			if (node.systemNode !== null && node.systemNode.datastruct !== null) {
				return node.systemNode.datastruct.class_
			}
		}
	}
	
	
	def private getMessages(IMappingModel model) '''
		«IF model.systemModel !== null»
		«model.systemModel.message»
		«ENDIF»
	'''
	
	def private getMessage(EObject obj) '''
	«IF obj instanceof Model»
		«obj.message.messageComponents»
	«ENDIF»
	'''
	
	def private getMessage(Model model) {
		for (node : model.nodes) {
			if (node.systemNode !== null && node.systemNode.messageNode !== null) {
				return node.systemNode.messageNode
			}
		}
	}
	
	def private getMessageComponents(MessageNode mess) '''
	«IF mess !== null»
		struct t«mess.name.replace(" ", "_").toFirstUpper» {
			tAllocation allocation;
			int* sig_refs;
		};
	«ENDIF»
	
	'''
	
	
	/**
	 * Returns data types with the signal and attribute informations given in the resources of the model.
	 */
 	def private getSignals(IMappingModel model) '''
	«IF model.systemModel !== null»
		«model.systemModel.signal»
	«ENDIF»
	'''
	
	def private getSignal(EObject obj) '''
	«IF obj instanceof Model»
		«FOR sig : obj.signals»
			«sig.compileSignal»
		«ENDFOR»
	«ENDIF»
	'''
	
	def private getSignals(Model model) {
		for (node : model.nodes) {
			if (node.systemNode !== null && node.systemNode.signalNode !== null) {
				return node.systemNode.signalNode
			}
		}
	}

	
	
	/**
	 * Constructs a float data type for the given Signal: float [signal_name];
	 */
	def private compileSignal(SignalNode signal) '''
		«IF signal !== null»
		struct t«signal.name.replace(" ", "_").toFirstUpper» {
			int sig_ref;
			char* «signal.datatype.node.replace("<", "").replace(">", "")»;
			float «signal.maxval.node.replace("<", "").replace(">", "")»;
			float «signal.minval.node.replace("<", "").replace(">", "")»;
			float «signal.prefval.node.replace("<", "").replace(">", "")»;
			float «signal.step.node.replace("<", "").replace(">", "")»;
		};
		«ENDIF»
		
	'''
	
	
	/**
	 * Constructs a struct with the name of the given class and 
	 * the attributes listed in the class:
	 * struct [class_name] : [inheritance] { [attribute_type] [attribute_name]; ... };
	 */
	def private compileClass(ClassNode cla) '''
		«IF cla.attribute.empty»
		struct «cla.name» «cla.compileInheritance»{};
		«ELSEIF cla.name.charAt(0).compareTo('e') === 0»
		enum «cla.name» {
			«cla.compileEnum»
		};
		«ELSE»
		struct «cla.name» «cla.compileInheritance»{	
			«cla.compileAttributes»
		};
		«ENDIF»
		
	'''
	
	/**
	 * Constructs the inheritance if it exists.
	 */
	def private compileInheritance(ClassNode node) {
		if (node.inheritance !== null && node.inheritance.name !== null 
			&& node.inheritance.name !== null && !node.inheritance.name.equals("")) {
		''': «node.inheritance.name» ''' 
		}
	}

	/**
	 * Constructs all attributes of the given class.
	 */
	def private compileAttributes(ClassNode node) '''
		«FOR attr : node.attribute»
		«IF attr.attrtype.type.type !== null»
		«attr.compileType»;
		«ELSEIF attr.attrtype.type.list !== null»
		«attr.compileList»;
		«ELSEIF attr.attrtype.type.newtype !== null»
		«attr.attrtype.type.newtype.name»;
		«ENDIF»
		«ENDFOR»
	'''
	
	def private compileEnum(ClassNode node) {
		if(node.attribute.empty) return ''''''
		val first = node.attribute.get(0)
		if(node.attribute.length ==2) {
			return '''«first.name.toUpperCase» = 0, «node.attribute.get(1).name.toUpperCase»'''
		}
		val rest = node.attribute.subList(1,node.attribute.length-2)
		val last = node.attribute.get(node.attribute.length-1)
		return '''«first.name.toUpperCase» = 0, 
		«FOR attr : rest»
	«IF attr.attrtype.type.type.name.compareTo('integer') === 0»«attr.name.toUpperCase», 
	«ENDIF»
	«ENDFOR»«last.name.toUpperCase»'''
	}
	
	
	 
	
	/**
	 * Constructs the attribute type.
	 */
	def private CharSequence compileType(AttributeNode attr) {
		if (attr.attrtype.type.type.name.equals("class")) {
			val splitted = attr.name.split(' ')
			if (splitted.length === 2) {
				return '''«splitted.get(0)» «splitted.get(1)»'''
			}
			return '''«attr.name» «attr.name.substring(1).toFirstLower»'''
		}
		return '''«attr.attrtype.type.type.name.selectCppType» «attr.name»'''
	}
	
	def private compileList(AttributeNode attr) {
		if (!attr.attrtype.type.list.type.empty) {
			val splitted = attr.name.split(' ')
			if (splitted.length != 2) return ''''''
			return '''«splitted.get(0)»* «splitted.get(1)»'''
		}
	}
	
	
	/**
	 * Choose the appropriate type in c++ for the given type.
	 */
 	def private CharSequence selectCppType(String name) {
		switch(name) {
			case 'integer': 
				return '''tUInt8'''
			case 'int':
				return '''tUInt8'''
			case 'float':
				return '''tFloat32'''
			case 'short':
				return '''tUInt8'''
			case 'double':
				return '''tFloat64'''
			case 'byte':
				return '''tUInt8'''
			case 'string':
				return '''tChar*'''
			case 'char':
				return '''tChar'''
			case 'boolean':
				return '''tBool'''
			case 'bool':
				return '''tBool'''
			case 'date':
				return '''void'''
			case 'class':
				return ''''''
			case 'newType':
				return '''void'''
			case 'signal':
				return '''float'''
			default:
				return ''''''
		}
			
		
		
	}
	
}