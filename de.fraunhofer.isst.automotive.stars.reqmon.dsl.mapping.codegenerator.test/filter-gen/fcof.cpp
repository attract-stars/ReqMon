#include system-types.h
#include mediatypes.h
#include function_correctness_oracle_filter.h

tBool debugOpt = tFalse;

ADTF_FILTER_PLUGIN("DADAS Functional Correctness Oracle Filter", OID_DADAS_FUNCTIONAL_CORRECTNESS_ORACLE, cDadasFunctionalCorrectnessOracleFilter)

cDadasFunctionalCorrectnessOracleFilter::cDadasFunctionalCorrectnessOracleFilter(const tChar* __info) : cConditionTriggeredFilter(tTrue,tTrue,__info), 
				m_bTimeout(tFalse)
{
	kernelMutex.Create();
	
	SetPropertyInt("timeout", $timeout_value$);
	SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
}

cDadasFunctionalCorrectnessOracleFilter::~cDadasFunctionalCorrectnessOracleFilter()
{
	kernelMutex.Release();
}

tResult cDadasFunctionalCorrectnessOracleFilter::Init(tInitStage eStage, __exception)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));
	
	if (eStage == StageFirst)
	{
		cObjectPtr<IMediaType> pCanInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_CAN);
		RETURN_IF_FAILED(m_oCanInput.Create("can", pCanInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oCanInput));
		
		cObjectPtr<IMediaType> pCategorizationInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_CATEGORIZATION);
		RETURN_IF_FAILED(m_oCategorizationInput.Create("categorization", pCategorizationInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oCategorizationInput));
		
		cObjectPtr<IMediaType> pAbstractTargetsInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_ABSTRACTTARGETS);
		RETURN_IF_FAILED(m_oAbstractTargetsInput.Create("abstractTargets", pAbstractTargetsInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oAbstractTargetsInput));
		
		cObjectPtr<IMediaType> pConcreteTargetsInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_CONCRETETARGETS);
		RETURN_IF_FAILED(m_oConcreteTargetsInput.Create("concreteTargets", pConcreteTargetsInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oConcreteTargetsInput));
		
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

tResult cDadasFunctionalCorrectnessOracleFilter::Start(__exception)
{
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
}

tResult cDadasFunctionalCorrectnessOracleFilter::Stop(__exception)
{
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}
	
	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasFunctionalCorrectnessOracleFilter::Shutdown(tInitStage eStage, __exception)
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

tResult cDadasFunctionalCorrectnessOracleFilter::Run(tInt nActivationCode,
	const tVoid* pvUserData,
	tInt szUserDataSize,
	ucom::IException** __exception_ptr)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));
	
	if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
	{
		
		LOG_WARNING("Timeout");
		// restart our timeout
		m_oTimeout.Start();
	}
	
	RETURN_NOERROR;
}

tResult cDadasFunctionalCorrectnessOracleFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception)
{
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	tTimeStamp nTriggerTime = pSample->GetTime();
	
	//Get Can Sample
	cObjectPtr<IMediaSample> pCanSample;
	ISampleQueue* pCanQueue = GetQueue(&m_oCanInput);
	if(pCanQueue) {
		pCanQueue->Get(&pCanSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest);
	}
	RETURN_IF_POINTER_NULL(pCanSample);
	
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
	
	//Get AbstractTargets Sample
	cObjectPtr<IMediaSample> pAbstractTargetsSample;
	ISampleQueue* pAbstractTargetsQueue = GetQueue(&m_oAbstractTargetsInput);
	if(pAbstractTargetsQueue) {
		pAbstractTargetsQueue->Get(&pAbstractTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest);
	}
	RETURN_IF_POINTER_NULL(pAbstractTargetsSample);
	
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
	
	Evaluate(&pCanSample, &pCategorizationSample);
	
	kernelMutex.Leave();
	
	RETURN_NOERROR;
}

tBool cDadasFunctionalCorrectnessOracleFilter::Evaluate(IMediaSample* pCanSample, IMediaSample* pCategorizationSample)
{
}

void cDadasFunctionalCorrectnessOracleFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
	}
}

