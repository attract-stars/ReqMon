/*
* Generated by STARS Dadas.
* All Rights reserved by Fraunhofer-Institut Software- und Systemtechnik ISST.
* 
* Generated by stars
* Project: test
* 
*/

#include "dtypes.h"
#include "stdafx.h"
#include "requirement_types.h"
#include "system_types.h"
#include "scene_abstraction_filter.h"

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
		cObjectPtr<IMediaType> pSceneInput = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_SCENE);
		RETURN_IF_FAILED(m_oSceneInput.Create("scene", pSceneInput, this));
		RETURN_IF_FAILED(RegisterPin(&m_oSceneInput));
		
		cObjectPtr<IMediaType> pCategorizationOutput = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_CATEGORIZATION);
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
		__sample_read_lock(pSample, tScene, pData);
	
		tCategorization* evaluationResult = Evaluate(pData);
	
		TransmitEvaluationResult(evaluationResult);
	
	}

	RETURN_NOERROR;
}

tCategorization* cDadasSceneAbstractionFilter::Evaluate(const tScene* scene)
{
	return nullptr;
}

tResult cDadasSceneAbstractionFilter::TransmitEvaluationResult(tCategorization* evaluationResult)
{
	cObjectPtr<IMediaSample> pNewSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pNewSample));
	
	RETURN_IF_FAILED(pNewSample->Update(_clock->GetStreamTime(), &evaluationResult, sizeof(tCategorization), 0));
	
	RETURN_IF_FAILED(m_oCategorizationOutput.Transmit(pNewSample));
	
	RETURN_NOERROR;
}

void cDadasSceneAbstractionFilter::LOG(cString mes)
{		
	if(debugOpt) {
		LOG_INFO(mes);
	}
}

