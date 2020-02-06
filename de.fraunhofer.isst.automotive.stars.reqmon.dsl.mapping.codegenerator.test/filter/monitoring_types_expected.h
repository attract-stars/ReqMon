#ifndef MONITORING_TYPES_H
#define MONITORING_TYPES_H

struct tCategorisation {
	tEgoVehicleCategory	ego;
	eDomainType domain;
	eDriverTurning driverInput;
	vector<tLaneCategory> lanes; //has to be converted to array
	tUInt16 objectCount;
	vector<tObjectCategory> objects;
};

struct tEgoVehicleCategory {
	eEgoVelocity velocity;
	eEgoOffset offset;
};

enum eEgoVelocity {
	INSUFFICIENT_VELOCITY=0,
	SUFFICIENT_VELOCITY,

	egoVelocityElements
};

enum eEgoOffset {
	SMALL= 0,
	LARGE,

	egoOffsetelements
};


#endif