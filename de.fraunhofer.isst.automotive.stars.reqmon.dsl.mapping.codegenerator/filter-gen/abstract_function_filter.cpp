#include "stdafx.h"
#include "dtypes.h"
#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"

tBool debugOpt = tFalse;

ADTF_FILTER_PLUGIN("DADAS Abstract Function Filter", OID_DADAS_ABSTRACT_FUNCTION, cDadasAbstractFunctionFilter)

cDadasAbstractFunctionFilter::cDadasAbstractFunctionFilter(const tChar* __info) : cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse), $more value settings$
{
	kernelMutex.Create();
	
	SetPropertyInt("timeout", $value$);
	SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
	
	$set more properties$
}

cDadasAbstractFunctionFilter::~cDadasAbstractFunctionFilter()
{
	kernelMutex.Release();
}

tResult cDadasAbstractFunctionFilter::Init(tInitStage eStage, __exception)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		//Description Manager
		cObjectPtr<IMediaDescriptionManager> pDescManager;
		RETURN_IF_FAILED(_runtime->GetObject(OID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			IID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			(tVoid**)&pDescManager, 
			__exception_ptr));
			
		cObjectPtr<IMediaType> $pTypeName$ = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_$TYPE$, $more parameters$);
		?RETURN_IF_POINTER_NULL($pTypeName$);?
		RETURN_IF_FAILED($m_oPin.Create("$type$", $pTypeName$, this, $more parameters$));
		RETURN_IF_FAILED(Register?Trigger?Pin(&$m_oPin));
		?RETURN_IF_FAILED($pTypeName$->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pTypeDesc));?
		
		?cMediaType* $pTypeName$;
		$pTypeName$ = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_$TYPE$, $more parameters$);
		RETURN_IF_POINTER_NULL($pTypeName$);
		RETURN_IF_FAILED($m_oPin.Create("$type$", $pTypeName$, this, $more parameters$));
		RETURN_IF_FAILED(Register?Trigger?Pin(&$m_oPin));
		?
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
}

tResult cDadasAbstractFunctionFilter::Start(__exception)
{
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
		
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
}

tResult cDadasAbstractFunctionFilter::Stop(__exception)
{
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasAbstractFunctionFilter::Shutdown(tInitStage eStage, __exception)
{
	if (StageGraphReady == eStage)
	{
		m_oTimeout.Release();
	}
	
	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
}

tResult cDadasAbstractFunctionFilter::Run(tInt nActivationCode,
	const tVoid* pvUserData,
	tInt szUserDataSize,
	ucom::IException** __exception_ptr)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));
	
	if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
	{
		clear buffers and/or queues
		
		LOG_WARNING("Timeout");
		// restart our timeout
		m_oTimeout.Start();
	}
	
	RETURN_NOERROR;
}

//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
tResult cDadasAbstractFunctionFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
{
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	tTimeStamp nTriggerTime = pSample->GetTime();
	
	//Get Categorisation Sample
	cObjectPtr<IMediaSample> pCategorisationSample;
	
	
	if(pCategorisationQueue) {
		pCategorisationQueue->Get(&pCategorisationSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); //Thinking that the categorisation is send first before the targets
	}
	RETURN_IF_POINTER_NULL(pCategorisationSample);
	
	?//Get Concrete Targets Sample
	cObjectPtr<IMediaSample> pConcreteTargetsSample;
	ISampleQueue* pConcreteTargetsQueue = GetQueue(&m_oConcreteTargetsInput);
	if(pConcreteTargetsQueue) {
		pConcreteTargetsQueue->Get(&pConcreteTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 
	}
	RETURN_IF_POINTER_NULL(pConcreteTargetsSample);
	?
	
	?//Get Abstract Targets Sample
	cObjectPtr<IMediaSample> pAbstrTargetsSample;
	ISampleQueue* pAbstrTargetsQueue = GetQueue(&m_oAbstractTargetsInput);
	if(pAbstrTargetsQueue) {
		pAbstrTargetsQueue->Get(&pAbstrTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 
	}
	RETURN_IF_POINTER_NULL(pAbstrTargetsSample);
	?
	
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
	
	
	kernelMutex.Leave();
	
	RETURN_NOERROR;
}

void cDadasAbstractFunctionFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}

