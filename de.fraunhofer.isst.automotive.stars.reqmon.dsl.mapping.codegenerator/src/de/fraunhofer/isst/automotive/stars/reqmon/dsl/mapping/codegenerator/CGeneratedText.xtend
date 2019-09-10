package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator

import org.eclipse.emf.ecore.resource.Resource
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.DefinitionElememnt
import org.eclipse.emf.ecore.EObject
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.ClassID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.ClassNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.AttributeID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Type
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.Types

class CGeneratedText {
	
	
	def CharSequence compiler(Resource resource) {
		return '''
			«FOR elem : resource.allContents.toIterable.filter(DefinitionElememnt)»
			«elem.def.select»
			«ENDFOR»
		'''
	}
	
	
	def select(EObject obj) '''
		«IF obj instanceof ClassID»
		«obj.compileClassID»
		«ENDIF»
	'''

 
	/**
	 * Constructs a struct with the name of the given class and 
	 * the attributes listed in the class:
	 * struct [class_name] : [inheritance] { [attribute_type] [attribute_name]; ... }
	 */
	def compileClassID(ClassID id) '''
		struct «id.cla.name.toFirstUpper» «id.cla.compileInheritance»{
			«id.cla.compileAttributes»
		}
	'''
	
	/**
	 * Constructs the attributes of the given class.
	 */
	def compileAttributes(ClassNode node) '''
		«FOR attr : node.attribute»
		«attr.attrtype.type.compileType» «node.name»;
		«ENDFOR»
	'''
	
	/**
	 * Constructs the attribute type or new type.
	 */
	def compileType(Types type) '''
	«IF type.type !== null»
		«type.type.name»
		«ELSEIF type.list !== null»
		«FOR elem : type.list.type»
		«elem.name»
		«ENDFOR»
		«ELSEIF type.newtype !== null»
		«type.newtype.name»
		«ENDIF»
	'''
	
	/**
	 * Constructs the inheritance if it exists.
	 */
	def compileInheritance(ClassNode node) '''
		«IF node.inheritance !== null && !node.inheritance.equals("")»
		: «node.inheritance.name» 
		«ENDIF»
	'''
	
	def compileAttributeID(AttributeID id) '''
		«id.def» «id.attr.attrtype.node»
	'''
	
	def selectReference(EObject refObj) '''
		«IF refObj !== null»
		«IF refObj instanceof ClassNode»
		«refObj.compileClassNode»
		«ENDIF»
		«ENDIF»
	'''
	
	def compileClassNode(ClassNode node) '''
		«node.name»
	'''
	
}