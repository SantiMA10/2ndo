using System;
using System.Diagnostics;

namespace TPP.Practicas.Concurrente.Practica2 {

    class Program {

        static void Main() {
            const long valor = 100000000;
            const int numeroHilos = 10000;

            Sumatorio sumatorio = new Sumatorio(valor, numeroHilos);
            Stopwatch crono = new Stopwatch();
            crono.Start();
            sumatorio.Calcular();
            crono.Stop();
            Console.WriteLine("Valor tras el decremento directo: {0}. Milisegundos: {1}.",
                sumatorio.Valor, crono.ElapsedMilliseconds);
        }

    }
}
