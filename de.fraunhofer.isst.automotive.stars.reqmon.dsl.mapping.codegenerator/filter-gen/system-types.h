#ifndef SYSTEM_DATA_TYPES.H
#define SYSTEM_DATA_TYPES.H

struct tAllocation {
	int bit_size;
	char* location;
};

// classes
struct tTargetPointMessage {	
	tTargetPoint* targetPoints;
};

struct tTargetPoint {	
	char* id;
	float s;
	float d;
	void ssamp;
	void dsamp;
	void vsamp;
	void tssamp;
	void tdsamp;
	tTargetType tTargetType;
	void preference;
	void constraint;
};

struct tTargetType {	
	char* id;
	tTargetType tTargetType;
	bool isLaneSpecific;
	bool isStrict;
};

struct tScene {	
	tObject* objects;
	tSdDistanceField tSdDistanceField;
	tEvent* events;
	tConflictArea* conflictAreas;
	tEgo tEgo;
	tLane* lanes;
	tSkills tSkills;
	tState tState;
	tStateV2 tStateV2;
	tAutobox tAutobox;
	void timestamp;
	void laneChangeRequest;
	void car;
};

struct tEgo : Object {};

struct tObject {	
	char* id;
	void type;
	float s;
	float s_sigma;
	float d;
	float d_sigma;
	float yaw;
	float length;
	float width;
	void refToFrontBumper;
	void refToRearBumper;
	void quality;
	void intention;
	void yieldProb;
	void brakeLights;
	bool isProjected;
	void indicator;
};

struct tSDDistanceField {	
	float value;
	bool isValid;
};

struct tEvent {	
	float distance;
	float value;
	void type;
};

struct tConflictArea {	
	char* id;
	float distance;
	float angle;
	void type;
	void precedence;
	void passState;
	void conflictingLane;
	void objectsOnConflictingLane;
};

struct tLane {	
	void laneAdvice;
	void optLaneDir;
	void laneType;
	tFutureLane tFutureLane;
	tLaneMarking tLaneMarking;
	void mergerFromLeftDist;
	void mergerFromRightDist;
	void distanceToSplit;
	tAPRGDistToLaneEnd tAPRGDistToLaneEnd;
	void aPRGCumulDist2NecLC;
	void contLeftChangeDistAhead;
	void contRightChangeDistAhead;
	int laneNumber;
	int laneNumberEstimated;
	int totalLaneNumber;
	int totalLaneNumberEstimated;
	float sRGDistToLaneEnd;
	float distanceToLaneRearEnd;
	float width;
	float curvature;
	bool hasSpeedLimit;
	int speedLimit;
	float quality;
	float offset;
	int age;
};

struct tFutureLane : Lane {};

struct tLaneMarking {	
	bool left;
	bool right;
	bool leftEstimated;
	bool rightEstimated;
};

struct tSkills {	
	void localizationElaMatchQuality;
	int averageNumOfDlmLaneJumps;
	void environment;
	void criticalInfrastructure;
	void averageSensorViewingRange;
	int averageLanePerceptionAge;
	int averageTrackPerceptionAge;
};

struct tFunctionState {	
	void delayObjects;
	void delayRoadGraph;
	void delayHypothesis;
};

struct tFunctionStateV2 : FunctionState {};

struct tAutoboxToAdtfMessage {	
	void driverProfile;
	void function;
	void clearance;
	void approval;
	void routeSelection;
	float targetVelocity;
	void timeGap;
	void timestamp;
};

struct tState {	
	float pos;
	float vel;
	float acc;
	void jerk;
};

struct tObjectIntention {	
	float distance;
	void intention;
};

struct tPreference {	
	tScosts tScosts;
	tDcosts tDcosts;
	tSzones tSzones;
	tDzones tDzones;
	void targetOffset;
	void ratioSD;
};

struct tSCosts {	
	void time;
	void comfort;
	void vOffset;
	void sOffset;
	float negativeVelocity;
	void strictViolation;
	void safetyZone;
	void ttc;
	void relativeVelocityThreshold;
};

struct tDCosts {	
	void time;
	void comfort;
	void dOffset;
	void overshoot;
	void objects;
	void swing;
	void grid;
};

struct tSZones {	
	void desiredTG;
	void desiredMinDist;
	void desiredZoneCosts;
	void comfortTG;
	void comfortMinDist;
	void comfortZoneCosts;
	void criticalTG;
	void criticalMinDist;
	void criticalZoneCosts;
	void comfortTTC;
	void criticalTTC;
};

struct tDZones {	
	void comfortMinDist;
	void criticalMinDist;
};

struct tLinearInterpolation {	
	void lowerValue;
	void upperValue;
	void lowerLimit;
	void upperLimit;
};

struct tInterval {	
	void min;
	void max;
	void incr;
};


// messages
struct tAll_signals {
	tAllocation allocation;
	int* sig_refs;
};


// signals
struct tVelocity {
	int sig_ref;
	tAllocation allocation;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tFront_distance {
	int sig_ref;
	tAllocation allocation;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tBack_distance {
	int sig_ref;
	tAllocation allocation;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tLeft_distance {
	int sig_ref;
	tAllocation allocation;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tRight_distance {
	int sig_ref;
	tAllocation allocation;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tCurvature {
	int sig_ref;
	tAllocation allocation;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};


#endif
