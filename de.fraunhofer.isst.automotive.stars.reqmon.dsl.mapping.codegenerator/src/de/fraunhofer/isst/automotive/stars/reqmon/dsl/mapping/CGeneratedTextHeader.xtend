package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping

import org.eclipse.emf.ecore.resource.Resource
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.DefinitionElememnt
import org.eclipse.emf.ecore.EObject
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.ClassID
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.sysDef.ClassNode
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.mapping.AttributeID

class CGeneratedTextHeader {
	
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
		«IF obj instanceof AttributeID»
		«obj.compileAttributeID»
		«ENDIF»
	'''

	def compileClassID(ClassID id) '''
		«id.def» «id.cla.name»
	'''
	
	def compileAttributeID(AttributeID id) '''
		«id.def» «id.attr.attrtype»
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