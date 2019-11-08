#include system-types.h
#include mediatypes.h
#include scene_abstraction_filter.h

tBool debugOpt = tFalse;

ADTF_FILTER_PLUGIN("DADAS Scene Abstraction Filter", OID_DADAS_SCENE_ABSTRACTION, cDadasSceneAbstractionFilter)

cDadasSceneAbstractionFilter::cDadasSceneAbstractionFilter(const tChar* __info) : cConditionTriggeredFilter(__info)
{
}

cDadasSceneAbstractionFilter::~cDadasSceneAbstractionFilter()
{
}

tResult cDadasSceneAbstractionFilter::Init(tInitStage eStage, __exception)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		cObjectPtr<IMediaType> pSceneInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_SCENE);
		RETURN_IF_FAILED(m_oSceneInput.Create("scene", pSceneInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oSceneInput));
		
		cObjectPtr<IMediaType> pCategorizationOutput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_CATEGORIZATION);
		RETURN_IF_FAILED(m_oCategorizationOutput.Create("categorization", pCategorizationOutput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oCategorizationOutput));
		
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
}

tResult cDadasSceneAbstractionFilter::Start(__exception)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
}

tResult cDadasSceneAbstractionFilter::Stop(__exception)
{
	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasSceneAbstractionFilter::Shutdown(tInitStage eStage, __exception)
{
	switch (eStage)
	{
	case StageFirst:
		{
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
	
	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
}

tResult cDadasSceneAbstractionFilter::OnPinEvent(IPin* pSource,
	tInt nEventCode,
	tInt nParam1,
	tInt nParam2,
	IMediaSample* pMediaSample)
{
	if (nEventCode == IPinEventSink::PE_MediaSampleReceived)
	{
		RETURN_IF_POINTER_NULL(pMediaSample);
		
		if (pSource == &m_oSceneInput)
		{
			ProcessSample(pMediaSample);
		}	else {
			RETURN_ERROR(ERR_NOT_SUPPORTED);
		}
	}
	RETURN_NOERROR;
}

tResult cDadasSceneAbstractionFilter::ProcessSample(IMediaSample* pSample)
{
	{
		__sample_read_lock(pMediaSample, tScene, pData);
	
		tCategorization categorization = Evaluate(&pData);
	
	}

	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));
	
	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));
	
	RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasAbstractFunctionFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
{
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	tTimeStamp nTriggerTime = pSample->GetTime();
	
	//Get Categorization Sample
	cObjectPtr<IMediaSample> pCategorizationSample;
	ISampleQueue* pCategorizationQueue = GetQueue(&m_oCategorizationInput);
	if(pCategorizationQueue) {
		pCategorizationQueue->Get(&pCategorizationSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest);
	}
	RETURN_IF_POINTER_NULL(pCategorizationSample);
	
	//Get ConcreteTargets Sample
	cObjectPtr<IMediaSample> pConcreteTargetsSample;
	ISampleQueue* pConcreteTargetsQueue = GetQueue(&m_oConcreteTargetsInput);
	if(pConcreteTargetsQueue) {
		pConcreteTargetsQueue->Get(&pConcreteTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest);
	}
	RETURN_IF_POINTER_NULL(pConcreteTargetsSample);
	
	
	//Lock Sample
	kernelMutex.Enter();
	
	tCategorization categorization = Evaluate(&pCategorizationSample, &pConcreteTargetsSample);
	
	kernelMutex.Leave();
	
	TransmitEvaluationResult(&categorization);
	
	RETURN_NOERROR;
}

tCategorization cDadasSceneAbstractionFilter::Evaluate(IMediaSample* pSceneSample, IMediaSample* pTimeSample)
{
}

tResult cDadasSceneAbstractionFilter::TransmitEvaluationResult(tCategorization* categorization)
{
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));
	pMediaSample = categorization;
	
	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));
	
	RETURN_IF_FAILED(m_oCategorizationOutput.Transmit(pMediaSample));
}

void cDadasSceneAbstractionFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
	}
}

