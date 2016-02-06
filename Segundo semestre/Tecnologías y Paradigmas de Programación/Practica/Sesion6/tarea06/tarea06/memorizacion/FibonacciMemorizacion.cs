using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Practicas.Funcional.Practica2 {

    /// <summary>
    /// Clase que demuestra la optimización de memorización
    /// </summary>
    static class FibonacciMemorizacion {

        /// <summary>
        /// Valores de la invocación memorizados
        /// </summary>
        private static IDictionary<int, int> valores = new Dictionary<int, int>();

        /// <summary>
        /// Función Fibonacci recursiva memorizada
        /// </summary>
        internal static int Fibonacci(int n) {
            if (valores.Keys.Contains(n))
                // * Si ya se calculó, devolvemos el valor cacheado
                return valores[n];
            // * En caso contrario, lo guardamos antes de devolverlo
            int valor =  n <= 2 ? 1 : Fibonacci(n - 2) + Fibonacci(n - 1);
            valores.Add(n, valor);
            return valor;
        }


    }
}
