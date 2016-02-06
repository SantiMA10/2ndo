#include "Clock.h"
#include "Processor.h"

int time=0;

void Clock_Update() {
	time++;
	if(time % INTERVALBETWEENINTERRUPTS == 0){
	      Processor_RaiseInterrupt(CLOCKINT_BIT);
	}
}


int Clock_GetTime() {

	return time;
}
