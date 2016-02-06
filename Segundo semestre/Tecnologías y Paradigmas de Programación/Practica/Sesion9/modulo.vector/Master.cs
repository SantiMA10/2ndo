using System;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica1 {

    public class Master {

        private short[] vector;

        private int numeroHilos;

        public Master(short[] vector, int numeroHilos) {
            if (numeroHilos < 1 || numeroHilos > vector.Length)
                throw new ArgumentException("El número de hilos ha de ser menor o igual que los elementos del vector.");
            this.vector = vector;
            this.numeroHilos = numeroHilos;
        }

        public double CalcularModulo() {
            Worker[] workers = new Worker[this.numeroHilos];
            int elementosPorHilo = this.vector.Length/numeroHilos;
            for (int i = 0; i < this.numeroHilos; i++)
            {
                workers[i] = new Worker(ref this.vector,
                    i * elementosPorHilo,
                    (i < this.numeroHilos - 1) ? ((i + 1) * elementosPorHilo - 1) : (this.vector.Length - 1) /**último**/);
            }

            Thread[] hilos = new Thread[workers.Length];
            for(int i = 0; i < workers.Length; i++) 
            {
                hilos[i] = new Thread(workers[i].Calcular); 
                hilos[i].Name = "Worker Vector Módulo " + (i + 1); 
                hilos[i].Priority = ThreadPriority.BelowNormal; 
                hilos[i].Start();  
            }

            foreach (Thread hilo in hilos)
            {
                hilo.Join();
            }
            
            long resultado = 0;
            foreach (Worker worker in workers)
                resultado += worker.Resultado;
            return Math.Sqrt(resultado);
        }

    }

}
