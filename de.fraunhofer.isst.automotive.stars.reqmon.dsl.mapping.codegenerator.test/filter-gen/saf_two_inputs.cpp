#include system-types.h
#include mediatypes.h
#include scene_abstraction_filter.h

tBool debugOpt = tFalse;

ADTF_FILTER_PLUGIN("DADAS Scene Abstraction Filter", OID_DADAS_SCENE_ABSTRACTION, cDadasSceneAbstractionFilter)

cDadasSceneAbstractionFilter::cDadasSceneAbstractionFilter(const tChar* __info) : cFilter(__info)
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
		
		cObjectPtr<IMediaType> pTimeInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_TIME);
		RETURN_IF_FAILED(m_oTimeInput.Create("time", pTimeInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oTimeInput));
		
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

tResult cDadasSceneAbstractionFilter::Start(__exception)
{
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
}

tResult cDadasSceneAbstractionFilter::Stop(__exception)
{
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasSceneAbstractionFilter::Shutdown(tInitStage eStage, __exception)
{
	switch (eStage)
	{
	case StageFirst:
		{
			m_oTimeout.Release();
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


tResult cDadasSceneAbstractionFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
{
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	tTimeStamp nTriggerTime = pSample->GetTime();
	
	//Get Scene Sample
	cObjectPtr<IMediaSample> pSceneSample;
	ISampleQueue* pSceneQueue = GetQueue(&m_oSceneInput);
	if(pSceneQueue) {
		pSceneQueue->Get(&pSceneSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest);
	}
	RETURN_IF_POINTER_NULL(pSceneSample);
	
	//Get Time Sample
	cObjectPtr<IMediaSample> pTimeSample;
	ISampleQueue* pTimeQueue = GetQueue(&m_oTimeInput);
	if(pTimeQueue) {
		pTimeQueue->Get(&pTimeSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest);
	}
	RETURN_IF_POINTER_NULL(pTimeSample);
	
	
	//Lock Sample
	kernelMutex.Enter();
	
	Evaluate(&pSceneSample, &pTimeSample);
	
	kernelMutex.Leave();
	
	RETURN_NOERROR;
}

tCategorization cDadasSceneAbstractionFilter::Evaluate(IMediaSample* pSceneSample, IMediaSample* pTimeSample)
{
}

void cDadasSceneAbstractionFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
	}
}

