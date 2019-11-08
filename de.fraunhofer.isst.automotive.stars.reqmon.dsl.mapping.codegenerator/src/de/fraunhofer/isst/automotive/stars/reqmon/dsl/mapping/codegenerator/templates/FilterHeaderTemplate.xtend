package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper

/**
 * This class offers header templates for three different filter types in c++.
 * @author sgraf
 */
class FilterHeaderTemplate {
	
	FilterType filtertype
	AbstractModelInformationHelper helper
	
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
	#define OID_DADAS_«oidName» "«oidString»"
	«moreDefines»
	
	«includes»
	
	class «className» «inheritances»
	{
		ADTF_DECLARE_FILTER_VERSION(OID_DADAS_«oidName», "«filterName»", OBJCAT_DataFilter, "«versionTerm»", «version», "«oidDesignation»")
		
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
	

	//  oid macro
	def private getOidName() '''«filtertype»'''
	
	def private getOidString() '''$oid_string$'''
	
	// defines macro
	def private getMoreDefines() '''«helper.getHeaderTemplateDefines»'''
	
	// includes macro
	def private getIncludes() '''«helper.getHeaderTemplateIncludes»'''
	
	// class name macro
	def private getClassName() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''cDadasAbstractFunctionFilter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''cDadasFunctionalCorrectnessOracleFilter'''
			case SCENE_ABSTRACTION: '''cDadasSceneAbstractionFilter'''
			case TEST_COVERAGE_MONITOR: '''cDadasTestCoverageMonitorFilter'''
			default: '''$class_name$'''
		}
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
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''DADAS Abstract Function Filter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''DADAS Functional Correctness Oracle Filter'''
			case SCENE_ABSTRACTION: '''DADAS Scene Abstraction Filter'''
			case TEST_COVERAGE_MONITOR: '''DADAS Test Coverage Monitor Filter'''
			default: '''$filter_name$'''
		}
	}
	
	def private getVersionTerm() '''Version'''
	
	def private getVersion() '''«helper.getFilterVersion»'''
	
	def private getOidDesignation() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''Abstract Function'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''Functional Correctness Oracle'''
			case SCENE_ABSTRACTION: '''Scene Abstraction'''
			case TEST_COVERAGE_MONITOR: '''Test Coverage Monitor'''
			default: '''$oid_designation$'''
		}
	}
	
	// input and output pin macro, object pointer macro
	def private getInputPins() '''
	«FOR pin : helper.inputPins»
	cInputPin m_o«pin.getPinName.toFirstUpper»;
	«ENDFOR»
	'''
	
	def private getOutputPins() '''
	«FOR pin : helper.outputPins»
	cOutputPin m_o«pin.getPinName.toFirstUpper»;
	«ENDFOR»
	'''
	
	def private getObjectPtrs() '''
	«FOR ptr : helper.getObjectPtrs»
	cObjectPtr<IMediaTypeDescription> m_p«ptr.toFirstUpper»;
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
	«helper.getHeaderTemplateProtectedFunctions»
	void LOG(cString mes);
	'''
		
	 
	
	
	
	
}