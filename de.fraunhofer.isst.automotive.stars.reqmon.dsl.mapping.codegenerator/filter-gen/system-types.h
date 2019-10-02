#ifndef SYSTEM_DATA_TYPES.H
#define SYSTEM_DATA_TYPES.H

struct tAllocation {
	int bit_size;
	char* location;
};

// classes
struct tVehicle {	
	bool exist;
	bool lane_change;
	struct tPosition;
	float front_distance;
	float back_distance;
	float left_distance;
	float right_distance;
	float velocity;
};

struct tThis_car : Vehicle {};

struct tRight_vehicle : Vehicle {};

struct tLeft_vehicle : Vehicle {};

struct tFront_vehicle : Vehicle {};

struct tBack_vehicle : Vehicle {};

struct tLane {	
	struct tLeft_lane;
	struct tRight_lane;
	bool center;
	struct tMarking;
	bool restricted;
	bool emergency;
	bool highway_on_ramp;
	float curvature;
};

struct tRight_lane : Lane {};

struct tLeft_lane : Lane {};

struct tMarking {	
	bool solid;
};

struct tPosition {	
	struct tLane;
	struct tFront_vehicle;
	struct tBack_vehicle;
	struct tLeft_vehicle;
	struct tRight_vehicle;
};

struct tUnknown {};


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
