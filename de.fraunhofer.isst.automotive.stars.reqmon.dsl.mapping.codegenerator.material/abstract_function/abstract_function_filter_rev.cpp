
#include "stdafx.h"
#include "dtypes.h"
#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"
#include "abstract_function_filter_rev.h"
#include "serializationhelper.h"
#include "string_util.h"

//#include <vld.h>

tBool debugOpt = tFalse;


ADTF_FILTER_PLUGIN("DADAS Abstract Function Filter (Rev. Offset)", OID_DADAS_ABSTRACT_FUNCTION, cDadasAbstractFunctionRevOffsetFilter)

	cDadasAbstractFunctionRevOffsetFilter::cDadasAbstractFunctionRevOffsetFilter(const tChar* __info) : cConditionTriggeredFilter(tTrue,tTrue,__info),
	m_bTimeout(tFalse),
	//laneChangeMissCount(0),
	bufferLaneChangeTarget(DADAS::tAbstractTarget(DADAS::OTHER,DADAS::Invalid))
{
	kernelMutex.Create();

	SetPropertyInt("timeout", 500000000);
	SetPropertyStr("timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("timeout" NSSUBPROP_MINIMUM, 0);
}


cDadasAbstractFunctionRevOffsetFilter::~cDadasAbstractFunctionRevOffsetFilter()
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
tResult cDadasAbstractFunctionRevOffsetFilter::Init(tInitStage eStage, __exception)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));

	if (eStage == StageFirst)
	{ 
		cObjectPtr<IMediaDescriptionManager> pDescManager;
		RETURN_IF_FAILED(_runtime->GetObject(OID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			IID_ADTF_MEDIA_DESCRIPTION_MANAGER, 
			(tVoid**)&pDescManager, 
			__exception_ptr));
		//cObjectPtr<IMediaType> pInputype = new cMediaType(MEDIATYPE_DADAS,MEDIASUBTYPE_CATEGORISATION, 0, 
		//	"tCategorisation", 
		//	pDescManager->GetMediaDescription("tCategorisation"),IMediaDescription::MDF_DDL_DEFAULT_VERSION);
		cObjectPtr<IMediaType> pInputCategorisationType = new cMediaType(MEDIATYPE_DADAS,MEDIASUBTYPE_CATEGORISATION_BOOST);
		RETURN_IF_POINTER_NULL(pInputCategorisationType);
		RETURN_IF_FAILED(m_oCategorisationInput.Create("Categorisation", pInputCategorisationType, this));
		RETURN_IF_FAILED(RegisterTriggerPin(&m_oCategorisationInput));
		RETURN_IF_FAILED(pInputCategorisationType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pCategorisationCoderDesc));

		//cObjectPtr<IMediaType> pInputype = new cMediaType(MEDIATYPE_DADAS,MEDIASUBTYPE_CATEGORISATION, 0, 
		//	"tCategorisation", 
		//	pDescManager->GetMediaDescription("tCategorisation"),IMediaDescription::MDF_DDL_DEFAULT_VERSION);
		cObjectPtr<IMediaType> pTargetsInputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_TARGETS_BOOST);
		RETURN_IF_POINTER_NULL(pTargetsInputType);
		RETURN_IF_FAILED(m_oConcreteTargetsInput.Create("Concrete_Targets", pTargetsInputType, this));
		RETURN_IF_FAILED(RegisterTriggerPin(&m_oConcreteTargetsInput));

		//cObjectPtr<IMediaType> pOutputype = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_TARGET_ABSTRACT, 0, 
		//	"tAbstractTargets", pDescManager->GetMediaDescription("tAbstractTargets"),IMediaDescription::MDF_DDL_DEFAULT_VERSION);

		cObjectPtr<IMediaType> pOutputType = new cMediaType(MEDIATYPE_DADAS,MEDIASUBTYPE_TARGET_ABSTRACT_BOOST);
		RETURN_IF_FAILED(m_oOutput.Create("Targets", pOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oOutput));
		RETURN_IF_FAILED(pOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pTargetLaneCoderDesc));

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

tResult cDadasAbstractFunctionRevOffsetFilter::Start(__exception)
{
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}

	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));

	RETURN_NOERROR;
}

tResult cDadasAbstractFunctionRevOffsetFilter::Stop(__exception)
{
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}

	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasAbstractFunctionRevOffsetFilter::Shutdown(tInitStage eStage, __exception)
{
	if (StageGraphReady == eStage)
	{
		m_oTimeout.Release();

	}

	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
}



//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
tResult cDadasAbstractFunctionRevOffsetFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception) {
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}

	tTimeStamp nTriggerTime = pSample->GetTime();
	// you can now get the relevant samples from the queues using the

	//Get Categorisation Sample
	cObjectPtr<IMediaSample> pCategorisationSample;
	ISampleQueue* pCategorisationQueue = GetQueue(&m_oCategorisationInput);
	if(pCategorisationQueue) {
		pCategorisationQueue->Get(&pCategorisationSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 

	}
	RETURN_IF_POINTER_NULL(pCategorisationSample);

	//Get Abstract Targets Sample
	cObjectPtr<IMediaSample> pConcreteTargetsSample;
	ISampleQueue* pConcreteTargetsQueue = GetQueue(&m_oConcreteTargetsInput);
	if(pConcreteTargetsQueue) {
		pConcreteTargetsQueue->Get(&pConcreteTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 

	}
	RETURN_IF_POINTER_NULL(pConcreteTargetsSample);

	//Get Categorisation
	DADAS::tCategorisation pCategorisationData;
	//Lock Sample

	kernelMutex.Enter();


	//tUInt16 size = 0;
	//pCategorisationSample->CopyBufferTo(&size, sizeof(tUInt16));
	//char* cbuffer = new char[size];
	//pCategorisationSample->CopyBufferTo(cbuffer, sizeof(char)*size,sizeof(tUInt16),0);

	//vector<char> test(cbuffer,cbuffer+size);
	//vector<char> cCatbuffer(size);
	//for(auto i=0;i<size;++i) {
	//	char test =cbuffer[i];
	//	cCatbuffer[i] = cbuffer[i];
	//}
	//delete [] cbuffer;
	////Lock Sample
	////pCategorisationSample->Lock((const void**)&cbuffer);

	////RETURN_IF_POINTER_NULL(cCatbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(cCatbuffer,pCategorisationData));

	DADAS::HELPER::DeserializeFromSample(pCategorisationSample,pCategorisationData);

	//Get Concrete Targets
	vector<DADAS::tAbstractTarget> pConcreteTargetsData;

	DADAS::HELPER::DeserializeFromSample(pConcreteTargetsSample,pConcreteTargetsData);

	//vector<char>* cAbtrTarbuffer= 0;
	////Lock Sample
	//pConcreteTargetsSample->Lock((const void**)&cAbtrTarbuffer);

	//RETURN_IF_POINTER_NULL(cAbtrTarbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(*cAbtrTarbuffer,pConcreteTargetsData));

	RETURN_IF_FAILED(Evaluate(pCategorisationData,pConcreteTargetsData));

	RETURN_IF_FAILED(SendBOOSTTargetlanes(targets));

	kernelMutex.Leave();

	//Unlock Samples
	//pCategorisationSample->Unlock(cCatbuffer);
	//pConcreteTargetsSample->Unlock(cAbtrTarbuffer);


	RETURN_NOERROR;
}


tResult  cDadasAbstractFunctionRevOffsetFilter::Evaluate(DADAS::tCategorisation &categorisation,vector<DADAS::tAbstractTarget> &concreteTargets) {
	//CHECK IF CATEGORISATION IS valid
	RETURN_IF_FAILED(CheckCategorisationCorrectness(categorisation));
	//Validation completed: Evaluate Categorisation
	if(!targets.empty()) 
	{
		targets.clear();
	}
	vector<DADAS::eLanePosition> possibleTargets;
	possibleTargets.push_back(DADAS::LEFT_NEIGHBOR);
	possibleTargets.push_back(DADAS::RIGHT_NEIGHBOR);
	for(auto targetLane=possibleTargets.begin(); targetLane!=possibleTargets.end();++targetLane) {
		//Check Condition for lane change to LEFT LANE
		//DADAS::eLanePosition targetLane = *target;
		//Check condidtion for Objects
		tBool  laneChangePossibility =  tTrue;
		for(auto object=categorisation.objects.begin();object!=categorisation.objects.end();++object) {
			//Condition 1: Object on target Lane / Position behind the Ego / relative Velocity larger than boundary
			if(object->lanePosition==*targetLane&&object->relativePosition==DADAS::BEHIND&&object->relativeVelocity==DADAS::HIGHER){
				laneChangePossibility=tFalse;
				break;
			}

			//COndition 2: Object on Target Lane / Position in front of Ego / realtive Velcoity LOWER than boundary (!!! Rel. Velocity over Length of difference vector is direction independent - therefore we use only the x components of the velocity for this classification here!
			if(object->lanePosition==*targetLane&&object->relativePosition==DADAS::FRONT&&object->relativeVelocity==DADAS::LOWER) {
				laneChangePossibility=tFalse;
				break;
			}

			//Condition 3: Object on Ego Lane / Object in front of Ego / distance less than 1m
			if(object->lanePosition==DADAS::EGO && object->relativePosition==DADAS::FRONT && object->distance==DADAS::INSUFFICIENT_DISTANCE) {
				laneChangePossibility=tFalse;
				break;
			}

			//Condition 4: Object on Ego Lane / Object behind Ego / relative Velocity larger than boundary
			if(object->lanePosition==DADAS::EGO&&object->relativePosition==DADAS::BEHIND&&object->relativeVelocity==DADAS::HIGHER) {
				laneChangePossibility=tFalse;
				break;
			}

			//Condition 5: Object on target Lane / Position next to ego / realtive Velcoity LOWER than boundary (!!! Rel. Velocity over Length of difference vector is direction independent - therefore we use only the x components of the velocity for this classification here!
			//if(object->lanePosition==*targetLane&&object->relativePosition==DADAS::NEXT&&object->relativeVelocity==DADAS::LOWER) {
			if(object->lanePosition==*targetLane&&object->relativePosition==DADAS::NEXT) { //Lets be velocity independent
				laneChangePossibility=tFalse;
				break;
			}
		}
		vector<DADAS::tLaneCategory> *lanes = &categorisation.lanes;
		//Condition 6 / 7: Lane Markings type (on target lane side) are restricted 6) solid / 7) Unknown
		//This has to be evaluated in repsect to the target lane
		DADAS::eLaneMarking marking;
		switch(*targetLane) {
		case DADAS::LEFT_NEIGHBOR:
			marking=lanes->at(DADAS::EGO).leftMarking;
			break;
		case DADAS::RIGHT_NEIGHBOR:
			marking=lanes->at(DADAS::EGO).rightMarking;
			break;
		default:
			//Error Case -> Eigentlich ein Problem, das es niemals vorkommen darf
			marking=DADAS::RESTRICTED_MARKING;
			break;
			//Default not used because it is the same used in  the target vector
		}
		if(marking==DADAS::RESTRICTED_MARKING) {
			laneChangePossibility=tFalse;
		}

		//Condition 8 / 9 / 10: (Target) Lane Type is 8) NonExistentLane 9) ForbiddenLane 10) EmergencyLane
		if(lanes->at(*targetLane).type==DADAS::RESTRICTED_TYPE||lanes->at(*targetLane).type==DADAS::NONEXISTING) {
			laneChangePossibility=tFalse;
		}

		//Condition 11 not evaluated --> Self-X property - (Till) Only Change with information about max allowed speed and vehicle in sight

		//Condition 12: Target Lane next to ramp
		if(lanes->at(*targetLane).position==DADAS::RAMP) {
			laneChangePossibility=tFalse;
		}

		//Condition 13: Target//Ego Curvature higher than boundary
		if(lanes->at(*targetLane).curvature==DADAS::HIGH||lanes->at(DADAS::EGO).curvature==DADAS::HIGH) {
			laneChangePossibility=tFalse;
		}

		//COndition 14: Ego vehicle's velocity too low
		if(categorisation.ego.velocity==DADAS::INSUFFICIENT_VELOCITY) {
			laneChangePossibility=tFalse;
		}

		//Condition 15: domain is not handled
		if(categorisation.domain==DADAS::NON_APPLICABLE) {
			laneChangePossibility=tFalse;
		}

		//Condition 16: driver overturning autopilot
		if(categorisation.driverInput==DADAS::OVERTURNED) {
			laneChangePossibility=tFalse;
		}

		//REV CHANGE: OFFSET has only considered if no Lane Change in progress or is about to initiated > Change of Concrete Targetpoint for LaneChange (-> also at the end)
		//Condition 17: ego's lateral offset to large
		//thats some kind of hack
		DADAS::tAbstractTarget laneChangeTarget = findLaneChangeTarget(concreteTargets);
		if(laneChangeTarget.target!=DADAS::OTHER&&laneChangeTarget.type!=DADAS::Invalid&&laneChangeTarget.target==*targetLane)
		{//Lane Change got us a target
			if(laneChangeTarget.target==DADAS::EGO) {//Ego never made LaneChange or Lane change ended
				LOG(DADAS::STRING_UTIL::getLanePositionText(*targetLane)+": Reset Lane Change Target Buffer! (Target matches Ego Lane)");
				bufferLaneChangeTarget=DADAS::tAbstractTarget(DADAS::OTHER,DADAS::Invalid);//RESET BUFFER
			}
			if(bufferLaneChangeTarget.type==DADAS::Invalid || bufferLaneChangeTarget.target!=laneChangeTarget.target)
			{
				LOG(DADAS::STRING_UTIL::getLanePositionText(*targetLane)+": New Lane Change Target");
				if(categorisation.ego.offset==DADAS::LARGE) {
					laneChangePossibility=tFalse;
				}
				bufferLaneChangeTarget=laneChangeTarget;
			} else {
				LOG(DADAS::STRING_UTIL::getLanePositionText(*targetLane)+": Lane Change Target remaining the same");
			}
			//here bufferLaneChangeTarget has to be set to NULL already or laneChangeTarget is the same and targepoint has not Changed
		}else {//NO Lane Change Set
			if(bufferLaneChangeTarget.target!=DADAS::OTHER&&bufferLaneChangeTarget.type!=DADAS::Invalid && bufferLaneChangeTarget.target==*targetLane) {
				bufferLaneChangeTarget=DADAS::tAbstractTarget(DADAS::OTHER,DADAS::Invalid); //Only Reset with correct Target
			}
			if(categorisation.ego.offset==DADAS::LARGE) {
				laneChangePossibility=tFalse;
			}
		}


		//Add Lane to targets?
		if(laneChangePossibility) {
			targets.push_back(DADAS::tAbstractTarget(*targetLane,DADAS::DADAS));
		}
		//Finished 
	}

	//ADD EGO as possible 
	if(categorisation.lanes[DADAS::EGO].type==DADAS::UNRESTRICTED_TYPE) {
		targets.push_back(DADAS::tAbstractTarget(DADAS::EGO,DADAS::DADAS));
	}

	RETURN_NOERROR;
}

DADAS::tAbstractTarget cDadasAbstractFunctionRevOffsetFilter::findLaneChangeTarget(vector<DADAS::tAbstractTarget> &targets)
{
	std::vector<DADAS::tAbstractTarget>::iterator ele  = find(targets.begin(),targets.end(),DADAS::LaneChangePlanner);
	if(ele==targets.end()) {
		return DADAS::tAbstractTarget(DADAS::OTHER,DADAS::Invalid);
	} else {
		return *ele;
	}
}


tResult cDadasAbstractFunctionRevOffsetFilter::SendBOOSTTargetlanes(vector<DADAS::tAbstractTarget> &targets){
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,targets));

	//std::vector<char>* buffer = new vector<char>();

	//DADAS::HELPER::Serialize(*buffer, targets);

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));

	//delete buffer; Can't delete buffer else Data is gone for the asynchrone Filter following

	RETURN_NOERROR;
}

tResult cDadasAbstractFunctionRevOffsetFilter::CheckCategorisationCorrectness(DADAS::tCategorisation &categorisation) {
	//if(categorisation==NULL) {
	//	RETURN_ERROR(ERR_POINTER);
	//}
	if(categorisation.domain<0||categorisation.domain>=DADAS::domainElements) {
		RETURN_ERROR(ERR_OUT_OF_RANGE);
	}
	if(categorisation.driverInput<0||categorisation.driverInput>=DADAS::driverElements) {
		RETURN_ERROR(ERR_OUT_OF_RANGE);
	}
	if(categorisation.lanes.size()<=0) {
		RETURN_ERROR(ERR_OUT_OF_RANGE);
	}
	if(categorisation.objectCount<0) {
		RETURN_ERROR(ERR_OUT_OF_RANGE);
	}
	if(categorisation.objects.size()<0||categorisation.objects.size()!=categorisation.objectCount) {
		RETURN_ERROR(ERR_OUT_OF_RANGE);
	}
	RETURN_NOERROR;
}

tResult cDadasAbstractFunctionRevOffsetFilter::Run(tInt nActivationCode,
	const tVoid* pvUserData,
	tInt szUserDataSize,
	ucom::IException** __exception_ptr)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));

	if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
	{
		//Clear buffer
		vector<DADAS::eLanePosition>* inputTargets=NULL;
		LOG_WARNING("Timeout");
		// restart our timeout
		m_oTimeout.Start();
	}

	RETURN_NOERROR;
}


void cDadasAbstractFunctionRevOffsetFilter::LOG(cString mes) {		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}


