using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;

namespace TPP.Practicas.Funcional.Practica2 {

    /// <summary>
    /// Ejemplo de memorización
    /// </summary>
    class Program {

        static void Main(string[] args) {
            const int terminoFibonacci = 40;
            int resultado;

            var crono = new Stopwatch();
            crono.Start();
            resultado = FibonacciNoMemorizado.Fibonacci(terminoFibonacci);
            crono.Stop();
            long ticksNoMemorizadoPrimeraLlamada = crono.ElapsedTicks;
            Console.WriteLine("Versión no memorizada, primera llamada: {0:N} ticks. Resultado: {1}.", ticksNoMemorizadoPrimeraLlamada, resultado);

            crono.Restart();
            resultado = FibonacciNoMemorizado.Fibonacci(terminoFibonacci);
            crono.Stop();
            long ticksNoMemorizadoSegundaLlamada = crono.ElapsedTicks;
            Console.WriteLine("Versión no memorizada, segunda llamada: {0:N} ticks. Resultado: {1}.", ticksNoMemorizadoSegundaLlamada, resultado);

            crono.Restart();
            resultado = FibonacciMemorizacion.Fibonacci(terminoFibonacci);
            crono.Stop();
            long ticksMemorizadoPrimeraLlamada = crono.ElapsedTicks;
            Console.WriteLine("Versión memorizada, primera llamada: {0:N} ticks. Resultado: {1}.", ticksMemorizadoPrimeraLlamada, resultado);

            crono.Restart();
            resultado = FibonacciMemorizacion.Fibonacci(terminoFibonacci);
            crono.Stop();
            long ticksMemorizadoSegundaLlamada = crono.ElapsedTicks;
            Console.WriteLine("Versión memorizada, segunda llamada: {0:N} ticks. Resultado: {1}.", ticksMemorizadoSegundaLlamada, resultado);
        }


    }
}
