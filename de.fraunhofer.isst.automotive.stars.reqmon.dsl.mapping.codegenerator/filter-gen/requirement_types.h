#ifndef REQUIREMENT_DATA_TYPES.H
#define REQUIREMENT_DATA_TYPES.H
	
struct tSystem {
};

struct tLane {
	Markings markings;
	Curvature curvature;
	eLaneType eLaneType;
};

struct tVehicle {
	Relative_velocity relative_velocity;
	Distance distance;
	Velocity velocity;
};

struct tEgo-vehicle : tVehicle {
};

struct tDomain {
};

struct tDriver {
};

enum eLaneType { RESTRICTED = 0, EMERGENCY };
	
#endif
