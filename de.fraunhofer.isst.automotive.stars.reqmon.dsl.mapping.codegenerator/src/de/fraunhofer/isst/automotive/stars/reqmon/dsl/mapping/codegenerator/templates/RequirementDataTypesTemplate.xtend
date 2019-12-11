package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import java.util.List
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper

/**
 * This class generates the requirement data types.
 * @author sgraf
 */
class RequirementDataTypesTemplate {
	
	AbstractModelInformationHelper helper
	
	new(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
	
	def CharSequence generateTemplate() '''
	«helper.getComment»
	
	#ifndef «includeGardsBegin»
	#define «includeGardsBegin»
		
	«types»
		
	#endif
	'''
	
	// TODO: The order of the structs and enums is important
	// TODO: The names should be different from the system type names
	// TODO: the enum values must be unambiguously
	
	def void setModelInformationHelper(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
			
	def private getIncludeGardsBegin() '''
		REQUIREMENT_DATA_TYPES_H
	'''
	
	def private getTypes() {
		val struc = structs
		val en = enums
		val examp = exampleStruct
		return 
		'''
		«en»
		
		«struc»
		«examp»
		'''
	}
	
	def private getStructs() '''
	«FOR obj : helper.getReqObjects»
	struct tReq«obj.toFirstUpper» «obj.getReqInheritance»{
		«FOR attr : helper.getReqAttribute(obj)»
		«IF attr.charAt(0).compareTo('e') === 0»«attr»«ELSE»«attr.toFirstUpper»«ENDIF» «attr»;
		«ENDFOR»
	};
	
	«ENDFOR»
	'''
	
	def private getExampleStruct() '''
	struct tCategorization {
	};
	'''
	
	def private getReqInheritance(String obj) {
		val inheritances = helper.getReqInheritance(obj)
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