/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel

/**
 * This class offers an example header template. It provides an example of how the information of the given model can be used 
 * for the template generation.
 * @author sgraf
 */
class ExampleCHeaderTemplate {
	
	/**
	 * Generates an example header template and uses for it the informations of the model.
	 */
	def CharSequence generateExampleTemplate(IMappingModel model) '''
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
	
	«model.structs»
	
	«model.signalsAndAttributes»
	
	#endif
	'''
		
	def private getIncludeGardsBegin() '''
	EXAMPLE.H
	'''
	
	/**
	 * Returns structures with the class informations given in the resources of the model.
	 */
	def private getStructs(IMappingModel model) '''
	«FOR res : model.getMappingResourceList»
		«res.struct»
	«ENDFOR»
	'''
		
	def private getStruct(Resource resource) '''
	«FOR elem : resource.allContents.toIterable.filter(DefinitionElememnt)»
		«elem.def.selectClasses»
	«ENDFOR»
	'''
	
	/**
	 * Returns data types with the signal and attribute informations given in the resources of the model.
	 */
	def private getSignalsAndAttributes(IMappingModel model) '''
	«FOR res : model.getMappingResourceList»
		«res.signalAndAttribute»
	«ENDFOR»
	'''
	
	def private getSignalAndAttribute(Resource resource) '''
	«FOR elem : resource.allContents.toIterable.filter(DefinitionElememnt)»
		«elem.def.selectSignalsAndAttributes»
	«ENDFOR»
	'''

	
	/**
	 * Select the correct compiler method for the given EObject: 
	 * compileClassID for ClassID. 
	 */
	def private selectClasses(EObject obj) '''
		«IF obj instanceof ClassID»
		«obj.compileClassID»
		«ENDIF»
	'''
	
	/**
	 * Select the correct compiler method for the given EObject: 
	 * compileSignalID for SignalID, compileAttributID for AttributeID. 
	 */
	def private selectSignalsAndAttributes(EObject obj) '''
		«IF obj instanceof SignalID»
		«obj.compileSignalID»
		«ELSEIF obj instanceof AttributeID»
		«obj.compileAttributeID»
		«ENDIF»
	'''
	
	/**
	 * Constructs a float data type for the given Signal: float [signal_name];
	 */
	def private compileSignalID(SignalID id) '''
		«IF id.signal !== null»
		/* signal */
		float «id.signal.name.replace(" ", "_").toFirstLower»;
		«ENDIF»
		
	'''
	
	/** 
	 * Constructs a data type for the given attribute: [attribute_type] [attribute_name];
	 */
	def private compileAttributeID(AttributeID id) '''
		«IF id.attr !== null»
		«id.attr.compileSignalAttribute»
		«id.attr.attrtype.type.compileType» «id.attr.compileStructAttribute»«id.attr.name.replace(" ", "_").toFirstLower»;
		«ENDIF»
		
	'''
	
	/**
	 * Add the structure name if the attribute is a structure.
	 */
	def private compileStructAttribute(AttributeNode attr) {
		if(attr.attrtype.type.compileType.toString.equals("struct")) {
			return '''«attr.name.replace(" ", "_").toFirstUpper» '''
		}
	}
	
	/**
	 * Add the signal comment if the attribute is a signal.
	 */
	def private compileSignalAttribute(AttributeNode attr) {
		if(attr.attrtype.type.type !== null && attr.attrtype.type.type.name.equals("signal")) {
			return '''/* signal */'''
		}
	}

	/**
	 * Constructs a struct with the name of the given class and 
	 * the attributes listed in the class:
	 * struct [class_name] : [inheritance] { [attribute_type] [attribute_name]; ... };
	 */
	def private compileClassID(ClassID id) '''
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
	def private compileInheritance(ClassNode node) {
		if (node.inheritance !== null && node.inheritance.name !== null 
			&& node.inheritance.name!== null && !node.inheritance.name.equals("")) {
		''': «node.inheritance.name.toString.toFirstUpper» ''' 
		}
	}

	/**
	 * Constructs all attributes of the given class.
	 */
	def private compileAttributes(ClassNode node) '''
		«FOR attr : node.attribute»
		«attr.attrtype.type.compileType» «attr.name.replace(" ", "_").toFirstLower»;
		«ENDFOR»
	'''
	
	/**
	 * Constructs the attribute type.
	 */
	def private CharSequence compileType(Types type) {
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
