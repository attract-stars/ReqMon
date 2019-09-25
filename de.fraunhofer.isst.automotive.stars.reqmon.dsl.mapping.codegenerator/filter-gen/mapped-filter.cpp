#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"
#include "serializationhelper.h"

$more includes$

tBool debugOpt = tFalse;

ADTF_FILTER_PLUGIN("$filter_name$", OID_DADAS_$oid_name$, $class_name$)

$class_name$::$class_name$(const tChar* __info) : $value settings$
{
}

$class_name$::~$class_name$()
{
}

tResult $class_name$::Init(tInitStage eStage, __exception)
{
	$init_implementation$
}

tResult $class_name$::Start(__exception)
{
	$start_implementation$
}

tResult $class_name$::Stop(__exception)
{
	$stop_implementation$
}

tResult $class_name$::Shutdown(tInitStage eStage, __exception)
{
	$shutdown_implementation$
}

$public_methods_implementation$

//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
tResult $class_name$::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception) {
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}
	
	tTimeStamp nTriggerTime = pSample->GetTime();
	
	//Get Categorisation Sample
	cObjectPtr<IMediaSample> pCategorisationSample;
	
	$more actions$
	
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
	
	$more actions$
	
	kernelMutex.Leave();
	
	RETURN_NOERROR;
}

void $class_name$::LOG(cString mes) {		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}

$more protected methods$

$private_methods_implementation$
