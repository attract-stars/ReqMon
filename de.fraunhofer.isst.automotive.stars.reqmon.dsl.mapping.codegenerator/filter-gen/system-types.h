#ifndef SYSTEM_DATA_TYPES.H
#define SYSTEM_DATA_TYPES.H

// classes
struct tScene {	
	tEgo ego
	tLane* lanes;
	tObject* objects;
	eEnvironment environment
	eAutoboxClearance autoboxClearance
};

struct tObject {	
	float velocity
	float lane_center_offset
	float front_distance
	float back_distance
};

struct tEgo : tObject {};

struct tLane {	
	float curvature
	eLaneType laneType
	eLaneMarking laneMarkingLeft
	eLaneMarking laneMarkingRight
};

enum eLaneType {
	NOT_EXISTING = 0, 
	NORMAL_LANE, 
	FORBIDDEN_LANE, 
	SPLIT_LANE_NORMAL
};

enum eLaneMarking {
	SOLID = 0, 
	NORMAL
};

enum eLocation {
	FRONTEGO = 0, 
	FRONTLEFT, 
	FRONTRIGHT, 
	REAREGO, 
	REARLEFT, 
	UNKNOWN
};

enum eEnvironment {
	HIGHWAY = 0, UNKNOWN
};

enum eAutoboxClearance {
	DRIVERHANDSON = 0, 
	DRIVERSTEERINGLEFT, 
	NODRIVERACTION
};


// signals
struct tVelocity {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tLane_center_offset {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tFront_distance {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tBack_distance {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tLeft_distance {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tRight_distance {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};

struct tCurvature {
	int sig_ref;
	char* datatype;
	float max_value;
	float min_value;
	float preferred_value;
	float step_size;
};


#endif
