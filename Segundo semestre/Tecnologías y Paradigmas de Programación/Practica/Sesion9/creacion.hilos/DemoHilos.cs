using System;
using System.Threading;
using System.Text;

namespace TPP.Practicas.Concurrente.Practica1 {

    class DemoHilo {

        static void Main(string[] args) {
            System.Console.WriteLine("Inicio del hilo principal");
            if (args.Length > 0) {
                int numeroHilos = Convert.ToInt32(args[0]);
                Hilo[] objeto = new Hilo[numeroHilos];
                for (int i = 0; i < numeroHilos; i++)
                {
                    objeto[i] = new Hilo();
                    new Thread(objeto[i].EjecuciónHilo).Start();
                }
            }

           //new Hilo().EjecuciónHilo(); //Es para que el hilo principal tambien trabaje
           System.Console.WriteLine("Final del hilo principal");
        }

    }
}
