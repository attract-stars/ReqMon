package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

class FilterCppTemplate {
	
	def CharSequence generateTemplate(String filtertype) '''
	«includes»
	
	«datatypeSettings»
	
	ADTF_FILTER_PLUGIN("«filterName»", OID_DADAS_«oidName», «className»)
	
	«className»::«className»(const tChar* __info) : «memberValueSetting»
	{
		«constructor»
	}
	
	«className»::~«className»()
	{
		«destructor»
	}
	
	tResult «className»::Init(tInitStage eStage, __exception)
	{
		«init»
	}
	
	tResult «className»::Start(__exception)
	{
		«filtertype.start»
	}
	
	tResult «className»::Stop(__exception)
	{
		«filtertype.stop»
	}
	
	tResult «className»::Shutdown(tInitStage eStage, __exception)
	{
		«filtertype.shutdown»
	}
	
	«publicMethods»
	
	«protectedMethods»
	
	«privateMethods»
	'''
	
	def getIncludes() '''
	#include $file_name1$.h
	#include $file_name2$.h
	#include $file_name3$.h
	'''
	
	def getDatatypeSettings() '''$type type_name = value$;'''
	
	def getFilterName() '''$filter_name$'''
	
	def getOidName() '''$oid_name$'''
	
	def getClassName() '''$class_name$'''
	
	def getMemberValueSetting() '''$cFilter(_info)$'''
	
	def getConstructor() '''$constructor_implementation$'''
	
	def getDestructor() '''$destructor_implementation$'''
	
	def getInit() '''$init_implementation$'''
	
	def getStart(String filtertype) {
		switch(filtertype) {
			case 'one': '''«startWithTypeOne»'''
			case 'two': '''«startWithTypeTwo»'''
			case 'three': '''«startWitTypeThree»'''
			default: '''$start_implementation$'''
		}
	}
	
	def getStartWithTypeOne() '''
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
		
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
	'''
	
	def getStartWithTypeTwo() '''
	RETURN_IF_FAILED(cFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
	'''
	
	def getStartWitTypeThree() '''
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	«moreConditions»
	
	RETURN_NOERROR;
	'''
	
	def getMoreConditions() '''$if (...)$'''
	
	def getStop(String filtertype) {
		switch(filtertype) {
			case 'one': '''«stopWithTypeOne»'''
			case 'two': '''«stopWithTypeTwo»'''
			case 'three': '''«stopWithTypeThree»'''
			default: '''$stop_implementation$'''
		}
	}
	
	def getStopWithTypeOne() '''
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
	'''
	
	def getStopWithTypeTwo() '''
	return cFilter::Stop(__exception_ptr);
	'''
	
	def getStopWithTypeThree() '''
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	«moreConditions»
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
	'''
	
	def getShutdown(String filtertype) {
		switch(filtertype) {
			case 'one': '''«shutdownWithTypeOne»'''
			case 'two': '''«shutdownWithTypeTwo»'''
			case 'three': '''«shutdownWithTypeOne»'''
			default: '''$shutdown_implementation$'''
		}
	}
	
	def getShutdownWithTypeOne() '''
	if (StageGraphReady == eStage)
	{
		m_oTimeout.Release();
	}
	
	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
	'''
	
	def getShutdownWithTypeTwo() '''
	switch (eStage)
	{
	case StageFirst:
		{
			m_pCoderDesc = NULL;
			break;
		}
	default:
		{
			break;
		}
	}
	
	return cFilter::Shutdown(eStage, __exception_ptr);
	'''
	
	
	def getPublicMethods() '''$public_methods_implementation$'''
	
	def getProtectedMethods() '''$protected_methods_implementation$'''
	
	def getPrivateMethods() '''$private_methods_implementation$'''
	
}