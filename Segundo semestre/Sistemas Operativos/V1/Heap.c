#include "Heap.h"
#include <stdlib.h>

// Implementa la inserción en un montículo binario de un PID. OJO que no incrementa el número de elementos
// Los parámetros son:
// info: PID a insertar
// heap: la cola que corresponda, puede ser la de usuario, de sistema o de dormidos
// queueType: QUEUE_PRIORITY, QUEUE_WAKEUP, QUEUE_ARRIVAL, ...
// numElem: El número de elementos actuales de la cola
// limit: el tamaño máximo de la cola
// devuelve 0 si todo ha ido bien, y sería a la vuelta cuando se debe incrementar el número de elementos si procede
int Heap_add(int info, int heap[], int queueType, int numElem, int limit) {
	if (numElem >= limit || info<0)
		return -1;
	heap[numElem]=info;
	Heap_cribaAscendente(numElem, heap, queueType);
	return 0;
}

// Implementa el sacar el elemento de mayor prioridad. OJO que no decrementa el número de elementos
// Los parámetros son:
// heap: la cola que corresponda, puede ser la de usuario, de sistema, de dormidos, etc.
// queueType: QUEUE_PRIORITY, QUEUE_WAKEUP, QUEUE_ARRIVAL, ...
// numElem: El número de elementos actuales de la cola
// Devuelve el PID con más prioridad de la cola, y sería a la vuelta cuando se debe decrementar el numero de elementos si procede
int Heap_poll(int heap[], int queueType, int numElem) {
	int info = heap[0];
	if (numElem==0)
		return -1; // no hay elementos en la cola de prioridad
	else {
		heap[0]=heap[numElem-1];
		Heap_cribaDescendente(0, heap, queueType, numElem);
	}
	return info;		
}

// Auxiliar para implementar colas de prioridad como montículos binarios
void Heap_cribaAscendente(int p, int heap[], int queueType) {
	if (p > 0)  {// si no he llegado a la cima
		int padre = abs(p - 1) / 2; // división entera
		if (Heap_compare(heap[p],heap[padre],queueType)>0) { // Es menor que el padre...
			int aux = heap[padre]; 
			heap[padre] = heap[p];
			heap[p] = aux;
			Heap_cribaAscendente(padre, heap, queueType);
		} // si no es menor no hay que intecambiar
	}// si se ha llegado a la cima fin de la criba
}

// Auxiliar para implementar colas de prioridad como montículos binarios
void Heap_cribaDescendente(int p, int heap[], int queueType, int numElem) {
	int izq = 2*p+1;
	int der = 2*p+2;
	int aux = heap[p];
	
	if (der < numElem) //tiene 2 hijos 
		if ((Heap_compare(heap[izq],heap[der], queueType)>0) && (Heap_compare(heap[p],heap[izq],queueType)<0)){ // Intercambiar por el izquierdo si es mayor/menor
			heap[p] = heap[izq];
			heap[izq] = aux;
			Heap_cribaDescendente(izq, heap, queueType, numElem);
		} else { // criba por la derecha
			if (Heap_compare(heap[p],heap[der], queueType)<0) { //intercambiar por el derecho si es mayor
				heap[p] = heap[der];
				heap[der] = aux;
				Heap_cribaDescendente(der, heap, queueType, numElem);
			}
		}
	else if (izq<numElem) { // sólo tiene hijo izquierdo
		if (Heap_compare(heap[p],heap[izq], queueType)<0){ // Intercambiar por el izquierdo si es mayor
			heap[p] = heap[izq];
			heap[izq] = aux;
			Heap_cribaDescendente(izq, heap, queueType, numElem);
		} // Si es menor que el único hijo izquierdo no intercambia
	} // si es nodo hoja fin de la criba
}

// Auxiliar para comparar generico
int Heap_compare(int uno, int dos, int queueType) {
  if (queueType==QUEUE_PRIORITY)
    return Heap_compare_priority(uno, dos);
  else if (queueType==QUEUE_WAKEUP) 
    return Heap_compare_wakeup(uno, dos);
  else // QUEUE_ARRIVAL si hubiese más tipos de comparaciones, meter maś opciones
	return Heap_compare_arrival(uno, dos);
  
}

// Auxiliar para comparar prioridades
int Heap_compare_priority(int uno, int dos) {
  return processTable[dos].priority-processTable[uno].priority;
}

// Auxiliar para comparar tiempo de WakeUp
int Heap_compare_wakeup(int uno, int dos) {
  return 0;
//   return processTable[dos].whenToWakeUp - processTable[uno].whenToWakeUp;
}

// Auxiliar para comparar tiempo de llegada
int Heap_compare_arrival(int uno, int dos) {
  return 0;
//   return userProgramsList[dos]->arrivalTime - userProgramsList[uno]->arrivalTime;
}



