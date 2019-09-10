#include <stdio.h>

#include "files.h"
#include "processing.h"
#include "defs.h"

int main() {
	float sine[SAMPLES];

	generateSine( sine );
	saveData( sine );

	return 0;
}
