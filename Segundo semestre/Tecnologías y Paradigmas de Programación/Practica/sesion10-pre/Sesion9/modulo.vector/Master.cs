using System;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica1 {

    public class Master {

        private char[] ADN;
        private char[] gen;

        private int numeroHilos;

        public Master(char[] ADN, char[] gen, int numeroHilos) {
            if (numeroHilos < 1 || numeroHilos > ADN.Length)
                throw new ArgumentException("El número de hilos ha de ser menor o igual que los elementos del vector.");
            this.ADN = ADN;
            this.gen = gen;
            this.numeroHilos = numeroHilos;
        }

        public int CalcularNumeroGenes() {
            Worker[] workers = new Worker[this.numeroHilos];
            int elementosPorHilo = this.ADN.Length/numeroHilos;
            for (int i = 0; i < this.numeroHilos; i++)
            {
                workers[i] = new Worker(ref this.ADN, ref this.gen,
                    i * elementosPorHilo,
                    (i < this.numeroHilos - 1) ? ((i + 1) * elementosPorHilo - 1) : (this.ADN.Length - 1) /**último**/);
            }

            Thread[] hilos = new Thread[workers.Length];
            for(int i = 0; i < workers.Length; i++) 
            {
                hilos[i] = new Thread(workers[i].Calcular); 
                hilos[i].Name = "Worker Gen en ADN " + (i + 1); 
                hilos[i].Priority = ThreadPriority.BelowNormal; 
                hilos[i].Start();  
            }

            foreach (Thread hilo in hilos)
            {
                hilo.Join();
            }
            
            int resultado = 0;
            foreach (Worker worker in workers)
                resultado += worker.Resultado;
            return resultado;
        }

    }

}
