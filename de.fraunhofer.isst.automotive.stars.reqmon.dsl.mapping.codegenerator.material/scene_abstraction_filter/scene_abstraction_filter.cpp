
#include "stdafx.h"
#include "dtypes.h"
//#include "../datatypes/inc/dadas_monitoring_types.h
#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"
#include "parametrization.h"
#include "scene_abstraction_filter.h"
#include "serializationhelper.h"

//#include <vld.h>

tBool debugOpt = tFalse;


ADTF_FILTER_PLUGIN("DADAS Scene Abstraction Filter", OID_DADAS_ABSTRACTION_SCENE, cDadasAbstractionSceneFilter)

	cDadasAbstractionSceneFilter::cDadasAbstractionSceneFilter(const tChar* __info) : cFilter(__info)
{

}


cDadasAbstractionSceneFilter::~cDadasAbstractionSceneFilter()
{

}

/**
*   The Filter Init Function.
*    eInitStage ... StageFirst ... should be used for creating and registering Pins
*               ... StageNormal .. should be used for reading the properies and initalizing
*                                  everything before pin connections are made
*   see {@link IFilter#Init IFilter::Init}.
*
*/
tResult cDadasAbstractionSceneFilter::Init(tInitStage eStage, __exception)
{
	RETURN_IF_FAILED(cFilter::Init(eStage, __exception_ptr));

	if (eStage == StageFirst)
	{ 
		cMediaType* pType;
		pType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_VW_SCENE_BOOST);
		RETURN_IF_POINTER_NULL(pType);
		RETURN_IF_FAILED(m_oInput.Create("Scene", pType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oInput));

		cObjectPtr<IMediaType> pOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_CATEGORISATION_BOOST);
		RETURN_IF_FAILED(m_oOutput.Create("Categorisation", pOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oOutput));
		//RETURN_IF_FAILED(pOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pCoderDesc));
	}  
	else if (eStage == StageNormal)
	{
		//Nothing to do here
	}
	else if (eStage == StageGraphReady)
	{
		//Nothing to do
	}
	RETURN_NOERROR;
}

tResult cDadasAbstractionSceneFilter::Start(__exception)
{
	RETURN_IF_FAILED(cFilter::Start(__exception_ptr));

	RETURN_NOERROR;
}

tResult cDadasAbstractionSceneFilter::Stop(__exception)
{
	return cFilter::Stop(__exception_ptr);
}

tResult cDadasAbstractionSceneFilter::Shutdown(tInitStage eStage, __exception)
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

tResult cDadasAbstractionSceneFilter::OnPinEvent(IPin* pSource,
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

tResult cDadasAbstractionSceneFilter::ProcessSample(IMediaSample* pSample) {
	//Extract Scene and input for Categorisation
	//tScene* pData = 0;

	//pSample->Lock((const tVoid**) &pData);

	//RETURN_IF_POINTER_NULL(pData);

	//DADAS::tCategorisation categorisation = Categorize(pData);

	//pSample->Unlock(pData);

	tScene pData;

	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pSample,pData));

	DADAS::tCategorisation categorisation = Categorize(&pData);

	SendBOOSTCategories(&categorisation);

	RETURN_NOERROR;
}

DADAS::tCategorisation  cDadasAbstractionSceneFilter::Categorize(tScene* scene) {
	//Build tCategorisation and send it over output

	DADAS::tCategorisation tCategorisation;//Hier fehlt doch die Allocation für das letzte Array

	//ego vehicle category
	//tObject s -> longitude 
	//tObject d -> Lateral Offset to ego lane
	//Both have a position and velocity --> so our velocity is not specified enogh (we need only the velocity in one direction) or has to be calculated!

	DADAS::tEgoVehicleCategory egoVehicle;
	tObject* ego = &(scene->ego);
	//velocity
	t2dVector egoVelocity;
	egoVelocity.x=ego->s.vel();
	egoVelocity.y=ego->d.vel();
	if(egoVelocity.length()<EGO_VELOCITY_BOUNDARY) {
		egoVehicle.velocity=DADAS::INSUFFICIENT_VELOCITY;
	}else {
		egoVehicle.velocity=DADAS::SUFFICIENT_VELOCITY;
	}
	//laneOffset
	tFloat64 egoOffset = ego->d.pos();//position offset ot ego lane! Ego on ego lane reuse total value //TODO DOES this really go from center of the ego lane??
	if(egoOffset>LANE_CENTER_OFFSET_BOUNDARY) {
		egoVehicle.offset=DADAS::LARGE;
	}else {
		egoVehicle.offset=DADAS::SMALL;
	}
	tCategorisation.ego = egoVehicle;
	//EGO Category DONE

	//lane category
	tUInt8 laneCounter = 0;
	vector<tLane> *lanes = &(scene->lanes);
	tInt8 laneSize = lanes->size();
	LOG(cString::Format("Lane Count: %i",laneSize));
	if(lanes!=NULL && !lanes->empty()&&lanes->size()>0&&laneSize>0) {
		for(auto lane=lanes->begin();lane!=lanes->end();++lane) {
			DADAS::tLaneCategory tlaneCategory;
			//ID
			//switch lane indexes to bring it to the abstracted description
			switch(laneCounter) {
			case eLateralLane_1stLeftNeighbor:
				LOG("Left Neighbor Lane");
				tlaneCategory.laneID=DADAS::LEFT_NEIGHBOR;
				break;
			case eLateralLane_LaneEgo:
				LOG("Ego Lane");
				tlaneCategory.laneID=DADAS::EGO;
				break;
			case eLateralLane_1stRightNeighbor:
				LOG("Right Neighbor Lane");
				tlaneCategory.laneID=DADAS::RIGHT_NEIGHBOR;
				break;
			default:
				LOG("Invalid Lane");
				tlaneCategory.laneID=DADAS::OTHER;
				break;
			}
			//tlaneCategory->tLaneID=laneCounter;
			//type
			switch(lane->laneType) {
			case eLaneType_NonExistentLane:
				LOG("Lane Type: Nonexisting");
				tlaneCategory.type=DADAS::NONEXISTING;
				break;
			case eLaneType_NormalLane:
				LOG("Lane Type: Unrestricted");
				tlaneCategory.type=DADAS::UNRESTRICTED_TYPE;
				break;
			case eLaneType_ForbiddenLane:
			case eLaneType_EmergencyLane:
			case eLaneType_SplitLaneNormal:
				LOG("Lane Type: Restricted");
				tlaneCategory.type=DADAS::RESTRICTED_TYPE;
				break;
			}
			//curvature
			if(lane->curvature>CURVATURE_BOUNDARY) {
				LOG("Lane Curvature: High");
				tlaneCategory.curvature=DADAS::HIGH;
			}else{
				LOG("Lane Currvature: Low");
				tlaneCategory.curvature=DADAS::LOW;
			}
			//position - the position next to a ramp - How to determine this?
			//THere are Events in the data structure for on/off Ramps
			//Using Events -> THE EVENTS are for the Speed on on/off Ramps
			//There are members lane->mergerFromRightDist lane->lane->mergerFromLeftDist but they are always 9999.0000
			//PROBLEM: EVENTS are global -> Which lane is next to ramp? Always the right one? Thats not even true for highway^^
			//tlaneCategory->position=DADAS::NO_RAMP; // Initialisierung mit False
			//multiset<tEvent> events = scene->events;
			//for(auto ev= events.begin(); ev!=events.end();++ev) {
			//	if(ev->type== eEventTypeEntryRamp ||ev->type== eEventTypeExitRamp) {
			//		if(ev->distance==0) {
			//			LOG("Lane Position: Ramp");
			//			//Sonderfall -> Next To Ramp -> Dont Cahge
			//			tlaneCategory->position=DADAS::RAMP;
			//			//but which lane!?
			//			//TODO FIND LANE
			//		} else {
			//			LOG("Lane Position: No Ramp");
			//			tlaneCategory->position=DADAS::NO_RAMP;
			//		}
			//	}
			//}

			//New Situation with the use of tLane Parameters for Distance to Merging Situation We dont use an parameter
			if(lane->mergerFromLeftDist==0 || lane->mergerFromRightDist==0) {
				LOG("Lane Position: Ramp");
				//Sonderfall -> Next To Ramp -> Dont Change
				tlaneCategory.position=DADAS::RAMP;
				//Problem: We do not focus on the wholen Lane Change Process -> Therefore we can initiate a Change before the mergingArea but the Change can end in this erea.
				//(In Function this is done by an Limit for the distance to have an artifical look ahead
			} else {
				LOG("Lane Position: No Ramp");
				tlaneCategory.position=DADAS::NO_RAMP;
			}

			//marking
			//problem with marking WE have 2 markings at each side! BUT Specification only states of one MARKING
			//Common decision to have 2 marking categories for each side and to use them for a lane change to corresponding side 
			//left marking
			if(lane->laneMarkingLeft==eLaneMarkingTypeSolid || lane->laneMarkingLeft == eLaneMarkingTypeUnknown) {
				LOG("Left Marking: Restricted");
				tlaneCategory.leftMarking= DADAS::RESTRICTED_MARKING;
			} else {
				LOG("Left Marking: Unrestricted");
				tlaneCategory.leftMarking= DADAS::UNRESTRICTED_MARKING;
			}
			//right marking
			if(lane->laneMarkingRight==eLaneMarkingTypeSolid || lane->laneMarkingRight == eLaneMarkingTypeUnknown) {
				LOG("Right Marking: Restricted");
				tlaneCategory.rightMarking= DADAS::RESTRICTED_MARKING;
			} else {
				LOG("Right Marking: Unrestricted");
				tlaneCategory.rightMarking= DADAS::UNRESTRICTED_MARKING;
			}

			//set categrorisation with lane data
			tCategorisation.lanes.push_back(tlaneCategory);
			//tlaneCategory = NULL;
			//increase laneCounter
			++laneCounter;
		}
	}


	//object category
	//First get all Objects from different Lanes
	tInt16 objectCount = 0;
	for(auto laneObjects = scene->objects.begin(); laneObjects!=scene->objects.end(); ++laneObjects) {
		objectCount+=(tInt16)laneObjects->size();//Test if this is the real count of elements and that there are no blank fields...
	}
	LOG(cString::Format("Overall Object Count: %i",objectCount));
	tCategorisation.objectCount=objectCount;
	//Create array dynamically and use it in tCategorisation
	//DADAS::tObjectCategory objectCats = DADAS::tObjectCategory[objectCount];
	tInt16 count = 0;
	if(scene->objects.size()==eNumberOfLocations) {
		for(tUInt8 i =  0; i<eNumberOfLocations;++i) {//iterate over place enum! - thats what the object array is initialized with
			vector<tObject> *laneObjects = &(scene->objects[i]);
			if(laneObjects!=NULL && !laneObjects->empty()&&laneObjects->size()>0) {
				//Nur Wenn objekte in diesem Bereich
				DADAS::eLanePosition lanePosition;
				//DADAS::eRelativePosition relPosition;
				switch(i) {
				case eLocationFrontEgo:    ///<- in front of ego on ego lane
					LOG(cString::Format("Obj%i Position: Front on Ego",count));
					lanePosition = DADAS::EGO;
					//relPosition = DADAS::FRONT;
					break;
				case eLocationFrontLeft:   ///<- in front of ego on left neighbor lane
					LOG(cString::Format("Obj%i Position: Front on Left Neighbor",count));
					lanePosition = DADAS::LEFT_NEIGHBOR;
					//relPosition = DADAS::FRONT;
					break;
				case eLocationFrontRight:  ///<- in front of ego on right neighbor lane
					LOG(cString::Format("Obj%i Position: Front on Right Neighbor",count));
					lanePosition = DADAS::RIGHT_NEIGHBOR;
					//relPosition = DADAS::FRONT;
					break;
				case eLocationRearEgo:     ///<- behind of ego on ego lane
					LOG(cString::Format("Obj%i Position: Rear on Ego",count));
					lanePosition = DADAS::EGO;
					//relPosition = DADAS::BEHIND;
					break;
				case eLocationRearLeft:   ///<- behind of ego on left neighbor lane+
					LOG(cString::Format("Obj%i Position: Rear on Left Neighbor",count));
					lanePosition = DADAS::LEFT_NEIGHBOR;
					//relPosition = DADAS::BEHIND;
					break;
				case eLocationRearRight:   ///<- behind of ego on right neighbor lane
					LOG(cString::Format("Obj%i Position: Rear on Right Neighbor",count));
					lanePosition = DADAS::RIGHT_NEIGHBOR;
					//relPosition = DADAS::BEHIND;
					break;		
				default:
					LOG(cString::Format("Obj%i Position: unknown and invalid position (Other)",count));
					lanePosition = DADAS::OTHER;
					//relPosition = DADAS::UNKNOWN;
				}
				LOG(cString::Format("Objects in sector: %i",laneObjects->size()));
				for(auto object = laneObjects->begin(); object!=laneObjects->end();++object) {
					if(object->id!=0) {//if ID is null the object is invalid or ego! (from types file)
						DADAS::tObjectCategory objCategory;
						//id
						objCategory.objID = count;
						//relative Velocity
						// This is a problem because the length of the vector has no direction -> there for even if the obj is falling behind the comaprison in the abstract function is against the high classification
						//We only consider the relative velocity in x-direction - the diretion of the street
						//t2dVector *objectVelocity = new t2dVector();
						//objectVelocity->x = object->s.vel();
						//objectVelocity->y = object->d.vel();
						tFloat64 relativeVelocity = object->s.vel()-ego->s.vel();
						if(relativeVelocity>RELATIVE_VELOCITY_BOUNDARY) {
							LOG(cString::Format("Obj%i Rel. Velocity: Higher than limit.(Ego:[%f,%f] - Obj:[%f,%f] by Limit: %f)",count,egoVelocity.x,egoVelocity.y,object->s.vel(),object->d.vel(),RELATIVE_VELOCITY_BOUNDARY));
							objCategory.relativeVelocity=DADAS::HIGHER;//relative Geschwindgkeit vom objekt zum Ego
						}else {
							LOG(cString::Format("Obj%i Rel. Velocity: Smaller than limit. (Ego:[%f,%f] - Obj:[%f,%f]  by Limit: %f)",count,egoVelocity.x,egoVelocity.y,object->s.vel(),object->d.vel(),RELATIVE_VELOCITY_BOUNDARY));
							objCategory.relativeVelocity=DADAS::LOWER;//relative Geschwindgkeit vom objekt zum Ego
						}
						//relative Positon -- Eventuell muss man dass auch über die Array Position durchführen -> Vlt sicherer
						t2dVector objectPosition(object->s.pos(),object->d.pos());
						t2dVector egoPosition(ego->s.pos(),ego->d.pos());
						//tFloat64 xPosDif = objectPosition->x-egoPosition->x;
						if(objectPosition.x-object->refToRearBumper>egoPosition.x+ego->refToFrontBumper) {
							// vor dem Fahrzeug
							LOG(cString::Format("Obj%i Rel. Position: In Front of Ego vehicle",count));
							objCategory.relativePosition=DADAS::FRONT;
						} else if((objectPosition.x+object->refToFrontBumper)<(egoPosition.x-ego->refToRearBumper)) {
							//hinter Fahrzeug
							LOG(cString::Format("Obj%i Rel. Position: Behind of Ego vehicle",count));
							objCategory.relativePosition=DADAS::BEHIND;
						}else {
							//neben Fahrzeug
							LOG(cString::Format("Obj%i Rel. Position: Next to Ego vehicle",count));
							objCategory.relativePosition=DADAS::NEXT;
						}
						//distance
						t2dVector egoRefFrontBumber(ego->refToFrontBumper,0);
						t2dVector objectRefRearBumber(object->refToRearBumper,0);
						tFloat64 distance = (egoPosition+egoRefFrontBumber-objectPosition-objectRefRearBumber).length();
						if(distance < OBJECT_DISTANCE_EGO_BOUNDARY){
							LOG(cString::Format("Obj%i Distance to Ego Vehicle: Insufficient",count));
							objCategory.distance=DADAS::INSUFFICIENT_DISTANCE;
						} else {
							LOG(cString::Format("Obj%i Distance to Ego Vehicle: Sufficient",count));
							objCategory.distance=DADAS::SUFFICIENT_DISTANCE;
						}
						//relative lane position
						//for(auto lane = lanes->begin(); lane!= lanes->end(); lane++) {
						//Lane has no Position - solved through classification of preprocessing
						objCategory.lanePosition = lanePosition;
						//}	

						//ObjectCategory to categorisation oobjects
						tCategorisation.objects.push_back(objCategory);
						++count;
					}

				}
			}
		}
	}

	//Domain - Use Environment from Scene // Problem --> Not handeled in Spec. is implicit! Which environment can the system Handle?
	//
	if(scene->skills.environment==eEnvironmentHighway) {
		LOG("Domain: Applicable");
		tCategorisation.domain=DADAS::APPLICABLE;
	}else {
		LOG("Domain: Not Applicable");
		tCategorisation.domain=DADAS::NON_APPLICABLE;
	}
	//Domain set in if-clause
	//Driver Overturning
	if(scene->autobox.clearance == ePlannerIntentionDriverHandsOn 
		|| scene->autobox.clearance == ePlannerIntentionDriverSteeringLeft 
		|| scene->autobox.clearance == ePlannerIntentionDriverSteeringRight) {
			LOG("Driver Input received");
			tCategorisation.driverInput=DADAS::OVERTURNED;
	}else {
		LOG("No Overturning");
		tCategorisation.driverInput=DADAS::NONE;
	}

	return tCategorisation;
}

tResult cDadasAbstractionSceneFilter::SendBOOSTCategories(DADAS::tCategorisation *categorisation) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::HELPER::SerializeToSample(pMediaSample,*categorisation);

	//std::vector<char>* buffer = new vector<char>();

	//DADAS::HELPER::Serialize(*buffer, *categorisation);

	////tSize bufferSize = sizeof(vector<char>)+(buffer->size()*sizeof(char));
	//tSize bufferSize = buffer->size()*sizeof(char);
	//

	//RETURN_IF_FAILED(pMediaSample->AllocBuffer(sizeof(tUInt16)+bufferSize));

	//tUInt16 bufferCount = buffer->size();
	//
	//tResult nThrowTmpResult = pMediaSample->CopyBufferFrom((tVoid*)&bufferCount,sizeof(tUInt16));
	//if (IS_FAILED(nThrowTmpResult)) 
	// { 
	//	 RETURN_ERROR(nThrowTmpResult);
	// } 
	//tResult nThrowTmpResult2 = pMediaSample->CopyBufferFrom((tVoid*)&buffer->front(),bufferSize,0,sizeof(tUInt16));
	//if (IS_FAILED(nThrowTmpResult2)) 
	// { 
	//	 RETURN_ERROR(nThrowTmpResult2);
	// } 
	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//char* testbuffer = 0;
	//pMediaSample->Lock((const tVoid**)&testbuffer);

	//pMediaSample->Unlock(testbuffer);

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oOutput.Transmit(pMediaSample));
	//
	//for(int i=0; i<1000;++i) {
	//	LOG("WAITING");
	//}
	//delete buffer; //Can't delete buffer due to asynchron usage of buffer in following filter
	//delete [] was;

	//char* testbuffer2 = 0;
	//pMediaSample->Lock((const tVoid**)&testbuffer2);

	//pMediaSample->Unlock(testbuffer2);
	
	RETURN_NOERROR;
}

void cDadasAbstractionSceneFilter::LOG(cString mes) {		
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}

