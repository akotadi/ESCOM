#include <math.h>

#include "defs.h"

void generateSine( float sine[] ){
	float f = 1000, fs = 45000;

	for (register int n = 0; n < SAMPLES; ++n){
		sine[n] = sinf( (2 * M_PI * n * f) / fs );
	}
}