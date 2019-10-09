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

class SystemDataTypesTemplate {
	
	def CharSequence generateTemplate(IMappingModel model) '''
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
	
	struct tAllocation {
		int bit_size;
		char* location;
	};
	
	// classes
	«model.structs»
	
	// messages
	«model.messages»
	
	// signals
	«model.signals»
	
	#endif
	'''
			
	def private getIncludeGardsBegin() '''
		SYSTEM_DATA_TYPES.H
	'''
	
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
			tAllocation allocation;
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
		struct t«cla.name.replace(" ", "_").toFirstUpper» «cla.compileInheritance»{};
		«ELSE»
		struct t«cla.name.replace(" ", "_").toFirstUpper» «cla.compileInheritance»{	
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
		''': «node.inheritance.name.toFirstUpper» ''' 
		}
	}

	/**
	 * Constructs all attributes of the given class.
	 */
	def private compileAttributes(ClassNode node) '''
		«FOR attr : node.attribute»
		«IF attr.attrtype.type.type !== null»
		«attr.compileType» «attr.attributeName»
		«ELSEIF attr.attrtype.type.list !== null»
		«attr.compileType» «attr.name.replace(" ", "_").toFirstLower»;
		«ENDIF»
		«ENDFOR»
	'''
	
	def private getAttributeName(AttributeNode attr) '''
		«IF attr.attrtype.type.type.name.equals("class")»
		t«attr.name.replace(" ", "_").toFirstUpper»;
		«ELSE»
		«attr.name.replace(" ", "_").toFirstLower»;
		«ENDIF»
	'''
	
	/**
	 * Constructs the attribute type.
	 */
	def private CharSequence compileType(AttributeNode attr) {
		if (attr.attrtype.type.type !== null) {
			if (attr.attrtype.type.type.name.equals("class")) {
				return '''t«attr.name.replace(" ","_").toFirstUpper»'''
			}
			return attr.attrtype.type.type.name.selectCppType
		}
		if (attr.attrtype.type.list !== null) {
			if (!attr.attrtype.type.list.type.empty) {
				return '''t«attr.name.substring(0,attr.name.length-1) .replace(" ","_").toFirstUpper»*'''
			}
		}
		if (attr.attrtype.type.newtype !== null) {
			return '''«attr.attrtype.type.newtype.name»'''
		}
	}
	
	
	/**
	 * Choose the appropriate type in c++ for the given type.
	 */
 	def private CharSequence selectCppType(String name) {
		switch(name) {
			case 'integer': 
				return '''int'''
			case 'int':
				return '''int'''
			case 'float':
				return '''float'''
			case 'short':
				return '''int'''
			case 'double':
				return '''double'''
			case 'byte':
				return '''int'''
			case 'string':
				return '''char*'''
			case 'char':
				return '''char'''
			case 'boolean':
				return '''bool'''
			case 'bool':
				return '''bool'''
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