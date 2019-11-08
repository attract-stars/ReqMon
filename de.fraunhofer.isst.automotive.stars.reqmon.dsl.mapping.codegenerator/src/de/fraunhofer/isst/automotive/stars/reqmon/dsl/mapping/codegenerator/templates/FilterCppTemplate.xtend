package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin

/**
 * This class offers templates for three different filter types in c++.
 * @author sgraf
 */
class FilterCppTemplate {
	
	FilterType filtertype
	AbstractModelInformationHelper helper
	
	
	def void setModelInformationHepler(AbstractModelInformationHelper helper) {
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
	
	«className»::«className»(const tChar* __info) : «constructorValueSetting»
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
		
	def private getIncludes()'''
	«FOR include : helper.includes»
	#include «include».h
	«ENDFOR»
	'''
	
	def private getDatatypeSettings() '''
	tBool debugOpt = t«IF helper.isDebugOpt»True«ELSE»False«ENDIF»;
	'''
	
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
		
	
	def private getConstructorValueSetting() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse)«moreConstructorValueSettings»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse)«moreConstructorValueSettings»'''
			case SCENE_ABSTRACTION: '''cFilter(__info)'''
			case TEST_COVERAGE_MONITOR: '''cConditionTriggeredFilter(tTrue,tTrue,__info), ILoadRecordsInterface(),
				m_bTimeout(tFalse)'''
			default: '''$value settings$'''
		}
	}
	
	def private getMoreConstructorValueSettings() '''
	«FOR setting : helper.getMoreConstructorValues»«ENDFOR»
	'''
	
	def private getConstructor() {
		helper.getTemplateConstructorContent
	}
	
	def private getDestructor() {
		helper.getTemplateDeconstructorContent
	}
	
	/*def private getInit() {
		switch(filtertype) {
			case ABSTRACT_FUNCTION: '''«getAFFInit»'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''«getFCOFInit»'''
			case SCENE_ABSTRACTION: '''«SAFInit»'''
			case TEST_COVERAGE_MONITOR: '''«TCMFInit»'''
			default: '''$init_implementation$'''
		}
	} */
	
	def private getInit() '''
	RETURN_IF_FAILED(«superClass»::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		«initFirst»
	}
	else if (eStage == StageNormal)
	{
		«initNormal»
	}
	else if (eStage == StageGraphReady)
	{
		«initGraphReady»
	}
	
	RETURN_NOERROR;
	'''
	
	def private getSuperClass() {
		if (helper.isTriggeredFilter) {
			return '''cConditionTriggeredFilter'''
		}
		else if (helper.CFilter) {
			return '''cFilter'''
		}
		else return ''''''
	}
	
	def private getInitFirst() {
		if (helper.isDescriptionManager) {
			descriptionManager
		}
		pinCreation
	}
	
	def private getInitNormal() {
		helper.getInitNormalTemplate
	}
	
	def private getInitGraphReady() {
		helper.initGraphReadyTemplate
	}
	
	
	
	def private getDescriptionManager() '''
	//Description Manager
	cObjectPtr<IMediaDescriptionManager> pDescManager;
	RETURN_IF_FAILED(_runtime->GetObject(OID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
		IID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
		(tVoid**)&pDescManager, 
		__exception_ptr));
	
	'''
	
	def private getPinCreation() '''
		«FOR pin : helper.getPins»
		«pin.mediaTypeCreation»
		«pin.pointer»
		«pin.createPin»
		«pin.registerPin»
		«pin.interfaceTemplate»
		
		«ENDFOR»
	'''
	
	def private getMediaTypeCreation(Pin pin) {
		val temp = pin.mediaTypeCreationTemplate
		if (temp !== null) temp
		else
		'''cObjectPtr<IMediaType> «pin.PTypeName» = new cMediaType(«pin.getMediaType», «pin.getMediaSubType»);'''
	} 
	
	def private getPTypeName(Pin pin) '''p«pin.getPinName.toFirstUpper»'''
	
	def private getPointer(Pin pin) {
		if (pin.isPointerTest) '''RETURN_IF_POINTER_NULL(«pin.PTypeName»);'''
	} 
	
	def private getCreatePin(Pin pin) '''RETURN_IF_FAILED(«pin.getHeaderPinName()».Create("«pin.pinObjectName»", «pin.PTypeName», this«pin.moreCreatePinParameters»));'''
	
	def private getHeaderPinName(Pin pin) '''m_o«pin.pinName.toFirstUpper»'''
	
	def private getRegisterPin(Pin pin) '''RETURN_IF_FAILED(Register«IF pin.trigger»Trigger«ENDIF»Pin(&«pin.headerPinName»));'''
	
	def private getMoreCreatePinParameters(Pin pin) '''«pin.createParameters»'''
	
	def private getInterfaceTemplate(Pin pin) {
		if (pin.interface && pin.mediaTypeDescription !== null) {
			'''RETURN_IF_FAILED(«pin.PTypeName»->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_p«pin.mediaTypeDescription»));'''
		}
	} 
	
	def private getStart() '''
	«IF helper.triggeredFilter»«getTimeoutStart»«ENDIF»
	RETURN_IF_FAILED(«superClass»::Start(__exception_ptr));
	«startConditions»
	
	RETURN_NOERROR;
	'''
	
	def private getTimeoutStart() '''
		// start the timeout
		if (m_bTimeout)
		{
			m_oTimeout.Start();
		}
		
	'''
	
	def private getStartConditions() {
		val temp = helper.getMoreStartConditions
		if (temp !== null) temp
	}
	
	def private getStop() '''
	«IF helper.triggeredFilter»«getTimeoutCancel»«ENDIF»
	«stopConditions»
	return «superClass»::Stop(__exception_ptr);
	'''
	
	def private getStopConditions() {
		val temp = helper.getMoreStopConditions
		if (temp !== null) temp
	}
	
	def private getTimeoutCancel() '''
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	'''
	
	def private getShutdown() '''
	switch (eStage)
	{
	case StageFirst:
		{
			«IF helper.triggeredFilter»m_oTimeout.Release();«ENDIF»
			break;
		}
	case StageNormal:
		{
			break;
		}
	case StageGraphReady:
		{
			break;
		}
	default:
		{
			break;
		}
	}
	
	return «superClass»::Shutdown(eStage, __exception_ptr);
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
		RETURN_IF_FAILED(«superClass»::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));
		
		if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
		{
			«helper.getRunClearTemplate»
			
			LOG_WARNING("Timeout");
			// restart our timeout
			m_oTimeout.Start();
		}
		
		RETURN_NOERROR;
	}
	'''
	
	
	
	def private getOnPinEvent() {
		if (helper.inputPins.size == 1) {
			getOnPinEventForOneInput(helper.inputPins.get(0))
		}
	}
	
	def private getOnPinEventForOneInput(Pin input) '''
	tResult «className»::OnPinEvent(IPin* pSource,
		tInt nEventCode,
		tInt nParam1,
		tInt nParam2,
		IMediaSample* pMediaSample)
	{
		if (nEventCode == IPinEventSink::PE_MediaSampleReceived)
		{
			RETURN_IF_POINTER_NULL(pMediaSample);
			
			if (pSource == &m_o«input.pinName.toFirstUpper»)
			{
				ProcessSample(pMediaSample);
			}	else {
				RETURN_ERROR(ERR_NOT_SUPPORTED);
			}
		}
		RETURN_NOERROR;
	}
	'''
	
	def private getProtectedMethods() '''
	«IF helper.inputPins.size == 1»«processSample»«ELSE»«onTrigger»«ENDIF»
	
	«evaluate»
	
	«helper.getMoreProtectedMethodsTemplate»
	«log»
	'''
	
	def private getOnTrigger() '''
	tResult «className»::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
	{
		// reset our timeout
		if (m_bTimeout)
		{
			m_oTimeout.Start();
		}
		
		tTimeStamp nTriggerTime = pSample->GetTime();
		
		«FOR pin :  helper.inputPins»
		«pin.getSample»
		
		«ENDFOR»
		
		//Lock Sample
		kernelMutex.Enter();
		
		«evaluateReturnTypeAndName»Evaluate(«helper.getEvaluateParameters(false)»);
		«helper.getMoreOnTriggerActionsTemplate»
		
		kernelMutex.Leave();
		
		RETURN_NOERROR;
	}
	'''
	
	def private getEvaluateReturnTypeAndName() {
		val temp = helper.getOnTriggerEvaluateReturnType
		if (temp.toString.compareTo("") !== 0) {
			'''«temp» evaluationResult = '''
		}
	}
	
	def private getSample(Pin input) {
	val obj = input.pinObjectName.toFirstUpper
	'''
	//Get «obj» Sample
	cObjectPtr<IMediaSample> p«obj»Sample;
	«IF input.isOwnQueue»cObjectPtr<ISampleQueue> p«obj»Queue;«ELSE»ISampleQueue* p«obj»Queue = GetQueue(&«input.getHeaderPinName»);«ENDIF»
	«IF input.isOwnQueue»RETURN_IF_FAILED(«input.headerPinName».GetSampleQueue(&p«obj»Queue));«ENDIF»
	if(p«obj»Queue) {
		p«obj»Queue->Get(&p«obj»Sample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest«IF input.isNearestOlderInQueue»Older«ENDIF»);
	}
	RETURN_IF_POINTER_NULL(p«obj»Sample);
	'''
	} 
	
	def private getLog() {
	val temp = helper.getLogTemplate
	if (temp !== null) return temp	
	'''
	void «className»::LOG(cString mes)
	{		
		if(debugOpt) {
			LOG_INFO(mes);
		}
	}
	'''
	} 
	
	def private getProcessSample() {
		if (helper.inputPins.size == 1 && helper.outputPins.size == 1) {
			getProcessSampleForOneInOutput(helper.inputPins.get(0), helper.outputPins.get(0))
		}
	}
	
	def private getProcessSampleForOneInOutput(Pin inputPin, Pin outputPin) '''
	tResult «className»::ProcessSample(IMediaSample* pSample)
	{
		{
			__sample_read_lock(pMediaSample, «inputPin.pinObjectType», pData);
		
			«outputPin.pinObjectType» «outputPin.pinObjectName.toFirstLower» = Evaluate(&pData);
		
		}
	
		cObjectPtr<IMediaSample> pMediaSample;
		RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));
		
		RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));
		
		RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));
	
		RETURN_NOERROR;
	}
	'''
	
	def private getEvaluate() {
		if (helper.inputPins.size == 1 && helper.outputPins.size == 1) {
			getEvaluateForOneInOutput(helper.inputPins.get(0), helper.outputPins.get(0))
		}
		else if (helper.inputPins.size > 1) {
			getEvaluateForMoreInputs
		}
	}
	
	
	def private getEvaluateForOneInOutput(Pin in, Pin out) '''
	«out.pinObjectType» «className»::Evaluate(«in.pinObjectType»* «in.pinObjectName.toFirstLower»)
	{
		«helper.getTemplateEvaluateContent»
	}
	'''
	
	def private getEvaluateForMoreInputs() '''
	«helper.getEvaluateReturnType» «className»::Evaluate(«helper.getEvaluateParameters(true)»)
	{
		«helper.templateEvaluateContent»
	}
	'''
	
	def private getPrivateMethods() ''''''
	
	/*def private getValueSettingsForAllSceneElements(Pin in, Pin out) '''
	«FOR elem : helper.getAttributes(out.pinObjectType)»
	«getValueSettingForElement(elem, in, out)»
	«ENDFOR»
	'''*/
	
	/*def private getValueSettingForElement(String elem, Pin in, Pin out) {
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
	}*/
	
	/*def private getListValues(String[] elems, Pin in) '''
	«getCorrespondingElement(elems.get(1), in.pinObjectName)»
	'''*/
	 
	/*def private getClassValues(String[] elems, Pin in, Pin out) '''
	«elems.get(1)» «elems.get(1)»;
	«getCorrespondingElement(elems.get(1), in.pinObjectName)»
	«FOR attr : helper.getAttributes(elems.get(1))»
		«IF attr.charAt(0).compareTo('a') == 0»
	«attr.getMonitoringAttribute»
		«ENDIF»
	«ENDFOR»
	«out.pinObjectName.toFirstLower».«elems.get(2)» = «elems.get(1)»;
	
	'''*/
		
	/*def private getCorrespondingElement(String elem, String inObj) {
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
	}*/
	
	/*def private getMonitoringAttribute(String attr) {
		val splitted = attr.split(' ')
		if (splitted.length !== 3) {
			return ''''''
		}
		return '''«splitted.get(1)» «splitted.get(2)» = «getCorrespondingValue(splitted.get(2))»();'''
	}*/
	
	/*def private getCorrespondingValue(String monAttr) {
		val splitted = helper.getCorrespondingAttribute(monAttr).split(' ')
		if (splitted.length != 2) {
			return ''''''
		}
		return '''«splitted.get(0)»->«splitted.get(1)»'''
	}*/
	
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
	
	
	
}