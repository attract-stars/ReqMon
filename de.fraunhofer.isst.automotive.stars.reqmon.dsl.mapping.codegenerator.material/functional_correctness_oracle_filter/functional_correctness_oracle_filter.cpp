
#include "stdafx.h"
#include "dtypes.h"
#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"
#include "dadas_event_codes.h"
#include "functional_correctness_oracle_filter.h"
#include "serializationhelper.h"

//#include <vld.h>

tBool debugOpt = tFalse;


ADTF_FILTER_PLUGIN("DADAS Functional Correctness Orace Filter", OID_DADAS_ABSTRACTION_FUNC_ORACLE, cDadasAbstractionFunctCorrectnessOracleFilter)

cDadasAbstractionFunctCorrectnessOracleFilter::cDadasAbstractionFunctCorrectnessOracleFilter(const tChar* __info)  : cConditionTriggeredFilter(tTrue,tTrue,__info),
m_bTimeout(tFalse) 
{
	kernelMutex.Create();

	SetPropertyInt("timeout", 500000000);
	SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
}


cDadasAbstractionFunctCorrectnessOracleFilter::~cDadasAbstractionFunctCorrectnessOracleFilter()
{
	kernelMutex.Release();
}

/**
*   The Filter Init Function.
*    eInitStage ... StageFirst ... should be used for creating and registering Pins
*               ... StageNormal .. should be used for reading the properies and initalizing
*                                  everything before pin connections are made
*   see {@link IFilter#Init IFilter::Init}.
*
*/
tResult cDadasAbstractionFunctCorrectnessOracleFilter::Init(tInitStage eStage, __exception)
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

		//Input for Categorisation
		/*cObjectPtr<IMediaType> pCategorisationType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_CATEGORISATION, 0, 
			"tCategorisation", 
			pDescManager->GetMediaDescription("tCategorisation"),IMediaDescription::MDF_DDL020000);*/

		cObjectPtr<IMediaType> pCategorisationType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_CATEGORISATION_BOOST);
		RETURN_IF_FAILED(m_oCategorisationInput.Create("Categorisation", pCategorisationType, this, static_cast<ISampleQueue*>(new cSampleQueue(1000000, 0))));
		RETURN_IF_FAILED(RegisterPin(&m_oCategorisationInput));
		RETURN_IF_FAILED(pCategorisationType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pCategorisationCoderDesc));

		//Input für Abstract Function
		/*cObjectPtr<IMediaType> pAbstrTargetOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_TARGET_ABSTRACT, 0, 
			"tAbstractTargets", pDescManager->GetMediaDescription("tAbstractTargets"),IMediaDescription::MDF_DDL020000);*/
		cObjectPtr<IMediaType> pAbstrTargetOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_TARGET_ABSTRACT_BOOST);
		//RETURN_IF_FAILED(m_oAbstractTargetsInput.Create("TEST", pAbstrTargetOutputType, this));
		RETURN_IF_FAILED(m_oAbstractTargetsInput.Create("Abstract_Targets", pAbstrTargetOutputType, this));
		RETURN_IF_FAILED(RegisterTriggerPin(&m_oAbstractTargetsInput));//PROBLEM WITH REGISTER WHILE STARTING ADTF
		RETURN_IF_FAILED(pAbstrTargetOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pAbstractTargetsCoderDesc));

		//Input für Concrete Function
		cObjectPtr<IMediaType> pConcrTargetType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_TARGETS_BOOST);
		RETURN_IF_FAILED(m_oConcreteTargetsInput.Create("Concrete_Targets", pConcrTargetType, this));
		RETURN_IF_FAILED(RegisterTriggerPin(&m_oConcreteTargetsInput));
		RETURN_IF_FAILED(pConcrTargetType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pConcreteTargetsCoderDesc));

		//Output about compliance //only bool
		cObjectPtr<IMediaType> pBoolOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_COMPLIANCE, 0, 
			"tFunctionalComplianceBool", pDescManager->GetMediaDescription("tFunctionalComplianceBool"),IMediaDescription::MDF_DDL_DEFAULT_VERSION);
		//cObjectPtr<IMediaType> pSmallOutputBoolType = new cMediaType(0,  0);
		RETURN_IF_FAILED(m_oBoolOutput.Create("Functional_Compliance_Bool", pBoolOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oBoolOutput));
		RETURN_IF_FAILED(pBoolOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputBoolCoderDesc));

		//Light Output without the situation
		//cObjectPtr<IMediaType> pSmallOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_COMPLIANCE, 0, 
		//	"tFunctionalCompliance", 
		//	pDescManager->GetMediaDescription("tFunctionalCompliance"),IMediaDescription::MDF_DDL020000);
		cObjectPtr<IMediaType> pSmallOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_COMPLIANCE_BOOST);
		RETURN_IF_FAILED(m_oOutput.Create("Functional_Compliance", pSmallOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oOutput));
		RETURN_IF_FAILED(pSmallOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputCoderDesc));

		//Complex Output with the Situation
		//cObjectPtr<IMediaType> pComplexOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_COMPLIANCE_COMPLEX, 0, 
		//	"tFunctionalComplianceComplex", 
		//	pDescManager->GetMediaDescription("tFunctionalComplianceComplex"),IMediaDescription::MDF_DDL020000);
		cObjectPtr<IMediaType> pComplexOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_COMPLIANCE_COMPLEX_BOOST);
		RETURN_IF_FAILED(m_oComplexOutput.Create("Complex_Functional_Compliance", pComplexOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oComplexOutput));
		RETURN_IF_FAILED(pComplexOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputComplexCoderDesc));

		//Logging Output
		cObjectPtr<IMediaType> pLoggingOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_LOGGING_COMPLIANCE_COMPLEX_BOOST);
		RETURN_IF_FAILED(m_oLoggingOutput.Create("Logging", pLoggingOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oLoggingOutput));
	}  
	else if (eStage == StageNormal)
	{
		//NOthing to do
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

tResult cDadasAbstractionFunctCorrectnessOracleFilter::Start(__exception)
{
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}

	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));

	RETURN_NOERROR;
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::Stop(__exception)
{

	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}


	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::Shutdown(tInitStage eStage, __exception)
{
	if (StageGraphReady == eStage)
	{
		m_oTimeout.Release();

	}

	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
}


//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
tResult cDadasAbstractionFunctCorrectnessOracleFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception) {
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}

	tTimeStamp nTriggerTime = pSample->GetTime();
	// you can now get the relevant samples from the queues using the

	//Get Categorisation Sample
	cObjectPtr<IMediaSample> pCategorisationSample;
	cObjectPtr<ISampleQueue>  pCategorisationQueue;
	RETURN_IF_FAILED(m_oCategorisationInput.GetSampleQueue(&pCategorisationQueue));//TODO: DOES THIS WORK IF THE PIN IS NOT REGISTERED AS TRIGGERED BUT HAS A NORMAL QUEUE?
	if(pCategorisationQueue) {
		pCategorisationQueue->Get(&pCategorisationSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearestOlder); //Thinking that the categorisation is send first before the targets

	}
	RETURN_IF_POINTER_NULL(pCategorisationSample);

	//Get Abstract Targets Sample
	cObjectPtr<IMediaSample> pAbstrTargetsSample;
	ISampleQueue* pAbstrTargetsQueue = GetQueue(&m_oAbstractTargetsInput);
	if(pAbstrTargetsQueue) {
		pAbstrTargetsQueue->Get(&pAbstrTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 

	}
	RETURN_IF_POINTER_NULL(pAbstrTargetsSample);

	//Get Concrete Target Sample
	cObjectPtr<IMediaSample> pConcreteTargetSample;
	ISampleQueue* pConcreteTargetQueue = GetQueue(&m_oConcreteTargetsInput);
	if(pConcreteTargetQueue) {
		pConcreteTargetQueue->Get(&pConcreteTargetSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 

	}
 	RETURN_IF_POINTER_NULL(pConcreteTargetSample);

	//Get Categorisation
	DADAS::tCategorisation pCategorisationData;
	//Lock Sample

	kernelMutex.Enter();

	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pCategorisationSample,pCategorisationData));

	//vector<char>* cCatbuffer=0;
	////Lock Sample
	//pCategorisationSample->Lock((const void**)&cCatbuffer);//PROBLEM BUFFER BEKOMMT KEINE DATEN

	//RETURN_IF_POINTER_NULL(cCatbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(*cCatbuffer,pCategorisationData));

	//GEt Abstract Targets
	vector<DADAS::tAbstractTarget> pAbstrTargetsData;

	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pAbstrTargetsSample,pAbstrTargetsData));

	//vector<char>* cAbtrTarbuffer= 0;
	////Lock Sample
	//pAbstrTargetsSample->Lock((const void**)&cAbtrTarbuffer);//PROBLEM BUFFER BEKOMMT KEINE DATEN

	//RETURN_IF_POINTER_NULL(cAbtrTarbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(*cAbtrTarbuffer,pAbstrTargetsData));

	//Get Concrete Target 
	vector<DADAS::tAbstractTarget> pConcreteTargetsData;
	
	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pConcreteTargetSample,pConcreteTargetsData));

	//vector<char>* pConcreteTarsbuffer= 0;

	//pConcreteTargetSample->Lock((const void**)&pConcreteTarsbuffer);

	//RETURN_IF_POINTER_NULL(pConcreteTarsbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(*pConcreteTarsbuffer,pConcreteTargetsData));

	//Evaluate Abstraction
	tBool evaluationResult = EvaluateTargets(pAbstrTargetsData,pConcreteTargetsData);

	//Save Situation History --> Where and why did the error happen!
	RETURN_IF_FAILED(expandCategorisationHistory(evaluationResult,pAbstrTargetsData,pConcreteTargetsData,pCategorisationData));

	//Transmitt Result for Visualisation
	RETURN_IF_FAILED(transmitEvaluationResult(evaluationResult,pAbstrTargetsData,pConcreteTargetsData,pCategorisationData));

	if(!evaluationResult) {
		LOG("Fault detected");
		RETURN_IF_FAILED(logFaultCompliance(evaluationResult,pAbstrTargetsData,pConcreteTargetsData,pCategorisationData));
	}

	kernelMutex.Leave();

	//Unlock Samples
	//pCategorisationSample->Unlock(cCatbuffer);
	//pAbstrTargetsSample->Unlock(cAbtrTarbuffer);
	//pConcreteTargetSample->Unlock(pConcreteTarsbuffer);

	RETURN_NOERROR;
}

tBool cDadasAbstractionFunctCorrectnessOracleFilter::EvaluateTargets(vector<DADAS::tAbstractTarget> &abstrTargets, DADAS::tAbstractTarget &concrTarget) {
	//Is the concrete Target in the set of abstract targets -> return true
	return (find(abstrTargets.begin(),abstrTargets.end(),concrTarget.target)!=abstrTargets.end()); //searches for concrTarget and returns its index -> if concrTarget is not present-> Return abstrTargets->end() (Last element of iterator)
}

//TODO EVENTUELL Solle man annotieren welche Targepoints ok sind und welche nicht
tBool cDadasAbstractionFunctCorrectnessOracleFilter::EvaluateTargets(vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTarget) {
	for(auto tar = concrTarget.begin();tar!=concrTarget.end();++tar) {
		if(!EvaluateTargets(abstrTargets,*tar)) 
		{
			SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_FAULT_SITUATION);
			return tFalse;
		}
	}
	//all targepoints are in the set of the targets of abstract function
	return tTrue;
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::transmitEvaluationResult(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets, DADAS::tCategorisation &categorisation) {
	//Forward the transmission to dedicated methods
	RETURN_IF_FAILED(transmitBoolResult(result));
	RETURN_IF_FAILED(transmitSimpleResult(result,abstrTargets,concrTargets));
	RETURN_IF_FAILED(transmitComplexResult(result,abstrTargets,concrTargets,categorisation));
	RETURN_NOERROR;
}


tResult cDadasAbstractionFunctCorrectnessOracleFilter::transmitBoolResult(tBool &result) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));


	cObjectPtr<IMediaSerializer> pSerializer;
	m_pOutputBoolCoderDesc->GetMediaSampleSerializer(&pSerializer);
	tInt nSize = pSerializer->GetDeserializedSize();
	RETURN_IF_FAILED(pMediaSample->AllocBuffer(nSize)); 

	cObjectPtr<IMediaCoderExt> pCoder;
	RETURN_IF_FAILED(m_pOutputBoolCoderDesc->Lock(pMediaSample, &pCoder));

	RETURN_IF_FAILED(pCoder->Set("tCompliance", &result));        

	RETURN_IF_FAILED(m_pOutputBoolCoderDesc->Unlock(pCoder));

	pMediaSample->SetTime(_clock->GetStreamTime());
	RETURN_IF_FAILED(m_oBoolOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::transmitSimpleResult(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets,  vector<DADAS::tAbstractTarget> &concrTargets) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tFunctionalCompliance compliance(result,concrTargets,abstrTargets);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,compliance));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();
	//
	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,compliance));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}


tResult cDadasAbstractionFunctCorrectnessOracleFilter::transmitComplexResult(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets,vector<DADAS::tAbstractTarget> &concrTargets,DADAS::tCategorisation &categorisation) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tFunctionalComplianceComplex compliance(result,concrTargets,abstrTargets, categorisation);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,compliance));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();
	//
	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,compliance));

	//pMediaSample->Update(_clock->GetStreamTime(),buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oComplexOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::logFaultCompliance(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets, DADAS::tCategorisation &categorisation)
{
	LOG("Sending Fault to Log");
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tFunctionalComplianceComplex compliance(result,concrTargets,abstrTargets, categorisation);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,compliance));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();
	//DADAS::tFunctionalComplianceComplex compliance(result,concrTargets,abstrTargets, categorisation);

	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,compliance));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);
	LOG("Transmiting...;");
	RETURN_IF_FAILED(m_oLoggingOutput.Transmit(pMediaSample));
	LOG("Transmit..Done");
	RETURN_NOERROR;
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::expandCategorisationHistory(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets, DADAS::tCategorisation &categorisation) {
	//TODO RECORD the History of the Situation -> NOT IN FIRST PROTOTYPE
	RETURN_NOERROR;
}

/**
This Kernelthread continously checks for timeout and writes Warning!
*/
tResult cDadasAbstractionFunctCorrectnessOracleFilter::Run(tInt nActivationCode,
	const tVoid* pvUserData,
	tInt szUserDataSize,
	ucom::IException** __exception_ptr)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));

	if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
	{
		//Clear Queues
		ISampleQueue* abtrTargetsQueue = GetQueue(&m_oAbstractTargetsInput);
		abtrTargetsQueue->Clear();
		ISampleQueue* concrTargetsQueue = GetQueue(&m_oConcreteTargetsInput);
		concrTargetsQueue->Clear();
		ISampleQueue* catQueue=0;
		m_oCategorisationInput.GetSampleQueue(&catQueue);
		catQueue->Clear();
		LOG_WARNING("Timeout");
		// restart our timeout
		m_oTimeout.Start();
	}

	RETURN_NOERROR;
}

tResult cDadasAbstractionFunctCorrectnessOracleFilter::SendStatisticsEvent(tInt32 code) 
{
	IFilter* pFilter = (IFilter *) this;
	cEventInfo statEvent(pFilter, IEvent::CLASS_CUSTOM,code,0,0,0,0);
	RETURN_IF_FAILED(_kernel->SignalSend(&statEvent));
	RETURN_NOERROR;
}


void cDadasAbstractionFunctCorrectnessOracleFilter::LOG(cString mes) {		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}


