
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
	RETURN_IF_FAILED(cFilter::Init(eStage, __exception_ptr));
	
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
	RETURN_IF_FAILED(cFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
}

tResult cDadasSceneAbstractionFilter::Stop(__exception)
{
	return cFilter::Stop(__exception_ptr);
}

tResult cDadasSceneAbstractionFilter::Shutdown(tInitStage eStage, __exception)
{
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

tCategorization cDadasSceneAbstractionFilter::Evaluate(tScene* scene)
{
}

void cDadasSceneAbstractionFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}
