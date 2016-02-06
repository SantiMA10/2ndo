#ifndef HEAP_H
#define HEAP_H

#define QUEUE_WAKEUP 0
#define QUEUE_PRIORITY 1
#define QUEUE_ARRIVAL 2

// Implements the insertion operation in a binary heap. IMPORTANT: does not increment the number of elements
// Parameters are:
//    info: PID to be inserted
//    heap: the corresponding queue: user, system or sleeping
//    queueType: if sleeping queue, QUEUE_WAKEUP; if ready queue, QUEUE_PRIORITY; if arrival, QUEUE_ARRIVAL
//    numElem: number of current elements inside the queue
//    limit: maximum capacity of the queue
// Returns: 0, if everything went ok (when returning, the number of elements must be incremented)
int Heap_add(int, int[], int , int, int);

// Implements the extraction operation (the element with the highest priority). IMPORTANT: does not decrement the number of elements
// Parameters are:
//    heap: the corresponding queue: user, system or sleeping
//    queueType: if sleeping queue, QUEUE_WAKEUP; if ready queue, QUEUE_PRIORITY; if arrival, QUEUE_ARRIVAL
//    numElem: number of current elements inside the queue
// Returns: the PID with the highest priority in the queue, if everything went ok (when returning, the number of elements must be decremented)
int Heap_poll(int[], int, int);

// Auxiliary function to make comparisons
// Parameters are:
// 	uno: PID one
// 	dos: PID two
// 	queueType: if sleeping queue, QUEUE_WAKEUP; if ready queue, QUEUE_PRIORITY; if arrival, QUEUE_ARRIVAL
int Heap_compare(int, int, int); //

#endif