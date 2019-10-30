package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType
import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper

class RequirementDataTypesTemplate {
	
	AbstractModelInformationHelper helper
	
	def CharSequence generateTemplate() '''
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
		
	«structs»
	«enums»
		
	#endif
	'''
	
	def void setModelInformationHelper(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
			
	def private getIncludeGardsBegin() '''
		REQUIREMENT_DATA_TYPES.H
	'''
	
	
	def private getStructs() '''
	«FOR obj : helper.getReqObjects»
	struct t«obj.toFirstUpper» «obj.getReqInheritance»{
		«FOR attr : helper.getReqAttribute(obj)»
		«IF attr.charAt(0).compareTo('e') === 0»«attr»«ELSE»«attr.toFirstUpper»«ENDIF» «attr»;
		«ENDFOR»
	};
	
	«ENDFOR»
	'''
	
	def private getReqInheritance(String obj) {
		val inheritances = helper.getInheritance(obj)
		if(!inheritances.empty) {
			inheritances.getInheritances
		}
	} 
	
	def private getInheritances(List<String> inhList) {
		if (inhList.size === 1) {
			return ''': t«inhList.get(0).toFirstUpper» '''
		}
		else if (inhList.size === 2) {
			return ''': t«inhList.get(0).toFirstUpper», t«inhList.get(1).toFirstUpper» '''
		}
		else if (inhList.size >=3) {
			return ''': «FOR i : 0..< inhList.size-1»t«inhList.get(i).toFirstUpper», «ENDFOR»t«inhList.get(inhList.size-1).toFirstUpper» '''
		}
		return ''''''
	}
	
	def private getEnums() '''
	«FOR e : helper.getReqEnums()»
	«IF e.size > 1»
	enum «e.get(0)» { «e.getEnumValues» };
	«ENDIF»
	«ENDFOR»
	'''
	
	def private getEnumValues(List<String> values) {
		if (values.size === 2) {
			return '''«values.get(1).toUpperCase» = 0'''
		}
		else {
			val lastIndex = values.size -1
			return '''«values.get(1).toUpperCase» = 0, «FOR i : 2..<lastIndex»«values.get(i).toUpperCase», «ENDFOR»«values.get(lastIndex).toUpperCase»'''
		}
	}
	
	/*def private getObjects() '''
		«IF model.requirementList !== null»
			«FOR req : model.requirementList»
				«IF req.elementType.equals(RequirementType.OBJECT)»
				t«req.elementName.replace(" ", "_").toFirstUpper» «req.elementName.replace(" ", "_").toFirstLower»;
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''
	
	def private getFunctions() '''
		«IF model.requirementList !== null»
			«FOR req : model.requirementList»
				«IF req.elementType.equals(RequirementType.FUNCTION)»
				tFunction «req.elementName.replace(" ", "_")»;
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''*/
	
}