#ifndef OPERATINGSYSTEM_H
#define OPERATINGSYSTEM_H

#include "ComputerSystem.h"
#include <stdio.h>

#define NUMBEROFQUEUES 2
#define USERPROCESSQUEUE 0 
#define DAEMONSQUEUE 1  

#define SUCCESS 1
#define PROGRAMDOESNOTEXIST -1
#define PROGRAMNOTVALID -2

#define BEFOREALLOCATING 1
#define AFTERALLOCATING  2
#define BEFORERELEASING  3
#define AFTERRELEASING	 4

#define MEMORYFULL -5

#define MAXLINELENGTH 150

#define PROCESSTABLEMAXSIZE 4

#define MEMCONFIG "MemConfig"

// Every process occupies a 60 positions main memory chunk 
// so we can use 16 positions for the system stack
#define MAINMEMORYSECTIONSIZE 240

#define NOFREEENTRY -3
#define TOOBIGPROCESS -4

#define NOPROCESS -1

// Enumerated type containing all the possible process states
enum ProcessStates { NEW, READY, EXECUTING, BLOCKED, EXIT};

// Enumerated type containing the list of system calls and their numeric identifiers
enum SystemCallIdentifiers { SYSCALL_PRINTPID=2, SYSCALL_END=3, SYSCALL_YIELD = 4, SYSCALL_PRINTEXECPID=5, SYSCALL_SLEEP=7};

// A PCB contains all of the information about a process that is needed by the OS
typedef struct {
	int busy;
	int initialPhysicalAddress;
	int processSize;
	int state;
	int priority;
	int copyOfPCRegister;
	int queueID;
	int mode;
	int accumulator;
	int whenToWakeUp;
} PCB;

typedef struct { 
     int occupied; 
     int initAddress; // Lowest physical address of the partition 
     int size; // Size of the partition in memory positions 
     int PID; // PID of the process using the partition, if occupied 
} PARTITIONDATA; 

// These "extern" declaration enables other source code files to gain access
// to the variable listed
extern PCB processTable[PROCESSTABLEMAXSIZE];


// Functions prototypes
void OperatingSystem_Initialize();
void OperatingSystem_CreateDaemons();
void OperatingSystem_PCBInitialization(int, int, int, int, int, int);
void OperatingSystem_MoveToTheREADYState(int, int);
void OperatingSystem_PrintReadyToRunQueue();
void OperatingSystem_Dispatch(int);
void OperatingSystem_RestoreContext(int);
void OperatingSystem_SaveContext(int);
void OperatingSystem_HandleException();
void OperatingSystem_TerminateProcess();
void OperatingSystem_HandleSystemCall();
void OperatingSystem_HandleClockInterrupt();
int OperatingSystem_LongTermScheduler();
void OperatingSystem_PreemptRunningProcess();
int OperatingSystem_CreateProcess(USER_PROGRAMS_DATA, int);
int OperatingSystem_ObtainAnEntryInTheProcessTable();
int OperatingSystem_ObtainMainMemory(int, int);
int OperatingSystem_ShortTermScheduler();
int OperatingSystem_ExtractFromReadyToRun();
int OperatingSystem_LoadProgram(FILE *, int, int);
int OperatingSystem_ObtainProgramSize(FILE **, char *);
int OperatingSystem_ObtainPriority(FILE *);
int OperatingSystem_lineBeginsWithANumber(char *);
void OperatingSystem_PrintStateChange(int, int , int);
void OperatingSystem_PrintStatus();
void OperatingSystem_MoveToTheSleepingProcessesQueue(int);
int OperatingSystem_ExtractFromSleepingProcessesQueue();
void OperatingSystem_FillInArrivalTimeQueue();
void OperatingSystem_PrintArrivalTimeQueue();
int OperatingSystem_GetExecutingProcessID();
int OperatingSystem_GetNewProgram();
void OperatingSystem_ShowPartitionTable(int);
void OperatingSystem_ReleaseMainMemory();

#endif
