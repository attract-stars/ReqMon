#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"
#include "serializationhelper.h"

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
	IMediaSample* pMediaSample) {
		if (nEventCode == IPinEventSink::PE_MediaSampleReceived)
		{
			if (pSource == &m_oInput)
			{
				ProcessSample(pMediaSample);
			}	else {
				RETURN_ERROR(ERR_NOT_SUPPORTED);
			}
		}
		RETURN_NOERROR;
}

tResult cDadasSceneAbstractionFilter::ProcessSample(IMediaSample* pSample) {
	tScene pData;

	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pSample,pData));

	DADAS::tCategorisation categorisation = Categorize(&pData);

	SendBOOSTCategories(&categorisation);

	RETURN_NOERROR;
}

DADAS::tCategorisation cDadasSceneAbstractionFilter::Categorize(tScene* scene) {
	//Build tCategorisation and send it over output

	DADAS::tCategorisation tCategorisation;


	return tCategorisation;
}

tResult cDadasSceneAbstractionFilter::SendBOOSTCategories(DADAS::tCategorisation *categorisation) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::HELPER::SerializeToSample(pMediaSample,*categorisation);

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));
	
	RETURN_NOERROR;
}

void cDadasSceneAbstractionFilter::LOG(cString mes) {		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}

