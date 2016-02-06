#include "OperatingSystem.h"
#include "MMU.h"
#include "Processor.h"
#include "Buses.h"
#include "Heap.h"
#include "Clock.h"
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

// The process table
PCB processTable[PROCESSTABLEMAXSIZE];

//Nombre de los estados de un proceso
char * statesNames [5]={"NEW","READY","EXECUTING","BLOCKED","EXIT"};

// Identifier of the current executing process
int executingProcessID=NOPROCESS;

// Identifier of the System Idle Process
int sipID;

PARTITIONDATA partitionsTable[PROCESSTABLEMAXSIZE];

// Array that contains the identifiers of the READY processes
int readyToRunQueue[NUMBEROFQUEUES][PROCESSTABLEMAXSIZE];
int numberOfReadyToRunProcesses[NUMBEROFQUEUES];

int arrivalTimeQueue[USERPROGRAMSMAXNUMBER];
int numberOfProgramsInArrivalTimeQueue;

int sleepingProcessesQueue[PROCESSTABLEMAXSIZE];
int numberOfSleepingProcesses=0;

// Variable containing the number of not terminated user processes
int numberOfNotTerminatedProcesses=0;

int numberOfClockInterrupts = 0;

void OperatingSystem_PrintStateChange(int PID, int actualState, int newState){
	if(actualState != newState){
		if(actualState != -1){
    		ComputerSystem_DebugMessage(SYSPROC, "sdssGdsGssGss","[",Clock_GetTime(),"] ", "Process [", PID, "] moving from the [",statesNames[actualState], "] state to the [",statesNames[newState],"] state\n");
  		}
  		else{
   			ComputerSystem_DebugMessage(SYSPROC, "sdssGdsGss","[",Clock_GetTime(),"] ", "New process [", PID, "] moving to the [",statesNames[newState], "] state\n");
  		}
	}
  
}

// Function to initialize the partition table
void OperatingSystem_InitializePartitionTable() {

	char lineRead[MAXLINELENGTH];
	FILE *fileMemConfig;
	
	fileMemConfig= fopen(MEMCONFIG, "r");
	int number = 0;
	// The initial physical address of the first partition is 0
	int initAddress=0;
	int currentPartition=0;
	
	// The file is processed line by line
	while (fgets(lineRead, MAXLINELENGTH, fileMemConfig) != NULL) {
		number=atoi(lineRead);
		// "number" is the size of a just read partition
		partitionsTable[currentPartition].initAddress=initAddress;
		partitionsTable[currentPartition].size=number;
		partitionsTable[currentPartition].occupied=0;
		// Next partition will begin at the updated "initAdress"
		initAddress+=number;
		// There is now one more partition
		currentPartition++;
	}
}

void OperatingSystem_FillInArrivalTimeQueue() {
	int i=0;

	while (userProgramsList[i]!=NULL && i<USERPROGRAMSMAXNUMBER) {
	  if (Heap_add(i,arrivalTimeQueue,QUEUE_ARRIVAL,i,USERPROGRAMSMAXNUMBER)==0)
		  i++; // There is one more program 
	}
	numberOfProgramsInArrivalTimeQueue=i;
}

// Print arrivalTiemQueue program information
void OperatingSystem_PrintArrivalTimeQueue(){
  int i;
  
  if (numberOfProgramsInArrivalTimeQueue>0) {
	ComputerSystem_DebugMessage(LONGTERMSCHEDULE,"s","\tArrival Time Queue: ");
	for (i=0; i< numberOfProgramsInArrivalTimeQueue; i++) {
	  ComputerSystem_DebugMessage(LONGTERMSCHEDULE,"sGssRds","[",userProgramsList[arrivalTimeQueue[i]]->executableName,", ",userProgramsList[arrivalTimeQueue[i]]->arrivalTime,"]");
	  if (i<numberOfProgramsInArrivalTimeQueue-1)
		ComputerSystem_DebugMessage(LONGTERMSCHEDULE,"s",", ");
	}
	ComputerSystem_DebugMessage(LONGTERMSCHEDULE,"s","\n");
  }
}

void OperatingSystem_PrintStatus(){ // Ejercicio 5-c de V2
  int i;
  OperatingSystem_PrintReadyToRunQueue();
  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"s","\tSLEEPING Queue: ");
  for (i=0; i< numberOfSleepingProcesses; i++) {
	ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"sGdsdsRds","[",sleepingProcessesQueue[i],", ",processTable[sleepingProcessesQueue[i]].priority,", ",processTable[sleepingProcessesQueue[i]].whenToWakeUp,"]");
	if (i<numberOfSleepingProcesses-1)
	  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"s",", ");
  }
  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"s","\n");
  
  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"sGdsdsRdsss","\tRunning Process Information: [PID:",executingProcessID
						  ,", Priority: ",processTable[executingProcessID].priority
						  ,", WakeUp: ",processTable[executingProcessID].whenToWakeUp
						  ,", Queue: ",processTable[executingProcessID].queueID?"DAEMONS":"USER","]\n");
	OperatingSystem_PrintArrivalTimeQueue();
}

// Initial set of tasks of the OS
void OperatingSystem_Initialize() {
	
	int i, selectedProcess;
	
	// Process table initialization (all entries are free)
	for (i=0; i<PROCESSTABLEMAXSIZE;i++)
		processTable[i].busy=0;
	
	// Initialization of the interrupt vector table of the processor
	Processor_InitializeInterruptVectorTable();
	
	OperatingSystem_InitializePartitionTable();
	
	// Create all system daemon processes
	OperatingSystem_CreateDaemons();
	
	OperatingSystem_FillInArrivalTimeQueue();
	OperatingSystem_PrintStatus();
	
	// Create all user processes from the information given in the command line
	int numberOfProcess = OperatingSystem_LongTermScheduler();

	if(numberOfProcess != 0 || numberOfProgramsInArrivalTimeQueue > 0){
		// At least, one user process has been created
		// Select the first process that is going to use the processor
		selectedProcess = OperatingSystem_ShortTermScheduler();
		OperatingSystem_Dispatch(selectedProcess);
	}

	//ComputerSystem_PowerOff();
	  
}

int OperatingSystem_GetExecutingProcessID(){
	return executingProcessID;
}

// This function returns:
// 		-1 if no programs in arrivalTimeQueue
//		1 if any program arrivalTime is now
//		0 else
// considered by the LTS to create processes at the current time
int OperatingSystem_GetNewProgram() {

        int currentTime;
		int programArrivalTime;

		if (numberOfProgramsInArrivalTimeQueue <= 0)
		  return -1;  // No new programs in command line list of programs
		
		// Get the current simulation time
        currentTime = Clock_GetTime();
		
		// Get arrivalTime of next program
		programArrivalTime = userProgramsList[arrivalTimeQueue[0]]->arrivalTime; 

		if (programArrivalTime > (currentTime-INTERVALBETWEENINTERRUPTS)
			&&  programArrivalTime <= currentTime)
		  return 1;  //  There'is new program to start
		 
		return 0;  //  No program in current time
}


// Daemon processes are system processes, that is, they work together with the OS.
// The System Idle Process uses the CPU whenever a user process is able to use it
void OperatingSystem_CreateDaemons() {
  
	USER_PROGRAMS_DATA systemIdleProcess;
	
	systemIdleProcess.executableName="SystemIdleProcess";
	sipID=OperatingSystem_CreateProcess(systemIdleProcess, DAEMONSQUEUE);  
}


// The LTS is responsible of the admission of new processes in the system.
// Initially, it creates a process from each program specified in the command line
int OperatingSystem_LongTermScheduler() {
  
	int PID;
	int id;
 	int i=0;
	int numberOfSuccessfullyCreatedProcesses=0;
	while (userProgramsList[i] != NULL && i < USERPROGRAMSMAXNUMBER) {
		//ComputerSystem_DebugMessage(ERROR,"sdsdsds","[i] ",i," [GNP] ",OperatingSystem_GetNewProgram()," [PIDa] ",arrivalTimeQueue[i],"\n");
		if (OperatingSystem_GetNewProgram() == 1) {
			id = Heap_poll(arrivalTimeQueue, QUEUE_ARRIVAL, numberOfProgramsInArrivalTimeQueue);
			numberOfProgramsInArrivalTimeQueue--;
			PID=OperatingSystem_CreateProcess(*userProgramsList[id], USERPROCESSQUEUE);
			
			if(PID == NOFREEENTRY){
				ComputerSystem_DebugMessage(ERROR,"sdsRsRsRs","[",Clock_GetTime(),"] ","ERROR: There are not free entries in the process table for the program [",userProgramsList[id]->executableName,"]\n");
			}
			else if (PID == PROGRAMDOESNOTEXIST){
				ComputerSystem_DebugMessage(ERROR,"sdsRsRsRs","[",Clock_GetTime(),"] ","ERROR: Program [",userProgramsList[id]->executableName,"] is not valid (it does not exist)\n");
			}
			else if (PID == PROGRAMNOTVALID){
				ComputerSystem_DebugMessage(ERROR,"sdsRsRsRs","[",Clock_GetTime(),"] ","ERROR: Program [",userProgramsList[id]->executableName,"] is not valid (invalid size or priority)\n");
			}
			else if (PID == TOOBIGPROCESS){
				ComputerSystem_DebugMessage(ERROR,"sdsRsRsRs","[",Clock_GetTime(),"] ","ERROR: Program [",userProgramsList[id]->executableName,"] is too big\n");
			}
			else if (PID == MEMORYFULL){
				ComputerSystem_DebugMessage(ERROR,"sdsRsRsRs","[",Clock_GetTime(),"] ","ERROR: A process could not be created from program [",userProgramsList[id]->executableName,"] because an appropriate partition is not available\n");
			}
			else{
			  numberOfSuccessfullyCreatedProcesses++;
			  ComputerSystem_DebugMessage(INIT,"sdsGsGdGsGsGs","[",Clock_GetTime(),"] ","Process [",PID,"] created from program [",userProgramsList[id]->executableName,"]\n");
			  //OperatingSystem_PrintStatus();
			}
		}
		i++;
	}
	
	//ComputerSystem_DebugMessage(ERROR,"sddds","[OperatingSystem_LongTermScheduler]",numberOfSuccessfullyCreatedProcesses,numberOfProgramsInArrivalTimeQueue,numberOfSleepingProcesses,"\n");

	if(numberOfSuccessfullyCreatedProcesses == 0 && numberOfProgramsInArrivalTimeQueue == 0 && numberOfSleepingProcesses == 0 && numberOfNotTerminatedProcesses==0){
		//ComputerSystem_DebugMessage(ERROR,"s","[OperatingSystem_LongTermScheduler]Off\n");
		processTable[sipID].copyOfPCRegister=processTable[sipID].processSize-1;
	  	OperatingSystem_Dispatch(sipID);
	}
	else{
		OperatingSystem_PrintStatus();
	}

	numberOfNotTerminatedProcesses += numberOfSuccessfullyCreatedProcesses;

	// Return the number of succesfully created processes
	return  numberOfSuccessfullyCreatedProcesses;
}

// This function creates a process from an executable program
int OperatingSystem_CreateProcess(USER_PROGRAMS_DATA executableProgram, int queueID) {
  
	int PID;
	int processSize;
	int loadingPhysicalAddress;
	int priority;
	int loadingResult;
	int mode = 0;
	int pos;
	FILE *programFile;

	// Obtain a process ID
	PID=OperatingSystem_ObtainAnEntryInTheProcessTable();

	if(PID == NOFREEENTRY){
		return NOFREEENTRY;
	}
		
	// Obtain the memory requirements of the program
	processSize=OperatingSystem_ObtainProgramSize(&programFile, executableProgram.executableName);
	if(processSize == PROGRAMDOESNOTEXIST){
		return processSize;
	}
	if(processSize == TOOBIGPROCESS){
		return TOOBIGPROCESS;
	}
	
	// Obtain the priority for the process
	priority=OperatingSystem_ObtainPriority(programFile);
	if(priority == PROGRAMNOTVALID || processSize == PROGRAMDOESNOTEXIST){
		return PROGRAMNOTVALID;
	}
	
	OperatingSystem_ShowPartitionTable(1);
	// Obtain enough memory space
	pos=OperatingSystem_ObtainMainMemory(processSize, PID);
	if(pos == TOOBIGPROCESS || pos == MEMORYFULL){
		return pos;
	}
	loadingPhysicalAddress = partitionsTable[pos].initAddress;
	OperatingSystem_ShowPartitionTable(2);
	
	// Load program in the allocated memory
	loadingResult=OperatingSystem_LoadProgram(programFile, loadingPhysicalAddress, processSize);
	if(loadingResult == TOOBIGPROCESS){
		return TOOBIGPROCESS;
	}

	if(queueID == DAEMONSQUEUE){
		mode = 1;
	}
		
	// PCB initialization
	OperatingSystem_PCBInitialization(PID, loadingPhysicalAddress, processSize, priority, queueID, mode);
	
	return PID;
	
}


// Search for a free entry in the process table. The index of the selected entry
// will be used as the process identifier
int OperatingSystem_ObtainAnEntryInTheProcessTable() {

	int entry=0;

	while (entry<PROCESSTABLEMAXSIZE)
		if (processTable[entry].busy==0)
			return entry;
		else
			entry++;
	return NOFREEENTRY;
}


// Main memory is assigned in chunks. All chunks are the same size. A process
// always obtains the chunk whose position in memory is equal to the processor identifier
int OperatingSystem_ObtainMainMemory(int processSize, int PID) {

 	//Antes del ejercicio 10
	
	if (processSize>MAINMEMORYSECTIONSIZE){
		return TOOBIGPROCESS;
	}
	
	ComputerSystem_DebugMessage(SYSMEM, "sdsGdsGds", "[",Clock_GetTime(),"] Process [",PID,"] requests [",processSize,"] memory positions\n");
	int i;
	int position[2];
	position[0] = -1;
	position[1] = 5000;
	for(i = 0; i < PROCESSTABLEMAXSIZE; i++){
		if(partitionsTable[i].occupied == 0){
			if(processSize <= partitionsTable[i].size){
				if(partitionsTable[i].size - processSize < position[1]){
					position[0] = i;
					position[1] = partitionsTable[i].size - processSize;
				}
			}
			else if(processSize > partitionsTable[i].size && position[0] < 0 && position[0] != -2){
				position[0] = -3;
			}
		}
		else{
			if(processSize <= partitionsTable[i].size && position[0] < 0){
				position[0] = -2;
			}
			else if(processSize > partitionsTable[i].size && position[0] < 0 && position[0] != -2){
				position[0] = -3;
			}
		}
	}
	if(position[0] > -1){
		partitionsTable[position[0]].occupied = 1;
		partitionsTable[position[0]].PID = PID;
		ComputerSystem_DebugMessage(SYSMEM, "sdsGdsGdsGdsGds", "[",Clock_GetTime(),"] Partition [",position[0],": ",partitionsTable[position[0]].initAddress,"-> ",partitionsTable[position[0]].size,"] has been assigned to process[",PID,"]\n");
	}
	else if(position[0] == -2){
		return MEMORYFULL;
	}
	else if(position[0] == -3){
		return TOOBIGPROCESS;
	}
	
	return position[0];
}


// Assign initial values to all fields inside the PCB
void OperatingSystem_PCBInitialization(int PID, int initialPhysicalAddress, int processSize, int priority, int queueID, int mode) {

	processTable[PID].busy=1;
	processTable[PID].initialPhysicalAddress=initialPhysicalAddress;
	processTable[PID].processSize=processSize;
	processTable[PID].copyOfPCRegister=0;
	processTable[PID].priority=priority;
	OperatingSystem_PrintStateChange(PID, -1, NEW);
	processTable[PID].state=NEW;
	processTable[PID].queueID = queueID;
	processTable[PID].mode = mode;
	OperatingSystem_MoveToTheREADYState(PID, processTable[PID].queueID);
}

// Move a process to the READY state: it will be inserted, depending on its priority, in
// a queue of identifiers of READY processes
void OperatingSystem_MoveToTheREADYState(int PID, int queueID) {
	if (Heap_add(PID, readyToRunQueue[queueID],QUEUE_PRIORITY ,numberOfReadyToRunProcesses[queueID] ,PROCESSTABLEMAXSIZE)>=0) {
	  numberOfReadyToRunProcesses[queueID]++;
	  OperatingSystem_PrintStateChange(PID, processTable[PID].state, READY);
	  processTable[PID].state=READY;
	  //OperatingSystem_PrintReadyToRunQueue();
	} 
}

void OperatingSystem_MoveToTheSleepingProcessesQueue(int PID) {
	if (Heap_add(PID, sleepingProcessesQueue, QUEUE_WAKEUP ,numberOfSleepingProcesses, PROCESSTABLEMAXSIZE)>=0) {
	  OperatingSystem_SaveContext(PID);
	  numberOfSleepingProcesses++;
	  OperatingSystem_PrintStateChange(PID, processTable[PID].state, BLOCKED);
	  processTable[PID].state=BLOCKED;
	} 
}

void OperatingSystem_PrintReadyToRunQueue(){
	
	int i;
	int daemonQueueSize = numberOfReadyToRunProcesses[DAEMONSQUEUE];
	int userQueueSize = numberOfReadyToRunProcesses[USERPROCESSQUEUE];
	if(daemonQueueSize != 0 || userQueueSize != 0 || numberOfSleepingProcesses != 0){
	   ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "sdss","[",Clock_GetTime(),"] ", "Ready-to-run processes queue:\n");
	   ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", "\tUSER: ");

	}

	for(i = 0; i < numberOfReadyToRunProcesses[USERPROCESSQUEUE]; i++){
		int PID = readyToRunQueue[USERPROCESSQUEUE][i];
		int prioridad = processTable[PID].priority;
	      	ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "sGdsds", "[",PID,", ", prioridad,"]");
		if(i != numberOfReadyToRunProcesses[USERPROCESSQUEUE] - 1){
		  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", ", ");
		}
	}
	if(daemonQueueSize != 0 || userQueueSize != 0 || numberOfSleepingProcesses != 0){
	   ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", "\n\tDAEMONS: ");
		if(daemonQueueSize == 0)
			ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", "\n");
	}
	for(i = 0; i < numberOfReadyToRunProcesses[DAEMONSQUEUE]; i++){
		int PID = readyToRunQueue[DAEMONSQUEUE][i];
		int prioridad = processTable[PID].priority;
	      	ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "sGdsds", "[",PID,", ", prioridad,"]");
		if(i == numberOfReadyToRunProcesses[DAEMONSQUEUE] - 1){
		  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", "\n");
		}
		else{
		  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", ", ");
		}
	}
}



// The STS is responsible of deciding which process to execute when specific events occur.
// It uses processes priorities to make the decission. Given that the READY queue is ordered
// depending on processes priority, the STS just selects the process in front of the READY queue
int OperatingSystem_ShortTermScheduler() {
	
	int selectedProcess;

	selectedProcess = OperatingSystem_ExtractFromReadyToRun(USERPROCESSQUEUE);
	if(selectedProcess == NOPROCESS && (numberOfSleepingProcesses > 0 || numberOfProgramsInArrivalTimeQueue > 0)){
		selectedProcess = OperatingSystem_ExtractFromReadyToRun(DAEMONSQUEUE);
	}
	
	return selectedProcess;
}


// Return PID of more priority process in the READY queue
int OperatingSystem_ExtractFromReadyToRun(int queueID) {
  
	int selectedProcess=NOPROCESS;
	if(numberOfReadyToRunProcesses[queueID] > 0){
		if(queueID == DAEMONSQUEUE){
			Processor_ActivatePSW_Bit(EXECUTION_MODE_BIT);
		}
		else{
			Processor_DeactivatePSW_Bit(EXECUTION_MODE_BIT);
		}
	  selectedProcess=Heap_poll(readyToRunQueue[queueID],QUEUE_PRIORITY ,numberOfReadyToRunProcesses[queueID]);
	  if (selectedProcess>=0) 
	    numberOfReadyToRunProcesses[queueID]--;
	}
	// Return more priority process or NOPROCESS if empty queue
	return selectedProcess; 
}

// Return PID of more priority process in the READY queue
int OperatingSystem_ExtractFromSleepingProcessesQueue() {
  
	int selectedProcess=NOPROCESS;
	if(numberOfSleepingProcesses > 0){
	  selectedProcess=Heap_poll(sleepingProcessesQueue, QUEUE_WAKEUP, numberOfSleepingProcesses);
	  if (selectedProcess>=0) 
	    numberOfSleepingProcesses--;
	}
	// Return more priority process or NOPROCESS if empty queue
	return selectedProcess; 
}

// Function that assigns the processor to a process
void OperatingSystem_Dispatch(int PID) {

	// Modify hardware registers with appropriate values for the process identified by PID
	OperatingSystem_RestoreContext(PID);
	// The process identified by PID becomes the current executing process
	executingProcessID=PID;
	// Change the process' state
	OperatingSystem_PrintStateChange(PID, processTable[PID].state, EXECUTING);
	processTable[PID].state=EXECUTING;
}


// Modify hardware registers with appropriate values for the process identified by PID
void OperatingSystem_RestoreContext(int PID) {
  
	// New values for the CPU registers are obtained from the PCB
	registerPC_CPU=processTable[PID].copyOfPCRegister;

	if(processTable[PID].mode == 1 || processTable[PID].queueID == DAEMONSQUEUE){
		Processor_ActivatePSW_Bit(EXECUTION_MODE_BIT);
	}
	else{
		Processor_DeactivatePSW_Bit(EXECUTION_MODE_BIT);
	}

	registerAccumulator_CPU = processTable[PID].accumulator;
	// Same thing for the MMU registers
	registerBase_MMU=processTable[PID].initialPhysicalAddress;
	//registerLimit_MMU=processTable[PID].processSize; //Original
	// registerBase_MMU=0;
	registerLimit_MMU=processTable[PID].processSize;
}


// Function invoked when the executing process leaves the CPU 
void OperatingSystem_PreemptRunningProcess() {

	// Save in the process' PCB essential values stored in hardware registers and the system stack
	OperatingSystem_SaveContext(executingProcessID);
	// Change the process' state
	OperatingSystem_MoveToTheREADYState(executingProcessID, processTable[executingProcessID].queueID);
	// The processor is not assigned until the OS selects another process
	executingProcessID=NOPROCESS;
}

// Save in the process' PCB essential values stored in hardware registers and the system stack
void OperatingSystem_SaveContext(int PID) {
	
	registerMAR_CPU=MAINMEMORYSIZE-1; // Load PC saved for interrupt manager

	Buses_write_AddressBus_From_To(registerMAR_CPU, &registerMAR_MainMemory);
	MainMemory_readMemory();
	processTable[PID].copyOfPCRegister= registerMBR_CPU.operationCode;
	processTable[PID].mode = Processor_PSW_BitState(EXECUTION_MODE_BIT);
	processTable[PID].accumulator = registerAccumulator_CPU;
	
}


// Exception management routine
void OperatingSystem_HandleException() {
  
  if(registerB_CPU == DIVISIONBYZERO){
  	ComputerSystem_DebugMessage(INTERRUPT,"sdsRdsRss","[",Clock_GetTime(),"] Process [",executingProcessID,"] has caused an exception (","division by zero",") and is being terminated\n");
  }	
  else if(registerB_CPU == INVALIDPROCESSORMODE){
  	ComputerSystem_DebugMessage(INTERRUPT,"sdsRdsRss","[",Clock_GetTime(),"] Process [",executingProcessID,"] has caused an exception (","invalid processor mode",") and is being terminated\n");
  }
  else if(registerB_CPU == INVALIDADDRESS){
  	ComputerSystem_DebugMessage(INTERRUPT,"sdsRdsRss","[",Clock_GetTime(),"] Process [",executingProcessID,"] has caused an exception (","invalid address",") and is being terminated\n");
  }
  else if(registerB_CPU == INVALIDINSTRUCTION){
  	ComputerSystem_DebugMessage(INTERRUPT,"sdsRdsRss","[",Clock_GetTime(),"] Process [",executingProcessID,"] has caused an exception (","invalid instruction",") and is being terminated\n");
  }
  OperatingSystem_TerminateProcess();

}

void OperatingSystem_HandleClockInterrupt() {
	numberOfClockInterrupts++;
	//ComputerSystem_DebugMessage(INTERRUPT,"sdsCsCdCs","[",Clock_GetTime(),"] ","Se ha producido la interrupcion de reloj numero [",numberOfClockInterrupts,"]\n");
    ComputerSystem_DebugMessage(INTERRUPT,"sdsCsCdCs","[",Clock_GetTime(),"] ","Clock interrupt [",numberOfClockInterrupts,"] has ocurred\n");
	OperatingSystem_LongTermScheduler();
	int PIDSleeping, i; //Recorrer cola de dormidos
	//if(executingProcessID == sipID && numberOfReadyToRunProcesses[USERPROCESSQUEUE] > 0){
	//	OperatingSystem_PreemptRunningProcess();
	//	actualPID = OperatingSystem_ShortTermScheduler();
	//	OperatingSystem_Dispatch(actualPID);
	//}
	
	//Este es el que tiene que despertar procesos

	for(i = 0; i <= numberOfSleepingProcesses; i++){
		PIDSleeping = sleepingProcessesQueue[i];
		if(processTable[PIDSleeping].whenToWakeUp == numberOfClockInterrupts){
			OperatingSystem_ExtractFromSleepingProcessesQueue();
			OperatingSystem_MoveToTheREADYState(PIDSleeping, processTable[PIDSleeping].queueID);
		}
	} 
	if(numberOfReadyToRunProcesses[USERPROCESSQUEUE] > 0){
		for(i = 0; i < numberOfReadyToRunProcesses[USERPROCESSQUEUE]; i++){
			PIDSleeping = readyToRunQueue[USERPROCESSQUEUE][i];
			//ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"s","[]");
			if(processTable[executingProcessID].priority > processTable[PIDSleeping].priority){
				//ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"sdssGdsGds","[",Clock_GetTime(),"] ","Proceso [",executingProcessID,"] es expulsado del procesador por el proceso [", PIDSleeping,"]\n");
				ComputerSystem_DebugMessage(SHORTTERMSCHEDULE,"sdssGdsGds","[",Clock_GetTime(),"] ","Process [",executingProcessID,"] is thrown out of the processor by process [", PIDSleeping,"]\n");
				OperatingSystem_PreemptRunningProcess();
				executingProcessID = OperatingSystem_ShortTermScheduler();
				OperatingSystem_Dispatch(executingProcessID);
				OperatingSystem_PrintStatus();
			}
		}
	}
}
void OperatingSystem_ReleaseMainMemory(){
	int i;
	for(i = 0; i < PROCESSTABLEMAXSIZE; i++){
		if(partitionsTable[i].PID == executingProcessID){
			ComputerSystem_DebugMessage(SYSMEM,"sdsGdsGdsGdsGds", "[",Clock_GetTime(),"] Partition [",i,": ",partitionsTable[i].initAddress,"-> ",partitionsTable[i].size,"] used by process [",executingProcessID,"] has been released\n");
			partitionsTable[i].occupied = 0;
		}
	}
}

void OperatingSystem_ShowPartitionTable(int state){

	int i;

	ComputerSystem_DebugMessage(SYSMEM,"sds", "[",Clock_GetTime(),"] Main memory state ");

	if(state == BEFOREALLOCATING){
		ComputerSystem_DebugMessage(SYSMEM,"s", "(before allocating memory):\n");
	}
	else if(state == AFTERALLOCATING){
		ComputerSystem_DebugMessage(SYSMEM,"s", "(after allocating memory):\n");
	}
	else if(state == BEFORERELEASING){
		ComputerSystem_DebugMessage(SYSMEM,"s", "(before releasing memory):\n");
	}
	else if(state == AFTERRELEASING){
		ComputerSystem_DebugMessage(SYSMEM,"s", "(after releasing memory):\n");
	}

	for(i = 0; i < PROCESSTABLEMAXSIZE; i++){
		if(partitionsTable[i].occupied == 1){
			ComputerSystem_DebugMessage(SYSMEM,"sGdsGdsGdsGds", "\t[",i,"] [",partitionsTable[i].initAddress,"-> ",partitionsTable[i].size,"] [",partitionsTable[i].PID,"]\n");
		}
		else{
			ComputerSystem_DebugMessage(SYSMEM,"sGdsGdsGdsGss", "\t[",i,"] [",partitionsTable[i].initAddress,"-> ",partitionsTable[i].size,"] [","AVAILABLE","]\n");
		}
	}

}


// All tasks regarding the removal of the process
void OperatingSystem_TerminateProcess() {
  
	int selectedProcess;
  	
  	OperatingSystem_PrintStateChange(executingProcessID, processTable[executingProcessID].state, EXIT);
	processTable[executingProcessID].state=EXIT;
	processTable[executingProcessID].busy=0;
	OperatingSystem_ShowPartitionTable(3);
	OperatingSystem_ReleaseMainMemory();
	OperatingSystem_ShowPartitionTable(4);

	// One more process that has terminated
	numberOfNotTerminatedProcesses--;
	
	if (numberOfNotTerminatedProcesses > 0 || numberOfProgramsInArrivalTimeQueue > 0) {
		//processTable[executingProcessID].busy=0;
		// Select the next process to execute
		selectedProcess = OperatingSystem_ShortTermScheduler();
		// Assign the processor to that process
		OperatingSystem_Dispatch(selectedProcess);
	}
	else{ // Simulation must finish (done by modifying the PC of the System Idle Process so it points to its 'halt' instruction,
		//ComputerSystem_DebugMessage(ERROR, "sdds", "[OperatingSystem_TerminateProcess]",numberOfNotTerminatedProcesses,numberOfProgramsInArrivalTimeQueue,"\n");
		// located at the last memory position used by that process
		processTable[sipID].copyOfPCRegister=processTable[sipID].processSize-1;
		// Assign the processor to the System Idle Process
		OperatingSystem_Dispatch(sipID);
	}
  
}


// System call management routine
void OperatingSystem_HandleSystemCall() {
  
	int sytemCallID;
	int actualPID, nextPID;

	// Register A contains the identifier of the issued system call
	sytemCallID=registerA_CPU;
	
	switch (sytemCallID) {

		case SYSCALL_PRINTEXECPID:
			printf("Process [%d] has the processor assigned\n",executingProcessID);
		break;

		case SYSCALL_END:
			ComputerSystem_DebugMessage(SYSPROC,"sdsRsRdRs","[",Clock_GetTime(),"] ","Process [",executingProcessID,"] has requested to terminate\n");
			OperatingSystem_TerminateProcess();
			break;
		case SYSCALL_YIELD:
			//Acedemos a la cola del proceso,mediante el psw y miramos si hay algun proceso con su misma prioridad 
			//si la hay, cambiamos de proceso, sino nada.	
			actualPID = executingProcessID;
			nextPID = OperatingSystem_ShortTermScheduler();
			if(processTable[actualPID].priority == processTable[nextPID].priority && processTable[nextPID].queueID == processTable[actualPID].queueID){
				ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "sdssGdsGds","[",Clock_GetTime(),"] ", "Process [",actualPID,"] transfers control of the processor to process [",nextPID,"]\n");
				OperatingSystem_PreemptRunningProcess();
				OperatingSystem_Dispatch(nextPID);
			}
			else{
				OperatingSystem_MoveToTheREADYState(nextPID, USERPROCESSQUEUE);  
			}
			OperatingSystem_PrintStatus();
			break;
		case SYSCALL_SLEEP:
			processTable[executingProcessID].whenToWakeUp = numberOfClockInterrupts+1+abs(registerAccumulator_CPU);
			OperatingSystem_MoveToTheSleepingProcessesQueue(executingProcessID);
			nextPID = OperatingSystem_ShortTermScheduler();
			OperatingSystem_Dispatch(nextPID);
			OperatingSystem_PrintStatus();
			break;
		default:
		  	ComputerSystem_DebugMessage(INTERRUPT,"sdsRdsRds","[",Clock_GetTime(),"] Process [",executingProcessID,"] has made an invalid system call (",sytemCallID,") and is being terminated\n");
			OperatingSystem_TerminateProcess();
			break;  	

	}
}

// Returns the size of the program, stored in the program file
int OperatingSystem_ObtainProgramSize(FILE **programFile, char *program) {

	char lineRead[MAXLINELENGTH];
	int isComment=1;
	int programSize;
	
	*programFile= fopen(program, "r");
	
	// Check if programFile exists
	if (*programFile==NULL)
		return PROGRAMDOESNOTEXIST;

	// Read the first number as the size of the program. Skip all comments.
	while (isComment==1) {
		if (fgets(lineRead, MAXLINELENGTH, *programFile) == NULL)
		      return PROGRAMNOTVALID;
		else
		      if (lineRead[0]!='/') { // Line IS NOT a comment
			    isComment=0;
			    if (OperatingSystem_lineBeginsWithANumber(lineRead))
				  programSize=atoi(strtok(lineRead," "));
			    else
				  return PROGRAMNOTVALID;
		      }
	}
	// Only sizes above 0 are allowed
	if (programSize<=0)
	      return PROGRAMNOTVALID;
	else if(programSize >= MAINMEMORYSECTIONSIZE)
		  return TOOBIGPROCESS;
	else
	      return programSize;
}


// Returns the priority of the program, stored in the program file
int OperatingSystem_ObtainPriority(FILE *programFile) {

	char lineRead[MAXLINELENGTH];
	int isComment=1;
	int processPriority;
	
	// Read the second number as the priority of the program. Skip all comments.
	while (isComment==1) {
		if (fgets(lineRead, MAXLINELENGTH, programFile) == NULL)
		      return PROGRAMNOTVALID;
		else
		      if (lineRead[0]!='/') { // Line IS NOT a comment
			    isComment=0;
			    if (OperatingSystem_lineBeginsWithANumber(lineRead))
				  processPriority=atoi(strtok(lineRead," "));
			    else
				  return PROGRAMNOTVALID;
		      }
	}
	return processPriority;
}


// Function that processes the contents of the file named by the first argument
// in order to load it in main memory from the address given as the second
// argument
// IT IS NOT NECESSARY TO COMPLETELY UNDERSTAND THIS FUNCTION

int OperatingSystem_LoadProgram(FILE *programFile, int initialAddress, int size) {

	char lineRead[MAXLINELENGTH];
	char *token0, *token1, *token2;

	registerMAR_CPU=initialAddress;
	int finalAddress = initialAddress + size;
	while (fgets(lineRead, MAXLINELENGTH, programFile) != NULL) {
		// REMARK: if lineRead is greater than MAXLINELENGTH in number of characters, the program
		// loading does not work
		registerMBR_CPU.operationCode=' ';registerMBR_CPU.operand1=registerMBR_CPU.operand2=0;
		token0=strtok(lineRead," ");
		if ((token0!=NULL) && (token0[0]!='/')) {
		      // I have an instruction with, at least, an operation code
		      registerMBR_CPU.operationCode=tolower(token0[0]);
		      token1=strtok(NULL," ");
		      if ((token1!=NULL) && (token1[0]!='/')) {
			    // I have an instruction with, at least, an operand
			    registerMBR_CPU.operand1=atoi(token1);
			    token2=strtok(NULL," ");
			    if ((token2!=NULL) && (token2[0]!='/')) {
				  // The read line is similar to 'sum 2 3 //coment'
				  // I have an instruction with two operands
				  registerMBR_CPU.operand2=atoi(token2);
			    }
		      }
		      
		      // More instructions than size...
		      if (registerMAR_CPU == finalAddress)
				  return TOOBIGPROCESS;

		      // Send data to main memory using the system buses
		      Buses_write_DataBus_From_To(registerMBR_CPU, &registerMBR_MainMemory);
		      Buses_write_AddressBus_From_To(registerMAR_CPU, &registerMAR_MainMemory);
		      // Tell the main memory controller to write
		      MainMemory_writeMemory();
		      registerMAR_CPU++;
		}
	}
	
	return SUCCESS;
}	


int OperatingSystem_lineBeginsWithANumber(char * line) {
	int i;
	
	for (i=0; i<strlen(line) && line[i]==' '; i++); // Don't consider blank spaces
	// If is there a digit number, return true
	if (i<strlen(line) && isdigit(line[i]))
		// It is a positive number
		return 1;
	else
		return 0;
}
	  
