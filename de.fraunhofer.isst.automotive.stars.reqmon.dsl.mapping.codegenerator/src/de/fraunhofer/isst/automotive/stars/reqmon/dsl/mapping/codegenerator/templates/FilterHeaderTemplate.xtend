package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

/**
 * This class offers header templates for three different filter types in c++.
 * @author sgraf
 */
class FilterHeaderTemplate {
	
	/**
	 * Generates a filter header of the given type.
	 */
	def CharSequence generateTemplate(String filtertype) '''
	#ifndef �filename�
	#define �filename�
	
	#define OID_DADAS_�oidName� "�oidString�"
	�moreDefines�
	
	�includes�
	
	class �className� �filtertype.inheritances�
	{
		ADTF_DECLARE_FILTER_VERSION(OID_DADAS_�oidName�, "�filterName�", OBJCAT_DataFilter, "�versionTerm�", �version�, "�oidDesignation�")
		
		private: // private members
			�inputPins�
			
			�outputPins�
			
			�objectPtrs�
			
			�morePrivateMembers�
			
		public:
			�className�(const tChar* __info);
			virtual ~�className�();
			
		private: // private functions
			�privateFunctions�
			
		public: // overwrites cFilter
			tResult Init(tInitStage eStage, __exception = NULL);
			tResult Start(__exception);
			tResult Stop(__exception);
			tResult Shutdown(tInitStage eStage, __exception);
			
		�filtertype.publicFunctions�
			
		protected: 
			�filtertype.protectedFunctions�
	}
	
	#endif
	'''
	

	// filename for the include guard macro
	def private getFilename() '''$name$'''
	
	//  oid macro
	def private getOidName() '''$oid_name$'''
	
	def private getOidString() '''$oid_string$'''
	
	// defines macro
	def private getMoreDefines() '''$more_defines$'''
	
	// includes macro
	def private getIncludes() '''$includes$'''
	
	// class name macro
	def private getClassName() '''$class_name$'''
	
	// inheritnace macro
	def private getInheritances(String filtertype) '''
	: public «switch(filtertype) {
		case 'one': '''cConditionTriggeredFilter'''
		case 'two': '''cFilter'''
		case 'three': '''ILoadRecordsInterface, public cConditionTriggeredFilter'''
		default: '''$class_name$''' 	
		}» 
	'''
	
	// adtf declare filter version macros
	def private getFilterName() '''$filter_name$'''
	
	def private getVersionTerm() '''$Version$'''
	
	def private getVersion() '''$0, 1, 0$'''
	
	def private getOidDesignation() '''$oid_designation$'''
	
	// input and output pin macro, object pointer macro
	def private getInputPins() '''
	$cInputPin m_oInput1$;
	$cInputPin m_oInput2$;'''
	
	def private getOutputPins() '''
	$cOutputPin m_oOutput1$;
	$cOutputPin m_oOutput2$;'''
	
	def private getObjectPtrs() '''
	$cObjectPtr<IMediaTypeDescription> m_pCoderDesc1$;
	$cObjectPtr<IMediaTypeDescription> m_pCoderDesc2$;'''
	
	// private member macro
	def private getMorePrivateMembers() '''
	$cMember member1$;
	$cMember member2$;
	'''
	
	// private function macro
	def private getPrivateFunctions() '''
	tResult $function1$();
	tResult $function2$();
	'''
	
	// public function macro
	def private getPublicFunctions(String filtertype) '''
	�IF filtertype.equals("one") || filtertype.equals("three")�
	public: // overrides cFilter //implements IRunnable
		tResult Run(tInt nActivationCode,
			const tVoid* pvUserData,
			tInt szUserDataSize,
			ucom::IException** __exception_ptr = NULL);
	�ENDIF�
	'''
	
	// protected function macro
	def private getProtectedFunctions(String filtertype) '''
	�IF filtertype.equals("one") || filtertype.equals("three")�
	tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
	�ENDIF�
	tResult Evaluate(�parameter�);
	
	$type function1$();
	$type function2$();
	$type function3$();
	
	void LOG(cString mes);
	'''
	
	def private getParameter() '''$parameter$'''
	
}