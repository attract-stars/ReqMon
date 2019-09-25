package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

/**
 * This class offers templates for three different filter types in c++.
 * @author sgraf
 */
class FilterCppTemplate {
	
	/**
	 * Generates a filter in c++ of the given type.
	 */
	def CharSequence generateTemplate(String filtertype) '''
	«filtertype.includes»
	
	«datatypeSettings»
	
	ADTF_FILTER_PLUGIN("«filterName»", OID_DADAS_«oidName», «className»)
	
	«className»::«className»(const tChar* __info) : «filtertype.memberValueSetting»
	{
		«filtertype.constructor»
	}
	
	«className»::~«className»()
	{
		«filtertype.destructor»
	}
	
	tResult «className»::Init(tInitStage eStage, __exception)
	{
		«filtertype.init»
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
	
	«filtertype.publicMethods»
	
	«protectedMethods»
	
	«privateMethods»
	'''
	
	def private getIncludes(String filtertype) '''
	«IF filtertype.equals("one") || filtertype.equals("three")»
	#include "stdafx.h"
	#include "dtypes.h"
	«ENDIF»
	#include "dadas_monitoring_types.h"
	#include "dadas_mediatypes.h"
	#include "serializationhelper.h"
	
	«moreIncludes»
	'''
	
	def private getMoreIncludes() '''$more includes$'''
	
	def private getDatatypeSettings() '''tBool debugOpt = tFalse;'''
	
	def private getFilterName() '''$filter_name$'''
	
	def private getOidName() '''$oid_name$'''
	
	def private getClassName() '''$class_name$'''
	
	def private getMemberValueSetting(String filtertype) {
		switch(filtertype) {
			case 'one': '''cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse)«moreValueSettings»'''
			case 'two': '''cFilter(__info)'''
			case 'three': '''cConditionTriggeredFilter(tTrue,tTrue,__info), ILoadRecordsInterface(),
				m_bTimeout(tFalse)'''
			default: '''$value settings$'''
		}
	}
	
	def private getMoreValueSettings() ''', $more value settings$'''
	
	def private getConstructor(String filtertype) '''
	«IF filtertype.equals("one") || filtertype.equals("three")»
	kernelMutex.Create();
	
	SetPropertyInt("timeout", $value$);
	SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
	
	«morePropertySettings»
	«ENDIF»
	'''
	
	def private getMorePropertySettings() '''$set more properties$'''
	
	def private getDestructor(String filtertype) '''
	«IF filtertype.equals("one") || filtertype.equals("three")»
	kernelMutex.Release();
	«ENDIF»
	'''
	
	def private getInit(String filtertype) {
		switch(filtertype) {
			case 'one': '''«initWithTypeOne»'''
			case 'two': '''«initWithTypeTwo»'''
			case 'three': '''«initWithTypeThree»'''
			default: '''$init_implementation$'''
		}
	} 
	
	def private getInitWithTypeOne() '''
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		//Description Manager
		cObjectPtr<IMediaDescriptionManager> pDescManager;
		RETURN_IF_FAILED(_runtime->GetObject(OID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			IID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			(tVoid**)&pDescManager, 
			__exception_ptr));
			
		«stageFirstActions»
	}
	else if (eStage == StageNormal)
	{
		//Nothing to do
	}
	else if (eStage == StageGraphReady)
	{
		// create a new timeout if required
		tTimeStamp nTimeout = GetPropertyInt("timeout");
		if (nTimeout < 0)
		{
			THROW_ERROR_DESC(ERR_INVALID_ARG, "The timeout value can not be negative");
		}
		else if (nTimeout != 0)
		{
			m_bTimeout = tTrue;
			RETURN_IF_FAILED(m_oTimeout.Create(this, nTimeout, OIGetInstanceName()));
		}
	}
	
	RETURN_NOERROR;
	'''
	
	def private getInitWithTypeTwo() '''
	RETURN_IF_FAILED(cFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		«stageFirstActions»
	}
	else if (eStage == StageNormal)
	{
		//Nothing to do
	}
	else if (eStage == StageGraphReady)
	{
		//Nothing to do
	}
	
	RETURN_NOERROR;
	'''
	
	def private getInitWithTypeThree() '''
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		«stageFirstActions»
	}
	else if (eStage == StageNormal)
	{
		//Nothing to do
	}
	else if (eStage == StageGraphReady)
	{
		// create a new timeout if required
		tTimeStamp nTimeout = GetPropertyInt("timeout");
		if (nTimeout < 0)
		{
			THROW_ERROR_DESC(ERR_INVALID_ARG, "The timeout value can not be negative");
		}
		else if (nTimeout != 0)
		{
			m_bTimeout = tTrue;
			RETURN_IF_FAILED(m_oTimeout.Create(this, nTimeout, OIGetInstanceName()));
		}
		
		«moreActions»
	}
	
	RETURN_NOERROR;
	'''
	
	def private getStageFirstActions() '''
	cObjectPtr<IMediaType> «PTypeName» = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_«mediaSubType»«moreMediaTypeParameters»);
	?«pointer»?
	«createPin»
	«registerPin»
	?«interface»?
	
	?cMediaType* «PTypeName»;
	«PTypeName» = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_«mediaSubType»«moreMediaTypeParameters»);
	«pointer»
	«createPin»
	«registerPin»
	?
	'''
	
	def private getPTypeName() '''$pTypeName$'''
	
	def private getMediaSubType() '''$TYPE$'''
	
	def private getMoreMediaTypeParameters() ''', $more parameters$'''
	
	def private getPointer() '''RETURN_IF_POINTER_NULL(«PTypeName»);'''
	
	def private getRegisterPin() '''RETURN_IF_FAILED(Register?Trigger?Pin(&«pinName»));'''
	
	def private getPinName() '''$m_oPin'''
	
	def private getCreatePin() '''RETURN_IF_FAILED(«pinName».Create("«mediaTypeName»", «PTypeName», this«moreCreatePinParameters»));'''
	
	def private getMediaTypeName() '''$type$'''
	
	def private getMoreCreatePinParameters() ''', $more parameters$'''
	
	def private getInterface() '''RETURN_IF_FAILED(«PTypeName»->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&«mediaTypeDescription»));'''
	
	def private getMediaTypeDescription() '''m_pTypeDesc'''
	
	def private getStart(String filtertype) {
		switch(filtertype) {
			case 'one': '''«startWithTypeOne»'''
			case 'two': '''«startWithTypeTwo»'''
			case 'three': '''«startWitTypeThree»'''
			default: '''$start_implementation$'''
		}
	}
	
	def private getStartWithTypeOne() '''
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
		
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
	'''
	
	def private getStartWithTypeTwo() '''
	RETURN_IF_FAILED(cFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
	'''
	
	def private getStartWitTypeThree() '''
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	«moreConditions»
	
	RETURN_NOERROR;
	'''
	
	def private getMoreConditions() '''$if (...)$'''
	
	def private getStop(String filtertype) {
		switch(filtertype) {
			case 'one': '''«stopWithTypeOne»'''
			case 'two': '''«stopWithTypeTwo»'''
			case 'three': '''«stopWithTypeThree»'''
			default: '''$stop_implementation$'''
		}
	}
	
	def private getStopWithTypeOne() '''
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
	'''
	
	def private getStopWithTypeTwo() '''
	return cFilter::Stop(__exception_ptr);
	'''
	
	def private getStopWithTypeThree() '''
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	«moreConditions»
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
	'''
	
	def private getShutdown(String filtertype) {
		switch(filtertype) {
			case 'one': '''«shutdownWithTypeOne»'''
			case 'two': '''«shutdownWithTypeTwo»'''
			case 'three': '''«shutdownWithTypeOne»'''
			default: '''$shutdown_implementation$'''
		}
	}
	
	def private getShutdownWithTypeOne() '''
	if (StageGraphReady == eStage)
	{
		m_oTimeout.Release();
	}
	
	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
	'''
	
	def private getShutdownWithTypeTwo() '''
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
	
	
	def private getPublicMethods(String filtertype) '''
	«IF filtertype.equals("one") || filtertype.equals("three")»
	«run»
	«ELSE»
	$public_methods_implementation$
	«ENDIF»
	'''
	
	def private getRun() '''
	tResult «className»::Run(tInt nActivationCode,
		const tVoid* pvUserData,
		tInt szUserDataSize,
		ucom::IException** __exception_ptr)
	{
		RETURN_IF_FAILED(cConditionTriggeredFilter::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));
		
		if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
		{
			«clear»
			
			LOG_WARNING("Timeout");
			// restart our timeout
			m_oTimeout.Start();
		}
		
		RETURN_NOERROR;
	}
	'''
	
	def private getClear() '''clear buffers and/or queues'''
	
	def private getProtectedMethods() '''
	//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
	tResult «className»::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception) {
		// reset our timeout
		if (m_bTimeout)
		{
			m_oTimeout.Start();
		}
		
		tTimeStamp nTriggerTime = pSample->GetTime();
		
		//Get Categorisation Sample
		cObjectPtr<IMediaSample> pCategorisationSample;
		
		«moreActions»
		
		if(pCategorisationQueue) {
			pCategorisationQueue->Get(&pCategorisationSample,
				nTriggerTime,
				1000000,
				adtf::ISampleQueue::SQG_GetNearest); //Thinking that the categorisation is send first before the targets
		}
		RETURN_IF_POINTER_NULL(pCategorisationSample);
		
		«targetSample»
		
		//Lock Sample
		kernelMutex.Enter();
		
		//Get Categorisation
		DADAS::tCategorisation pCategorisationData;
		
			?RETURN_IF_FAILED(?DADAS::HELPER::DeserializeFromSample(pCategorisationSample,pCategorisationData)?)?;
		
			?//Get Abstract Targets
			vector<DADAS::tAbstractTarget> pAbstrTargetsData;
			
			RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pAbstrTargetsSample,pAbstrTargetsData));?
		
			?//Get Concrete Targets
			vector<DADAS::tAbstractTarget> pConcreteTargetsData;?
		
			?RETURN_IF_FAILED(?DADAS::HELPER::DeserializeFromSample(pConcreteTargetsSample,pConcreteTargetsData)?)?;
		
		«moreActions»
		
		kernelMutex.Leave();
		
		RETURN_NOERROR;
	}
	
	void «className»::LOG(cString mes) {		
		if(debugOpt) {
			LOG_INFO(mes);
			//OutputDebugStringWrapper(mes+"\n");
		}
	}
	
	$more protected methods$
	'''
	
	def private getMoreActions() '''$more actions$'''
	
	def private getTargetSample() '''
	?«concreteTargetSample»?
	
	?«abstractTargetSample»?
	'''
	
	def private getConcreteTargetSample() '''
	//Get Concrete Targets Sample
	cObjectPtr<IMediaSample> pConcreteTargetsSample;
	ISampleQueue* pConcreteTargetsQueue = GetQueue(&m_oConcreteTargetsInput);
	if(pConcreteTargetsQueue) {
		pConcreteTargetsQueue->Get(&pConcreteTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 
	}
	RETURN_IF_POINTER_NULL(pConcreteTargetsSample);
	'''
	
	def private getAbstractTargetSample() '''
	//Get Abstract Targets Sample
	cObjectPtr<IMediaSample> pAbstrTargetsSample;
	ISampleQueue* pAbstrTargetsQueue = GetQueue(&m_oAbstractTargetsInput);
	if(pAbstrTargetsQueue) {
		pAbstrTargetsQueue->Get(&pAbstrTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 
	}
	RETURN_IF_POINTER_NULL(pAbstrTargetsSample);
	'''
	
	def private getPrivateMethods() '''$private_methods_implementation$'''
	
}