#include "OperatingSystem.h"
#include "MMU.h"
#include "Processor.h"
#include "Buses.h"
#include "Heap.h"
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

// Array that contains the identifiers of the READY processes
int readyToRunQueue[NUMBEROFQUEUES][PROCESSTABLEMAXSIZE];
int numberOfReadyToRunProcesses[NUMBEROFQUEUES];

// Variable containing the number of not terminated user processes
int numberOfNotTerminatedProcesses=0;

void OperatingSystem_PrintStateChange(int PID, int actualState, int newState){
  if(actualState != -1){
    ComputerSystem_DebugMessage(SYSPROC, "sGdsGssGss", "Process [", PID, "] moving from the [",statesNames[actualState], "] state to the [",statesNames[newState],"] state\n");
  }
  else{
    ComputerSystem_DebugMessage(SYSPROC, "sGdsGss", "New process [", PID, "] moving to the [",statesNames[newState], "] state\n");
  }
}

// Initial set of tasks of the OS
void OperatingSystem_Initialize() {
	
	int i, selectedProcess;
	
	// Process table initialization (all entries are free)
	for (i=0; i<PROCESSTABLEMAXSIZE;i++)
		processTable[i].busy=0;
	
	// Initialization of the interrupt vector table of the processor
	Processor_InitializeInterruptVectorTable();
	
	// Create all system daemon processes
	OperatingSystem_CreateDaemons();
	
	// Create all user processes from the information given in the command line
	OperatingSystem_LongTermScheduler();
	
	// At least, one user process has been created
	// Select the first process that is going to use the processor
	selectedProcess = OperatingSystem_ShortTermScheduler();
	
	if(selectedProcess == -1){
	  processTable[sipID].copyOfPCRegister=processTable[sipID].processSize-1;
	  OperatingSystem_Dispatch(sipID);
	}
	else{
		// Assign the processor to the selected process
		OperatingSystem_Dispatch(selectedProcess);
	}

	//ComputerSystem_PowerOff();
	  
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
 	int i=0;
	int numberOfSuccessfullyCreatedProcesses=0;
	
	while (userProgramsList[i]!=NULL && i<USERPROGRAMSMAXNUMBER) {
		PID=OperatingSystem_CreateProcess(*userProgramsList[i], USERPROCESSQUEUE);
		if(PID == NOFREEENTRY){
			ComputerSystem_DebugMessage(ERROR,"RsRsRs","ERROR: There are not free entries in the process table for the program [",userProgramsList[i]->executableName,"]\n");
		}
		else if (PID == PROGRAMDOESNOTEXIST){
			ComputerSystem_DebugMessage(ERROR,"RsRsRs","ERROR: Program [",userProgramsList[i]->executableName,"] is not valid (it does not exist)\n");
		}
		else if (PID == PROGRAMNOTVALID){
			ComputerSystem_DebugMessage(ERROR,"RsRsRs","ERROR: Program [",userProgramsList[i]->executableName,"] is not valid (invalid size or priority)\n");
		}
		else if (PID == TOOBIGPROCESS){
			ComputerSystem_DebugMessage(ERROR,"RsRsRs","ERROR: Program [",userProgramsList[i]->executableName,"] is too big\n");
		}
		else{
		  numberOfSuccessfullyCreatedProcesses++;
		  ComputerSystem_DebugMessage(INIT,"GsGdGsGsGs","Process [",PID,"] created from program [",userProgramsList[i]->executableName,"]\n");
		}
		i++;
	}

	numberOfNotTerminatedProcesses+=numberOfSuccessfullyCreatedProcesses;

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
	
	// Obtain enough memory space
	loadingPhysicalAddress=OperatingSystem_ObtainMainMemory(processSize, PID);
	
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

 	if (processSize>MAINMEMORYSECTIONSIZE)
		return TOOBIGPROCESS;
	
 	return PID*MAINMEMORYSECTIONSIZE;
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
	  OperatingSystem_PrintReadyToRunQueue();
	} 
}

void OperatingSystem_PrintReadyToRunQueue(){
	
	int i;
	//int[] daemonQueue = readyToRunQueue[DAEMONSQUEUE];
	//int[] userQueue = readyToRunQueue[USERPROCESSQUEUE];
	int daemonQueueSize = numberOfReadyToRunProcesses[DAEMONSQUEUE];
	int userQueueSize = numberOfReadyToRunProcesses[USERPROCESSQUEUE];
	if(daemonQueueSize != 0 || userQueueSize != 0){
	   ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", "Ready-to-run processes queue:\n");
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
	if(daemonQueueSize != 0 || userQueueSize != 0){
	   ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "s", "\n\tDAEMONS: ");

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

	selectedProcess=OperatingSystem_ExtractFromReadyToRun();
	
	return selectedProcess;
}


// Return PID of more priority process in the READY queue
int OperatingSystem_ExtractFromReadyToRun() {
  
	int selectedProcess=NOPROCESS;
	if(numberOfReadyToRunProcesses[USERPROCESSQUEUE] > 0){
	  Processor_DeactivatePSW_Bit(EXECUTION_MODE_BIT);
	  selectedProcess=Heap_poll(readyToRunQueue[USERPROCESSQUEUE],QUEUE_PRIORITY ,numberOfReadyToRunProcesses[USERPROCESSQUEUE]);
	  if (selectedProcess>=0) 
	    numberOfReadyToRunProcesses[USERPROCESSQUEUE]--;
	}
	//else{
	//  selectedProcess=Heap_poll(readyToRunQueue[DAEMONSQUEUE],QUEUE_PRIORITY ,numberOfReadyToRunProcesses[DAEMONSQUEUE]);
	//  if (selectedProcess>=0) 
	//    numberOfReadyToRunProcesses[DAEMONSQUEUE]--;
	//}
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
	if(processTable[PID].mode == 1){
		Processor_ActivatePSW_Bit(EXECUTION_MODE_BIT);
	}
	else{
		Processor_DeactivatePSW_Bit(EXECUTION_MODE_BIT);
	}
	registerAccumulator_CPU = processTable[PID].accumulator;
	// Same thing for the MMU registers
	registerBase_MMU=processTable[PID].initialPhysicalAddress;
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
  
  ComputerSystem_DebugMessage(SYSPROC,"RsRdRs","Process [",executingProcessID,"] has generated an exception and is terminating\n");
	
  OperatingSystem_TerminateProcess();

}


// All tasks regarding the removal of the process
void OperatingSystem_TerminateProcess() {
  
	int selectedProcess;
  	
  	OperatingSystem_PrintStateChange(executingProcessID, processTable[executingProcessID].state, EXIT);
	processTable[executingProcessID].state=EXIT;

	// One more process that has terminated
	numberOfNotTerminatedProcesses--;
	
	if (numberOfNotTerminatedProcesses>0) {
		// Select the next process to execute
		selectedProcess=OperatingSystem_ShortTermScheduler();
		// Assign the processor to that process
		OperatingSystem_Dispatch(selectedProcess);
	}
	else  { // Simulation must finish (done by modifying the PC of the System Idle Process so it points to its 'halt' instruction,
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
		ComputerSystem_DebugMessage(SYSPROC,"RsRdRs","Process [",executingProcessID,"] has requested to terminate\n");
		OperatingSystem_TerminateProcess();
		break;
	  case SYSCALL_YIELD:
		//Acedemos a la cola del proceso,mediante el psw y miramos si hay algun proceso con su misma prioridad 
		//si la hay, cambiamos de proceso, sino nada.	
		actualPID = executingProcessID;
		nextPID = OperatingSystem_ShortTermScheduler();
		if(processTable[actualPID].priority == processTable[nextPID].priority && processTable[nextPID].queueID == USERPROCESSQUEUE){
		  ComputerSystem_DebugMessage(SHORTTERMSCHEDULE, "sGdsGds", "Process [",actualPID,"] transfers control of the processor to process [",nextPID,"]\n");
		  OperatingSystem_PreemptRunningProcess();
		  OperatingSystem_Dispatch(nextPID);
		  //OperatingSystem_MoveToTheREADYState(actualPID, USERPROCESSQUEUE);  
		}
		else{
		  OperatingSystem_MoveToTheREADYState(nextPID, USERPROCESSQUEUE);  
		}
		//OperatingSystem_PrintReadyToRunQueue();
		//ComputerSystem_PowerOff();
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
	  
