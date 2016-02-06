#ifndef PROCESSOR_H
#define PROCESSOR_H

#include "MainMemory.h"

#define INTERRUPTTYPES 10

// Enumerated type that connects bit positions in the PSW register with
// processor events and status
enum PSW_BITS {POWEROFF_BIT=0, EXECUTION_MODE_BIT=7};

// Enumerated type that connects bit positions in the interruptLines with
// interrupt types 
enum INT_BITS {SYSCALL_BIT=2, EXCEPTION_BIT=6, CLOCKINT_BIT=9};

// Functions prototypes
void Processor_InitializeInterruptVectorTable();
void Processor_InstructionCycleLoop();
void Processor_FetchInstruction();
void Processor_DecodeAndExecuteInstruction();
void Processor_ManageInterrupts();
void Processor_CopyInSystemStack(int, int);
void Processor_RaiseInterrupt(const unsigned int);
void Processor_ACKInterrupt(const unsigned int);
unsigned int Processor_GetInterruptLineStatus(const unsigned int);
void Processor_ActivatePSW_Bit(const unsigned int);
void Processor_DeactivatePSW_Bit(const unsigned int);
unsigned int Processor_PSW_BitState(const unsigned int);

extern MEMORYCELL registerMBR_CPU;
extern int registerMAR_CPU;

// The OS needs to access the PC register to restore the context of
// the process to which the processor is being assigned
extern int registerPC_CPU;

// The OS needs to access the accumulator register to restore the context of
// the process to which the processor is being assigned and to save the context
// of the process being preempted for another ready process
extern int registerAccumulator_CPU;

// The OS needs to access register A to when executing the system call management
// routine, so it will be able to know the invoked system call identifier
// llamada al sistema realizada
extern int registerA_CPU;

#endif
