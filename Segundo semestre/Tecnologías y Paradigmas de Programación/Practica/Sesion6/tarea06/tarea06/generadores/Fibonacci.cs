using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Practicas.Funcional.Practica2 {

    static class Fibonacci {

        static internal IEnumerable<int> FibonacciInfinito() {
            int primero = 1, segundo = 1;
            while (true) {
                yield return primero; //yield ?= ceder, pero aun continuo
                int suma = primero + segundo;
                primero = segundo;
                segundo = suma;
            }
        }

    }

}
