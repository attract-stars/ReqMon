#ifndef EXAMPLE.H
#define EXAMPLE.H

struct TScene {	
	struct tEgo;
	 tLane_lanes;
	 tObject_objects;
	struct eEnvironment;
	struct eAutoboxClearance;
};

struct TLane {	
	float curvature;
	struct eLaneType;
	struct eLaneMarking_laneMarkingLeft;
	struct eLaneMarking_laneMarkingRight;
};

struct TObject {	
	float velocity;
	float lane_center_offset;
	float front_distance;
	float back_distance;
	 eLocation;
};

struct TEgo : TObject {};

struct EEnvironment {	
	int highway;
	int unknown;
};

struct EAutoboxClearance {	
	int driverHandsOn;
	int driverSteeringLeft;
	int driverSteeringRight;
	int noDriverAction;
};

struct TLane {	
	float curvature;
	struct eLaneType;
	struct eLaneMarking_laneMarkingLeft;
	struct eLaneMarking_laneMarkingRight;
};

struct TLane {	
	float curvature;
	struct eLaneType;
	struct eLaneMarking_laneMarkingLeft;
	struct eLaneMarking_laneMarkingRight;
};

struct ELaneType {	
	int not_existing;
	int normal_lane;
	int forbidden_lane;
	int emergency_lane;
	int split_lane_normal;
};

struct ELaneType {	
	int not_existing;
	int normal_lane;
	int forbidden_lane;
	int emergency_lane;
	int split_lane_normal;
};

struct EEnvironment {	
	int highway;
	int unknown;
};

struct EAutoboxClearance {	
	int driverHandsOn;
	int driverSteeringLeft;
	int driverSteeringRight;
	int noDriverAction;
};

struct TEgo : TObject {};


/* signal */
float velocity;

/* signal */
float velocity;

/* signal */
float front_distance;

/* signal */
float back_distance;

/* signal */
float front_distance;

/* signal */
float back_distance;

struct ELaneMarking_laneMarkingLeft eLaneMarking_laneMarkingLeft;

struct ELaneMarking_laneMarkingRight eLaneMarking_laneMarkingRight;

int forbidden_lane;

int split_lane_normal;

int emergency_lane;

int emergency_lane;

/* signal */
float curvature;

/* signal */
float curvature;

/* signal */
float velocity;

/* signal */
float velocity;

struct ELaneMarking_laneMarkingLeft eLaneMarking_laneMarkingLeft;

int solid;

struct ELaneMarking_laneMarkingRight eLaneMarking_laneMarkingRight;

int solid;

struct ELaneMarking_laneMarkingLeft eLaneMarking_laneMarkingLeft;

int unknown;

struct ELaneMarking_laneMarkingRight eLaneMarking_laneMarkingRight;

int unknown;

int not_existing;

int not_existing;

int forbidden_lane;

int split_lane_normal;

int emergency_lane;

int emergency_lane;

int highway;

int driverHandsOn;

int driverSteeringLeft;

int driverSteeringRight;

/* signal */
float lane_center_offset;

/* signal */
float lane_center_offset;

int rearEgo;

int frontEgo;


#endif
