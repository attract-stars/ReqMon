#ifndef EXAMPLE.H
#define EXAMPLE.H

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

struct Lane {	
	struct left_lane;
	struct right_lane;
	bool center;
	struct marking;
	bool restricted;
	bool emergency;
	bool highway_on_ramp;
	float curvature;
};

struct Lane {	
	struct left_lane;
	struct right_lane;
	bool center;
	struct marking;
	bool restricted;
	bool emergency;
	bool highway_on_ramp;
	float curvature;
};

struct This_car : Vehicle {};


bool solid;


#endif
