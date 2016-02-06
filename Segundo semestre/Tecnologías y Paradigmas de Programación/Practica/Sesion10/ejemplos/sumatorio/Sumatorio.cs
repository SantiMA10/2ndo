using System;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica2 {

    internal class Sumatorio {
        protected long valor;
        private int numeroHilos;
        private object semaforo = new object();

        internal Sumatorio(long valor, int numeroHilos) {
            this.valor = valor;
            this.numeroHilos = numeroHilos;
        }

        internal long Valor {
            get { return this.valor; }
        }

        protected virtual void DecrementarValorLook() {
            lock (semaforo)
            {
                valor = valor - 1;
            }
        }

        protected virtual void DecrementarValorInterlook()
        {
            Interlocked.Decrement(ref valor);
        }

        internal void CalcularLook()
        {
            int iteraciones = (int)valor / numeroHilos;
            Thread[] hilos = new Thread[numeroHilos];
            for (int i = 0; i < numeroHilos; i++)
                hilos[i] = new Thread(() =>
                {
                    for (int j = 0; j < iteraciones; j++)
                        this.DecrementarValorLook();
                });
            foreach (Thread hilo in hilos)
                hilo.Start();
            foreach (Thread hilo in hilos)
                hilo.Join();
        }

        internal void CalcularInterlook()
        {
            int iteraciones = (int)valor / numeroHilos;
            Thread[] hilos = new Thread[numeroHilos];
            for (int i = 0; i < numeroHilos; i++)
                hilos[i] = new Thread(() =>
                {
                    for (int j = 0; j < iteraciones; j++)
                        this.DecrementarValorInterlook();
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
