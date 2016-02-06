#include "Clock.h"

before(): execution(void Processor_FetchInstruction()) {
	Clock_Update();
}
