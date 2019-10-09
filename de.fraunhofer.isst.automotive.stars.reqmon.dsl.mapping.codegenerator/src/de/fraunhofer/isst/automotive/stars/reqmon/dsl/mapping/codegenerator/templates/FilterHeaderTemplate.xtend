package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.IModelInformationHelper

/**
 * This class offers header templates for three different filter types in c++.
 * @author sgraf
 */
class FilterHeaderTemplate {
	
	FilterType filtertype
	IModelInformationHelper helper
	
	def void setModelInformationHepler(IModelInformationHelper helper) {
		this.helper = helper
	}
	
	/**
	 * Generates a filter header of the given type.
	 */
	def CharSequence generateTemplate(FilterType filtertype) {
	this.filtertype = filtertype
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
			
		public: // overwrites cFilter
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
	def private getMoreDefines() ''''''
	
	// includes macro
	def private getIncludes() ''''''
	
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
	def private getInheritances() '''
		: public «switch(filtertype) {
		case ABSTRACT_FUNCTION: '''cConditionTriggeredFilter'''
		case FUNCTIONAL_CORRECTNESS_ORACLE: '''cConditionTriggeredFilter'''
		case SCENE_ABSTRACTION: '''cFilter'''
		case TEST_COVERAGE_MONITOR: '''ILoadRecordsInterface, public cConditionTriggeredFilter'''
		default: '''$class_name$''' 	
		}»
	'''
	
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
	
	def private getVersion() '''0, 1, 0'''
	
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
	def private getInputPins() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«AFFInputPins»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«FCOFInputPins»'''
			case SCENE_ABSTRACTION: '''«SAFInputPins»'''
			case TEST_COVERAGE_MONITOR: '''«TCMFInputPins»'''
			default: '''$input_pin$'''
		}
	}
	
	def private getAFFInputPins() {
		'''
		'''
	}
	
	def private getFCOFInputPins() {
		'''
		'''
	}
	
	def private getSAFInputPins() {
		'''
		cInputPin m_oInput;
		'''
	}
	
	def private getTCMFInputPins() {
		'''
		$cInputPin m_oInput1$;
		$cInputPin m_oInput2$;
		'''
	}
	
	def private getOutputPins() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«AFFOutputPins»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«FCOFOutputPins»'''
			case SCENE_ABSTRACTION: '''«SAFOutputPins»'''
			case TEST_COVERAGE_MONITOR: '''«TCMFOutputPins»'''
			default: '''$output_pin$'''
		}
	}
	
	def private getAFFOutputPins() {
		''''''
	}
	
	def private getFCOFOutputPins() {
		''''''
	}
	
	def private getSAFOutputPins() {
		'''
		cOutputPin m_oOutput;
		'''
	}
	
	def private getTCMFOutputPins() {
		'''
		$cOutputPin m_oOutput1$;
		$cOutputPin m_oOutput2$;
		'''
	}
	
	def private getObjectPtrs() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«AFFObjectPtrs»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«FCOFObjectPtrs»'''
			case SCENE_ABSTRACTION: '''«SAFObjectPtrs»'''
			case TEST_COVERAGE_MONITOR: '''«TCMFObjectPtrs»'''
			default: '''$object_ptrs$'''
		}
	}
	
	def private getAFFObjectPtrs() {
		''''''
	}
	
	def private getFCOFObjectPtrs() {
		''''''
	}
	
	def private getSAFObjectPtrs() {
		'''cObjectPtr<IMediaTypeDescription> m_pCoderDesc;'''
	}
	
	def private getTCMFObjectPtrs() {
		'''	
		$cObjectPtr<IMediaTypeDescription> m_pCoderDesc1$;
		$cObjectPtr<IMediaTypeDescription> m_pCoderDesc2$;
		'''
	}
	
	
	
	// private member macro
	def private getMorePrivateMembers() ''''''
	
	// private function macro
	def private getPrivateFunctions() ''''''
	
	// public function macro
	def private getPublicFunctions() '''
	«IF filtertype.equals(FilterType.ABSTRACT_FUNCTION) || filtertype.equals(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE) 
		|| filtertype.equals(FilterType.TEST_COVERAGE_MONITOR)»
	public: // overrides cFilter //implements IRunnable
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
	«IF filtertype.equals(FilterType.ABSTRACT_FUNCTION) || filtertype.equals(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE) 
			|| filtertype.equals(FilterType.TEST_COVERAGE_MONITOR)»
	tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
	tResult Evaluate(«parameter»);
	«ELSEIF filtertype.equals(FilterType.SCENE_ABSTRACTION)»
	tResult ProcessSample(IMediaSample* pSample);
	DADAS::tCategorisation Categorize(tScene* scene);
	tResult SendBOOSTCategories(DADAS::tCategorisation* categorisation);
	«ENDIF»
	void LOG(cString mes);
	'''
	
	def private getParameter() '''$parameter$'''
	
}