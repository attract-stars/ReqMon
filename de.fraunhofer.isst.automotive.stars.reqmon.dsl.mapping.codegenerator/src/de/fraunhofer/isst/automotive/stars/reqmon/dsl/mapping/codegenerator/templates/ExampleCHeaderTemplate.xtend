package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import org.eclipse.emf.ecore.resource.Resource
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.DefinitionElememnt
import org.eclipse.emf.ecore.EObject
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.ClassID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.ClassNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.AttributeID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Types
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.SignalID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.AttributeNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.GenerationModel

/**
 * This class generates the header file content.
 * @author sgraf
 */
class ExampleCHeaderTemplate {
	
	
	def CharSequence generateExampleTemplate(GenerationModel model) '''
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
	
	«model.structs»
	
	«model.signalsAndAttributes»
	
	#endif
	'''
		
		

	def getIncludeGardsBegin() '''
	EXAMPLE.H
	'''
	
	/**
	 * Returns structures with the information given in the resource for classes.
	 */
	def getStructs(GenerationModel model) '''
	«FOR res : model.mappingResourceList»
		«res.struct»
	«ENDFOR»
	'''
		
	
	def getStruct(Resource resource) '''
	«FOR elem : resource.allContents.toIterable.filter(DefinitionElememnt)»
		«elem.def.selectClasses»
	«ENDFOR»
	'''
	
	def getSignalsAndAttributes(GenerationModel model) '''
	«FOR res : model.mappingResourceList»
		«res.signalAndAttribute»
	«ENDFOR»
	'''
	
	/**
	 * Constructs data types with the information given in the resource for signals and attributes.
	 */
	def getSignalAndAttribute(Resource resource) '''
	«FOR elem : resource.allContents.toIterable.filter(DefinitionElememnt)»
		«elem.def.selectSignalsAndAttributes»
	«ENDFOR»
	'''

	
	/**
	 * Select the correct compiler method for the given EObject: 
	 * compileClassID for ClassID. 
	 */
	def selectClasses(EObject obj) '''
		«IF obj instanceof ClassID»
		«obj.compileClassID»
		«ENDIF»
	'''
	
	/**
	 * Select the correct compiler method for the given EObject: 
	 * compileSignalID for SignalID, compileAttributID for AttributeID. 
	 */
	def selectSignalsAndAttributes(EObject obj) '''
		«IF obj instanceof SignalID»
		«obj.compileSignalID»
		«ELSEIF obj instanceof AttributeID»
		«obj.compileAttributeID»
		«ENDIF»
	'''
	
	/**
	 * Constructs a float data type for the given Signal: float [signal_name];
	 */
	def compileSignalID(SignalID id) '''
		«IF id.signal !== null»
		/* signal */
		float «id.signal.name.replace(" ", "_").toFirstLower»;
		«ENDIF»
		
	'''
	
	/** 
	 * Constructs a data type for the given attribute: [attribute_type] [attribute_name];
	 */
	def compileAttributeID(AttributeID id) '''
		«IF id.attr !== null»
		«id.attr.compileSignalAttribute»
		«id.attr.attrtype.type.compileType» «id.attr.compileStructAttribute»«id.attr.name.replace(" ", "_").toFirstLower»;
		«ENDIF»
		
	'''
	
	/**
	 * Add the structure name if the attribute is a structure.
	 */
	def compileStructAttribute(AttributeNode attr) {
		if(attr.attrtype.type.compileType.toString.equals("struct")) {
			return '''«attr.name.replace(" ", "_").toFirstUpper» '''
		}
	}
	
	/**
	 * Add the signal comment if the attribute is a signal.
	 */
	def compileSignalAttribute(AttributeNode attr) {
		if(attr.attrtype.type.type !== null && attr.attrtype.type.type.name.equals("signal")) {
			return '''/* signal */'''
		}
	}

	/**
	 * Constructs a struct with the name of the given class and 
	 * the attributes listed in the class:
	 * struct [class_name] : [inheritance] { [attribute_type] [attribute_name]; ... };
	 */
	def compileClassID(ClassID id) '''
		«IF id.cla.attribute.empty»
		struct «id.cla.name.replace(" ", "_").toFirstUpper» «id.cla.compileInheritance»{};
		«ELSE»
		struct «id.cla.name.replace(" ", "_").toFirstUpper» «id.cla.compileInheritance»{	
			«id.cla.compileAttributes»
		};
		«ENDIF»
		
	'''
	
	/**
	 * Constructs the inheritance if it exists.
	 */
	def compileInheritance(ClassNode node) {
		if (node.inheritance !== null && node.inheritance.name !== null 
			&& node.inheritance.name.name!== null && !node.inheritance.name.name.equals("")) {
		''': «node.inheritance.name.name.toFirstUpper» ''' 
		}
	}

	/**
	 * Constructs all attributes of the given class.
	 */
	def compileAttributes(ClassNode node) '''
		«FOR attr : node.attribute»
		«attr.attrtype.type.compileType» «attr.name.replace(" ", "_").toFirstLower»;
		«ENDFOR»
	'''
	
	/**
	 * Constructs the attribute type.
	 */
	def CharSequence compileType(Types type) {
		if (type.type !== null) {
			return type.type.name.selectCppType
		}
		if (type.list !== null) {
			return ''''''
		}
		if (type.newtype !== null) {
			return '''«type.newtype.name»'''
		}
	}
	
	/**
	 * Choose the appropriate type in c++ for the given type.
	 */
	def CharSequence selectCppType(String name) {
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
				return '''char[100]'''
			case 'char':
				return '''char'''
			case 'boolean':
				return '''bool'''
			case 'bool':
				return '''bool'''
			case 'date':
				return '''void'''
			case 'class':
				return '''struct'''
			case 'newType':
				return ''''''
			case 'signal':
				return '''float'''
			default:
				return ''''''
		}
			
		
		
	}
	
	
	
}