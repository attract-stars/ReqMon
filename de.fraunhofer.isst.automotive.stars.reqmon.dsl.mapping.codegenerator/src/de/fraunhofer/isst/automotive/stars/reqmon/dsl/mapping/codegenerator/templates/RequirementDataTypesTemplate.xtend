package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType

class RequirementDataTypesTemplate {
	
	def CharSequence generateTemplate(IMappingModel model) '''
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
		
	«model.objects»
	
	«model.functions»
		
	#endif
	'''
			
	def private getIncludeGardsBegin() '''
		REQUIREMENT_DATA_TYPES.H
	'''
	
	def private getObjects(IMappingModel model) '''
		«IF model.requirementList !== null»
			«FOR req : model.requirementList»
				«IF req.elementType.equals(RequirementType.OBJECT)»
				t«req.elementName.replace(" ", "_").toFirstUpper» «req.elementName.replace(" ", "_").toFirstLower»;
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''
	
	def private getFunctions(IMappingModel model) '''
		«IF model.requirementList !== null»
			«FOR req : model.requirementList»
				«IF req.elementType.equals(RequirementType.FUNCTION)»
				tFunction «req.elementName.replace(" ", "_")»;
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''
	
}