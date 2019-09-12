#ifndef MAPPED_H
#define MAPPED_H

struct Back_vehicle : Vehicle {};

struct Marking {	
	bool solid;
};

struct Vehicle {	
	bool exist;
	bool lane_change;
	struct position;
	float front_distance;
	float back_distance;
	float left_distance;
	float right_distance;
	float velocity;
};

/* signal */
float back_distance;

/* signal */
float curvature;


#endif
