using System;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica2 {

    internal class Sumatorio {
        protected long valor;
        private int numeroHilos;

        internal Sumatorio(long valor, int numeroHilos) {
            this.valor = valor;
            this.numeroHilos = numeroHilos;
        }

        internal long Valor {
            get { return this.valor; }
        }

        protected virtual void DecrementarValor() {
            valor = valor - 1;
        }

        internal void Calcular() {
            Object thisLock = new Object();
            int iteraciones = (int)valor / numeroHilos;
            Thread[] hilos = new Thread[numeroHilos];
            for (int i = 0; i < numeroHilos; i++)
                hilos[i] = new Thread(() => {
                    for (int j = 0; j < iteraciones; j++)
                        //this.DecrementarValor();
                        //Interlocked.Decrement(ref this.valor);
                        
                        lock(thisLock)
                        {
                          this.DecrementarValor();
                     
                        }
                });
            foreach (Thread hilo in hilos)
                hilo.Start();
            foreach (Thread hilo in hilos)
                hilo.Join();
        }

        private void Lock(long p)
        {
            throw new NotImplementedException();
        }
    }
}
