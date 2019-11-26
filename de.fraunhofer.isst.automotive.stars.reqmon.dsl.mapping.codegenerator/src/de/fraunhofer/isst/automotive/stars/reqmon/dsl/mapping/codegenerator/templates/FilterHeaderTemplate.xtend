package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper

/**
 * This class offers an header structure template for the different filter types.
 * @author sgraf
 */
class FilterHeaderTemplate {
	
	FilterType filtertype
	AbstractModelInformationHelper helper
	
	new(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
	
	def void setModelInformationHepler(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
	
	def CharSequence generateTemplate(FilterType filtertype) {
		this.filtertype = filtertype
		this.helper.filterType = filtertype
		getTemplate()
	}
	
	/**
	 * Generates a filter header of the given type.
	 */
	private def getTemplate() {
	'''
	#define «helper.oidName» "«oidString»"
	«moreDefines»
	
	«includes»
	
	class «className» «inheritances»
	{
		ADTF_DECLARE_FILTER_VERSION(«helper.oidName», "«filterName»", OBJCAT_DataFilter, "«versionTerm»", «version», "«oidDesignation»")
		
		private: // private members
			«inputPins»
			
			«outputPins»
			
			«objectPtrs»
			
			«morePrivateMembers»
			
		public:
			«className»(const tChar* __info);
			virtual ~«className»();
			
		private: // private functions
			«privateFunctions»
			
		public: // overwrites «filterSuperClass»
			tResult Init(tInitStage eStage, __exception = NULL);
			tResult Start(__exception);
			tResult Stop(__exception);
			tResult Shutdown(tInitStage eStage, __exception);
			
		«publicFunctions»
			
		protected: 
			«protectedFunctions»
	};
	
	'''
	}
	

	def private getOidString() '''«IF helper.getHeaderOidString !== null»«helper.getHeaderOidString»«ELSE»de.fraunhofer.isst.automotive.stars.reqmon.dsl.data.monitoring.«oidStringEnd»«ENDIF»'''
	
	def private getOidStringEnd() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''abstract.function'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''functional.correctness.oracle'''
			case SCENE_ABSTRACTION: '''scene.abstraction'''
			case TEST_COVERAGE_MONITOR: '''test.coverage.monitor'''
			default: '''filter.type'''
		}
	}
	
	// defines macro
	def private getMoreDefines() {
		helper.getHeaderTemplateDefines
	}
	
	// includes macro
	def private getIncludes() {
		helper.getHeaderTemplateIncludes
	}
	
	// class name macro
	def private getClassName() {
		helper.getClassName
	}
	
	// inheritnace macro
	def private getInheritances() ''': public «filterSuperClass»«helper.getMoreSuperClasses»'''  
	
	def private getFilterSuperClass() {
		if (helper.inputPins.size === 1) {
			return '''cFilter'''
		}
		else if (helper.inputPins.size >= 2) {
			return '''cConditionTriggeredFilter'''
		}
	}
	
	// adtf declare filter version macros
	def private getFilterName() {
		helper.getAdtfDeclareFilterVersionName
	}
	
	def private getVersionTerm() '''Version'''
	
	def private getVersion() '''«helper.getFilterVersion»'''
	
	def private getOidDesignation() {
		helper.getAdtfDeclareFilterVersionDesignation
	}
	
	// input and output pin macro, object pointer macro
	def private getInputPins() '''
	«FOR pin : helper.inputPins»
	cInputPin «pin.getPinName»;
	«ENDFOR»
	'''
	
	def private getOutputPins() '''
	«FOR pin : helper.outputPins»
	cOutputPin «pin.getPinName»;
	«ENDFOR»
	'''
	
	def private getObjectPtrs() '''
	«FOR pin : helper.pins»
	«IF pin.isCoderDesc»cObjectPtr<IMediaTypeDescription> «pin.coderDescName»;«ENDIF»
	«ENDFOR»
	'''
	
	// private member macro
	def private getMorePrivateMembers() '''«helper.getHeaderTemplatePrivateMembers»'''
	
	// private function macro
	def private getPrivateFunctions() '''«helper.getHeaderTemplatePrivateFunctions»'''
	
	// public function macro
	def private getPublicFunctions() '''
	«IF filtertype.equals(FilterType.ABSTRACT_FUNCTION) || filtertype.equals(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE) 
		|| filtertype.equals(FilterType.TEST_COVERAGE_MONITOR)»
	public: // overrides «filterSuperClass» //implements IRunnable
		tResult Run(tInt nActivationCode,
			const tVoid* pvUserData,
			tInt szUserDataSize,
			ucom::IException** __exception_ptr = NULL);
	«ELSEIF filtertype.equals(FilterType.SCENE_ABSTRACTION)»
	public: 
		tResult OnPinEvent(IPin* pSource,
			tInt nEventCode,
			tInt nParam1,
			tInt nParam2,
			IMediaSample* pMediaSample);
	«ENDIF»
	'''
	
	// protected function macro
	def private getProtectedFunctions() '''
	«IF helper.inputPins.size === 1»tResult ProcessSample(IMediaSample* pSample);
	«ELSEIF helper.inputPins.size >= 2»
	tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
	«ENDIF»
	«helper.getEvaluateMethod»
	tResult TransmitEvaluationResult(«transmitEvaluationResultParameters»«moreTransmitEvaluationResultParameters»);
	«helper.getHeaderTemplateProtectedFunctions»
	void LOG(cString mes);
	'''
	
	def private getTransmitEvaluationResultParameters() {
		if (isEvaluationReturnType) {
			'''«helper.getEvaluateReturnType»* evaluationResult'''
		}
	}
	
	def private getMoreTransmitEvaluationResultParameters() {
		val temp = helper.moreTransmitParameters
		if (temp !== null) {
			if (isEvaluationReturnType) {
				''', temp'''
			}
			else {
				'''temp'''
			}
		}
	}
	
	def private isEvaluationReturnType() {
		return helper.getEvaluateReturnType.toString.compareTo("") !== 0
	}
	 
	
	
	
	
}