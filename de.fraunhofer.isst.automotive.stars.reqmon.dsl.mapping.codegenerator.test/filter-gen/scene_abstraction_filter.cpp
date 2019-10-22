#include scene_abstraction_filter.h
#include system-types.h
#include mediatypes.h

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
		cObjectPtr<IMediaType> pInput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_SCENE);
		RETURN_IF_FAILED(m_oInput.Create("scene", pInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oInput));
		
		cObjectPtr<IMediaType> pOutput = new cMediaType(MEDIATYPE_DADAS, MEDIATYPE_DADAS_CATEGORISATION);
		RETURN_IF_FAILED(m_oOutput.Create("categorisation", pOutput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oOutput));
		
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
		
		if (pSource == &m_oInput)
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
	
		tCategorisation categorisation = Categorize(&pData);
	
	}

	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));
	
	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));
	
	RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tCategorisation cDadasSceneAbstractionFilter::Categorize(tScene* scene)
{
	//Build tCategorisation

	tCategorisation categorisation;

	tEgoVehicleCategory tEgoVehicleCategory;
	tObject* ego = &(scene->ego);
	tFloat egoVelocity = ego->velocity();
	tFloat egoOffset = ego->offset();
	categorisation.ego = tEgoVehicleCategory;
	
	vector<tLane>* lanes = &(scene->lanes);

	return categorisation;
}

void cDadasSceneAbstractionFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}

