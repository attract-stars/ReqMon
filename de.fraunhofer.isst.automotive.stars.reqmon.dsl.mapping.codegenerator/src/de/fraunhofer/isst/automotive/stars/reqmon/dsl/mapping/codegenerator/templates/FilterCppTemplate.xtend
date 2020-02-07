/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin

/**
 * This class offers templates for the structure of the different filter types in c++.
 * @author sgraf
 */
class FilterCppTemplate {
	
	FilterType filtertype
	AbstractModelInformationHelper helper
	
	new(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
	
	def void setModelInformationHepler(AbstractModelInformationHelper helper) {
		this.helper = helper
	}
	
	/**
	 * Generates a filter in c++ of the given type.
	 */
	def CharSequence generateTemplate(FilterType filtertype) {
	this.filtertype = filtertype
	this.helper.setFilterType(filtertype);
	
	val clas = helper.getClassName
	'''
	«helper.getComment»
	
	«includes»
	
	«datatypeSettings»
	
	ADTF_FILTER_PLUGIN("«filterName»", «helper.oidName», «clas»)
	
	«clas»::«clas»(const tChar* __info) : «constructorValueSetting»
	{
		«constructor»
	}
	
	«clas»::~«clas»()
	{
		«destructor»
	}
	
	tResult «clas»::Init(tInitStage eStage, __exception)
	{
		«init»
	}
	
	tResult «clas»::Start(__exception)
	{
		«start»
	}
	
	tResult «clas»::Stop(__exception)
	{
		«stop»
	}
	
	tResult «clas»::Shutdown(tInitStage eStage, __exception)
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
	#include "«include».h"
	«ENDFOR»
	'''
	
	def private getDatatypeSettings() '''
	tBool debugOpt = «IF helper.isDebugOpt»tTrue«ELSE»tFalse«ENDIF»;
	'''
	
	def private getFilterName() {
		helper.getAdtfDeclareFilterVersionName
	}
	
	
	def private getConstructorValueSetting() {
		'''«superClass»«IF helper.isCFilter»(__info)«ELSE»(tTrue,tTrue,__info),
				m_bTimeout(tFalse)«ENDIF»«moreConstructorValueSettings»'''
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
		helper.getTemplateInitNormal
	}
	
	def private getInitGraphReady() {
		helper.templateInitGraphReady
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
		'''cObjectPtr<IMediaType> «pin.pinObjectPointerName» = new cMediaType(«pin.getMediaType», «pin.getMediaSubType»);'''
	} 
	
	def private getPointer(Pin pin) {
		if (pin.isPointerTest) '''RETURN_IF_POINTER_NULL(«pin.pinObjectPointerName»);'''
	} 
	
	def private getCreatePin(Pin pin) '''RETURN_IF_FAILED(«pin.pinName».Create("«pin.pinObjectName»", «pin.pinObjectPointerName», this«pin.moreCreatePinParameters»));'''
	
	def private getRegisterPin(Pin pin) '''RETURN_IF_FAILED(Register«IF pin.trigger»Trigger«ENDIF»Pin(&«pin.pinName»));'''
	
	def private getMoreCreatePinParameters(Pin pin) '''«pin.createParameters»'''
	
	def private getInterfaceTemplate(Pin pin) {
		if (pin.interface && pin.mediaTypeDescription !== null) {
			'''RETURN_IF_FAILED(«pin.pinObjectPointerName»->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_p«pin.mediaTypeDescription»));'''
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
	tResult «helper.getClassName»::Run(tInt nActivationCode,
		const tVoid* pvUserData,
		tInt szUserDataSize,
		ucom::IException** __exception_ptr)
	{
		RETURN_IF_FAILED(«superClass»::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));
		
		if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
		{
			«helper.getTemplateRunClear»
			
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
	tResult «helper.getClassName»::OnPinEvent(IPin* pSource,
		tInt nEventCode,
		tInt nParam1,
		tInt nParam2,
		IMediaSample* pMediaSample)
	{
		if (nEventCode == IPinEventSink::PE_MediaSampleReceived)
		{
			RETURN_IF_POINTER_NULL(pMediaSample);
			
			if (pSource == &«input.pinName»)
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
	'''
	«IF helper.inputPins.size == 1»«processSample»«ELSE»«onTrigger»«ENDIF»
	
	«evaluate»
	
	«transmitEvaluationResult»
	
	«helper.getTemplateMoreProtectedMethods»
	«log»
	'''
	}
	
	def private getOnTrigger() '''
	tResult «helper.getClassName»::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
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
		
		«IF helper.getGetEvaluateReturnType.toString.compareTo("") !== 0»TransmitEvaluationResult(&evaluationResult);«ENDIF»
		
		RETURN_NOERROR;
	}
	'''
	
	def private getEvaluateReturnTypeAndName() {
		val temp = helper.getGetEvaluateReturnType
		if (temp.toString.compareTo("") !== 0) {
			'''«temp» evaluationResult = '''
		}
	}
	
	def private getSample(Pin input) {
	val obj = input.pinObjectName.toFirstUpper
	'''
	//Get «obj» Sample
	cObjectPtr<IMediaSample> «input.samplePointerName»;
	«IF input.isOwnQueue»cObjectPtr<ISampleQueue> «input.sampleQueueName»;«ELSE»ISampleQueue* «input.sampleQueueName» = GetQueue(&«input.pinName»);«ENDIF»
	«IF input.isOwnQueue»RETURN_IF_FAILED(«input.pinName».GetSampleQueue(&«input.sampleQueueName»));«ENDIF»
	if(«input.sampleQueueName») {
		«input.sampleQueueName»->Get(&«input.samplePointerName»,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest«IF input.isNearestOlderInQueue»Older«ENDIF»);
	}
	RETURN_IF_POINTER_NULL(«input.samplePointerName»);
	'''
	} 
	
	def private getLog() {
	val temp = helper.getTemplateLog
	if (temp !== null) return temp	
	'''
	void «helper.getClassName»::LOG(cString mes)
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
	tResult «helper.getClassName»::ProcessSample(IMediaSample* pSample)
	{
		{
			__sample_read_lock(pSample, «inputPin.pinObjectType», pData);
		
			«outputPin.pinObjectType»* evaluationResult = Evaluate(pData);
		
			TransmitEvaluationResult(evaluationResult);
		
		}
	
		RETURN_NOERROR;
	}
	'''
	
	def private getEvaluate() {
	'''
	«helper.getEvaluateReturnTypeOnlySafAsPointer» «helper.getClassName»::Evaluate(«helper.getEvaluateParameters(true)»)
	{
		«helper.templateEvaluateContent»
	}
	'''
	}
	
	
	def private getTransmitEvaluationResult() '''
	tResult «helper.getClassName»::TransmitEvaluationResult(«transmitEvaluationResultParameters»«moreTransmitEvaluationResultParameters»)
	{
		«transmitContent»
	}
	'''
	
	def private getTransmitContent() {
		val temp = helper.templateTransmitContent
		if ((temp === null || temp.toString.compareTo("") === 0) && helper.outputPins.size === 1) {
			'''
			cObjectPtr<IMediaSample> pNewSample;
			RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pNewSample));
			
			RETURN_IF_FAILED(pNewSample->Update(_clock->GetStreamTime(), &evaluationResult, sizeof(«helper.getGetEvaluateReturnType»), 0));
			
			RETURN_IF_FAILED(«helper.outputPins.get(0).pinName».Transmit(pNewSample));
			
			RETURN_NOERROR;
			'''
		}
		else {
			'''RETURN_NOERROR;'''
		}
	}
	
	def private getTransmitEvaluationResultParameters() {
		if (isEvaluationReturnType) {
			'''«helper.getEvaluateReturnTypeAsPointer» evaluationResult'''
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
		return helper.getEvaluateReturnTypeAsPointer.toString.compareTo("") !== 0
	}
	
	def private getPrivateMethods() '''«helper.getTemplatePrivateMethods»'''
	
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
