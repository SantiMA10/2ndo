using System;
using System.Diagnostics;

namespace TPP.Practicas.Concurrente.Practica2 {

    class Program {

        static void Main() {
            const long valor = 100000000;
            const int numeroHilos = 10000;

            Sumatorio sumatorioLook = new Sumatorio(valor, numeroHilos);
            Stopwatch cronoLook = new Stopwatch();
            cronoLook.Start();
            sumatorioLook.CalcularLook();
            cronoLook.Stop();
            Console.WriteLine("Valor tras el decremento Look: {0}. Milisegundos: {1}.",
                sumatorioLook.Valor, cronoLook.ElapsedMilliseconds);

            Sumatorio sumatorioInterlook = new Sumatorio(valor, numeroHilos);
            Stopwatch cronoInterlook = new Stopwatch();
            cronoInterlook.Start();
            sumatorioInterlook.CalcularInterlook();
            cronoInterlook.Stop();
            Console.WriteLine("Valor tras el decremento Interlooked: {0}. Milisegundos: {1}.",
                sumatorioInterlook.Valor, cronoInterlook.ElapsedMilliseconds);
        }

    }
}
