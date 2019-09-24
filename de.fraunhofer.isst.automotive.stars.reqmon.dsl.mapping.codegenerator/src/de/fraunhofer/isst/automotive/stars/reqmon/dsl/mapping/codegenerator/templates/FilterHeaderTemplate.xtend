package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

class FilterHeaderTemplate {
	
	def CharSequence generateTemplate() '''
	#ifndef «filename»
	#define «filename»
	
	#define OID_DADAS_«oidName» "«oidString»"
	«moreDefines»
	
	«includes»
	
	class «className» «'aff'.inheritances»
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
			
		public: «comment»
			«publicFunctions»
			
		protected: 
			«protectedFunctions»
	}
	
	#endif
	'''
	

	// filename for the include guard macro
	def getFilename() '''$name$'''
	
	//  oid macro
	def getOidName() '''$oid_name$'''
	
	def getOidString() '''$oid_string$'''
	
	// defines macro
	def getMoreDefines() '''$more_defines$'''
	
	// includes macro
	def getIncludes() '''$includes$'''
	
	// class name macro
	def getClassName() '''$class_name$'''
	
	// inheritnace macro
	def getInheritances(String filtertype) '''
	: public «switch(filtertype) {
		case 'aff': '''cConditionTriggeredFilter'''
		case 'fcof': '''cConditionTriggeredFilter'''
		case 'saf': '''cFilter'''
		case 'tcmf': '''ILoadRecordsInterface, public cConditionTriggeredFilter'''
		default: '''$class_name$''' 	
		}»
	'''
	
	// adtf declare filter version macros
	def getFilterName() '''$filter_name$'''
	
	def getVersionTerm() '''$Version$'''
	
	def getVersion() '''$0, 1, 0$'''
	
	def getOidDesignation() '''$oid_designation$'''
	
	// input and output pin macro, object pointer macro
	def getInputPins() '''
	$cInputPin m_oInput1$;
	$cInputPin m_oInput2$;'''
	
	def getOutputPins() '''
	$cOutputPin m_oOutput1$;
	$cOutputPin m_oOutput2$;'''
	
	def getObjectPtrs() '''
	$cObjectPtr<IMediaTypeDescription> m_pCoderDesc1$;
	$cObjectPtr<IMediaTypeDescription> m_pCoderDesc2$;'''
	
	// private member macro
	def getMorePrivateMembers() '''
	$cMember member1$;
	$cMember member2$;
	'''
	
	// private function macro
	def getPrivateFunctions() '''
	tResult $function1$();
	tResult $function2$();
	'''
	// comment macro
	def getComment() '''// $comment$'''
	
	// public function macro
	def getPublicFunctions() '''
	$type function1$();
	$type function2$();
	$type function3$();
	'''
	
	// protected function macro
	def getProtectedFunctions() '''
	$type function1$();
	$type function2$();
	$type function3$();
	'''
	
}