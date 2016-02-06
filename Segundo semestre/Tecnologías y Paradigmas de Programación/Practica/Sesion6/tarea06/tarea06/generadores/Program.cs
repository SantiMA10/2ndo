using System;

namespace TPP.Practicas.Funcional.Practica2 {

    class Program {
        static void Main() {
            int i = 0;
            foreach (int valor in Fibonacci.FibonacciInfinito()) {
                Console.Write(valor + " ");
                if (++i == 10)
                    break;
            }
            Console.WriteLine();

            i = 0;
            foreach (int valor in Fibonacci.FibonacciInfinito()) {
                Console.Write(valor + " ");
                if (++i == 10)
                    break;
            }
            Console.WriteLine();
        }
    }
}
