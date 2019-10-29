package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.IModelInformationHelper
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.IPin



/**
 * This class offers templates for three different filter types in c++.
 * @author sgraf
 */
class FilterCppTemplate {
	
	FilterType filtertype
	IModelInformationHelper helper
	
	
	def void setModelInformationHepler(IModelInformationHelper helper) {
		this.helper = helper
	}
	
	/**
	 * Generates a filter in c++ of the given type.
	 */
	def CharSequence generateTemplate(FilterType filtertype) {
	this.filtertype = filtertype
	this.helper.setFilterType(filtertype);
	'''
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
		«start»
	}
	
	tResult «className»::Stop(__exception)
	{
		«stop»
	}
	
	tResult «className»::Shutdown(tInitStage eStage, __exception)
	{
		«shutdown»
	}
	
	«publicMethods»
	
	«protectedMethods»
	
	«privateMethods»
	'''
	}
		
	def private getIncludes() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«exampleIncludes»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«exampleIncludes»'''
			case SCENE_ABSTRACTION: '''«SAFIncludes»'''
			case TEST_COVERAGE_MONITOR: '''«exampleIncludes»'''
			default: '''$includes$'''
		}
	}
	
	def private getExampleIncludes() '''
	#include "stdafx.h"
	#include "dtypes.h"
	#include "dadas_monitoring_types.h"
	#include "dadas_mediatypes.h"
	'''
	
	def private getSAFIncludes() '''
		«FOR include : helper.includes»
		#include «include».h
		«ENDFOR»
	'''
	
	def private getDatatypeSettings() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«SAFDataTypeSettings»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«SAFDataTypeSettings»'''
			case SCENE_ABSTRACTION: '''«SAFDataTypeSettings»'''
			case TEST_COVERAGE_MONITOR: '''«SAFDataTypeSettings»'''
			default: '''$datatypeSettings$'''
		}
	}
	
	def private getSAFDataTypeSettings() '''tBool debugOpt = t«IF helper.isDebugOpt»True«ELSE»False«ENDIF»;'''
	
	def private getFilterName() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''DADAS Abstract Function Filter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''DADAS Functional Correctness Oracle Filter'''
			case SCENE_ABSTRACTION: '''DADAS Scene Abstraction Filter'''
			case TEST_COVERAGE_MONITOR: '''DADAS Test Coverage Monitor Filter'''
			default: '''$filter_name$'''
		}
	}
	
	def private getOidName() '''«filtertype»'''
	
	def private getClassName() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''cDadasAbstractFunctionFilter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''cDadasFunctionalCorrectnessOracleFilter'''
			case SCENE_ABSTRACTION: '''cDadasSceneAbstractionFilter'''
			case TEST_COVERAGE_MONITOR: '''cDadasTestCoverageMonitorFilter'''
			default: '''$class_name$'''
		}
	}
		
	
	def private getMemberValueSetting() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse)«moreValueSettings»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse)«moreValueSettings»'''
			case SCENE_ABSTRACTION: '''cFilter(__info)'''
			case TEST_COVERAGE_MONITOR: '''cConditionTriggeredFilter(tTrue,tTrue,__info), ILoadRecordsInterface(),
				m_bTimeout(tFalse)'''
			default: '''$value settings$'''
		}
	}
	
	def private getMoreValueSettings() ''', $more value settings$'''
	
	def private getConstructor() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«exampleConstructor»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«exampleConstructor»'''
			case SCENE_ABSTRACTION: ''''''
			case TEST_COVERAGE_MONITOR: '''«exampleConstructor»'''
			default: '''$Constructor$'''
		}	
	}
	
	def private getExampleConstructor() '''
	kernelMutex.Create();
	
	SetPropertyInt("timeout", $value$);
	SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
	
	«morePropertySettings»
	'''
	
	def private getMorePropertySettings() '''$set more properties$'''
	
	def private getDestructor() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«exampleDestructor»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«exampleDestructor»'''
			case SCENE_ABSTRACTION: ''''''
			case TEST_COVERAGE_MONITOR: '''«exampleDestructor»'''
			default: '''$Destructor$'''
		}	
	}
	
	def private getExampleDestructor() '''
	«IF filtertype.equals(FilterType.ABSTRACT_FUNCTION) || filtertype.equals(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE) 
			|| filtertype.equals(FilterType.TEST_COVERAGE_MONITOR)»
	kernelMutex.Release();
	«ENDIF»
	'''
	
	def private getInit() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«getAFFInit»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«getFCOFInit»'''
			case SCENE_ABSTRACTION: '''«SAFInit»'''
			case TEST_COVERAGE_MONITOR: '''«TCMFInit»'''
			default: '''$init_implementation$'''
		}
	} 
	
	def private getAFFInit() '''
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		//Description Manager
		cObjectPtr<IMediaDescriptionManager> pDescManager;
		RETURN_IF_FAILED(_runtime->GetObject(OID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			IID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			(tVoid**)&pDescManager, 
			__exception_ptr));
			
		«pinCreation»
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
	
	def private getFCOFInit() '''
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		//Description Manager
		cObjectPtr<IMediaDescriptionManager> pDescManager;
		RETURN_IF_FAILED(_runtime->GetObject(OID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			IID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			(tVoid**)&pDescManager, 
			__exception_ptr));
			
		«pinCreation»
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
	
	def private getSAFInit() '''
	RETURN_IF_FAILED(cFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		«SAFPinCreation»
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
	
	def private getTCMFInit() '''
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		«pinCreation»
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
	
	def private getPinCreation() '''
	cObjectPtr<IMediaType> «PTypeName» = new cMediaType(«mediatype», «mediaSubType»«moreMediaTypeParameters»);
	?«pointer»?
	«createPin»
	«registerPin»
	?«interface»?
	
	?cMediaType* «PTypeName»;
	«PTypeName» = new cMediaType(«mediatype», «mediaSubType»«moreMediaTypeParameters»);
	«pointer»
	«createPin»
	«registerPin»
	?
	'''
	
	def private getSAFPinCreation() '''
	«FOR pin : helper.getPins»
	cObjectPtr <IMediaType> «pin.SAFPTypeName» = new cMediaType(«pin.mediaType», «pin.mediaSubType»);
	«pin.createSAFPin»
	«pin.registerSAFPin»
	
	«ENDFOR»
	'''
	
	def private getPTypeName() '''$pTypeName$'''
	
	def private getSAFPTypeName(IPin pin) '''p«pin.pinName.toFirstUpper»'''
	
	def private getMediatype() '''MEDIATYPE_DADAS'''
	
	def private getMediaSubType() '''MEDIASUBTYPE_$TYPE$'''
	
	def private getMoreMediaTypeParameters() ''', $more parameters$'''
	
	def private getPointer() '''RETURN_IF_POINTER_NULL(«PTypeName»);'''
	
	def private getRegisterPin() '''RETURN_IF_FAILED(Register?Trigger?Pin(&«pinName»));'''
	
	def private getRegisterSAFPin(IPin pin) '''RETURN_IF_FAILED(RegisterPin(&«pin.SAFPinName»));'''
	
	def private getPinName() '''$m_oPin'''
	
	def private getSAFPinName(IPin pin) '''m_o«pin.pinName.toFirstUpper»'''
	
	def private getCreatePin() '''RETURN_IF_FAILED(«pinName».Create("«mediaTypeName»", «PTypeName», this«moreCreatePinParameters»));'''
	
	def private getCreateSAFPin(IPin pin) '''RETURN_IF_FAILED(«pin.SAFPinName».Create("«pin.pinObjectName»", «pin.SAFPTypeName», this));'''
	
	def private getMediaTypeName() '''$type$'''
	
	def private getMoreCreatePinParameters() ''', $more parameters$'''
	
	def private getInterface() '''RETURN_IF_FAILED(«PTypeName»->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&«mediaTypeDescription»));'''
	
	def private getMediaTypeDescription() '''m_pTypeDesc'''
	
	def private getStart() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«startWithTypeOne»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«startWithTypeOne»'''
			case SCENE_ABSTRACTION: '''«SAFStart»'''
			case TEST_COVERAGE_MONITOR: '''«startWitTypeThree»'''
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
	
	def private getSAFStart() '''
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
	
	def private getStop() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«stopWithTypeOne»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«stopWithTypeOne»'''
			case SCENE_ABSTRACTION: '''«SAFStop»'''
			case TEST_COVERAGE_MONITOR: '''«stopWithTypeThree»'''
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
	
	def private getSAFStop() '''
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
	
	def private getShutdown() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«shutdownWithTypeOne»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«shutdownWithTypeOne»'''
			case SCENE_ABSTRACTION: '''«SAFShutdown»'''
			case TEST_COVERAGE_MONITOR: '''«shutdownWithTypeOne»'''
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
	
	def private getSAFShutdown() '''
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
	
	def private getPublicMethods() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«run»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«run»'''
			case SCENE_ABSTRACTION: '''«onPinEvent»'''
			case TEST_COVERAGE_MONITOR: '''«run»'''
			default: '''$public_methods$'''
		}
	}
	
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
	
	def private getOnPinEvent() '''
	tResult «className»::OnPinEvent(IPin* pSource,
		tInt nEventCode,
		tInt nParam1,
		tInt nParam2,
		IMediaSample* pMediaSample)
	{
		if (nEventCode == IPinEventSink::PE_MediaSampleReceived)
		{
			RETURN_IF_POINTER_NULL(pMediaSample);
			
			if (pSource == &m_oInput)
			{
				ProcessSample(pMediaSample);
			}	else {
				RETURN_ERROR(ERR_NOT_SUPPORTED);
			}
		}
		RETURN_NOERROR;
	}
	'''
	
	def private getProtectedMethods() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''
				«onTrigger»
				
				«log»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''
				«onTrigger»
				
				«log»'''
			case SCENE_ABSTRACTION: '''
				«processSample»
				
				«categorize»
				
				«log»'''
			case TEST_COVERAGE_MONITOR: '''
				«onTrigger»
				
				«log»'''
			default: '''$protected_methods$'''
		}
	}
	
	def private getOnTrigger() '''
	//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
	tResult «className»::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
	{
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
	'''
	
	def private getLog() '''
	void «className»::LOG(cString mes)
	{		
		if(debugOpt) {
			LOG_INFO(mes);
			//OutputDebugStringWrapper(mes+"\n");
		}
	}
	'''
	
	def private getProcessSample() {
		if (helper.inputPins.size == 1 && helper.outputPins.size == 1) {
			getProcessSampleForOneInOutput(helper.inputPins.get(0), helper.outputPins.get(0))
		}
	}
	
	def private getProcessSampleForOneInOutput(IPin inputPin, IPin outputPin) '''
	tResult «className»::ProcessSample(IMediaSample* pSample)
	{
		{
			__sample_read_lock(pMediaSample, «inputPin.pinObjectType», pData);
		
			«outputPin.pinObjectType» «outputPin.pinObjectName.toFirstLower» = Categorize(&pData);
		
		}
	
		cObjectPtr<IMediaSample> pMediaSample;
		RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));
		
		RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));
		
		RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));
	
		RETURN_NOERROR;
	}
	'''
	
	def private getCategorize() {
		if (helper.inputPins.size == 1 && helper.outputPins.size == 1) {
			getCategorisationForOneInOutput(helper.inputPins.get(0), helper.outputPins.get(0))
		}
	}
	
	
	def private getCategorisationForOneInOutput(IPin in, IPin out) '''
	«out.pinObjectType» «className»::Categorize(«in.pinObjectType»* «in.pinObjectName.toFirstLower»)
	{
		//Build «out.pinObjectType»
	
		«out.pinObjectType» «out.pinObjectName.toFirstLower»;

		«getValueSettingsForAllSceneElements(in, out)»
	
		return «out.pinObjectName.toFirstLower»;
	}
	'''
	
	def private getValueSettingsForAllSceneElements(IPin in, IPin out) '''
	«FOR elem : helper.getAttributes(out.pinObjectType)»
	«getValueSettingForElement(elem, in, out)»
	«ENDFOR»
	'''
	
	def private getValueSettingForElement(String elem, IPin in, IPin out) {
		if (elem.charAt(0).compareTo('c') == 0) {
			val elements = elem.split(' ')
			if (elements.length === 3) {
				getClassValues(elements, in, out)
			}
		}
		else if (elem.charAt(0).compareTo('l') == 0) {
			val elements = elem.split(' ')
			if (elements.length === 3) {
				getListValues(elements, in)
			}
		}
	}
	
	def private getListValues(String[] elems, IPin in) '''
	«getCorrespondingElement(elems.get(1), in.pinObjectName)»
	'''
	 
	def private getClassValues(String[] elems, IPin in, IPin out) '''
	«elems.get(1)» «elems.get(1)»;
	«getCorrespondingElement(elems.get(1), in.pinObjectName)»
	«FOR attr : helper.getAttributes(elems.get(1))»
		«IF attr.charAt(0).compareTo('a') == 0»
	«attr.getMonitoringAttribute»
		«ENDIF»
	«ENDFOR»
	«out.pinObjectName.toFirstLower».«elems.get(2)» = «elems.get(1)»;
	
	'''
		
		// for (sceneObj : scene)
		// for (attr : sceneObj)
		// systemObj.attr = attr
		//     (boundary test and enum setting, switch cases)
		// category examples:
		//     ego vehicle, lanes (counter, type, curvature, ramp, marking), 
		//     objects (count, location relative to ego, relative velocity, distance),
		//     domain, driver overturning)
		// Logging: LOG(cString::Format("attr: %i", attr));
		// tCategorisation.systemObj = systemObj
	
	def private getCorrespondingElement(String elem, String inObj) {
		val attr = helper.getSystemAttribut(elem)
		if (attr.length === 0) return ''''''
		val splitted = attr.split(' ')
		if (splitted.length !== 3) return ''''''
		
		if (splitted.get(0).compareTo('i') == 0) {
			return '''«splitted.get(1)»* «splitted.get(2)» = &(«inObj.toFirstLower»->«splitted.get(2)»);'''
		}
		else if (splitted.get(0).compareTo('l') == 0) {
			return '''vector<«splitted.get(1)»>* «splitted.get(2)» = &(«inObj.toFirstLower»->«splitted.get(2)»);'''
		}
	}
	
	def private getMonitoringAttribute(String attr) {
		val splitted = attr.split(' ')
		if (splitted.length !== 3) {
			return ''''''
		}
		return '''«splitted.get(1)» «splitted.get(2)» = «getCorrespondingValue(splitted.get(2))»();'''
	}
	
	def private getCorrespondingValue(String monAttr) {
		val splitted = helper.getCorrespondingAttribute(monAttr).split(' ')
		if (splitted.length != 2) {
			return ''''''
		}
		return '''«splitted.get(0)»->«splitted.get(1)»'''
	}
	
	/*def private getBoundaryTest(String attr) {
		val signal = signals.get(monAttr)
		println("Signal: " + signal)
		if (signal === null) return ''''''
		val bounds = helper.getSignalBoundarys(signal)
		val splitted = attr.split(' ')
		if (splitted.length !== 6) {
			return ''''''
		}
		'''
		if («monAttr < «bounds.get(0)) {
			«monObj.«splitted.get(1) = «splitted.get(2);
		} else if («monAttr > «bounds.get(1)) {
			«monObj.«splitted.get(1) = «splitted.get(3);
		} else {
			«monObj.«splitted.get(1) = «splitted.get(5);
		}
		'''
		
	}*/
	
	def private getMoreActions() ''''''
	
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
	
	def private getPrivateMethods() ''''''
	
}