#ifndef EXAMPLE.H
#define EXAMPLE.H

struct Back_vehicle : Vehicle {};

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


/* signal */
float back_distance;

/* signal */
float back_distance;


#endif
